/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.mathcomponent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kelvinashu
 */
@ManagedBean(name = "imageExtractorController")
@SessionScoped
public class ImageExtractorController {

    private String imageValue;

    public String getImageValue() {
        return imageValue;
    }

    public void setImageValue(String imageValue) {
        this.imageValue = imageValue;
    }

    public String someMehtod() {
        System.out.println("base64 image data -> -> ->  " + imageValue);
        
        return "";
    }
}
