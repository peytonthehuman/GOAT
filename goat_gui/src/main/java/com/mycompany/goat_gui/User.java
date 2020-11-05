/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author peytonwhite
 */
public class User {
    
    //info user holds
    private int id;
    private String username;
    private String email;
    private String birthday;
    private String firstName;
    private String lastName;
    private String pic_Path;
    private Image photo;
    
    //the list of what the user rates and saves
    private ArrayList<Media> savedMedia;
    private ArrayList<Media> ratedMedia;
    
    //for dummy data in ProfileController
        //DELETE ONCE DUMMY DATA NOT NEEDED
    public User(int id,String u,String e,String b, String fn,String ln)
    {
        this.id = id;
        this.username = u;
        this.email = e;
        this.birthday = b;
        this.firstName = fn;
        this.lastName = ln;
        //no pic cause troubles. will try to implement later
    }
    
    //con with info init
    public User(int id,String u,String e,String b, String fn,String ln,String pic )
    {
        this.id = id;
        this.username = u;
        this.email = e;
        this.birthday = b;
        this.firstName = fn;
        this.lastName = ln;
        this.pic_Path = pic;
        
        //set arraylist later
    }
    
    public void setId(int i)
    {
        id = i;
    }
    public int getId()
    {
        return id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * @return the Full Name
     */
    public String getFullName(){
        return firstName + " " + lastName;
    }

    /**
     * @return the pic_Path
     */
    public String getPic_Path() {
        return pic_Path;
    }

    /**
     * @param pic_Path the pic_Path to set
     */
    public void setPic_Path(String pic_Path) {
        this.pic_Path = pic_Path;
    }
    
    
    
    /**
     * @return the Image
     */
    public Image getImage(){
        return photo;
    }
    
    
    
    

    /**
     * @return the savedMedia
     */
    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

    /**
     * @param savedMedia the savedMedia to set
     */
    public void setSavedMedia(ArrayList<Media> savedMedia) {
        this.savedMedia = savedMedia;
    }

    /**
     * @return the ratedMedia
     */
    public ArrayList<Media> getRatedMedia() {
        return ratedMedia;
    }

    /**
     * @param ratedMedia the ratedMedia to set
     */
    public void setRatedMedia(ArrayList<Media> ratedMedia) {
        this.ratedMedia = ratedMedia;
    }
    
}
