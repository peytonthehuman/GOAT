package com.mycompany.goat_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

    @FXML private TextField searchTextField;
    private User user;
    
    public void setUser(User u)
    {
        user = u;
        
    }
      
    
    /**
     * Profile Information Display Functions
     */
    
    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label nameLabel;
    @FXML private Label birthdayLabel;
    @FXML private ImageView photo;
    
    public void initData(User u){
        user = u;
        usernameLabel.setText(user.getUsername());
        emailLabel.setText(user.getEmail());
        nameLabel.setText(user.getFullName());
        birthdayLabel.setText(user.getBirthday());
        photo.setImage(user.getImage());
    }
  
    
    /**
     * Media Table Functions and instances
     */
    
    //configure the table
    @FXML private TableView<Media> tableView;
    @FXML private TableColumn<Media, Image> coverArtColumn;
    @FXML private TableColumn<Media, String> titleColumn;
    @FXML private TableColumn<Media, String> genreColumn;
    @FXML private TableColumn<Media, String> descriptionColumn;
    @FXML private TableColumn<Media, String> ratingColumn;
    
    @FXML private Button detailedMediaView;
    
    public void userClickedOnTable(){
        //Enable the detailed media view button once a row is selected
            //connected through SceneBuilder code on Media Info button
        this.detailedMediaView.setDisable(false);
    }
    
    
    /**
     * Initialize (Dummy Information)
     */    
    
    public ObservableList<Media> getDummyMedia(){
        ObservableList<Media> media = FXCollections.observableArrayList();
        media.add(new Media(new Image("file:C:\\Users\\Sean\\Documents\\NetBeansProjects\\GOAT\\goat_gui\\src\\main\\java\\images\\sassy_goat.jpg"), "dummyTitle", "dummy", "dumdum give me gumgum", 5));
        media.add(new Media("Human Centipede", "Horror", "Fun movie with tender moments. Great for children. Trust me", 0));
        media.add(new Media("Marley and Me", "Pupper", "You're either laughing or crying the whole time. There is no inbetween.", 10));
        
        return media;
    }
    
    private Image dummyImage = new Image("file:C:\\Users\\Sean\\Documents\\NetBeansProjects\\GOAT\\goat_gui\\src\\main\\java\\images\\ya_got_some_candy_goat.jpg");
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        coverArtColumn.setCellValueFactory(new PropertyValueFactory<Media, Image>("coverArt"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("genre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("description"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("rating"));
        
        //load dummy data
            //MAKE SURE TO DELETE ONCE WE GET DATA FLOWING
        tableView.setItems(getDummyMedia());
                
        usernameLabel.setText("dummyUser");
        emailLabel.setText("dummyEmail");
        nameLabel.setText("dummy");
        birthdayLabel.setText("");
        photo.setImage(dummyImage);
        

        //Disable the detailed media view button until a row is selected
        this.detailedMediaView.setDisable(true);
    }
    
     /**
     * Load Profile Window
     */
    public void onProfilePressed(ActionEvent event) throws IOException{
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("profile.fxml"));
                    
          Parent profileParent = loader.load();
                    
          System.out.println("---------" + user.getUsername());   //debug        
            
                    
          Scene profileScene = new Scene(profileParent);
                    
          ProfileController pc = loader.getController();
                   
          pc.setUser(user);
            
          Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
          window.setScene(profileScene);
          window.show();
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
     * Media Item Functions
     */
    public void changeScreenToMediaItem(ActionEvent event) throws Exception {
        Parent mediaParent = FXMLLoader.load(getClass().getResource("mediaItem.fxml"));
        Scene mediaScene = new Scene(mediaParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mediaScene);
        window.show();
    }
    /**
     * Change Photo Functions
     */
    public void changeProfilePhoto(ActionEvent event) throws Exception{
        Parent photoParent = FXMLLoader.load(getClass().getResource("mediaItem.fxml"));
        Scene photoScene = new Scene(photoParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(photoScene);
        window.show();
    }
    /**
     * Home Functions 
     */
    public void onHomePressed(ActionEvent event) throws IOException { 
        
        /**
         * For whatever reason, this code breaks traversal from one window to the next
         * on certain instances.
         * 
         * Look into why.
         */
         /*FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("home.fxml"));
                    
         Parent profileParent = loader.load();
                    
         System.out.println("---------" + user.getUsername());   //debug        
           
                    
         Scene profileScene = new Scene(profileParent);
                    
         HomeController hc = loader.getController();
                   
         hc.setUser(user);
            
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
       
                    
         window.setScene(profileScene);
         window.show();*/
         
        Parent profileParent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene profileScene = new Scene(profileParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(profileScene);
        window.show();
        
    }
    /**
     * Search Functions
     */
    public void onSearchEntered(ActionEvent event) throws IOException {
        Parent searchParent = FXMLLoader.load(getClass().getResource("searchList.fxml"));
        Scene searchScene = new Scene(searchParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(searchScene);
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
}

