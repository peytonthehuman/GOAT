/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import java.util.ArrayList;

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
    
    //the list of what the user rates and saves
    ArrayList<Media> savedMedia;
    ArrayList<Media> ratedMedia;
    
    
    
    //con with info init
    public User()
    {
        
    }
    
    
}
