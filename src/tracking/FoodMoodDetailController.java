package tracking;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class FoodMoodDetailController implements Initializable {

    @FXML
    private TextArea foodMoodDetail_foodMoodDescription;
    @FXML
    private Text foodMoodDetail_foodName;
    @FXML
    private Text foodMoodDetail_foodMoodID;
    @FXML
    private Text foodMoodDetail_foodMoodDate;
    @FXML
    private Text foodMoodDetail_isFavorite;
    @FXML
    private Button foodMoodDetail_goToMain;
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("FoodMoodDetail Window Initialized"); 
    }
    
    /**
     * Set values of text labels for FoodMoodDetail scene
     * @param foodMoodID 
     * @param foodName
     * @param foodMoodDescription
     * @param foodMoodDate
     * @param isFavorite 
     */
    public void setValues(int foodMoodID, String foodName, 
            String foodMoodDescription, String foodMoodDate, boolean isFavorite) {
        foodMoodDetail_foodMoodID.setText(Integer.toString(foodMoodID));
        foodMoodDetail_foodName.setText(foodName);
        foodMoodDetail_foodMoodDate.setText(foodMoodDate);
        foodMoodDetail_foodMoodDescription.setText(foodMoodDescription);
        if(isFavorite) {
            foodMoodDetail_isFavorite.setText("YES");
        } else {
            foodMoodDetail_isFavorite.setText("NO");
        }
        
    }
    
    /**
     * Clears the food mood text labels and text areas of FoodMoodDetail scene
     */
    public void clearFields() {
        foodMoodDetail_foodMoodDescription.setText("");
        foodMoodDetail_foodName.setText("");
        foodMoodDetail_foodMoodID.setText("");
        foodMoodDetail_foodMoodDate.setText("");
        foodMoodDetail_isFavorite.setText("");
    }
    
    /**
     * Closes Food Mood Detail Scene
     * @param event foodMoodDetail_goToMain button action
     */
    @FXML
    private void goToMainMenu(ActionEvent event) {
        // Close Food Mood Detail Scene
        Stage temp = (Stage) foodMoodDetail_foodMoodDescription.getScene().getWindow();
        temp.close();
    }

}
