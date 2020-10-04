package com.mycompany.goat_gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class rootLayoutController {
   
    public void onHomeClick(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene homeScene = new Scene(homeParent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(homeScene);
        window.show();
    }
    
    public void onProfileClick(ActionEvent event) throws IOException{
        Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profileScene = new Scene(profileParent);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(profileScene);
        window.show();
    }
    
    public void onSearch(){
        
    }
}
