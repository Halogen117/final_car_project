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
    public Connection Conn() {
    	String urlDB = "jdbc:postgresql://localhost/carparkInformation";
        String user = "postgres";
        String password = "68709904";
        try {
        	Connection connectDB = DriverManager.getConnection(urlDB, user, password);
        	System.out.println("Connected to PostgreSQL server");
        	
        	// COMMENT OUT THIS LINE IF YOU ALREADY HAVE A TABLE
        	//createDatabase(connectDB);
        	//createFavDb(connectDB);
        	return connectDB;
        }
        catch (SQLException se) {
        	se.printStackTrace();
        }
        return null;
    }
    
    ////////////////////// USER DATABASE /////////////////////////////
    public void createUserDb() {
        
    	try {
            Connection connectDB = Conn();
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
            Connection connectDB = Conn();
            Statement statement = connectDB.createStatement();

            String sql = "CREATE TABLE history_DB" + //EDIT here
                    "(user_ID TEXT," +
                    "carpark_ID TEXT,"
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
            Connection connectDB = Conn();
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
}
