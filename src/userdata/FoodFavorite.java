/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jdgra_000
 */
public class FoodFavorite implements Initializable {

    private ArrayList<String> foodList = new ArrayList<>();
  
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void addFavoriteFood(String favFood){
      foodList.add(favFood);
      System.out.println("Add Food Type successful");
    }
    
    /**
    * @return the foodList
    */
    public ArrayList<String> getFoodList() {
      System.out.println("Get Favorite Food List successful");
      return foodList;
    }

  public void removeFavoriteFood(int index) {
    foodList.remove(index);
    System.out.println("Remove Food Type successful");
  }
}
