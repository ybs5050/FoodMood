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
import java.util.ArrayList;
import javafx.application.Platform;
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
            try {
                conn.close();
                stmt.close();
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
            }
            
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
         * @return True = succss, False = failure
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
         * Attepts to get all the moods that corresponds to the current user in the database
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
                /*
                moodList.forEach((_item) -> {
                    System.out.println(_item.getMoodDescription());
                });
                */
                
                return moodList;
            } catch (SQLException except) {
                System.out.println("Error occured: " + except.toString());
                return null;
            }
        }
    }
    
        

}
