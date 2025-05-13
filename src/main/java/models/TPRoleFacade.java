/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.tsystem.tplatform.security.TPRole;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author mrbis
 */
@Stateless
public class TPRoleFacade extends AbstractFacade<TPRole> {

    @PersistenceContext(unitName = "TestDbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TPRoleFacade() {
        super(TPRole.class);
    }
    
}
