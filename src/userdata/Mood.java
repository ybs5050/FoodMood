/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userdata;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jdgra_000
 */
public class Mood {
  
    private int moodId;
    private String moodName;
    private String moodDescription;
    private String moodTimeOccured;
    private String moodSeverity;
    private final String[] moodSeverityLevels = {"", "None", "Minimal", "Moderate", "High", "Severe"};
   
    // Java Table properties
    public SimpleStringProperty moodNameT = new SimpleStringProperty();
    public SimpleStringProperty moodSeverityT = new SimpleStringProperty();
    public SimpleIntegerProperty moodIdT = new SimpleIntegerProperty();
    
    /**
     * This is the default constructor for the FoodMood Class
     * @param moodName
     * @param moodDescription
     */ 
    public Mood(String moodName, String moodDescription) {
        this.moodName = moodName;
        this.moodDescription = moodDescription;
    }
    
    // second constructor
    public Mood(int moodId, String moodName, String moodSeverity, String moodDescription, String moodTimeOccured) {
        this.moodId = moodId;
        this.moodIdT = new SimpleIntegerProperty(moodId);
        this.moodName = moodName;
        this.moodNameT = new SimpleStringProperty(moodName);
        this.moodSeverity = moodSeverity;
        this.moodSeverityT = new SimpleStringProperty(moodSeverity);
        this.moodDescription = moodDescription;
        this.moodTimeOccured = moodTimeOccured;
    }
    //default constructor
    public Mood(){
        this("", "");
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
     * Returns mood Id
     * @return 
     */
    public int getMoodId() {
        return this.moodId;
    }
    
    /**
     * Returns mood time
     * @return 
     */
    public String getMoodTimeOccured() {
        return this.moodTimeOccured;
    }
    
    /**
     * Returns mood severity
     * @return 
     */
    public String getMoodSeverity() {
        return this.moodSeverity;
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
    
    /**
     * Return mood name for table view
     * @return moodNameT
     */
    public String getMoodNameT() {
        return moodNameT.get();
    }
    
    /**
     * Return mood severity for table view
     * @return moodSeverityT
     */
    public String getMoodSeverityT() {
        return moodSeverityT.get();
    }
    
    /**
     * Return mood ID for table view
     * @return moodSeverityT
     */
    public int getMoodIdT() {
        return moodIdT.get();
    }
    
    
}
