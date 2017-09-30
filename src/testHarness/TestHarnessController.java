package testHarness;

import login.LoginController;
import navigation.MainMenuController;
import tracking.FoodMoodListController;

/**
 *
 * @author jamie <jxb488@psu.edu>
 * Created on Sep 30, 2017, 10:15:37 AM
 */
public class TestHarnessController {
    private LoginController lc;
    private MainMenuController mmc;
    private FoodMoodListController fmlc;
    
    TestHarnessController() {
        lc = new LoginController();
        mmc = new MainMenuController();
        fmlc = new FoodMoodListController();
        getLogin(); 
}
    public void getLogin(){

        String userName = "testuser";
        String password = "password";
        lc.login(userName, password);
        viewFML();
    }
    
    public void viewFML(){
        mmc.viewFoodMoodList();
        addFM();
    }
    
    public void addFM(){
        fmlc.addFoodMood();
        fmlc.removeFoodMood();
        fmlc.saveFoodMood();
        fmlc.sortFoodMoodList();
        fmlc.viewFoodMood();
    }
}