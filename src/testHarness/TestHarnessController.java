package testHarness;

import login.LoginController;
import tracking.FoodMoodList;

/**
 *
 * @author jamie <jxb488@psu.edu>
 * Created on Sep 30, 2017, 10:15:37 AM
 */
public class TestHarnessController {
    private LoginController lc;
    private FoodMoodList fml;
    
    TestHarnessController() {
        getLogin(); 
}
    public void getLogin(){
        lc = new LoginController();
        String userName = "testuser";
        String password = "password";
        lc.login(userName, password);
        viewFML();
    }
    
    public void viewFML(){

        
    }
    
    public void addFM(){
        
    }
}