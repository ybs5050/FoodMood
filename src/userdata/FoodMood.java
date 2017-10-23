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
    private String foodMoodDescription;
    private String foodMoodDate;
    private boolean isFavorite;
    
    /**
     * This is the default constructor for the FoodMood Class
     * @param foodName Sets a food mood name of the FoodMood object
     */ 
    public FoodMood(String foodName) {
        this.foodName = foodName;
    }
    
    /**
     * This is the second constructor for the FoodMood Class
     * @param foodName
     * @param foodMoodDescription
     * @param foodMoodDate
     * @param isFavorite
     * @param foodMoodID 
     */
    public FoodMood(String foodName, String foodMoodDescription, String foodMoodDate, boolean isFavorite, int foodMoodID) {
        this.foodName = foodName;
        this.foodMoodDescription = foodMoodDescription;
        this.foodMoodDate = foodMoodDate;
        this.isFavorite = isFavorite;
        this.foodMoodID = foodMoodID;
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
     * @return foodname
     */
    public String getFoodName() {
        return this.foodName;
    }
    
    /**
     * Returns the object's food mood description
     * @return 
     */
    public String getFoodMoodDescription() {
        return this.foodMoodDescription;
    }
    
    /**
     * Returns the object's food date
     * @return foodMoodDate
     */
    public String getFoodMoodDate() {
        return this.foodMoodDate;
    }
    
    /**
     * Returns the object's string value of isFavorite
     * @return 
     */
    public String getIsFavorite() {
        if (isFavorite) {
            int starSymbol = 9733; 
            return Character.toString((char) starSymbol);
        } else {
            return "X";
        }
    }
    
    /**
     * Returns the object's boolean value of isFavorite
     * @return 
     */
    boolean getIsFavoriteBoolean() {
        return this.isFavorite;
    }
    
    /**
     * Returns the object's summary
     * @return string
     */
    public String getFoodMoodSummary() {
        String summary = "";
        summary += "Food Name: " + this.foodName + "\n";
        summary += "Food Mood Description: " + this.foodMoodDescription + "\n";
        summary += "Food Mood Date: " + this.foodMoodDate + "\n";
        summary += "Food Mood Favorite: " + this.isFavorite + "\n";
        summary += "Food Mood ID: " + this.foodMoodID + "\n";
        return summary;
    }
    
}
