/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import database.Database;
import java.sql.SQLException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
//import navigation.MainMenuController; //TODO: Update handleLogin, then DELETE

/**
 * FXML Controller Class
 * @author jdgra_000
 */
public class LoginController implements Initializable{

    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_password;
    @FXML
    private Button login_loginButton;
    @FXML
    private Button login_registerButton;
    @FXML
    private Button login_exitButton;
    
    /**
     * Initializes the controller class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Login Window Initialized");
    }
    
    /**
     * initiate login sequence to the app
     * @param username Login username
     * @param password Login password
     */
    public void login(String username, String password) {
      if (validate(username, password)) {
        // Send Welcome message
        // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Success");
        alert.setHeaderText(null);
        alert.setContentText("Welcome, " + username);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showMainMenu();
        } 
        
        // Close login stage and show main menu stage
      } else {
        // Send error message and clear the username and password text field
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong username or password");
        alert.setContentText("Please check the username and password");
        alert.showAndWait();
        login_loginButton.setDisable(false);
        clearFields();
        login_username.requestFocus();
      }
    }
    
    /**
     * Creates a new account and initiate login sequence to the app
     * @param username New account username
     * @param password New account password
     */
    private void register(String username, String password) {
        try {
            if (Database.DatabaseHandler.createAccount(username, password)) {
                // Send Welcome message
                // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Registration Success");
                alert.setHeaderText(null);
                alert.setContentText("Account Created \nWelcome, " + username);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    showMainMenu();
                } 
            } else {
                // Send error dialog if username already exists/failed to connect to db
                // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("The username already exists");
                alert.setContentText("Please try again with a different username");
                alert.showAndWait();
                login_loginButton.setDisable(false);
                login_username.requestFocus();
                clearFields();
            }
        } catch (SQLException except) {
            System.out.println("Error occured: " + except.toString());
        }
    }
    
    
    
    /**
     * Validates username and password
     * @param username
     * @param password
     * @return false if given text is not empty, true if given text is empty
     */
    private boolean validate(String username, String password) {
        try {
            return Database.DatabaseHandler.authenticateUser(username, password);
        } catch (SQLException except) {
            System.out.println("Error occured: " + except.toString());
            return false;
        }
    }
    
    /**
     * Authenticate the user and show main menu UI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        //app.getLogin().setRoot(FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
        
        // Disable the login button while attempting to authenticate the user
        login_loginButton.setDisable(true);
        if (isNull()) {
            // Send error dialog if either textfields are empty
            // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty username or password");
            alert.setContentText("Please check the username and password");
            alert.showAndWait();
            login_loginButton.setDisable(false);
            login_username.requestFocus();
        } else {
            // Attempt to authenticate
            login(login_username.getText(), login_password.getText());
        }
    }
    
    /**
     * Register a new account
     * @param event 
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        if(isNull()) {
            // Send error dialog if either textfields are empty
            // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty username or password");
            alert.setContentText("Please enter a username and password to create a new account");
            alert.showAndWait();
            login_loginButton.setDisable(false);
            login_username.requestFocus();
            clearFields(); 
        } else {
            // Attempt to create an account
            register(login_username.getText(), login_password.getText());
        }
    }
    
    /**
     * Proceed to main menu stage
     */
    private void showMainMenu() {
        // Close Login Stage
        Stage temp = (Stage) login_username.getScene().getWindow();
        temp.close();
        try {
            // Loads FXML resources and create,display a FXML scene 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/navigation/MainMenu.fxml"));
            Parent root = loader.load();
            Stage base = new Stage();
            base.setTitle("FoodMood - Main Menu");
            Scene main = new Scene(root);
            base.setScene(main);
            base.show();
        } catch (IOException except) {
            System.out.println("Error occured: " + except.toString());
        }
    }
    
    /**
     * Shuts down the program
     * @param event 
     */
    @FXML
    private void exitProgram(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Clears the two textfields 
     */
    private void clearFields() {
        login_username.setText("");
        login_password.setText("");
    }
    
    /**
     * Null check of username and password field
     * @return True = username and/or password field are empty, False = username and password not empty
     */
    private boolean isNull() {
        return login_username.getText().isEmpty() || login_password.getText().isEmpty();
    }

}
