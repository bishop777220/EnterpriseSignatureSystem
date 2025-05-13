/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entitys.DocumentType;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author mrbis
 */
@Stateless
public class DocumentTypeFacade extends AbstractFacade<DocumentType> {

    @PersistenceContext(unitName = "TestDbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentTypeFacade() {
        super(DocumentType.class);
    }
    
}
