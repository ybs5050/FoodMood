/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class FoodMoodController implements Initializable {
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("FoodMood Window Initialized"); 
    }
    
    /**
     * Creates and returns a food mood object
     * @param foodName
     * @param description
     * @return A FoodMood object
     */
    public FoodMood addFoodMood(String foodName, String description) {
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

}
