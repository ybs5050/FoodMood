package testHarness;

import login.LoginController;
import tracking.FoodMoodList;
import navigation.MainMenuController;

/**
 *
 * @author jamie <jxb488@psu.edu>
 * Created on Sep 30, 2017, 10:15:37 AM
 */
public class TestHarnessController {
    private LoginController lc;
    private MainMenuController mmc;
    
    TestHarnessController() {
        lc = new LoginController();
        mmc = new MainMenuController();
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
        
    }
}