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
     * Set values of text labels
     * @param foodMoodID
     * @param userId
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
     * Resets the food mood detail user interface 
     */
    public void reset() {
        System.out.println("Resetting Window");
    }
    
    /**
     * Closes Food Mood Detail Scene
     * @param event 
     */
    @FXML
    private void goToMainMenu(ActionEvent event) {
        // Close Food Mood Detail Scene
        Stage temp = (Stage) foodMoodDetail_foodMoodDescription.getScene().getWindow();
        temp.close();
    }

    
  
}
