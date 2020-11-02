package com.mycompany.goat_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileController {
    
    @FXML private TextField searchTextField;
    private User user;
    
    public void setUser(User u)
    {
        user = u;
        
    }
    
    public void onHomePressed(ActionEvent event) throws IOException {      
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("home.fxml"));
                    
         Parent profileParent = loader.load();
                    
         System.out.println("---------" + user.getUsername());   //debug        
           
                    
         Scene profileScene = new Scene(profileParent);
                    
         HomeController hc = loader.getController();
                   
         hc.setUser(user);
            
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
         window.setScene(profileScene);
         window.show();
        
    }
    
    public void onProfilePressed(ActionEvent event) throws IOException{
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("profile.fxml"));
                    
          Parent profileParent = loader.load();
                    
          System.out.println("---------" + user.getUsername());   //debug        
            
                    
          Scene profileScene = new Scene(profileParent);
                    
          ProfileController pc = loader.getController();
                   
          pc.setUser(user);
            
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
          window.setScene(profileScene);
          window.show();
    }
    
    public void onSearchEntered(ActionEvent event) throws IOException {
        Parent profileParent = FXMLLoader.load(getClass().getResource("searchList.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
    }
    
    public void onLogoutPressed(ActionEvent event) throws IOException {
        Parent profileParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
    }
    
    
    
    
}

