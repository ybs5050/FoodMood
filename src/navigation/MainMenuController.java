package navigation;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller Class
 * @author
 */
public class MainMenuController implements Initializable {

    @FXML
    private Text mainmenu_username;
    @FXML
    private Text mainmenu_userid;
    @FXML
    private Button mainmenu_viewMoods;
    @FXML
    private Button mainmenu_foodMoodList;
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainMenu Window Initialized"); //successful call
        if(database.Database.username != null && database.Database.userId < Integer.MAX_VALUE) {
            // Show username, userid
            mainmenu_username.setText("Welcome, " + database.Database.username);
            mainmenu_userid.setText("User ID: " + database.Database.userId);
        }
    }
    
    /**
     * Opens a new scene to view mood list
     * @param event "View Mood Food" button action
     */
    @FXML
    private void viewMoodList(javafx.event.ActionEvent event) {
        System.out.println("View Food Mood List event occured");
        try {
            // Loads FXML resources and create,display a FXML scene 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userdata/Mood.fxml"));
            Parent root = loader.load();
            Stage base = new Stage();
            base.setTitle("FoodMood - Mood List");
            Scene main = new Scene(root);
            base.setScene(main);
            base.setResizable(false);
            base.show();
        } catch (IOException except) {
            System.out.println("Error occured: " + except.toString());
        }
    }
    
    /**
     * Opens a new scene to view food mood list
     * @param event "View Food/Mood" button action
     */
    @FXML
    private void viewFoodMoodList(javafx.event.ActionEvent event) {
        System.out.println("View Food Mood List event occured");
        try {
            // Loads FXML resources and create,display a FXML scene 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userdata/FoodMood.fxml"));
            Parent root = loader.load();
            Stage base = new Stage();
            base.setTitle("FoodMood - Food/Mood List");
            Scene main = new Scene(root);
            base.setScene(main);
            base.setResizable(false);
            base.show();
        } catch (IOException except) {
            System.out.println("Error occured: " + except.toString());
        }
    }
    
    /**
     * Closes this FoodMood application
     * @param event "Exit FoodMood" button action
     */
    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
    }
    
}
