package com.mycompany.goat_gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author peytonwhite
 */
public class Media {
            
    private String title;
    private String media_Id;
    private String date_Released;
    private String numOfGeneres;
    
    
    /**
     * Constructor with data
     */
    public Media(String t, String m,String d,String n)
    {
        this.title = t;
        this.date_Released = d;
        this.media_Id = m;
        this.numOfGeneres = n;
    }
   
    
   
    

}

