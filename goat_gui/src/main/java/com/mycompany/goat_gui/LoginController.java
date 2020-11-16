package com.mycompany.goat_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.*;
//import org.json.simple.JSONArray;




public class LoginController {  
    
    /**These instance variables are used to login to the users
     * specific profile.
     */ 
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPassField;
    @FXML private Label errorlabel;
    FXMLLoader loader;
    private User user;


    
    /**
     * When this method is called, the users username and password will
     * be checked in the database.
     * If the criteria are met, they are met with their profile page.
     * If not, they are prompted to enter the parameters again.
     */
    public void loginToProfile(ActionEvent event) throws IOException, JSONException{
        
         
        // Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
          
       // Scene profileScene = new Scene(profileParent);
            
         //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        
            /*
            window.setScene(profileScene);
            window.show();
            */
          
            

            //check if feilds are empty 
            if((usernameTextField.getText().isEmpty() || passwordPassField.getText().isEmpty()))
            {
                errorlabel.setText("Must fill out all fields");
            }
            else 
            {
                String check = checkLoginCredentials(usernameTextField.getText().toString(),passwordPassField.getText().toString());
                System.out.println(check);
                //1 is success
                if(check.equals("1"))
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("profile.fxml"));
                    
                    Parent profileParent = loader.load();
                    
                    
                    
                    Scene profileScene = new Scene(profileParent);
                    
                    ProfileController pc = loader.getController();
                   
                    pc.setUser(user);
                    pc.initData(user);
            
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
                    window.setScene(profileScene);
                    window.show();
                }
                else 
                {
                   // errorlabel.setText("");
                    errorlabel.setText("Invalid Password/Username");
                }
               
            }
    }
    
    /**
     * If the username and/or password do not match the system
     * the user is given the prompt that the input was wrong and
     * to try again.
     */
    
    
    public void signupForProfile(ActionEvent event) throws IOException{
        Parent loginParent = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }
    
    
    private String checkLoginCredentials(String username,String password) throws IOException, JSONException
    {
        URL url = new URL("http://www.peytonlwhite.com/blog/checklogin");

       
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); 
        http.setDoOutput(true);
        
        
        Map<String,String> arguments = new HashMap<>();
        arguments.put("username", username);
        arguments.put("password", password); 
         

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
        
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String line;
        StringBuilder jsonString = new StringBuilder();
        
        
        
        while ((line = rd.readLine()) != null) {
        //System.out.println(line);
        //convert into json string format
        jsonString.append(line);

        }
        rd.close();
        System.out.println(jsonString.toString());
        //convert into json obejct for reading 
        
       JSONObject jsonObj = new JSONObject(jsonString.toString());
       //JSONArray jsonArr= new JSONArray(jsonString.toString());

       
         
       System.out.println("---------------------------");
       System.out.println(jsonObj.get("success"));
       
       String s = (String) jsonObj.get("success");
       
       
       
       if(s.equals("1"))
       {
           //get json array login out of the object and parse through it
            JSONArray jsonArr = jsonObj.getJSONArray("login");
            
            System.out.println(jsonArr); //debug
            String id =null;
            String u = null;
            String e = null;
            String b = null;
            String fn  = null;
            String ln = null;
            String pic_Path = null;
            
            for(int i = 0 ; i<jsonArr.length();i++)
            {
                JSONObject item = jsonArr.getJSONObject(i);  
                id = item.getString("User_Id");
                u = item.getString("Username");
                e = item.getString("Email");
                b = item.getString("Birthday");
                fn = item.getString("FirstName");
                ln = item.getString("LastName");
                pic_Path = item.getString("Picture_Path");
                
            }
            System.out.println(u); //debug
            
            
            
          
            
            //set user info with constructor
            user = new User(Integer.valueOf(id),u,e,b,fn,ln,pic_Path);

       }
       
       
       
       return s;
    }
    
}