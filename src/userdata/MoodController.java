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
 * FXML Controller Class
 * @author jdgra_000
 */
public class MoodController implements Initializable {
  
    private ArrayList<Mood> moodList = new ArrayList<>();
    private Mood mood = new Mood();
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Creates a new mood object
     * @param moodName
     * @param moodDescription 
     */
    public void addMood(String moodName, String moodDescription) {
      //String[] newMoodItem = {moodName, moodDescription};
      //moodList.add(newMoodItem);
      System.out.println("Add Mood action performed");
    }

  /**
   * @return the moodList
   */
  public ArrayList<Mood> getMoodList() {
    System.out.println("Get Mood List Action performed.");
    return moodList;
  }
    
}
