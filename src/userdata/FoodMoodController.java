/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tracking.FoodMoodDetailController;

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class FoodMoodController implements Initializable {
    private ArrayList<FoodMood> foodMoodList = new ArrayList<>();
    private FoodMood foodMood = new FoodMood("");
    @FXML
    private Button foodMood_goToMain;
    @FXML
    private TableView<FoodMood> foodMood_foodMoodListTable;
    @FXML
    private Button foodMood_viewDetails;
    @FXML
    private Button foodMood_addFoodMood;
    @FXML
    private Button foodMood_deleteFoodMood;
    @FXML
    private Label foodMood_searchDateLabel;
    @FXML
    private Label foodMood_startDate;
    @FXML
    private DatePicker foodMood_startDatePicker;
    @FXML
    private Label foodMood_endDate;
    @FXML
    private DatePicker foodMood_endDatePicker;
    @FXML
    private Button foodMood_filter;
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
        TableColumn foodNameCol = new TableColumn("Food");
        TableColumn foodMoodCol = new TableColumn("Mood");
        TableColumn foodMoodDateCol = new TableColumn("Date");
        TableColumn foodFavoriteCol = new TableColumn("Favorite");
        //TableColumn idCol = new TableColumn("FoodMood ID");
        // Restrict table to only single selection mode
        foodMood_foodMoodListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        foodNameCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodName")
        );
        foodMoodCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodMoodDescription")
        );
        foodMoodDateCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("foodMoodDate")
        );
        foodFavoriteCol.setCellValueFactory(
                new PropertyValueFactory<FoodMood, String>("isFavorite")
        );
        
        foodMood_foodMoodListTable.getColumns().setAll(foodNameCol, foodMoodCol, foodMoodDateCol, foodFavoriteCol);
        try {
            initializeFoodMoodTable();
        } catch (ParseException ex) {
            System.out.println("Error occured: " + ex.toString());
        }
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
     * Get foodmood from the database
     * @return the foodMoodList
     * @throws java.text.ParseException
     */
    public ArrayList<FoodMood> getFoodMoodList() throws ParseException {
        return database.Database.DatabaseHandler.getFoodMoodList();
    }
    
    /**
     * Get foodMood list from the db and show it to the table
     */
    private void initializeFoodMoodTable() throws ParseException {
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
    private void addFoodMood(ActionEvent event) throws IOException {
        // Loads FXML resources and create,display a FXML scene 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tracking/FoodMoodList.fxml"));
        Parent root = loader.load();
        Stage base = new Stage();
        base.setTitle("FoodMood - Add Food Mood");
        Scene main = new Scene(root);
        base.setScene(main);
        base.show();
        // Detect Add Food Mood scene is closing and refresh table
        // Decorator Implementation
        base.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            try {
                initializeFoodMoodTable();
            } catch (ParseException ex) {
                System.out.println("Error occured: " + ex.toString());
            }
        }
        }); 
    }
    
    /**
     * Deletes a selected foodMood
     * @param event 
     */
    @FXML
    private void deleteFoodMood(ActionEvent event) throws ParseException {
        if(foodMood_foodMoodListTable.getSelectionModel().isEmpty()) {
            foodMood_deleteFoodMood.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a row to delete and try again");
            alert.setContentText("Please select a row delete and try again");
            alert.showAndWait();
            foodMood_foodMoodListTable.requestFocus();
            foodMood_deleteFoodMood.setDisable(false);
        } else {
            FoodMood selectedFoodMood = foodMood_foodMoodListTable.getSelectionModel().getSelectedItem();
            int foodMoodID = foodMood_foodMoodListTable.getSelectionModel().getSelectedItem().getFoodMoodID();
            // Ask for confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Food Mood?");
            alert.setHeaderText("Delete Food Mood");
            alert.setContentText("Delete Food Mood ?");
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");
            alert.getButtonTypes().setAll(yes, no);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == yes) {
                boolean deleteResult = database.Database.DatabaseHandler.deleteFoodMood(foodMoodID);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Delete Success");
                alert.setContentText("Food Mood Deleted");
                alert.showAndWait();
                foodMood_foodMoodListTable.getItems().remove(selectedFoodMood);
                initializeFoodMoodTable();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Delete Failed");
                alert.setContentText("Delete Food Mood Failed. Please try again");
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Go back to main menu
     * @param event 
     */
    @FXML
    private void goToMainMenu(ActionEvent event) {
        // Close Food Mood Scene
        Stage temp = (Stage) foodMood_goToMain.getScene().getWindow();
        temp.close();
    }

    /**
     * Show details of selected row
     * @param event 
     */
    @FXML
    private void viewFoodMoodDetails(ActionEvent event) throws IOException {
        if(foodMood_foodMoodListTable.getSelectionModel().isEmpty()) {
            foodMood_deleteFoodMood.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a row to view details and try again");
            alert.setContentText("Please select a row to view details and try again");
            alert.showAndWait();
            foodMood_foodMoodListTable.requestFocus();
            foodMood_deleteFoodMood.setDisable(false);
        } else {
            FoodMood temp = foodMood_foodMoodListTable.getSelectionModel().getSelectedItem();
            // Loads FXML resources and create,display a FXML scene 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tracking/FoodMoodDetail.fxml"));
            Parent root = loader.load();
            Stage base = new Stage();
            base.setTitle("FoodMood - View Food Mood Details");
            Scene main = new Scene(root);
            FoodMoodDetailController controller = loader.<FoodMoodDetailController>getController();
            controller.setValues(temp.getFoodMoodID(), temp.getFoodName(), temp.getFoodMoodDescription(), temp.getFoodMoodDate(), temp.getIsFavoriteBoolean());
            base.setScene(main);
            base.show();
        }
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
    
    /**
     * returns a list of FoodMood objects that was added in a given date range 
     * @return filteredList
     * @throws ParseException 
     */
    private ArrayList<FoodMood> filterByDate(Date start, Date end) throws ParseException{
        ArrayList<FoodMood> filteredList = new ArrayList<>();
        // Get all foodmood from db incase the list is emtpy
        Iterator<FoodMood> foodMoodSearch = getFoodMoodList().iterator();
        while(foodMoodSearch.hasNext()){
            FoodMood currentItem = foodMoodSearch.next();
            Date currentItemDate = new SimpleDateFormat("MM/dd/yyyy").parse(currentItem.getFoodMoodDate());
            // If date is on or after start date and on or before the end date, add to list
            if(currentItemDate.after(start) && currentItemDate.before(end) || currentItemDate.equals(start) || currentItemDate.equals(end)){
                filteredList.add(currentItem);
            }
        }
        return filteredList;
    }
    
    /**
     * Filter FoodMood objects that was added in a given date range
     * @param event 
     */
    @FXML
    private void filterFoodMood(ActionEvent event) throws ParseException {
        // Check if dates are picked
        if(foodMood_startDatePicker.getValue() == null || foodMood_endDatePicker.getValue() == null) {
            // Send error message
            foodMood_filter.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select proper dates");
            alert.setContentText("Please select a date for BOTH Start date and End date");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                foodMood_filter.setDisable(false);
                foodMood_startDatePicker.requestFocus();
            }  
        } else {
            Date start = new SimpleDateFormat("MM/dd/yyyy").parse(Integer.toString(foodMood_startDatePicker.getValue().getMonthValue())
                    + "/" + Integer.toString(foodMood_startDatePicker.getValue().getDayOfMonth())
                    + "/" + Integer.toString(foodMood_startDatePicker.getValue().getYear()));
            Date end = new SimpleDateFormat("MM/dd/yyyy").parse(Integer.toString(foodMood_endDatePicker.getValue().getMonthValue())
                    + "/" + Integer.toString(foodMood_endDatePicker.getValue().getDayOfMonth())
                    + "/" + Integer.toString(foodMood_endDatePicker.getValue().getYear()));
            if(start.after(end)) {
                // Start date is after end date
                foodMood_filter.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Select proper dates");
                alert.setContentText("Start date is after End date. Please try again");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    foodMood_filter.setDisable(false);
                    foodMood_startDatePicker.requestFocus();
                }
            } else {
                // filter FoodMood
                foodMoodList = filterByDate(start, end);
                // populate the table if not empty
                ObservableList<FoodMood> data;
                data = FXCollections.observableArrayList(foodMoodList);
                foodMood_foodMoodListTable.setItems(data);
                }
            }
        }
    }