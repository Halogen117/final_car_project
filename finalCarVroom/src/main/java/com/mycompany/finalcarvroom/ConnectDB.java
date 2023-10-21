/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mokda
 */
public class ConnectDB {

    //Change this to your Database, user and password
    //private static final String urlDB = "jdbc:postgresql://localhost/carpark";
    private static final String urlDB = "jdbc:postgresql://localhost/user_storage";
    private static final String user = "postgres";
    //private static final String password = "admin";
    private static final String password = "Pass1234";
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(urlDB, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated catch block
        return connection;
    }
}
