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
            
    private SimpleStringProperty mediaTitle;
    private SimpleStringProperty mediaGenre;
    private SimpleStringProperty mediaDescription;
    private SimpleDoubleProperty mediaRating;
    private Image mediaPhoto;
    
    /**
     * Constructor with data
     */
    public Media(String title, String genre, double rating, String descr, Image photo){
        this.mediaTitle = new SimpleStringProperty(title);
        this.mediaGenre = new SimpleStringProperty(genre);
        this.mediaDescription = new SimpleStringProperty(descr);
        this.mediaRating = new SimpleDoubleProperty(rating);
            //image doesnt show up in examples
        //this.mediaPhoto = photo;
    }
    
    
    public String getTitle(){
        return mediaTitle.get();
    }
    public void setTitle(String title){
        this.mediaTitle = new SimpleStringProperty(title);
    }
    
    
    public String getGenre(){
        return mediaGenre.get();
    }
    public void setGenre(String genre){
        this.mediaGenre = new SimpleStringProperty(genre);
    }

    
    public String getDescription(){
        return mediaDescription.get();
    }
    public void setDescription(String descr){
        this.mediaDescription = new SimpleStringProperty(descr);
    }
    

    public Double getRating(){
        return mediaRating.get();
    }
    public void setRating(Double rating){
        this.mediaRating = new SimpleDoubleProperty(rating);
    }
    

}

