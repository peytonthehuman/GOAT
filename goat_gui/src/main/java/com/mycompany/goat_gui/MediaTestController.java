/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sean
 */
public class MediaTestController {
        
    public void onSubmitPressed(ActionEvent event) throws IOException{
        //Adds ratings to profile
        
        //Sends to profile page
        Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
    }
    
    public void onSkipPressed(ActionEvent event) throws IOException{
        //Sends to profile page with cold case
        Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
    }
}
