package testHarness;

import java.awt.event.ActionEvent;
import java.util.EventListener;
import login.LoginController;

/**
 *
 * @author 
 */
public class TestHarness {

    private LoginController lc;
    
    public static void main(String[] args) {
        
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
