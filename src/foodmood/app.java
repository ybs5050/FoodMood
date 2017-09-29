package foodmood;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class app extends Application{

    private static Stage base;
    private static Scene login;
    private static LoginController loginCntl;
    
    
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
        loginCntl = loader.getController();
        base.setTitle("FoodMood - Login");
        login = new Scene(root);
        base.setScene(getLogin());
        base.show();
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
