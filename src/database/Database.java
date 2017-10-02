/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;

/**
 * Takes care of Java DB conection/interaction
 * @author Youngmin
 */
public class Database {
    
    // jdbc information
    private static final String dbURL = "jdbc:derby://localhost:1527/foodmood";
    public static Connection conn = null;
    public static Statement stmt = null;
    
    /**
     * Static class for database CRUD operations
     */
    public static class DatabaseHandler {
        
        /**
         * This is the default constructor for the DatabaseHandler class
         * @throws java.lang.ClassNotFoundException
         */
        public DatabaseHandler() throws ClassNotFoundException {
            boolean connResult = establishConnection(dbURL);
            // Close program if db is not available
            if (!connResult) {
                System.out.println("Closing program due to database connection error");
                Platform.exit();
            }
        }
        
        /**
         * Returns database url
         * @return  dbURL
         */
        public String getDbUrl() {
            return dbURL;
        }
        
        /**
         * Returns current db connection 
         * @return conn
         */
        public Connection getConn() {
            return conn;
        }
        
        /**
         * Establishes a connection to the Java DB schema
         * Used example codes from https://db.apache.org/derby/integrate/plugin_help/derby_app.html
         * @param dbURL url of the database to connect
         * @return true = success, false = failure
         */
        public static boolean establishConnection(String dbURL) {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                // Establish connection
                conn = DriverManager.getConnection(dbURL);
                System.out.println("Connected to " + dbURL);
                return true;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return false;
            }
        }
        
        /**
         * Closes currenct connection to Java DB
         * @throws SQLException 
         */
        public static void closeConnection() throws SQLException {
            try {
                conn.close();
                stmt.close();
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
            }
            
        }
        
        /**
         * Check if given credentials match the database records
         * @param username user username
         * @param password user password
         * @return True = match, false = does not match/does not exist
         * @throws java.sql.SQLException
         */
        public static boolean authenticateUser(String username, String password) throws SQLException {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            String authStatement = "SELECT * FROM app.\"accounts\" WHERE USERNAME = \'" 
                    + username + "\' " + "AND PASSWORD = \'" + password + "\'";
            try {
                stmt = (Statement) conn.createStatement();
                System.out.println(authStatement);
                ResultSet result = stmt.executeQuery(authStatement);
                // Result is empty = account does not exist or password does not match
                // Result is not empty = username and password matches
                return result.next();
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return false;
            }
        }
        
        /**
         * Attepts to create a new account to the database
         * @param username
         * @param password
         * @return True = Success, False = Failure/Username already exists
         * @throws SQLException 
         */
        public static boolean createAccount(String username, String password) throws SQLException {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            String regStatement = "INSERT INTO app.\"accounts\" "
                    + "VALUES (" + "DEFAULT, " + "\'" + username + "\', " + "\'" + password + "\')";
            try {
                stmt = (Statement) conn.createStatement();
                System.out.println(regStatement);
                stmt.execute(regStatement);
                stmt.close();
                return true;
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return false;
            }
        }
    }

}
