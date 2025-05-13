/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Destination;
import entitys.DocumentType;
import entitys.FileTemplate;
import entitys.Register;
import entitys.Source;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import models.DestinationFacade;
import models.DocumentTypeFacade;
import models.FileTemplateFacade;
import models.RegisterFacade;
import models.SourceFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "reestrViewBean")
@ViewScoped
public class RegisterViewBean implements Serializable {

    @EJB
    RegisterFacade registerFacade;

    @EJB
    SourceFacade sourceFacade;

    @EJB
    DestinationFacade destinationFacade;
    
    @EJB
    DocumentTypeFacade documentTypeFacade;
    
    @EJB
    FileTemplateFacade  fileTemplateFacade;

    private Register register;

    private Source source;

    private Destination destination;
    
    private DocumentType documentType;
    
    private FileTemplate fileTemplate;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String rID = paramMap.get("rID");
        if (rID != null) {
            this.register = registerFacade.find(UUID.fromString(rID));

        } else {
            this.register = new Register();
        }

        String sID = paramMap.get("sID");
        if (sID != null) {
            this.source = sourceFacade.find(UUID.fromString(sID));
            this.register = this.source.getRegister();
        } else {
            this.source = new Source();
            this.source.setRegister(this.register);
        }
        
        String destID = paramMap.get("destID");
        if (destID != null) {
            this.destination = destinationFacade.find(UUID.fromString(destID));
            this.register = this.destination.getRegister();
        } else {
            this.destination = new Destination();
            this.destination.setRegister(this.register);
        }
        
        String dtID = paramMap.get("dtID");
        if (dtID != null) {
            this.documentType = documentTypeFacade.find(UUID.fromString(dtID));
            this.register = this.documentType.getRegister();
        } else {
            this.documentType = new DocumentType();
            this.documentType.setRegister(this.register);
        }
        
        String fID = paramMap.get("fID");
        if (fID != null) {
            this.fileTemplate = fileTemplateFacade.find(UUID.fromString(fID));
            this.documentType = this.fileTemplate.getDocumentType();
        } else {
            this.fileTemplate = new FileTemplate();
            this.fileTemplate.setDocumentType(this.documentType);
        }
    }

    /**
     * Создает новый экземпляр для ReestrViewBean
     */
    public RegisterViewBean() {
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public FileTemplate getFileTemplate() {
        return fileTemplate;
    }

    public void setFileTemplate(FileTemplate fileTemplate) {
        this.fileTemplate = fileTemplate;
    }

    /**
     * Возвращает список реестров
     *
     * @return List
     */
    public List<Register> findRegister() {
        return registerFacade.findAll();
    }

    /**
     * Возвращает список директорий источник
     *
     * @return List
     */
    public List<Source> findSource() {
        return sourceFacade.findAll();
    }

    /**
     * Возвращает список директорий назначения
     *
     * @return List
     */
    public List<Destination> findDestination() {
        return destinationFacade.findAll();
    }

    /**
     * Создает реестр
     *
     * @param path (String)
     * @return (String)
     */
    public String createRegister(String path) {
        registerFacade.create(this.register);
        return path + "?faces-redirect=true";
    }

    /**
     * Изменяет реестр
     *
     * @param path (String)
     * @return (String)
     */
    public String editRegister(String path) {
        registerFacade.edit(this.register);
        return path + "?faces-redirect=true&rID=" + this.register.getId();
    }
    
    /**
     * Удалить реестр
     *
     * @param path (String)
     * @return (String)
     */
    public String removeRegister(Register register, String path) {
        registerFacade.remove(register);
        return path + "?faces-redirect=true";
    }

    /**
     * Создает директорию источника
     *
     * @param path (String)
     * @return (String)
     */
    public String createSource(String path) {
        sourceFacade.create(this.source);
        return path + "?faces-redirect=true&rID=" + this.source.getRegister().getId();
    }

    /**
     * Изменяет директорию источника
     *
     * @param path (String)
     * @return (String)
     */
    public String editSource(String path) {
        sourceFacade.edit(this.source);
        return path + "?faces-redirect=true&rID=" + this.source.getRegister().getId();
    }
    
    /**
     * Удалить директорию источника
     *
     * @param path (String)
     * @return (String)
     */
    public String removeSource(Source source,String path) {
        sourceFacade.remove(source);
        return path + "&faces-redirect=true&rID=" + source.getRegister().getId();
    }

    /**
     * Создает директорию назначения
     *
     * @param path (String)
     * @return (String)
     */
    public String createDestination(String path) {
        destinationFacade.create(this.destination);
        return path + "?faces-redirect=true&rID=" + this.destination.getRegister().getId();
    }

    /**
     * Изменяет директорию назначения
     *
     * @param path (String)
     * @return (String)
     */
    public String editDestination(String path) {
        destinationFacade.edit(this.destination);
        return path + "?faces-redirect=true&rID=" + this.destination.getRegister().getId();
    }
    
    /**
     * Удалить директорию назначения
     *
     * @param path (String)
     * @return (String)
     */
    public String removeDestination(Destination destination,String path) {
        destinationFacade.remove(destination);
        return path + "?faces-redirect=true&rID=" + destination.getRegister().getId();
    }
    
    /**
     * Создает вида документа
     *
     * @param path (String)
     * @return (String)
     */
    public String createDocumentType(String path) {
        documentTypeFacade.create(this.documentType);
        return path + "?faces-redirect=true&rID=" + this.documentType.getRegister().getId();
    }

    /**
     * Изменяет вида документа
     *
     * @param path (String)
     * @return (String)
     */
    public String editDocumentType(String path) {
        documentTypeFacade.edit(this.documentType);
        return path + "?faces-redirect=true&rID=" + this.documentType.getRegister().getId();
    }
    
    /**
     * Удалить вида документа
     *
     * @param path (String)
     * @return (String)
     */
    public String removeDocumentType(DocumentType documentType,String path) {
        documentTypeFacade.remove(documentType);
        return path + "?faces-redirect=true&rID=" + destination.getRegister().getId();
    }

    /**
     * Создает шаблона файла
     *
     * @param path (String)
     * @return (String)
     */
    public String createFileTemplate(String path) {
        fileTemplateFacade.create(this.fileTemplate);
        return path + "?faces-redirect=true&rID=" + this.fileTemplate.getDocumentType().getRegister().getId();
    }

    /**
     * Изменяет шаблона файла
     *
     * @param path (String)
     * @return (String)
     */
    public String editFileTemplate(String path) {
        fileTemplateFacade.edit(this.fileTemplate);
        return path + "?faces-redirect=true&rID=" + this.fileTemplate.getDocumentType().getRegister().getId();
    }
    
    /**
     * Удалить шаблона файла
     *
     * @param path (String)
     * @return (String)
     */
    public String removedocumentType(FileTemplate fileTemplate,String path) {
        fileTemplateFacade.remove(fileTemplate);
        return path + "?faces-redirect=true&rID=" + this.register.getId();
    }
    
}
