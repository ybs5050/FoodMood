/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

/**
 *
 * @author Youngmin Son
 */
public class Alerts {
    
    public static class AlertGenerator {
        
        /**
         * Generate Alert object and returns it to the method caller
         * @param alertType AlertType INFORMATION, WARNING, ERROR, ETC
         * @param alertTitle
         * @param headerText
         * @param contentText
         * @return 
         */
        public static Alert generateAlert(AlertType alertType, String alertTitle, String headerText, String contentText) {
            // Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
            // Refactoring implementation
            Alert alert = new Alert(alertType);
            alert.setTitle(alertTitle);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            return alert;
        }
        
    }
    
}
