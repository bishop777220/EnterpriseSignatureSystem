/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.FileTemplate;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import models.FileTemplateFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "fileTemplateViewController")
@ViewScoped
public class FileTemplateViewController  implements Serializable{

    @EJB
    private FileTemplateFacade fileTemplateFacade;
    
    private FileTemplate fileTemplate;
    /**
     * Creates a new instance of FileTemplateViewController
     */
    public FileTemplateViewController() {
    }
    
    public void removeFileTemplate(FileTemplate FileTemplate){
        fileTemplateFacade.remove(FileTemplate);
    }
}
