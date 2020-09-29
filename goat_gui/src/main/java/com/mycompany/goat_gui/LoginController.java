package com.mycompany.goat_gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {  
    @FXML
    private void SwitchToSignup() throws IOException {
        App.setRoot("signup");
    }
    
    @FXML
    private void SwitchToProfile() throws IOException {
        App.setRoot("profile");
    }
    
    @FXML
    private void CheckCredentials() throws IOException {
        
    }
}