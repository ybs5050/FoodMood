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
import java.text.ParseException;
import java.util.ArrayList;
import javafx.application.Platform;
import userdata.FoodMood;
import userdata.Mood;

/**
 * Takes care of Java DB conection/interaction
 * @author Youngmin
 */
public class Database {
    
    // jdbc information
    private static final String dbURL = "jdbc:derby://localhost:1527/foodmood";
    public static Connection conn = null;
    public static Statement stmt = null;
    
    // User information after logging in 
    public static String username = null;
    public static int userId = Integer.MAX_VALUE;
    
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
            conn = null;
            stmt = null;
        }
        
        /**
         * Check if given credentials match the database records
         * @param username username username
         * @param password username password
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
                // Result is empty (false) = account does not exist or password does not match
                // Result is not empty (true) = username and password matches
                if (result.next()) {
                    //System.out.println(result.getInt(1) + " " + result.getString(2));
                    // getString(2) = username, getString(1) = ID
                    Database.username = result.getString(2);
                    userId = result.getInt(1);
                    return true;
                } 
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
                return authenticateUser(username, password);
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return false;
            }
        }
       
        
        /**
         * Attepts to create a mood to the database
         * @param moodName
         * @param moodDescription
         * @param moodSeverity
         * @param moodTimeOccured
         * @return True = Success, False = Failture/illegal formatting
         */
        public static boolean createMood(String moodName, String moodDescription, String moodSeverity, String moodTimeOccured) {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            // mood table insert order [ID, USERID, MOOD, SEVERITY, DESCRIPTION, TIME]
            // NULL ALLOWED = DESCRIPTION, TIME
            
            String regStatement = "INSERT INTO app.\"mood\" "
                    + "VALUES (" + "DEFAULT, " + userId + ", " + "\'" + moodName + "\', "
                    + "\'" + moodSeverity + "\')";
            if(moodDescription.length() > 0) {
                regStatement =  regStatement.substring(0, regStatement.length()-1) + ", \'" + moodDescription + "\')";
            } else {
                regStatement =  regStatement.substring(0, regStatement.length()-1) + ", NULL)";
            }
            if (moodTimeOccured.length() > 0) {
                regStatement = regStatement.substring(0, regStatement.length()-1) + ", \'" + moodTimeOccured + "\')";
            } else {
                regStatement =  regStatement.substring(0, regStatement.length()-1) + ", NULL)";
            }
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
        
        /**
         * Attepts to delete a mood based in the given mood Id
         * @param moodId
         * @return True = success, False = failure
         */
        public static boolean deleteMood(int moodId) {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            // mood table insert order [ID, USERID, MOOD, SEVERITY, DESCRIPTION, TIME]
            // NULL ALLOWED = DESCRIPTION, TIME
            String regStatement = "DELETE FROM app.\"mood\" WHERE ID = " + Integer.toString(moodId);
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
        
        /**
         * Attepts to delete a food mood based in the given food mood Id
         * @param foodMoodId
         * @return True = success, False = failure
         */
        public static boolean deleteFoodMood(int foodMoodId) {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            String regStatement = "DELETE FROM app.\"foodmood\" WHERE ID = " + Integer.toString(foodMoodId);
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
        
        /**
         * Attempts to get all the moods that corresponds to the current user from the database
         * @return ArrayList<Mood> list of moods
         */
        public static ArrayList<Mood> getMoodList() {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            String regStatement = "SELECT * FROM app.\"mood\" WHERE USERID = " + Integer.toString(userId);
            ArrayList<Mood> moodList = new ArrayList<>();
            try {
                stmt = (Statement) conn.createStatement();
                System.out.println(regStatement);
                ResultSet result = stmt.executeQuery(regStatement);
                while(result.next()) {
                    int moodId = result.getInt(1);
                    String moodName = result.getString(3);
                    String moodSeverity = result.getString(4);
                    String moodDescription = result.getString(5);
                    String moodTimeOccured = result.getString(6);
                    //System.out.println(moodId + " " + moodName  + " " + moodSeverity + " " + moodDescription + " " + moodTimeOccured);
                    moodList.add(new Mood(moodId, moodName, moodSeverity, moodDescription, moodTimeOccured));
                }
                //System.out.println(moodList.size());
                stmt.close();
                result.close();   
                return moodList;
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return null;
            }
        }
        
        /**
         * Attempts to get all the foodmoods that corresponds to the current user from the database
         * @return 
         * @throws java.text.ParseException 
         */
        public static ArrayList<FoodMood> getFoodMoodList() throws ParseException {
            // Used example codes from https://netbeans.org/kb/docs/ide/java-db.html?print=yes
            String regStatement = "SELECT * FROM app.\"foodmood\" WHERE USERID = " + Integer.toString(userId);
            ArrayList<FoodMood> foodMoodList = new ArrayList<>();
            try {
                stmt = (Statement) conn.createStatement();
                System.out.println(regStatement);
                ResultSet result = stmt.executeQuery(regStatement);
                while(result.next()) {
                    String foodName = result.getString(3);
                    String foodMoodDescription = result.getString(4);
                    String foodMoodDate = result.getString(5);
                    boolean isFavorite = result.getBoolean(6);
                    int foodMoodID = result.getInt(1);
                    foodMoodList.add(new FoodMood(foodName, foodMoodDescription, foodMoodDate, isFavorite, foodMoodID));
                }
                stmt.close();
                result.close();
                return foodMoodList;
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return null;
            }
        }
        
        /**
         * Attempts add a food mood to the database
         * @param foodName
         * @param foodMoodDescription
         * @param foodMoodDate
         * @param favorite
         * @return 
         */
        public static boolean addFoodMood(String foodName, String foodMoodDescription, String foodMoodDate, boolean favorite) {
            String regStatement = "INSERT INTO app.\"foodmood\" "
                    + "VALUES (" + "DEFAULT, " + userId + ", " + "\'" + foodName + "\', "
                    + "\'" + foodMoodDescription + "\', " + "\'"+ foodMoodDate + "\', "  + favorite  + ")";
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
        
        /**
         * Attempt to update food mood to have 'ISFAVORITE' column to 'true'
         * @param favorite
         * @param foodMoodID
         * @return true = success, false = failure
         * @throws java.sql.SQLException
         */
        public static boolean setFavorites(boolean favorite, int foodMoodID) throws SQLException {
            stmt = conn.createStatement();
            String regStatement = "UPDATE " + "APP.\"foodmood\" " +
                    "SET ISFAVORITE = " + favorite + " WHERE ID = " + foodMoodID;
            System.out.println(regStatement);
            try {
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
