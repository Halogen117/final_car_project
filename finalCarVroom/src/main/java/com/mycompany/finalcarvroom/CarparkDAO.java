/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mokda
 */
public class CarparkDAO {

    private static final String SELECT_ALL_SQL = "SELECT * FROM carpark_db;";
    private static final String SELECT_ONE_SQL = "SELECT * FROM carpark_db WHERE carpark_id=?;";

    public List<Carpark> getAllCarpark() throws SQLException {
        List<Carpark> carparkList = new ArrayList<>();
        ConnectDB conn = new ConnectDB();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Carpark carpark = new Carpark(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)), Double.parseDouble(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        Integer.parseInt(rs.getString(10)), Double.parseDouble(rs.getString(11)), rs.getString(12),
                        rs.getBoolean(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16));
                carparkList.add(carpark);
            }
        }
        return carparkList;
    }

    public Carpark getCarpark(String carparkID) throws SQLException {
       Carpark carpark=null;
        ConnectDB conn = new ConnectDB();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ONE_SQL);
            System.out.println(carparkID);
            statement.setString(1, carparkID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                carpark = new Carpark(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)), Double.parseDouble(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        Integer.parseInt(rs.getString(10)), Double.parseDouble(rs.getString(11)), rs.getString(12),
                rs.getBoolean(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16));
                
            }
        }
        return carpark;
    }
}
