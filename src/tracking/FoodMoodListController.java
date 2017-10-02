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
    public void addFoodMood(String[] foodMood) {
        System.out.println("add FoodMood action performed");
    }
    
    /**
     * Removes a food mood
     * @param event "Remove Food Mood" button action
     */
    public void removeFoodMood(int index) {
        System.out.println("remove FoodMood action performed");
    }
    
    /**
     * Displays food mood detail
     * @param event "View Food Mood" selection from a list
     */
    public void viewFoodMood() throws InstantiationException, IllegalAccessException {
      FoodMoodDetailController.class.newInstance().initialize(null, null);
      System.out.println("view FoodMood Detailed List action performed");
    }
    
    /**
     * Sorts food mood list
     * @param foodMoodList
     * @param event "Sort Food Mood list" button action
     */
    public void sortFoodMoodList(ArrayList foodMoodList) {
        System.out.println("sort FoodMood action performed");
    }
    
    /**
     * Saves food mood list
     * @param event "Save Food Mood" button action
     */
    public void saveFoodMood() {
        System.out.println("save FoodMood action performed");
    }
    
    /**
   * @return the foodMoodList
   */
  public ArrayList getFoodMoodList() {
    return foodMoodList;
  }

  /**
   * @param foodMoodList the foodMoodList to set
   */
  public void setFoodMoodList(ArrayList foodMoodList) {
    this.foodMoodList = foodMoodList;
  }
    
  public void printList(ArrayList foodMoodArray){
    System.out.println("FoodMoodList Contains These Objects: " + foodMoodArray);
  }  
}
