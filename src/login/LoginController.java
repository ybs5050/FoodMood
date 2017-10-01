/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
//import navigation.MainMenuController; //TODO: Update handleLogin, then DELETE

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
     * @param password Login password
     */
    public void login(String userName, String password) {
      if(validate(password)){
        System.out.println("Login successful: UserName=" + userName + 
            " Password=" + password);
      }  
      else{
        System.out.println("Failed Login Attempt: UserName=" + userName + 
            " Password=" + password);
      }
    }
    
    /**
     * Validates text fields
     * @param textField
     * @return false if given text is not empty, true if given text is empty
     */
    public boolean validate(String textField) {
      //test data and output 
      String testPass = "password";
      System.out.println("Password Validated: " + (textField.compareTo(testPass) == 0));
      return textField.compareTo(testPass) == 0;
    }
    
    /**
     * Authenticate the user and show main menu UI
     * @param event
     * @throws IOException 
     */
    //
    private void handleLogin(ActionEvent event) throws IOException {
        //app.getLogin().setRoot(FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
        //TODO: Replace test code below
        System.out.println("Login Button Pressed");
    }
}
