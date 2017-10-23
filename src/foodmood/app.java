package foodmood;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.Database;
import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class app extends Application {

    private static Stage base;
    private static Scene login;
    
    
    /**
     * Stage method for Main Menu 
     * @param primaryStage None for now
     * @throws java.lang.Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Loads FXML resources and create,display a FXML scene 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/Login.fxml"));
        Parent root = loader.load();
        base = primaryStage;
        base.setTitle("FoodMood - Login");
        login = new Scene(root);
        base.setScene(getLogin());
        base.show();
        // Decorator Implementation
        base.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    // Close DB Connection 
                    System.out.println("Closing DB connection..");
                    database.Database.DatabaseHandler.closeConnection();
                } catch (SQLException except) {
                    System.out.println("Error occured: " + except.toString());
                }
            }
        });  
        // Establish a connection to the Java DB schema
        Database.DatabaseHandler db = new Database.DatabaseHandler();
    }
    
    /**
     * Main method for the execution of this application
     * @param args None for normal use
     */
    public static void main(String[] args) {
        launch();
    }
    
   /**
   * @return the login
   */
    public static Scene getLogin() {
        return login;
    }
    
}
