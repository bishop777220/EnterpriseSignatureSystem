/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converters;

import controllers.RegisterViewController;
import entitys.Register;
import jakarta.el.ValueExpression;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.UUID;

/**
 *
 * @author mrbis
 */
@FacesConverter("registerConverter")
public class RegisterConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ValueExpression vex =
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{registerViewController}", RegisterViewController.class);
        RegisterViewController registerViewController = (RegisterViewController)vex.getValue(context.getELContext());
        if(value.replace("&nbsp;", "") != ""){
            return registerViewController.getRegisterById(UUID.fromString(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((Register)value).getId());
        }
        else {
            return null;
        }
    }
    
}
