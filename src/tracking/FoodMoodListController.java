package tracking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
       
/**
 * FXML Controller Class
 * @author 
 */
public class FoodMoodListController implements Initializable {

    private ArrayList foodMoodList;
    @FXML
    private TextField addFoodMood_moodName;
    @FXML
    private TextArea addFoodMood_foodMoodDescription;
    @FXML
    private Button addFoodMood_addFoodMood;
    @FXML
    private CheckBox addFoodMood_isFavorite;
    @FXML
    private DatePicker addFoodMood_datePicker;
    
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
    
  public void printList(ArrayList foodMoodArray) {
      System.out.println("FoodMoodList Contains These Objects: " + foodMoodArray);
  }  

  @FXML
  private void handleAddFoodMood(javafx.event.ActionEvent event) {
      if(isNull()) {
          addFoodMood_addFoodMood.setDisable(true);
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Please check if all entries are complete");
          alert.setContentText("Please try again");
          alert.showAndWait();
          addFoodMood_addFoodMood.setDisable(false);
          addFoodMood_moodName.requestFocus();    
      } else {
          // Add food mood
          String foodName = addFoodMood_moodName.getText();
          String foodMoodDescription = addFoodMood_foodMoodDescription.getText();
          String month = Integer.toString(addFoodMood_datePicker.getValue().getMonthValue());
          String days = Integer.toString(addFoodMood_datePicker.getValue().getDayOfMonth());
          if(month.length() < 2) {
              month = "0" + month;
          }
          if(days.length() < 2) {
              days = "0" + days;
          }
          String foodMoodDate = month + "/" +days + "/" + 
                  Integer.toString(addFoodMood_datePicker.getValue().getYear());
          boolean isFavorite = addFoodMood_isFavorite.isSelected();
          boolean result = database.Database.DatabaseHandler.addFoodMood(foodName, foodMoodDescription, foodMoodDate,
                  isFavorite);
          if(result) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Add Food Mood Success");
              alert.setHeaderText(null);
              alert.setContentText("Food Mood Added");
              alert.showAndWait();
              clearFields();
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Failed to add FoodMood to the database");
              alert.setContentText("Please try again");
              alert.showAndWait();
          }
      }
  }
  
  /**
    * Null check of user input nodes
    * @return True = 1 or more nodes are empty, False = All nodes are not empty
    */
  private boolean isNull() {
    return addFoodMood_moodName.getText().isEmpty() || addFoodMood_foodMoodDescription.getText().isEmpty()
            || addFoodMood_datePicker.getValue() == null;
  }
  
  /**
   * Clears user entries
   */
  private void clearFields() {
      addFoodMood_moodName.setText("");
      addFoodMood_foodMoodDescription.setText("");
      addFoodMood_isFavorite.selectedProperty().set(false);
  }
  
}
