/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Register;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.UUID;
import models.RegisterFacade;

/**
 *
 * @author mrbis
 */
@Named(value = "registerViewController")
@ViewScoped
public class RegisterViewController implements Serializable {

    @EJB
    RegisterFacade registerFacade;
    
    private Register register;
    /**
     * Creates a new instance of RegisterViewController
     */
    public RegisterViewController() {
    }

    public Register getRegisterById(UUID id) {
        return registerFacade.find(id);
    }
    
    
    
}
