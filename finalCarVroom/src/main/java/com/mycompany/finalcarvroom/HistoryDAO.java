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
import java.util.Date;
import java.util.List;

/**
 *
 * @author mokda
 */
public class HistoryDAO {
    private static final String INSERT_HIST_SQL = "insert into history_db (user_id,carpark_id,time_stamp) values(?,?,NOW());";
    private static final String SELECT_HIST_LIMITED_DAYS_SQL = "SELECT * FROM history_db WHERE time_stamp >=CURRENT_DATE - INTERVAL '? days AND user_id=?';";
    
    public void insertHistory(String userID,String carparkID) throws SQLException{
        ConnectDB conn = new ConnectDB();
        try(Connection connection = conn.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_HIST_SQL);
            statement.setString(1, userID);
            statement.setString(2, carparkID);
            statement.executeUpdate();
            System.out.println("Data Successfully Inserted!");
        }
    }
    public List<History> getHistory(String userID,String days) throws SQLException{
        List<History> historyCarpark= new ArrayList<>();
        ConnectDB conn = new ConnectDB();
        try(Connection connection = conn.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_HIST_LIMITED_DAYS_SQL);
            statement.setString(1, days);
            statement.setString(2, userID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                History hist= new History(rs.getString(1), rs.getString(2),rs.getTimestamp(3));
                historyCarpark.add(hist);
            }
        }
        return historyCarpark;
    }
    
}
