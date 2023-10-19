/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;


import java.sql.*;
/**
 *
 * @author evans
 */
public class ManipulateDB {
    ConnectDB create = new ConnectDB();
	//////////////////////FAVOURITE DATABASE ////////////////////////////////////////
	public void insertFavDb(Connection connectDB, String userID, String carparkID) {
		try {
        	//Connection connection = DriverManager.getConnection(url, user, password);
        	//System.out.println("Connected to PostgreSQL server");
        	
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "INSERT INTO favourite_DB (user_ID,carpark_ID)" +
        			"VALUES('" + userID +"','"+ carparkID  + "')";
        	
        	statement.executeUpdate(sql);
        	System.out.println("Data Successfully Inserted!");
        	
        }
        catch(SQLException e) {
        	System.out.println("Error in updating to Favourite Server");
        	e.printStackTrace();
        }
	}
	
	public void updateFavDb(String userID, String carparkID) {
		
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();
                    String sql = "SELECT COUNT(*) " +
                    "FROM favourite_DB " +
                    "WHERE user_ID = '" + userID + "'";
    	
                    // Execute the query
                    ResultSet resultSet = statement.executeQuery(sql);


                    // Retrieve the count from the result set
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1); // Get the count from the first (and only) column
                        System.out.println("Count: " + count);
                        if(count < 5) {
                            insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
                            System.out.println("Data Successfully Inserted!");
                        }
                        else {
                            System.out.println("Max Number of favourite!");

                        }
                    }
                    connectDB.close();
		}catch(SQLException e) {
                    System.out.println("Error in updating to Favourite Server");
                    e.printStackTrace();
		}

    }
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////// HISTORY DATABASE ////////////////////////////////////////
	public void insertHistDb(Connection connectDB, String userID, String carparkID) {
		try {
        	//Connection connection = DriverManager.getConnection(url, user, password);
        	//System.out.println("Connected to PostgreSQL server");
        	
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "INSERT INTO history_DB (user_ID,carpark_ID)" +
        			"VALUES('" + userID +"','"+ carparkID  + "')";
        	
        	statement.executeUpdate(sql);
        	System.out.println("Data Successfully Inserted!");
        	
        }
        catch(SQLException e) {
        	System.out.println("Error in updating to Favourite Server");
        	e.printStackTrace();
        }
	}
	
	public void updateSearchHist(String userID, String carparkID) {
		
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();
                    String sql = "SELECT COUNT(*) " +
                    "FROM history_DB " +
                    "WHERE user_ID = '" + userID + "'";
    	
                    // Execute the query
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Retrieve the count from the result set
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1); // Get the count from the first (and only) column
                        System.out.println("Count: " + count);
                        if(count < 10) {
                            insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
                            System.out.println("Data Successfully Inserted!");
                        }
                        else {
                            System.out.println("Max Number of favourite!");
                        }
                    }
                    connectDB.close();
		}catch(SQLException e) {
                    System.out.println("Error in updating to History Server");
                    e.printStackTrace();
		}
    }
	/////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////// USER DATABASE ////////////////////////////////////////
	public void updateProfileDetails(String userID, String changes, String columnName) {
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();
                    /////////////////// check if userID column is the  /////////////////////////
                    String sql = "UPDATE user_DB SET " + columnName + " = '" + changes + "' WHERE user_ID = '" + userID + "'";

                    statement.executeUpdate(sql);
                    System.out.println("Update Successful");
                    connectDB.close();
		}catch(SQLException e) {
			System.out.println("Update Failed!");
			e.printStackTrace();
		}
	}
	// FOR Profile Page Details
	public String [] getUserDetails(String userID) {
		String [] userDetails = new String[11];
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();

                    String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID + "'";

                    ResultSet resultSet = statement.executeQuery(sql);
                    // get User details
                    if(resultSet.next()) {
                        for(int i = 1; i <= userDetails.length; i++) {
                                userDetails[i-1] = resultSet.getString(i);
                        }
                    }
                    else {
                            System.out.println("User Does not exist");
                    }
                    connectDB.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userDetails;
	}
	
	// FOR Email validation
	public boolean checkEmailExist(Connection connectDB, String email){
		
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if email and phone number already exist /////////////////////////
			String checkEmail = "SELECT * FROM user_DB WHERE email = '" + email + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber
			
			ResultSet resultSet = statement.executeQuery(checkEmail);
			
			if(resultSet.next()) {
				//System.out.println("Email already Existed!");
				return true;
			}
			else {
				//System.out.println("No Exisiting Email!");
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error Validating Email!");
			e.printStackTrace();
			
		}
		
		return true;
	}
	
	//FOR Phone Number Validation
	public boolean checkContactExist(Connection connectDB, String phoneNum){
		
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if email and phone number already exist /////////////////////////
			String checkContact = "SELECT * FROM user_DB WHERE phoneNum = '" + phoneNum + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber
			
			ResultSet resultSet = statement.executeQuery(checkContact);
			
			if(resultSet.next()) {
				//System.out.println("Phone Number already Existed!");
				return true;
			}
			else {
				//System.out.println("No Exisiting Phone Number!");
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error Validating Phone Number!");
			e.printStackTrace();
		}
		return true;
	}
	
	//CreateAccount
	public int insertUserDb(String [] createAcc) {
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();
                    /////////////////// check if email and phone number already exist /////////////////////////
                    boolean getContact = checkContactExist(connectDB, createAcc[4]);
                    boolean getEmail = checkEmailExist(connectDB, createAcc[2]);

                    if(getEmail) {
                            System.out.println("Email already Existed!");
                            return 0;
                    }
                    else if(getContact) {
                            System.out.println("Phone Number already Existed");
                            return -1;
                    }
                    else {
                            String sql = "INSERT INTO user_DB "
                                            + "VALUES('" + createAcc[0] + "','" + createAcc[1] + "','" + createAcc[2] + "','"
                                            + createAcc[3] + "','" + createAcc[4] + "','" + createAcc[5] + "','"
                                            + createAcc[6] + "','" + createAcc[7] + "','" + createAcc[8] + "','"
                                            + createAcc[9] + "','" + createAcc[10] +"')";

                            statement.executeUpdate(sql);
                            System.out.println("User Account Created!");
                            return 1;
                            //activate "Authentication Failed" display message code here
                    }
		}catch(SQLException e) {
			System.out.println("User Account Creation Failed!");
			e.printStackTrace();
			return 2;
		}
	}
        
        public int insertFeedbackDb(String user_id, String feedback){
            try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();
                    String sql = "INSERT INTO feedback_DB "
                                    + "VALUES('" + user_id + "','" + feedback + "')";

                    statement.executeUpdate(sql);
                    System.out.println("Feedback Created!");
                    return 1;
                    //activate "Authentication Failed" display message code here
                    
		}catch(SQLException e) {
			System.out.println("User Account Creation Failed!");
			e.printStackTrace();
			return 2;
		}
        }
}
