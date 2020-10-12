package com.mycompany.goat_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;

import java.net.*; 
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class SignupController {

    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPassField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private Label errorTextField;

    @FXML private DatePicker birthdayPicker;
    private boolean canSignUp = true;


    
    public void createProfile (ActionEvent event) throws IOException{
        //if(usernameAvailable() && emailAvailable()){
            //dynamically allocate profile for user.
            
            //send them to their new profile page.
            Parent profileParent = FXMLLoader.load(getClass().getResource("mediaTest.fxml"));
            Scene profileScene = new Scene(profileParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
          
           TextField o[] = {emailTextField,usernameTextField,passwordPassField,firstNameTextField,lastNameTextField};
           
           //check for empty textfields
            for(TextField txt : o)
            {
                if(txt.getText().isEmpty())
                {
                    canSignUp = false;
                }
            }
           //check for bday picker to be empty
            if(birthdayPicker == null)
            {
                canSignUp = false;

            }
            
            
            if(canSignUp)
            {
            
               CreateProfile(emailTextField.getText(),usernameTextField.getText(),passwordPassField.getText(),
                          firstNameTextField.getText(),lastNameTextField.getText(),birthdayPicker.getValue().toString());
               //show profile
               window.setScene(profileScene);
               window.show();
            
            }
            else 
            {
                errorTextField.setTextFill(Color.web("#ff0000"));
                errorTextField.setText("Must fill out all fields");
                canSignUp = true;
            }
        
    }
    
    
    
     public void CreateProfile(String email,String userName,String password,String firstName,String lastName,String birthday) throws IOException 
    {
        
    
        URL url = new URL("http://www.peytonlwhite.com/blog/signup");
        
       
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); 
        http.setDoOutput(true);
        
        
        Map<String,String> arguments = new HashMap<>();
        arguments.put("username", userName);
        arguments.put("password", password); 
        arguments.put("email", email); 
        arguments.put("birthday", birthday); 
        arguments.put("firstName", firstName); 
        arguments.put("lastName", lastName); 


        
        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
        sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
          + URLEncoder.encode(entry.getValue(), "UTF-8"));
        
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
       
       
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        
        try(OutputStream os = http.getOutputStream()) 
        {
            os.write(out);
        }
        
        
       
       
        
        
        
        
    }

}
