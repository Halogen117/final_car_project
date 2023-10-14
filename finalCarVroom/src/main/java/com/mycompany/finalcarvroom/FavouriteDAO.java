/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mokda
 */
public class FavouriteDAO {


    private static final String INSERT_FAV_SQL = "insert into favourite_db (user_id,carpark_id) values(?,?)";
    private static final String DELETE_FAV_SQL = "delete from favourite_db where user_id=? AND carpark_id=?;";
    private static final String SELECT_ALL_FAV_BY_USERID = "SELECT * FROM favourite_db WHERE user_id=?";

    public void insertFavourite(String userID, String carparkID) throws SQLException {
        
            ConnectDB conn = new ConnectDB();
        //System.out.println("Connected to PostgreSQL server");
        try (Connection connection = conn.getConnection()) {
            //System.out.println("Connected to PostgreSQL server");
            PreparedStatement statement = connection.prepareStatement(INSERT_FAV_SQL);
            statement.setString(1, userID);
            statement.setString(2, carparkID);
            System.out.println(statement);

            statement.executeUpdate();
            System.out.println("Data Successfully Inserted!");
        }

       
    }
    //Get favourited carparks by user ID

    public List<String> getAllFavourites(String userID) {
        List<String> favouritedCarparks = new ArrayList<>();
        try {
            ConnectDB conn = new ConnectDB();
            Connection connection = conn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FAV_BY_USERID);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                favouritedCarparks.add(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favouritedCarparks;
    }

    public boolean deleteFavourite(String userID, String carparkID) {
        boolean rowDeleted = false;
        try {
            ConnectDB conn = new ConnectDB();
            Connection connection = conn.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_FAV_SQL);
            statement.setString(1, userID);
            statement.setString(2, carparkID);
            rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;

        } catch (SQLException ex) {
            Logger.getLogger(FavouriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;
    }

}
