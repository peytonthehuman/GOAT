package com.mycompany.goat_gui;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {  
    @FXML
    private Button cancelButton;
    
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    private Button Login;
    
    private void LoginToProfile() throws IOException {    
        
    }
    private void SwitchToSignup() throws IOException {
        App.setRoot("signup");
    }
    
}