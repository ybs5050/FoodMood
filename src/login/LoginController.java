/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import foodmood.app;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void login(String userName, String passWord) {
        System.out.println(userName + " " + passWord);
    }
    
    /**
     * Validates text fields
     * @param textField
     * @return false if given text is not empty, true if given text is empty
     */
    public boolean vaildate(String textField) {
        return textField.trim().length() == 0;
    }
    
    /**
     * Authenticate the user and show main menu UI
     * @param event
     * @throws IOException 
     */
    private void handleLogin(ActionEvent event) throws IOException {
        app.getLogin().setRoot(FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
    }
}
