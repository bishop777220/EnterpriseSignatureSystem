/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.DocumentType;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import models.DocumentTypeFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "documentTypeViewController")
@ViewScoped
public class DocumentTypeViewController implements Serializable {

    @EJB
    private DocumentTypeFacade documentTypeFacade;
    
    private DocumentType documentType;
    /**
     * Creates a new instance of DocumentTypeViewController
     */
    public DocumentTypeViewController() {
    }
    
    public  void removeDocumentType(DocumentType documentType){
        documentTypeFacade.remove(documentType);
    }
    
}
