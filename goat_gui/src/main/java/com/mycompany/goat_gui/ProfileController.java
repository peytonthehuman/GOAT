package com.mycompany.goat_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileController implements Initializable {

    
    
    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label nameLabel;
    @FXML private Label birthdayLabel;
    //@FXML private ImageView photo;
    @FXML private TextField searchTextField;
   
    Pane apane;
    
    private ArrayList<Media> movies = new ArrayList<>();
    private User user;
    private int id;

    
    public void setUser(User u)
    {
        user = u;
        
    
    }
    
    public ProfileController()
    {
        
    }
      
    public ProfileController(User u) throws IOException
    {
        user = u;
          FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
   loader.setControllerFactory(type -> {
       if (type == ProfileController.class) {
           return this ;
       } else {
           try {
               return type.newInstance();
           } catch (RuntimeException e) {
               throw e ;
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
   });
   Parent root = loader.load();
   Stage stage = new Stage();
   stage.setScene(new Scene(root));
   stage.show();
    }
    
    /**
     * Profile Information Display Functions
     */
    
    
    public void initData(User u) throws IOException, JSONException {
        user = u;
        usernameLabel.setText(user.getUsername());
        emailLabel.setText(user.getEmail());
        nameLabel.setText(user.getFullName());
        birthdayLabel.setText(user.getBirthday());
        //photo.setImage(user.getImage());
        //movies = getMovies();
        
    }
  
    
    /**
     * Media Table Functions and instances
     */
    
    //configure the table
    @FXML private TableView<Media> tableView;
  //  @FXML private TableColumn<Media, Image> coverArtColumn;
    @FXML private TableColumn<Media, String> titleColumn;
    @FXML private TableColumn<Media, String> genreColumn;
   // @FXML private TableColumn<Media, String> descriptionColumn;
    @FXML private TableColumn<Media, String> dateColumn;
    
    @FXML private Button detailedMediaView;
    
    public void userClickedOnTable(){
        //Enable the detailed media view button once a row is selected
            //connected through SceneBuilder code on Media Info button
        this.detailedMediaView.setDisable(false);
    }
    
    
    /**
     * Initialize (Dummy Information)
     */    
    
    public ObservableList<Media> getMedia() throws IOException, JSONException {
        ObservableList<Media> media = FXCollections.observableArrayList();
     //   media.add(new Media(new Image("file:C:\\Users\\Sean\\Documents\\NetBeansProjects\\GOAT\\goat_gui\\src\\main\\java\\images\\sassy_goat.jpg"), "dummyTitle", "dummy", "dumdum give me gumgum", 5));
     //   media.add(new Media("Human Centipede", "Horror", "Fun movie with tender moments. Great for children. Trust me", 0));
       // media.add(new Media("Marley and Me", "Pupper", "You're either laughing or crying the whole time. There is no inbetween.", 10));

       
         movies = getMovies();
        for(int i =0; i < movies.size();i++)
        {
            media.add(movies.get(i));
        }
       
       
       
        return media;
    }
    
    // Old Image
    // private Image dummyImage = new Image("file:C:\\Users\\Sean\\Documents\\NetBeansProjects\\GOAT\\goat_gui\\src\\main\\java\\images\\ya_got_some_candy_goat.jpg");
    
    //private Image dummyImage = new Image("http://i.stack.imgur.com/Hbnkb.png");
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
     //   coverArtColumn.setCellValueFactory(new PropertyValueFactory<Media, Image>("coverArt"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("numOfGeneres"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("date_Released"));

       // descriptionColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("description"));
        //ratingColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("rating"));
        try {
            //load dummy data
            //MAKE SURE TO DELETE ONCE WE GET DATA FLOWING
          //  movies = getMovies();
            tableView.setItems(getMedia());
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
                
       
        try {
            initData(user);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        //Disable the detailed media view button until a row is selected
        this.detailedMediaView.setDisable(true);
    }
    
     /**
     * Load Profile Window
     */
    public void onProfilePressed(ActionEvent event) throws IOException, JSONException{
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
    
   
    /**
     * Logout Function
     */
    public void onLogoutPressed(ActionEvent event) throws IOException {
        Parent logoutParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene logoutScene = new Scene(logoutParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(logoutScene);
        window.show();
    }
    
        /**
     * Media Item Functions
     */
    public void changeScreenToMediaItem(ActionEvent event) throws Exception {
        
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
        
        
        /*
        Parent mediaParent = FXMLLoader.load(getClass().getResource("mediaItem.fxml"));
        Scene mediaScene = new Scene(mediaParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mediaScene);
        window.show();
        */
    }
    
     /**
     * Home Functions 
     */
    public void onHomePressed(ActionEvent event) throws IOException { 
        
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("home.fxml"));
                    
         Parent profileParent = loader.load();
                    
         System.out.println("---------" + user.getUsername());   //debug        
           
                    
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
    
    
    
    /**
     * 
     * These functions are used to traverse from one window to
     * the next depending on the action the user chooses.
     * 
     * ALERT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * SOME ARE BOUND TO CHANGE ONCE MORE THINGS ARE IMPLEMENTED.
     * They are only put here for organizational purposes to make code
     * easier to look through until delved into further
     * 
     */
    
    
    

    /**
     * Change Photo Functions
     */
    public void changeProfilePhoto(ActionEvent event) throws Exception{
        Parent photoParent = FXMLLoader.load(getClass().getResource("profilePhotos.fxml"));
        Scene photoScene = new Scene(photoParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(photoScene);
        window.show();
    }
 
    /**
     * Search Functions
     */
    public void onSearchEntered(ActionEvent event) throws IOException {
        Parent profileParent = FXMLLoader.load(getClass().getResource("searchList.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchList.fxml"));
                    
        Parent profileParent = loader.load();
                    
        System.out.println("---------" + user.getUsername());   //debug        

                    
        Scene profileScene = new Scene(profileParent);
                    
        SearchListController pc = loader.getController();
                   
        pc.setUser(user);
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
         
        window.setScene(profileScene);
        window.show();*/
    }
    
    
    
     private ArrayList<Media> getMovies() throws IOException, JSONException
    {
        
        ArrayList<Media> moviesM = new ArrayList<>();
        URL url = new URL("http://www.peytonlwhite.com/blog/getmoviesfromuser/");
       

       
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST"); 
        http.setDoOutput(true);
        
        
        Map<String,String> arguments = new HashMap<>();
        arguments.put("user_id", Integer.toString(user.getId()));
       //arguments.put("password", password); 
         

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
        sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
          + URLEncoder.encode(entry.getValue(), "UTF-8"));
        
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
       
       
        
        try(OutputStream os = http.getOutputStream()) 
        {
            os.write(out);
        }
        
        
       // http.setFixedLengthStreamingMode(length);
       // http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
       // http.connect();
        
        
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
        //JSONArray jsonArr = new JSONArray(jsonString.toString());
        
       
         
       System.out.println("---------------------------");
       System.out.println("is sucesssssss " + jsonObj.get("success"));
      // System.out.println("is Movie " + jsonObj.get("movieid"));
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
                System.out.println("testttt" + m.getTitle()); 
            
              
            }
           //debug
            
            
            
          
            
            //set user info with constructor
            //user = new User(Integer.valueOf(id),u,e,b,fn,ln,pic_Path);
            
            //set the array with new data

       }
       
       
       
       return moviesM;
    }
    
    
    
    
    
}

