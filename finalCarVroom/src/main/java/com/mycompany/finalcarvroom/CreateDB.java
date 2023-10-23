/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

/**
 *
 * @author evans
 */

import java.sql.*;

public class CreateDB {
    
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
            table.createHistoryDb();
            //table.createCarparkDb();
            //table.createFavDb();
            //table.createUserDb();
            //table.createFeedbackDb();

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
    ConnectDB create = new ConnectDB();
    ////////////////////// USER DATABASE /////////////////////////////
    public void createUserDb() {
        
    	try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE user_DB" + //EDIT here
                    "(user_ID TEXT,"
                    + "name TEXT," +
                    "email TEXT,"
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
        	}
        	catch(SQLException e) {
            	System.out.println("Error in Creating Table to History Server");
            	e.printStackTrace();
            }
    }
    ///////////////////////////////////////////////////////////////////
    
    //////////////////// HISTORY DATABASE /////////////////////////////
    public void createHistoryDb() {
    	try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE history_DB"
                    + //EDIT here
                    "(history_id BIGSERIAL, user_ID TEXT,"
                    + "carpark_ID TEXT,"
                    + "time_stamp TIMESTAMP WITH TIME ZONE)";
                	
            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        	}
        	catch(SQLException e) {
            	System.out.println("Error in Creating History Table to Server");
            	e.printStackTrace();
            }
    }
    /////////////////////////////////////////////////////////////////////
    
    /////////////////// FAVOURITE DATABASE //////////////////////////////
    public void createFavDb() {
    	try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE favourite_DB" + //EDIT here
                    "(user_ID TEXT," +
                    "carpark_ID TEXT)";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
    	}
    	catch(SQLException e) {
        	System.out.println("Error in Creating Favourite Table to Server");
        	e.printStackTrace();
        }
    }
    
    public void createCarparkDb() {
        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();
            
            String sql="Create TABLE carpark_db (carpark_id TEXT,address TEXT,x_coord DOUBLE PRECISION,y_coord DOUBLE PRECISION,car_park_type TEXT,type_of_parking_system TEXT,short_term_parking TEXT,free_parking TEXT,night_parking TEXT,car_park_decks integer,gantry_height DOUBLE PRECISION,car_park_basement TEXT)";
            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        }catch(SQLException e){
            System.out.println("Error in creating carpark table to server");
            e.printStackTrace();
        }
    }
    
    public void createFeedbackDb() {
    	try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE feedback_DB" + //EDIT here
                    "(user_ID TEXT," +
                    "Feedback TEXT)";

            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
    	}
    	catch(SQLException e) {
        	System.out.println("Error in Creating Feedback Table to Server");
        	e.printStackTrace();
        }
    }
}
