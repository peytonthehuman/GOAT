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

    
            
    private final SimpleStringProperty title;
    private SimpleStringProperty media_Id;
    private SimpleStringProperty date_Released;
    private SimpleStringProperty numOfGeneres;
    private SimpleStringProperty genre;
    
    
    /**
     * Constructor with data
     */
    public Media(String t, String m,String d,String n)
    {
        super();
        this.title = new SimpleStringProperty(t);
        this.date_Released = new SimpleStringProperty(d);
        this.media_Id = new SimpleStringProperty(m);
        this.numOfGeneres = new SimpleStringProperty(n);
        this.genre = new SimpleStringProperty("GE");
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * @return the media_Id
     */
    public String getMedia_Id() {
        return media_Id.get();
    }

    /**
     * @return the date_Released
     */
    public String getDate_Released() {
        return date_Released.get();
    }

    /**
     * @return the numOfGeneres
     */
    public String getNumOfGeneres() {
        return numOfGeneres.get();
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre.get();
    }
   
    
   
    

}

