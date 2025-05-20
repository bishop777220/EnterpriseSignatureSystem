/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.DocumentType;
import entitys.FileTemplate;
import entitys.FileToBeSigned;
import entitys.Register;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.FileToBeSignedFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "fileLoadrBeansAsync")
@ViewScoped
public class FileLoadrBeansAsync implements Serializable {
    
    @PersistenceContext(unitName = "TestDbPU")
    private EntityManager em;

    @EJB
    FileToBeSignedFacade fileToBeSignedFacade;
    
    private List<LocalDate> dateList;

    private Register currentRregister;

    private List<DayDocumentTypes> dayDocumentTypesList;
    //private HashSet<DayDocumentTypes> dayDayDocumentTypeSet;

    /**
     * Creates a new instance of FileLoadrBeans
     */
    public FileLoadrBeansAsync() {
        this.dayDocumentTypesList = new ArrayList<>();
    }

    public List<DayDocumentTypes> getDayDocumentTypesList() {
        return dayDocumentTypesList;
    }

    public Register getCurrentRregister() {
        return currentRregister;
    }

    public void setCurrentRregister(Register currentRregister) {
        this.currentRregister = currentRregister;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    public void createDayDocumentTypesSet() {
        if (this.currentRregister != null && this.dateList != null) {
            for (LocalDate date : this.dateList) {
                String dayDirectoty = dateMaskToString(date);
                DayDocumentTypes dayDocumentTypes = new DayDocumentTypes();
                if(this.currentRregister.getIsDayTimeDocuments()){
                    dayDocumentTypes.setDate(date);
                } else {
                    dayDocumentTypes.setDate(null);
                }
                this.dayDocumentTypesList.add(dayDocumentTypes);
                
                for (DocumentType documentType : this.currentRregister.getDocumentTypeSet()) {
                    dayDocumentTypes.getFileTemplateList().addAll(documentType.fileTemplateList);
                    
                    
                    for (FileTemplate fileTemplate : documentType.fileTemplateList) {
                        List<File> fileList = findFileByDate(date, documentType);
                        for (File file : fileList) {
                            FileToBeSigned fileToBeSigned = getFileToBeSignedByFileName(file.getName());
                            if(fileToBeSigned == null){
                                fileToBeSigned = new FileToBeSigned();

                                fileToBeSigned.setFileTemplate(fileTemplate);

                                
                                fileToBeSigned.setDayTimeDirectory(dayDirectoty);
                                fileToBeSigned.setFileName(file.getName());
                                fileToBeSignedFacade.create(fileToBeSigned);
                                fileTemplate.getFileToBeSignedSet().add(fileToBeSigned);
                            }
                        }
                        
                    }

                }

            }
        }
    }

    private List<File> findFileByDate(LocalDate date, DocumentType documentType) {
        List<File> fileList = new ArrayList<>();
        for (String dirPath : documentType.getFullSourcePath(date)) {
            File dir = new File(dirPath);
            if (dir.canExecute() && dir.isDirectory()) {
                List<File> dirFiles = getFileDirectory(dir, documentType.getRecursive());
                fileList.addAll(dirFiles);
            }
        }
        return fileList;
    }

    private List<File> getFileDirectory(File dir, Boolean recursive) {
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            }
            if (file.isDirectory() && recursive) {
                List<File> dirFiles = getFileDirectory(file, recursive);
                fileList.addAll(dirFiles);
            }
        }
        return fileList;
    }

    private String dateMaskToString(LocalDate date) {
        if(this.currentRregister.getDateFolderMask() != null){
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern(this.currentRregister.getDateFolderMask());
            return date.format(sdf);
        } else {
            return null;
        }
    }

    private FileToBeSigned getFileToBeSignedByFileName(String fileName) {
        List<FileToBeSigned> fileToBeSignedList = em.createQuery("SELECT f FROM FileToBeSigned f WHERE f.fileName = :fileName")
                .setParameter("fileName", fileName)
                .getResultList();
        if(fileToBeSignedList.size() != 0){
            return fileToBeSignedList.get(0);
        } else {
            return null;
        }
    }
}
