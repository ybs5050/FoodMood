package tracking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
       
/**
 * FXML Controller Class
 * @author 
 */
public class FoodMoodListController implements Initializable {
    
    private ArrayList foodMoodList;
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("FoodMoodList Window Initialized"); 
    }
    
    /**
     * Adds a food mood
     * @param event "Add Food Mood" button action
     */
    public void addFoodMood() {
        System.out.println("add FoodMood action performed");
    }
    
    /**
     * Removes a food mood
     * @param event "Remove Food Mood" button action
     */
    public void removeFoodMood() {
        System.out.println("remove FoodMood action performed");
    }
    
    /**
     * Displays food mood detail
     * @param event "View Food Mood" selection from a list
     */
    public void viewFoodMood() {
        System.out.println("view FoodMood action performed");
    }
    
    /**
     * Sorts food mood list
     * @param event "Sort Food Mood list" button action
     */
    public void sortFoodMoodList() {
        System.out.println("sort FoodMood action performed");
    }
    
    /**
     * Saves food mood list
     * @param event "Save Food Mood" button action
     */
    public void saveFoodMood() {
        System.out.println("save FoodMood action performed");
    }
    
}
