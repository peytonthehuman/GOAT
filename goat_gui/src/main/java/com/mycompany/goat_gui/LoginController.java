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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {  
    
    /**These instance variables are used to login to the users
     * specific profile.
     */ 
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPassField;
    
    /**
     * When this method is called, the users username and password will
     * be checked in the database.
     * If the criteria are met, they are met with their profile page.
     * If not, they are prompted to enter the parameters again.
     */
    public void loginToProfile(ActionEvent event) throws IOException{
        
        //if(login(username, password)){
            Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
            Scene profileScene = new Scene(profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(profileScene);
            window.show();
        //}
        //else {
          //  wrongCredentials();
                
        //}
    }
    
    /**
     * If the username and/or password do not match the system
     * the user is given the prompt that the input was wrong and
     * to try again.
     */
    public void wrongCredentials(){
        
    }

    /**
     * When this method is called, it will change the Scene to
     * signup.fxml
     */
    public void signupForProfile(ActionEvent event) throws IOException{
        Parent loginParent = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }
    
}