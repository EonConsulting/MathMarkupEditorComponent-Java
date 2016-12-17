/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.mathcomponent.controller;

import com.unisa.mathcomponent.entity.Fomulae;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mbaka Motale
 */
public class FomulaeConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        FomulaeController controller = (FomulaeController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "fomulae");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Fomulae) {
            Fomulae o = (Fomulae) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.unisa.mathcomponent.entity.Fomulae");
        }
    }
    
}
