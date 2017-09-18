/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

/**
 *
 * @author jdgra_000
 */
public class Mood {
  
    private String moodName;
    private String moodDescription;
    
    /**
     * This is the default constructor for the FoodMood Class
     */ 
    public Mood() {
        
    }
    
    /**
     * Returns the object's member moodDescription
     * @return String moodDescription
     */
    public String getMoodDescription() {
        return this.moodDescription;
    }
    
    /**
     * Returns the object's member moodName
     * @return String moodName
     */
    public String getMoodName() {
        return this.moodName;
    }
    
    /**
     * Sets a new value for member String moodDescription 
     * @param newDescription 
     */
    public void setMoodDescription(String newDescription) {
        this.moodDescription = newDescription;
    }
    
    /**
     * Sets a new value for member String moodName 
     * @param newName 
     */
    public void setMoodName(String newName) {
        this.moodName = newName;
    }
    
}
