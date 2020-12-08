/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.goat_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Sean
 */
public class HomeController {
    
    @FXML private TextField searchTextField;
    @FXML private TableView movieView = new TableView();
    private User user;
    ArrayList<Media> movies;

    
    public void setUser(User u)
    {
        user = u;
       
        
    }
    
    
    public void onHomePressed(ActionEvent event) throws IOException, JSONException { 
        /**
         * This code works sometimes but not others.
         * 
         * Will need to figure out why
         */
        
        
         
         ArrayList<Media> movies = getMovies();

         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("home.fxml"));
                    
         Parent profileParent = loader.load();
                    
         System.out.println("---------" + user.getUsername());           
                    
         Scene profileScene = new Scene(profileParent);
                    
         HomeController hc = loader.getController();
                   
         hc.setUser(user);
            
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
         window.setScene(profileScene);
         window.show(); 
         
         /*
        Parent profileParent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
         */
    }
    
    public void onProfilePressed(ActionEvent event) throws IOException{
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
        
        /*
        
        Parent profileParent = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
*/
    }
    
    public void onSearchEntered(ActionEvent event) throws IOException {
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchList.fxml"));
                    
        Parent profileParent = loader.load();
                    
        System.out.println("---------" + user.getUsername());   //debug        

                    
        Scene profileScene = new Scene(profileParent);
                    
        ProfileController pc = loader.getController();
                   
        pc.setUser(user);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
         
        window.setScene(profileScene);
        window.show();
        
        /*
        Parent profileParent = FXMLLoader.load(getClass().getResource("searchList.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
*/
    }
    
    public void onLogoutPressed(ActionEvent event) throws IOException {
        Parent profileParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
    }
    
     /**
     * Media Item Functions
     */
    public void mediaItemPressed(ActionEvent event) throws Exception {
        
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("mediaItem.fxml"));
                    
          Parent mediaParent = loader.load();
                    
          System.out.println("---------" + user.getUsername());   //debug        
            
                    
          Scene mediaScene = new Scene(mediaParent);
                    
          MediaItemController mc = loader.getController();
                   
          mc.setUser(user);
            
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
          window.setScene(mediaScene);
          window.show();
        
    }
    
    
    
     
    private ArrayList<Media> getMovies() throws IOException, JSONException
    {
        
        ArrayList<Media> moviesM = new ArrayList<>();
        URL url = new URL("http://www.peytonlwhite.com/blog/getmovies/");
        

       
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); 
        http.setDoOutput(true);
        
        /*
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
        */
        
         
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        
        
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
        System.out.println("after to while" + jsonString.toString());
        //convert into json obejct for reading 
        
       JSONObject jsonObj = new JSONObject(jsonString.toString());
       // JSONArray jsonArr= new JSONArray(jsonString.toString());

       
         
       System.out.println("---------------------------");
       System.out.println("is sucess " + jsonObj.get("success"));
       System.out.println("is Movie " + jsonObj.get("movie"));

       
       String s = (String) jsonObj.get("success");
       
       
       
       if(s.equals("1"))
       {
           //get json array login out of the object and parse through it
            JSONArray jsonArr = jsonObj.getJSONArray("movie");
            
            System.out.println("1E " + jsonArr); //debug
            String id =null;
            String title = null;
            String date_r = null;
            String num_of_generes = null;
          
            
            for(int i = 0 ; i<jsonArr.length();i++)
            {
                JSONObject item = jsonArr.getJSONObject(i);  
                id = item.getString("Media_Id");
                title = item.getString("Title");
                date_r = item.getString("Date_Released");
                num_of_generes = item.getString("num_of_generes");
                
                //add data to a object with media data(movies)
                Media m = new Media(title,id,date_r,num_of_generes);
                moviesM.add(m);
                System.out.println(title); 
            
              
            }
           //debug
            
            
            
          
            
            //set user info with constructor
            //user = new User(Integer.valueOf(id),u,e,b,fn,ln,pic_Path);
            
            //set the array with new data

       }
       
       
       
       return movies;
    }
   
}
