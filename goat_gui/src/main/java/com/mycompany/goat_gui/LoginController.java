package com.mycompany.goat_gui;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {  
    @FXML
    private void SwitchToSignup() throws IOException {
        App.setRoot("signup");
    }
    
}