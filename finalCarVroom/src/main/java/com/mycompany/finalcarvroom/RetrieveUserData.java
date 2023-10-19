package com.mycompany.finalcarvroom;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class RetrieveUserData {
    // Use other class for DB connection

    private connect_to_postgres connPostGres = new connect_to_postgres();

    public RetrieveUserData() {
    }

    // Authenticate user login details
    public boolean userAuthLogin(String email, String password) {
        String emailInput = email;
        String passwordInput = password;
        boolean authenticated = false;

        try {

            Connection conn = connPostGres.connect();
            Statement statement = conn.createStatement();
            // Query to check for email and password
            // ENSURE THAT YOU HAVE CREATED A TABLE CALLED USERINFO
            ResultSet rs = statement.executeQuery("SELECT * FROM user_db "
                    + "WHERE email = \'" + emailInput + "\'");
            if (rs.next()) {
                // Check password using this function as password in DB is encrypted
                if (PasswordManager.verifyPassword(passwordInput, rs.getString("Password"))) {
                    authenticated = true;
                    System.out.println("Authenticated!");
                } else {
                    System.out.println("Not Authenticated!");
                }
            } else {
                System.out.println("Not Authenticated!");
            }
            rs.close();
            statement.close();
            conn.close();
            return authenticated;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
            return false;
        }
    }

    // Fetch username (May change to fetch other)
    public String[] getNameIdDB(String email) {
        String emailInput = email;
        String[] userDataQuery = new String[2];
        try {
            Connection conn = connPostGres.connect();
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
}
