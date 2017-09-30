package navigation;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

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
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("MainMenu Window Initialized"); 
    }
    
    /**
     * Opens a new scene from FoodMoodList class
     * @param event "View Mood Food" button action
     */
    public void viewFoodMoodList() {
        System.out.println("View Food Mood List event occured");
    }
    
}
