/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Iterator;

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class FoodMoodController implements Initializable {
    private ArrayList<FoodMood> foodMoodList = new ArrayList<>();
    private FoodMood foodMood = new FoodMood("");
    @FXML
    private Button foodMood_addMood;
    @FXML
    private Button foodMood_deleteMood;
    @FXML
    private Button foodMood_goToMain;
    @FXML
    private TableView<FoodMood> foodMood_foodMoodListTable;
    @FXML
    private Button foodMood_viewDetails;
    /**
     * Initializes the controller class
     */
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("FoodMood Window Initialized"); 
        // Used example codes from https://examples.javacodegeeks.com/desktop-java/javafx/tableview/javafx-tableview-example/
        TableColumn foodMoodCol = new TableColumn("Mood");
        TableColumn describeCol = new TableColumn("Description");
        TableColumn foodMoodDateCol = new TableColumn("Date");
        TableColumn idCol = new TableColumn("Mood ID");
        // Restrcit table to only single selection mode
        foodMood_foodMoodListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        foodMoodCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodMoodNameT")
        );
        describeCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodMoodDescription")
        );
        foodMoodDateCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodMoodDate")
        );
        
        foodMood_foodMoodListTable.getColumns().setAll(foodMoodCol, describeCol, foodMoodDateCol);
        initializeFoodMoodTable();
    }
    
    /**
     * Creates and returns a food foodMood object
     * @param foodName
     * @param description
     * @param date
     * @return A FoodMood object
     */
    public FoodMood addFoodMood(String foodName, String description, String Date) {
        return new FoodMood(foodName);
    }
    /**
     * Validates text fields
     * @param textField
     * @return false if given text is not empty, true if given text is empty
     */
    public boolean vaildate(String textField) {
        return textField.trim().length() == 0;
    }
    
    /**
     * 
     * @return the foodMoodList
     */
    public ArrayList<FoodMood> getFoodMoodList() {
        System.out.println("Get FoodMood List Action performed.");
        return foodMoodList;
    }
    
    /**
     * Get foodMood list from the db and show it to the table
     */
    private void initializeFoodMoodTable() {
        this.foodMoodList.clear();
        this.foodMoodList = getFoodMoodList();
        ObservableList<FoodMood> data;
        // populate the table if not empty
        if(!foodMoodList.isEmpty()) {
            data = FXCollections.observableArrayList(foodMoodList);
            foodMood_foodMoodListTable.setItems(data);
        }
    }
    
    /**
     * Shows AddFoodMood Scene
     * @param event 
     */
    @FXML
    private void addFoodMood(ActionEvent event) {
        
    }
    
    /**
     * Deletes a selected foodMood
     * @param event 
     */
    @FXML
    private void deleteFoodMood(ActionEvent event) {
        
    }
    
    /**
     * Go back to main menu
     * @param event 
     */
    @FXML
    private void goToMainMenu(ActionEvent event) {
        
    }

    /**
     * Show details of selected row
     * @param event 
     */
    @FXML
    private void viewFoodMoodDetails(ActionEvent event) {
    }
    
    private FoodMood findSearchTerm(String st){
      Iterator<FoodMood> foodMoodSearch = foodMoodList.iterator();
      while(foodMoodSearch.hasNext()){
        FoodMood tempSearch = foodMoodSearch.next();
        if (tempSearch.getFoodName().contains(st)) {
          return tempSearch;
        } 
      }
      return null;
    }
    
    private ArrayList<FoodMood> filterByDate(Date start, Date end){
      ArrayList<FoodMood> filteredList = new ArrayList<>();
      Iterator<FoodMood> foodMoodSearch = foodMoodList.iterator();
      while(foodMoodSearch.hasNext()){
        FoodMood currentItem = foodMoodSearch.next();
        if(currentItem.getFoodMoodDate().after(start) && currentItem.getFoodMoodDate().before(end)){
          filteredList.add(currentItem);
        }
      }
      return filteredList;
    }
}