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
import javafx.stage.Stage;

public class LoginController implements Initializable {  
        /**
     * When this method is called, it will change the Scene to
     * a TableView example
     */
    public void signupForProfile(ActionEvent event) throws IOException{
        Parent loginParent = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}