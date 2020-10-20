/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import javafx.scene.image.Image;

/**
 *
 * @author Sean
 */
public class ImageController {
    
    private Image photo;
    
    public Image getImage(){
        return photo;
    }
    
    public void setImage(Image newPic){
        this.photo = newPic;
    }
    
}
