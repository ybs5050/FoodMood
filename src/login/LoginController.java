/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class LoginController implements Initializable{
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login Window Initialized");
    }
    
    /**
     * initiate login sequence to the app
     * @param userName Login username
     * @param passWord Login password
     */
    public static void login(String userName, String passWord) {
        System.out.println("Login success: " + userName + " " + passWord);
    }
    
    /**
     * Validates text fields
     * @param textField
     * @return false if given text is not empty, true if given text is empty
     */
    public boolean vaildate(String textField) {
        return textField.trim().length() == 0;
    }

    
  
}
