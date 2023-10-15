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
public class Authentication {
    ConnectDB create = new ConnectDB();
    public void login(String email, String password) {
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();

                    String sql = "SELECT * FROM user_DB WHERE email = '" + email +"' AND password = '" + password +"'";

                    ResultSet resultSet = statement.executeQuery(sql);

                    if(resultSet.next()) {
                            System.out.println("Login Successful");
                            //activate "Login Successful" display message code here
                            //Close Guest Page and
                            //Display Member Page
                    }
                    else {
                            System.out.println("Authentication Failed");
                            //activate "Authentication Failed" display message code here
                            //Reset password textbox
                    }
                    connectDB.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	///////////////////////////////////////////////////////////
	
	////////////// GET SECURITY QUESTION ////////////////////////////
	public String [] getSecQuestion(String userID) {
		String [] secQuestion = new String[3];
		
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();

                    String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID +"'";

                    ResultSet resultSet = statement.executeQuery(sql);

                    if(resultSet.next()) {
                            secQuestion[0] = resultSet.getString("sec1");
                            secQuestion[1] = resultSet.getString("sec2");
                            secQuestion[2] = resultSet.getString("sec3");
                    }
                    else {
                            System.out.println("UserID not found!");
                    }
                    connectDB.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return secQuestion;
	}
	
	//////////////////////// VERIFY IF ANSWER IS CORRECT ///////////////////////////////////
	public boolean verification(String userID, String answer1, String answer2, String answer3) {
		
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();

                    String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID +"'";

                    ResultSet resultSet = statement.executeQuery(sql);

                    if(resultSet.next()) {
                            if((answer1 != resultSet.getString("ans1")) || (answer2 != resultSet.getString("ans2")) || (answer3 != resultSet.getString("ans3"))) {
                                    System.out.println("Verification Failed!");
                                    return false;
                            }
                            else {
                                    System.out.println("Verification Successful!");
                                    return true;
                            }

                    }else {
                            System.out.println("UserID not found!");
                    }
                    connectDB.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
