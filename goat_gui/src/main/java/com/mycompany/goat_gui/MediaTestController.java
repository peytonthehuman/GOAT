/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sean
 */
public class MediaTestController {
        
    
    private User user;
    
    public void setUser(User u)
    {
        user = u;
        
    }
    
    
    public void onSubmitPressed(ActionEvent event) throws IOException, JSONException{
        //Adds ratings to profile
        
        //Sends to profile page
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profile.fxml"));
                    
        Parent profileParent = loader.load();
                    
        System.out.println("---------" + user.getUsername());   //debug        

                    
        Scene profileScene = new Scene(profileParent);
                    
        ProfileController pc = loader.getController();
                   
        pc.setUser(user);
        pc.initData(user);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
         
        window.setScene(profileScene);
        window.show();
    }
    
    public void onSkipPressed(ActionEvent event) throws IOException, JSONException{
        //Sends to profile page with cold case
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profile.fxml"));
                    
        Parent profileParent = loader.load();
                    
        System.out.println("---------" + user.getUsername());   //debug        

                    
        Scene profileScene = new Scene(profileParent);
                    
        ProfileController pc = loader.getController();
                   
        pc.setUser(user);
        pc.initData(user);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
         
        window.setScene(profileScene);
        window.show();
    }
    
    
   
}
