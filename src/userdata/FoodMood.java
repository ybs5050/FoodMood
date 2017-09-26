package userdata;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class FoodMood {
    
    private int foodMoodID;
    private String foodName;
    
    /**
     * This is the default constructor for the FoodMood Class
     * @param foodName Sets a food mood name of the FoodMood object
     */ 
    public FoodMood(String foodName) {
        this.foodName = foodName;
    }
    
    /**
     * Returns the Food Mood ID for this object
     * @return A int representing the Food Mood ID for the food mood object
     */
    public int getFoodMoodID() {
        return this.foodMoodID;
    }
    
    /**
     * Sets a new value for food mood's name
     * @param newFoodName Sets a new name for the food mood object
     */
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }
    
    /**
     * Returns the object's food name
     * @return A string represending t he name for the food mood object
     */
    public String getFoodName() {
        return this.foodName;
    }
    
    
}
