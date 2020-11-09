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
    //private SimpleStringProperty mediaPhotoID;
    private Image mediaPhoto;
    
    /**
     * Constructor with data
     */
    public Media(Image photo, String title, String genre, String descr, double rating){
        this.mediaTitle = new SimpleStringProperty(title);
        this.mediaGenre = new SimpleStringProperty(genre);
        this.mediaDescription = new SimpleStringProperty(descr);
        this.mediaRating = new SimpleDoubleProperty(rating);
        //this.mediaPhotoID = new SimpleStringProperty(photo);
            //image doesnt show up in examples
        this.mediaPhoto = photo;
    }
    //default constructor for getDummyMedia located in ProfileController
        //MAY BE DELETED ONCE PHOTOS SUCCESSFULLY PASSED THROUGH FUNCTIONS!!!!!!!!!!!!!!!!!!!
    public Media(String title, String genre, String descr, double rating){
        this.mediaTitle = new SimpleStringProperty(title);
        this.mediaGenre = new SimpleStringProperty(genre);
        this.mediaDescription = new SimpleStringProperty(descr);
        this.mediaRating = new SimpleDoubleProperty(rating);
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

