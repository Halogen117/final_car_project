package com.mycompany.finalcarvroom;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
//import org.json.JSONArray;
//import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author
 */
public class carparkAPI {

    private static final String urlDB = "jdbc:postgresql://localhost/user_storage";
    private static final String user = "postgres";
    // private static final String password = "admin";
    private static final String password = "Pass1234";

    public static void main(String[] args) {

        Connection connectDB = null;

        //Datab db = new Datab();
        //manipulateDb manDb = new manipulateDb();
        //db.Conn();  //Connect


        boolean run = true;
        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
            //connectDB = DriverManager.getConnection(urlDB, user, password);
            //System.out.println("Connected to PostgreSQL server");
            CreateDB table = new CreateDB();
            // COMMENT OUT THIS LINE IF YOU ALREADY HAVE A TABLE
            //db.createDatabase(connectDB);
            //db.createFavDb(connectDB);
            //db.createHistoryDb(connectDB);
            //db.createUserDb(connectDB);
            //table.createHistoryDb();
            //table.createCarparkDb();
            //table.createFavDb();
            //table.createUserDb();

            String[] createAcc = new String[11];

            createAcc[0] = "user1";
            createAcc[1] = "Evan";
            createAcc[2] = "evanseah123@gmail.com";
            createAcc[3] = "qwerty123";
            createAcc[4] = "81257183";
            createAcc[5] = "Pet name?";
            createAcc[6] = "Favourite food?";
            createAcc[7] = "Favourite place?";
            createAcc[8] = "Fluffy";
            createAcc[9] = "Pizza";
            createAcc[10] = "Singapore";

            ManipulateDB manip = new ManipulateDB();

            manip.insertUserDb(createAcc);

            //create array for ["name", "password", "phoneNum"] , and boolean array [false, false, false]
            //manip.updateProfileDetails(connectDB, "user3", "Russel", "name");
            //String[] userDetails = manip.getUserDetails(connectDB, "user3");
            //for (int i = 0; i < userDetails.length; i++) {
            //    System.out.println(userDetails[i]);
           // }

            connectDB.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}

class Datab {
    private static final String urlDB = "jdbc:postgresql://localhost/user_storage";
    private static final String user = "postgres";
    private static final String password = "Pass1234";
    public Connection Conn() {

        boolean run = true;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connectDB = DriverManager.getConnection(urlDB, user, password);
            System.out.println("Connected to PostgreSQL server");

            // COMMENT OUT THIS LINE IF YOU ALREADY HAVE A TABLE
            //createDatabase(connectDB);
            //createFavDb(connectDB);
            return connectDB;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    ////////////////////// USER DATABASE /////////////////////////////
    public void createUserDb(Connection connectDB) {
        try {
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE user_DB"
                    + //EDIT here
                    "(user_ID TEXT,"
                    + "name TEXT,"
                    + "email TEXT,"
                    + "password TEXT,"
                    + "phoneNum TEXT,"
                    + "sec1 TEXT,"
                    + "sec2 TEXT,"
                    + "sec3 TEXT,"
                    + "ans1 TEXT,"
                    + "ans2 TEXT,"
                    + "ans3 TEXT)";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        } catch (SQLException e) {
            System.out.println("Error in Creating Table to History Server");
            e.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////

    //////////////////// HISTORY DATABASE /////////////////////////////
    public void createHistoryDb(Connection connectDB) {
        try {
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE history_DB"
                    + //EDIT here
                    "(user_ID TEXT,"
                    + "carpark_ID TEXT,"
                    +"time_stamp TIMESTAMP WITH TIME ZONE)";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        } catch (SQLException e) {
            System.out.println("Error in Creating History Table to Server");
            e.printStackTrace();
        }
    }
    /////////////////////////////////////////////////////////////////////

    /////////////////// FAVOURITE DATABASE //////////////////////////////
    public void createFavDb(Connection connectDB) {
        try {
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE favourite_DB"
                    + //EDIT here
                    "(user_ID TEXT,"
                    + "carpark_ID TEXT)";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        } catch (SQLException e) {
            System.out.println("Error in Creating Favourite Table to Server");
            e.printStackTrace();
        }
    }
}

