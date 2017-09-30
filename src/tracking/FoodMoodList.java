package tracking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedHashMap;
import userdata.FoodMood;
import userdata.Mood;
       
/**
 * FoodMoodList class
 * @author 
 */
public class FoodMoodList {
    
    private LinkedHashMap<FoodMood, Mood> foodMoodList;
    
    /**
     * This is the default constructor for the FoodMoodList class
     */
    public FoodMoodList() {
        this.foodMoodList = new LinkedHashMap<>();
    }
    
    /**
     * Adds a FoodMood object to foodMoodList 
     * @param newFoodMood 
     * @param newMood 
     */
    public void addFoodMoodToList(FoodMood newFoodMood, Mood newMood) {
        this.foodMoodList.put(newFoodMood, newMood);
        System.out.println("FoodMood has been added!");
    }
    
    /**
     * Removes a specified FoodMood object from foodMoodList
     * @param targetFoodMood 
     */
    public void removeFoodMood(FoodMood targetFoodMood) {
        if(foodMoodList.containsKey(targetFoodMood)) {
            this.foodMoodList.remove(targetFoodMood);
            System.out.println("Selected FoodMood Has Been Deleted");
        } else {
            System.out.println("Selected FoodMood Does Not Exist");
        }
    }
    
    /**
     * Resets the foodMoodList arraylist object
     */
    public void reset() {
        this.foodMoodList = new LinkedHashMap<>();
    }
    
}
