/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import com.tsystem.tplatform.entityes.CurrentSession;
import com.tsystem.tplatform.security.TPUser;
import entitys.FileTemplate;
import entitys.Register;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import models.RegisterFacade;
import models.TPUserFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "registerViewMasterController")
@SessionScoped
public class registerSessionMasterController implements Serializable {

    @PersistenceContext(unitName = "TestDbPU")
    private EntityManager em;

    @EJB
    TPUserFacade tpuserFacade;

    @EJB
    RegisterFacade registerFacade;
    
    private LocalDate currentDate;

    private LocalDate startDate = LocalDate.now();

    private LocalDate endDate;

    private Boolean dateInterval = false;

    private TPUser currentUuser;

    private Register currentRregister;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String rID = paramMap.get("rID");

        currentUuser = findUserByLogin("bishop");
        //
        if (currentUuser == null) {
            TPUser newUser = new TPUser();
            newUser.setTitle("Герлиани Михаил Зауровисч");
            newUser.setLogin("bishop");
            newUser.setLastName("Герлиани");
            newUser.setFirstName("Михаил");
            newUser.setSurname("Зауровисч");
            newUser.seteMail("mzgerliani@gmail.com");
            tpuserFacade.create(newUser);
            currentUuser = newUser;
        }
        CurrentSession.setCurrentUser(currentUuser);
        //

    }

    /**
     * Creates a new instance of registerViewMasterController
     */
    public registerSessionMasterController() {
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(Boolean dateInterval) {
        this.dateInterval = dateInterval;
    }

    public TPUser getCurrentUuser() {
        return currentUuser;
    }

    public void setCurrentUuser(TPUser currentUuser) {
        this.currentUuser = currentUuser;
    }

    public Register getCurrentRregister() {
        return currentRregister;
    }

    public void setCurrentRregister(Register currentRregister) {
        this.currentRregister = currentRregister;
    }
  
    public List<LocalDate> getDateList(){
        List<LocalDate> datelit = new ArrayList<>();
        
        if(dateInterval && endDate != null){
            Period period = Period.between(startDate, endDate);
            int days = Math.abs(period.getDays())+1;
            for (int i = 0; i < days; i++) {
                datelit.add(startDate.plusDays(i));
            }
        } else {
            datelit.add(startDate);
        }
        return datelit;
    }

    private TPUser findUserByLogin(String login) {
        return em.createQuery("SELECT u FROM TPUser u WHERE u.login = :login", TPUser.class)
                .setParameter("login", login)
                .getResultList().stream().findFirst().orElse(null);
    }

    /**
     * !!!!!!!!!!! Временный метод удалить при появлении цельевого
     */
    public List<Register> getRegisterList() {
        return registerFacade.findAll();
    }

    public List<File> findFileByDocumentType(FileTemplate fileTemplate) {
        List<File> fileList = new ArrayList<>();
        for (String dirPath : fileTemplate.getDocumentType().getFullSourcePath(this.startDate)) {
            File dir = new File(dirPath);
            if (dir.canExecute() && dir.isDirectory()) {
                List<File> dirFiles = getFileDirectory(dir, fileTemplate.getDocumentType().getRecursive());
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
            if(file.isDirectory() && recursive){
                List<File> dirFiles = getFileDirectory(file, recursive);
                fileList.addAll(dirFiles);
            }
        }
        return fileList;
    }

}
