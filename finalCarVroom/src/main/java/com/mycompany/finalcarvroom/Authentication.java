/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.util.Base64;
import java.sql.*;
/**
 *
 * @author evans
 */
public class Authentication {
    ConnectDB create = new ConnectDB();
    ////////////////////// LOGIN ////////////////////////
    public String login(String email, String password) {
                String userName = "";
		try {
                    Connection connectDB = create.getConnection();
                    Statement statement = connectDB.createStatement();

                    String sql = "SELECT * FROM user_DB WHERE email = '" + email +"' AND password = '" + password +"'";

                    ResultSet resultSet = statement.executeQuery(sql);
                    
                    if(resultSet.next()) {
                            System.out.println("Login Successful");
                            userName = resultSet.getString("user_ID");
                            connectDB.close();
                            return userName;
                            //activate "Login Successful" display message code here
                            //Close Guest Page and
                            //Display Member Page
                    }
                    else {
                            System.out.println("Authentication Failed");
                            connectDB.close();
                            return userName;
                            //activate "Authentication Failed" display message code here
                            //Reset password textbox
                    }
                    
		}catch(SQLException e) {
			e.printStackTrace();
		}
                return userName;
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
	//////////////// FORGET PASSWORD ////////////////////
	public String forget_password(String email){
            String [] userDetails = new String[11];
            try{
            Connection connectDB = create.getConnection();
            
            Statement statement = connectDB.createStatement();
            String sql = "SELECT name FROM user_DB WHERE email = '" + email +"'";
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
        return userDetails[0];
        }
        
        ///////////////////////// ENCODER /////////////////////////
        public String encoder_link(String email){
            String [] userDetails = new String[2];
            String baser = "";
            try{
            Connection connectDB = create.getConnection();
            
            Statement statement = connectDB.createStatement();
            String sql = "SELECT user_id, name FROM user_DB WHERE email = '" + email +"'";
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
            // Return user
            System.out.println(userDetails[0]);
            // Return email
            System.out.println(userDetails[1]);
            // Return userid
            baser = userDetails[0]+"_|_"+userDetails[1]+"_|_"+email;
            // Encode base 64
            baser = Base64.getEncoder().encodeToString(baser.getBytes());
            return baser;
        }
        
        
        /////////////////// DECODER ///////////////////////////////
        public boolean decoder_link(String decoder){
            String acquire_base_64 = decoder;
            byte[] baser = Base64.getDecoder().decode(acquire_base_64);
            String decoded = new String(baser);
            String[] arrOfStr = decoded.split("_|_");

            // Check if valid based on the data
            System.out.println(arrOfStr[0]);
            System.out.println(arrOfStr[2]);
            System.out.println(arrOfStr[4]);
            try{
            Connection connectDB = create.getConnection();
            
            Statement statement = connectDB.createStatement();
            String sql = "SELECT 1 FROM user_DB WHERE user_id = '" + arrOfStr[0] +"' AND email = '"+arrOfStr[4]+"' AND name = '"+arrOfStr[2]+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                return true;
            }else{
                return false;
            }
            }catch(SQLException e) {
                e.printStackTrace();
            }
            
            return false;
        }
}
