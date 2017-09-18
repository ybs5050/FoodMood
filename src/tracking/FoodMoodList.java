package tracking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import userdata.FoodMood;
       
/**
 * FoodMoodList class
 * @author 
 */
public class FoodMoodList {
    
    private ArrayList<FoodMood> foodMoodList;
    
    /**
     * This is the default constructor for the FoodMoodList class
     */
    public FoodMoodList() {
        
    }
    
    /**
     * Adds a FoodMood object to foodMoodList 
     * @param newFoodMood 
     */
    public void addFoodMoodToList(FoodMood newFoodMood) {
        this.foodMoodList.add(newFoodMood);
    }
    
    /**
     * Removes a specified FoodMood object from foodMoodList
     * @param targetFoodMood 
     */
    public void removeFoodMood(FoodMood targetFoodMood) {
        
    }
    
    /**
     * Resets the foodMoodList arraylist object
     */
    public void reset() {
        this.foodMoodList = new ArrayList<>();
    }
    
}
