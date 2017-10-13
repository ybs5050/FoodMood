/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class MoodController implements Initializable {
  
    private ArrayList<Mood> moodList = new ArrayList<>();
    private Mood mood = new Mood();
    @FXML
    private Button mood_addMood;
    @FXML
    private Button mood_deleteMood;
    @FXML
    private Button mood_goToMain;
    @FXML
    private TableView<Mood> mood_moodListTable;
    @FXML
    private Button mood_viewDetails;
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Mood List Window Initialized");
        // Used example codes from https://examples.javacodegeeks.com/desktop-java/javafx/tableview/javafx-tableview-example/
        TableColumn moodCol = new TableColumn("Mood");
        TableColumn severityCol = new TableColumn("Severity Level");
        TableColumn idCol = new TableColumn("Mood ID");
        // Restrcit table to only single selection mode
        mood_moodListTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        moodCol.setCellValueFactory(
                new PropertyValueFactory<Mood, String>("moodNameT")
        );
        severityCol.setCellValueFactory(
                new PropertyValueFactory<Mood, String>("moodSeverityT")
        );
        /*
        idCol.setCellValueFactory(
                new PropertyValueFactory<Mood, Integer>("moodIdT")
        );
        */
        mood_moodListTable.getColumns().setAll(moodCol, severityCol);
        //mood_moodListTable.getColumns().setAll(moodCol, severityCol, idCol);
        initializeMoodTable();
    }
    
    /**
     * 
     * @return the moodList
     */
    public ArrayList<Mood> getMoodList() {
        System.out.println("Get Mood List Action performed.");
        return moodList;
    }
    
    /**
     * Get mood list from the db and show it to the table
     */
    private void initializeMoodTable() {
        this.moodList.clear();
        this.moodList = database.Database.DatabaseHandler.getMoodList();
        ObservableList<Mood> data;
        // populate the able if not empty
        if(!moodList.isEmpty()) {
            data = FXCollections.observableArrayList(moodList);
            mood_moodListTable.setItems(data);
        }
    }
    
    /**
     * Shows AddMood Scene
     * @param event 
     */
    @FXML
    private void addMood(ActionEvent event) {
        try {
            // Loads FXML resources and create,display a FXML scene 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moodUI/AddMood.fxml"));
            Parent root = loader.load();
            Stage base = new Stage();
            base.setTitle("FoodMood - Add Mood");
            Scene main = new Scene(root);
            base.setScene(main);
            base.show();
            // Detect addmood scene is closing and refresh table
            base.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                initializeMoodTable();
            }
            });  
        } catch (IOException except) {
            System.out.println("Error occured: " + except.toString());
        }
    }
    
    /**
     * Deletes a selected mood
     * @param event 
     */
    @FXML
    private void deleteMood(ActionEvent event) {
        if (mood_moodListTable.getSelectionModel().isEmpty()) {
            mood_deleteMood.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a row and try again");
            alert.setContentText("Please select a row and try again");
            alert.showAndWait();
            mood_moodListTable.requestFocus();
            mood_deleteMood.setDisable(false);
        } else {
            mood_deleteMood.setDisable(true);
            Mood temp = mood_moodListTable.getSelectionModel().getSelectedItem();
            // Ask for confirmation
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Mood?");
            alert.setHeaderText("Delete Mood");
            alert.setContentText("Delete Mood " + temp.getMoodName() + "?");
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");
            alert.getButtonTypes().setAll(yes, no);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == yes) {
                // Delete mood
                mood_deleteMood.setDisable(false);
                boolean delResult = database.Database.DatabaseHandler.deleteMood(temp.getMoodId());
                if (delResult) {
                    // Send Welcome message
                    // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Delete Mood Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Mood Deleted");
                    alert.showAndWait();
                    // Reopen mood window
                    
                    // Close current stage
                    Stage tempWindow = (Stage) mood_addMood.getScene().getWindow();
                    tempWindow.close();
                    System.out.println("View Food Mood List event occured");
                    try {
                        // Loads FXML resources and create,display a FXML scene 
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userdata/Mood.fxml"));
                        Parent root = loader.load();
                        Stage base = new Stage();
                        base.setTitle("FoodMood - Mood List");
                        Scene main = new Scene(root);
                        base.setScene(main);
                        base.show();
                    } catch (IOException except) {
                        System.out.println("Error occured: " + except.toString());
                    }
                    
                } else {
                    // Send error message if failed
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to delete Mood");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                }
                mood_deleteMood.setDisable(false);
            } else {
                // Don't delete mood
                mood_deleteMood.setDisable(false);
            }
        }
    }
    
    /**
     * Go back to main menu
     * @param event 
     */
    @FXML
    private void goToMainMenu(ActionEvent event) {
        // Close Add Mood Scene
        Stage temp = (Stage) mood_addMood.getScene().getWindow();
        temp.close();
    }

    /**
     * Show details of selected row
     * @param event 
     */
    @FXML
    private void viewMoodDetails(ActionEvent event) {
        if (mood_moodListTable.getSelectionModel().isEmpty()) {
            mood_viewDetails.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a row and try again");
            alert.setContentText("Please select a row and try again");
            alert.showAndWait();
            mood_moodListTable.requestFocus();
            mood_viewDetails.setDisable(false);
        } else {
            // Show mood details
            mood_viewDetails.setDisable(true);
            Mood temp = mood_moodListTable.getSelectionModel().getSelectedItem();
            int moodId = temp.getMoodId();
            String moodName = temp.getMoodName();
            String moodDescription = temp.getMoodDescription() == null ? "N/A" : temp.getMoodDescription();
            String moodSeverity = temp.getMoodSeverity();
            String moodTimeOccured = temp.getMoodTimeOccured() == null ? "N/A" : temp.getMoodTimeOccured();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Food Mood Details");
            alert.setHeaderText(null);
            alert.setContentText("Mood Name: " + moodName + "\n"
                    + "Mood Description: " + moodDescription + "\n"
                    + "Mood Severity: " + moodSeverity + "\n"
                    + "Mood Time: " + moodTimeOccured + "\n"
                    + "Mood Id: " + moodId);
            alert.showAndWait();
            mood_moodListTable.requestFocus();
            mood_viewDetails.setDisable(false);
        }
    }

    
    
}
