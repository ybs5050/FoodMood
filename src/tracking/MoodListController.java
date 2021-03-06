/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tracking;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.Alerts;

/**
 * FXML Controller Class
 * @author Youngmin
 */
public class MoodListController implements Initializable {

    @FXML
    private TextField addMood_moodName;
    @FXML
    private TextArea addMood_moodDescription;
    @FXML
    private TextField addMood_moodTimeOccured; 
    @FXML
    private Button addMood_addMood;
    @FXML
    private Button addMood_cancel;
    @FXML
    private ComboBox<String> addMood_moodSeverity;
  
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Add Mood Window Initialized");
        // Add menus in combo box
        // Used example codes from http://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
        addMood_moodSeverity.getItems().addAll(
                "None",
                "Minimal",
                "Moderate",
                "High",
                "Severe"
        ); 
    }
    
    /**
     * Call create mood function in the database and show results
     * @param moodName
     * @param moodDescription
     * @param moodSeverity
     * @param moodTimeOccured 
     */
    private void addMood(String moodName, String moodDescription, String moodSeverity, String moodTimeOccured) {
        boolean result = database.Database.DatabaseHandler.createMood(moodName, moodDescription, moodSeverity, moodTimeOccured);
        if (result) {
            // If success send confirmation alert
            Optional<ButtonType> resultAlert = Alerts.AlertGenerator.generateAlert(Alert.AlertType.INFORMATION, "Add Mood Success", null, "Added Mood " + moodName).showAndWait();
            if (resultAlert.get() == ButtonType.OK) {
                // Close Add Mood Scene
                //temp.close();
                addMood_addMood.setDisable(false);
                clearFields();
            } 
        } else {
            // Send error alert 
            Alerts.AlertGenerator.generateAlert(Alert.AlertType.ERROR, "Error", "Wrong username or password", "Please check the username and password").showAndWait();
            addMood_addMood.setDisable(false);
            clearFields();
            addMood_moodName.requestFocus();
        }
    }
    
    
    /**
     * Check user inputs before adding mood and show results
     * @param event 
     */
    @FXML
    private void handleAddMood(ActionEvent event) {
        // Disable the add mood button while attempting to add mood to the database
        addMood_addMood.setDisable(true);
        if (isNull()) {
            // Send error alert
            Alerts.AlertGenerator.generateAlert(Alert.AlertType.ERROR, "Error", "Empty mood name or mood severity level", "Please try again").showAndWait();
            addMood_addMood.setDisable(false);
            addMood_moodName.requestFocus();
        } else {
            // Check if entered time is has the correct formatting
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            try {
                Date ss = df.parse(addMood_moodTimeOccured.getText());
                //System.out.println(df.format(ss));
            } catch (ParseException e) {
                // Send error dialog if user entered invalid time fomrat
                // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
                if(!addMood_moodTimeOccured.getText().isEmpty()) {
                    Alerts.AlertGenerator.generateAlert(Alert.AlertType.ERROR, "Error", "Wrong Time Format", "Please try again using HH:mm format").showAndWait();
                    addMood_moodTimeOccured.clear();
                    addMood_addMood.setDisable(false);
                    addMood_moodName.requestFocus();
                }
            }
            // Add mood
            String moodName = addMood_moodName.getText();
            String moodDescription = addMood_moodDescription.getText();
            String moodSeverity = addMood_moodSeverity.getValue();
            String moodTimeOccured = addMood_moodTimeOccured.getText();
            addMood(moodName, moodDescription, moodSeverity, moodTimeOccured);
        }
    }
    
    /**
     * Null check of username and password field
     * @return True = username and/or password field are empty, False = username and password not empty
     */
    private boolean isNull() {
        //System.out.println(addMood_moodSeverity.getValue()); // Get combobox menu selected value
        return addMood_moodName.getText().isEmpty() || addMood_moodSeverity.getSelectionModel().isEmpty();
    }
    
    /**
     * Close current window 
     * @param event 
     */
    @FXML
    private void cancel(ActionEvent event) {
        // Close Add Mood Scene
        Stage temp = (Stage) addMood_moodName.getScene().getWindow();
        temp.close();
    }
    
    /**
     * Clears user entries
     */
    private void clearFields() {
        addMood_moodName.setText("");
        addMood_moodDescription.setText("");
        addMood_moodTimeOccured.setText("");
    }
    
}
