/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "fileTemplate")
public class FileTemplate extends TPItem  {

    // Описние реестра
    @Column(name = "description", length = 4000)
    private String description;
    
    // Маска названия файла в формате REGEX
    @Column(name = "fileMask", length = 50)
    private String fileMask;
    
    @ManyToOne    
    @JoinColumn(name = "documentType", referencedColumnName = "id")
    private DocumentType documentType;
    
    // Указывает что фальл обязательный в наборе данного вида по умолчанию false
    @Column(name = "required")
    private Boolean required = false;
    
    // Указывает что фальл не подписыается по умолчанию false
    @Column(name = "notSignable")
    private Boolean notSignable = false;

    @OneToMany(mappedBy = "fileTemplate")
    public Set<FileToBeSigned> fileToBeSignedSet;
    
    public FileTemplate() {
        this.fileToBeSignedSet = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileMask() {
        return fileMask;
    }

    public void setFileMask(String fileMask) {
        this.fileMask = fileMask;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getNotSignable() {
        return notSignable;
    }

    public void setNotSignable(Boolean notSignable) {
        this.notSignable = notSignable;
    }
    
//    public List<File> findFileByDate(LocalDate date) {
//        List<File> fileList = new ArrayList<>();
//        for (String dirPath : this.getDocumentType().getFullSourcePath(date)) {
//            File dir = new File(dirPath);
//            if (dir.canExecute() && dir.isDirectory()) {
//                List<File> dirFiles = getFileDirectory(dir, this.getDocumentType().getRecursive());
//                fileList.addAll(dirFiles);
//            }
//        }
//        return fileList;
//    }
    
//    private List<File> getFileDirectory(File dir, Boolean recursive) {
//        List<File> fileList = new ArrayList<>();
//        File[] files = dir.listFiles();
//        for (File file : files) {
//            if (file.isFile()) {
//                fileList.add(file);
//            }
//            if(file.isDirectory() && recursive){
//                List<File> dirFiles = getFileDirectory(file, recursive);
//                fileList.addAll(dirFiles);
//            }
//        }
//        return fileList;
//    }

    public Set<FileToBeSigned> getFileToBeSignedSet() {
        return fileToBeSignedSet;
    }

    public void setFileToBeSignedSet(Set<FileToBeSigned> fileToBeSignedSet) {
        this.fileToBeSignedSet = fileToBeSignedSet;
    }
    
}
