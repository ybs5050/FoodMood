package testharness;

import java.awt.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.LoginController;
import navigation.MainMenuController;
import tracking.FoodMoodListController;
import userdata.FoodFavorite;
import userdata.MoodController;

/**
 *
 * @author
 */
public class TestHarnessController {

  private LoginController lc;
  private MainMenuController mmc;
  private FoodMoodListController fmlc;
  private FoodFavorite ff;
  private MoodController mc;

  TestHarnessController() {
    lc = new LoginController();
    mmc = new MainMenuController();
    fmlc = new FoodMoodListController();
    ff = new FoodFavorite();
    mc = new MoodController();
    getLogin(); //pass control to login API
  }
  
  //test for successful call to login and pass control to main menu
  public void getLogin() {
    String userName = "testuser"; //test value for userName
    String password = "password"; //test value for password
    lc.login(userName, password); //pass test values in as parameters

    //Call main menu controller. Once button objects and event handlers are 
    //coded, this call will take place via the login button assuming that 
    //credentials are validated
    mainMenuTest();
  }

  //Depending on user selection, one of the following use cases will execute
  public void mainMenuTest() {
    viewFML();//view FoodMood List and test functionality of list/detail actions
    addMood();//add a mood only
    addFM();//add a FoodMood entry to the FoodMood List
    updateFF(); //add or remove favorite foods from list
  }

  public void viewFML() {
    System.out.println("USE CASE 1");
    //assign test values for testing view, print, and remove functionality
    String food = "burgers";
    String mood = "sad";
    String[] foodMood = {food, mood};
    ArrayList<String[]> foodMoodList = new ArrayList<>();
    foodMoodList.add(foodMood);
    int index = 0; //the index for foodMoodList
    
    fmlc.printList(foodMoodList); //print food list
    fmlc.removeFoodMood(index); //remove foodMood from list
    fmlc.sortFoodMoodList(foodMoodList);
    
    //If user selects the option, view detailed item from list
    System.out.println("USE CASE 2");
    try {
      fmlc.viewFoodMood(); //calls FoodMoodDetailController, executes new use case
    } catch (InstantiationException ex) {
      Logger.getLogger(TestHarnessController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(TestHarnessController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void addMood() {
    System.out.println("USE CASE 3"); //part of Use Case 3 but has own menu option
    //test values
    String moodName = "happy";
    String moodDescription = "description";
    mc.addMood(moodName, moodDescription);
  }

  public void addFM() {
   //assign test values, remainder of Use Case 3, has its own menu choice
    String food = "pizza";
    String mood = "happy";
    String[] foodMood = {food, mood};
    ArrayList<String[]> foodMoodList = new ArrayList<>();
    int index = 0; //the index for foodMoodList
    //test actions for use case
    fmlc.addFoodMood(foodMood);
    fmlc.removeFoodMood(index);
    fmlc.saveFoodMood();
    fmlc.sortFoodMoodList(foodMoodList);
  }

  public void updateFF() {
    System.out.println("USE CASE 4");
    //test values
    String favFood = "beef";
    int index = 0;
    //test actions for use case
    ff.getFoodList();
    ff.addFavoriteFood(favFood);
    ff.removeFavoriteFood(index);
  }
}
