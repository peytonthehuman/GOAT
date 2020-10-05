package com.mycompany.goat_gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPassField;
    
    public void createProfile (ActionEvent event) throws IOException{
        //if(usernameAvailable() && emailAvailable()){
            //dynamically allocate profile for user.
            
            //send them to their new profile page.
            Parent profileParent = FXMLLoader.load(getClass().getResource("mediaTest.fxml"));
            Scene profileScene = new Scene(profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(profileScene);
            window.show();
        /*}
        else if (!usernameAvailable()) {
            //prompt user to enter different username as it is taken
        }
        else if (!emailAvailable()){
            //prompt user to enter different email, as it is already
            //in use
        }*/
    }

}
