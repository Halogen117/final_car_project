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

            String sql = "INSERT INTO favourite_DB (user_ID,carpark_ID)"
                    + "VALUES('" + userID + "','" + carparkID + "')";

            statement.executeUpdate(sql);
            System.out.println("Data Successfully Inserted!");

        } catch (SQLException e) {
            System.out.println("Error in updating to Favourite Server");
            e.printStackTrace();
        }
    }

    public void updateFavDb(String userID, String carparkID) {

        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();
            String sql = "SELECT COUNT(*) "
                    + "FROM favourite_DB "
                    + "WHERE user_ID = '" + userID + "'";

            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);

            // Retrieve the count from the result set
            if (resultSet.next()) {
                int count = resultSet.getInt(1); // Get the count from the first (and only) column
                System.out.println("Count: " + count);
                if (count < 5) {
                    insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
                    System.out.println("Data Successfully Inserted!");
                } else {
                    System.out.println("Max Number of favourite!");

                }
            }
            connectDB.close();
        } catch (SQLException e) {
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

            String sql = "INSERT INTO history_DB (user_ID,carpark_ID)"
                    + "VALUES('" + userID + "','" + carparkID + "')";

            statement.executeUpdate(sql);
            System.out.println("Data Successfully Inserted!");

        } catch (SQLException e) {
            System.out.println("Error in updating to Favourite Server");
            e.printStackTrace();
        }
    }

    public void updateSearchHist(String userID, String carparkID) {

        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();
            String sql = "SELECT COUNT(*) "
                    + "FROM history_DB "
                    + "WHERE user_ID = '" + userID + "'";

            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);

            // Retrieve the count from the result set
            if (resultSet.next()) {
                int count = resultSet.getInt(1); // Get the count from the first (and only) column
                System.out.println("Count: " + count);
                if (count < 10) {
                    insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
                    System.out.println("Data Successfully Inserted!");
                } else {
                    System.out.println("Max Number of favourite!");
                }
            }
            connectDB.close();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            System.out.println("Update Failed!");
            e.printStackTrace();
        }
    }
    // FOR Profile Page Details

    public User getUser(String userID) {
        User userDetails = null;
        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            // get User details
            while (resultSet.next()) {
                String user_id = resultSet.getString("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phonenum = resultSet.getString("phonenum");
                String sec1 = resultSet.getString("sec1");
                String sec2 = resultSet.getString("sec2");
                String sec3 = resultSet.getString("sec3");
                String ans1 = resultSet.getString("ans1");
                String ans2 = resultSet.getString("ans2");
                String ans3 = resultSet.getString("ans3");
                userDetails = new User(user_id, name, email, password, phonenum, sec1, sec2, sec3, ans1, ans2, ans3);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    public String[] getUserDetails(String userID) {
        String[] userDetails = new String[11];
        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();

            String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID + "'";

            ResultSet resultSet = statement.executeQuery(sql);
            // get User details
            if (resultSet.next()) {
                for (int i = 1; i <= userDetails.length; i++) {
                    userDetails[i - 1] = resultSet.getString(i);
                }
            } else {
                System.out.println("User Does not exist");
            }
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    // FOR Email validation
    public boolean checkEmailExist(Connection connectDB, String email) {

        try {
            Statement statement = connectDB.createStatement();
            /////////////////// check if email and phone number already exist /////////////////////////
            String checkEmail = "SELECT * FROM user_DB WHERE email = '" + email + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber

            ResultSet resultSet = statement.executeQuery(checkEmail);

            if (resultSet.next()) {
                //System.out.println("Email already Existed!");
                return true;
            } else {
                //System.out.println("No Exisiting Email!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error Validating Email!");
            e.printStackTrace();

        }

        return true;
    }

    //FOR Phone Number Validation
    public boolean checkContactExist(Connection connectDB, String phoneNum) {

        try {
            Statement statement = connectDB.createStatement();
            /////////////////// check if email and phone number already exist /////////////////////////
            String checkContact = "SELECT * FROM user_DB WHERE phoneNum = '" + phoneNum + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber

            ResultSet resultSet = statement.executeQuery(checkContact);

            if (resultSet.next()) {
                //System.out.println("Phone Number already Existed!");
                return true;
            } else {
                //System.out.println("No Exisiting Phone Number!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error Validating Phone Number!");
            e.printStackTrace();
        }
        return true;
    }

        // UpdateProfile - FOR Email and phone number validation
    public int updateCheck(Connection connectDB, String userID, String nameUpdate,
            String emailUpdate, String phoneUpdate) {

        try {
            Statement statement = connectDB.createStatement();
            /////////////////// check if email and phone number already exist /////////////////////////
            String[] userData = getUserDetails(userID);
            
            if(userData[2].equals(emailUpdate)&&userData[4].equals(phoneUpdate))
            {
                return 1;
            }
            else if(!userData[2].equals(emailUpdate)&&userData[4].equals(phoneUpdate)){
                boolean checkEmail = checkEmailExist(connectDB, emailUpdate);
                if(checkEmail){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            else if(userData[2].equals(emailUpdate)&&!userData[4].equals(phoneUpdate)){
                boolean checkPhoneNum = checkContactExist(connectDB, phoneUpdate);
                if(checkPhoneNum){
                    return -2;
                }
                else{
                    return 1;
                }
            }
            else if(!userData[2].equals(emailUpdate)&&!userData[4].equals(phoneUpdate)){
                boolean checkEmail = checkEmailExist(connectDB, emailUpdate);
                boolean checkPhoneNum = checkContactExist(connectDB, phoneUpdate);
                if(checkEmail){
                    return -1;
                }
                else if(checkPhoneNum){
                    return -2;
                }
                else if(checkEmail&&checkPhoneNum){
                    return -3;
                }
                else{
                    return 1;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error Validating Email!");
            e.printStackTrace();

        }
        return 0;
    }

    
    //CreateAccount
    public int insertUserDb(String[] createAcc) {
        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();
            /////////////////// check if email and phone number already exist /////////////////////////
            boolean getContact = checkContactExist(connectDB, createAcc[4]);
            boolean getEmail = checkEmailExist(connectDB, createAcc[2]);

            if (getEmail) {
                System.out.println("Email already Existed!");
                return 0;
            } else if (getContact) {
                System.out.println("Phone Number already Existed");
                return -1;
            } else {
                String sql = "INSERT INTO user_DB "
                        + "VALUES('" + createAcc[0] + "','" + createAcc[1] + "','" + createAcc[2] + "','"
                        + createAcc[3] + "','" + createAcc[4] + "','" + createAcc[5] + "','"
                        + createAcc[6] + "','" + createAcc[7] + "','" + createAcc[8] + "','"
                        + createAcc[9] + "','" + createAcc[10] + "')";

                statement.executeUpdate(sql);
                System.out.println("User Account Created!");
                return 1;
                //activate "Authentication Failed" display message code here
            }
        } catch (SQLException e) {
            System.out.println("User Account Creation Failed!");
            e.printStackTrace();
            return 2;
        }
    }
    
    // Retrieve data for session login
     public String[] getNameIdDB(String email) {
        String emailInput = email;
        String[] userDataQuery = new String[2];
        try {
            Connection conn = create.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT user_id, name FROM user_db "
                    + "WHERE email = \'" + emailInput + "\'");
            if (rs.next()) {
                userDataQuery[0] = rs.getString("user_id");
                userDataQuery[1] = rs.getString("name");
            }
            rs.close();
            statement.close();
            conn.close();
            return userDataQuery;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
            return userDataQuery;
        }
    }

    public int insertFeedbackDb(String user_id, String feedback) {
        try {
            Connection connectDB = create.getConnection();
            Statement statement = connectDB.createStatement();
            String sql = "INSERT INTO feedback_DB "
                    + "VALUES('" + user_id + "','" + feedback + "')";

            statement.executeUpdate(sql);
            System.out.println("Feedback Created!");
            return 1;
            //activate "Authentication Failed" display message code here

        } catch (SQLException e) {
            System.out.println("User Account Creation Failed!");
            e.printStackTrace();
            return 2;
        }
    }
    
    public boolean deleteUserData(String email){
        String query = "DELETE from user_db where email = '"+email+"'";
        try {
            Connection conn = create.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("User Account Deletion Failed!");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteFavData(String userId){
        String query = "DELETE from favourite_db where user_id = '"+userId+"'";
        try {
            Connection conn = create.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            return true;
            
        } catch (SQLException e) {
            System.out.println("Favourite DB Deletion Failed!");
            e.printStackTrace();
            return false;
        }
    }
}
