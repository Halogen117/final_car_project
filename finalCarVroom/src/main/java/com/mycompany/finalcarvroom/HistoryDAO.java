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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mokda
 */
public class HistoryDAO {

    private static final String INSERT_HIST_SQL = "insert into history_db (user_id,carpark_id,time_stamp) values(?,?,NOW());";

    //https://stackoverflow.com/questions/33058684/org-postgresql-util-psqlexception-error-syntax-error-at-or-near-1
    //casting interval so that it would not produce an error of syntax error at or near "$1"
    private static final String SELECT_HIST_LIMITED_DURATION_SQL = "SELECT history_db.history_id,history_db.user_id,history_db.carpark_id,history_db.time_stamp,carpark_db.address "
            + "FROM history_db "
            + "INNER JOIN carpark_db ON history_db.carpark_id=carpark_db.carpark_id "
            + "WHERE history_db.time_stamp >=NOW() - ?::INTERVAL AND history_db.user_id=? "
            + "ORDER BY history_db.time_stamp DESC;";

    private static final String DELETE_HISTORY_BY_ID_SQL = "DELETE FROM history_db WHERE history_id=?;";
    private static final String DELETE_ALL_HISTORY_BY_ID_SQL = "DELETE FROM history_db WHERE user_id=?;";

    public void insertHistory(String userID, String carparkID) throws SQLException {
        ConnectDB conn = new ConnectDB();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_HIST_SQL);
            statement.setString(1, userID);
            statement.setString(2, carparkID);
            statement.executeUpdate();
            System.out.println("Data Successfully Inserted!");
        }
    }

    public List<HistoryCarpark> getHistory(String userID, String duration) throws SQLException {
        List<HistoryCarpark> historyCarparkList = new ArrayList<>();
        ConnectDB conn = new ConnectDB();
        try (Connection connection = conn.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_HIST_LIMITED_DURATION_SQL);
            statement.setString(1, duration);
            statement.setString(2, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HistoryCarpark hist = new HistoryCarpark(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5));
                historyCarparkList.add(hist);
            }
        }
        return historyCarparkList;
    }

    public boolean deleteHistory(int historyID) {
        boolean rowDeleted = false;
        try {
            ConnectDB conn = new ConnectDB();
            Connection connection = conn.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_HISTORY_BY_ID_SQL);
            statement.setInt(1, historyID);
            rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;

        } catch (SQLException ex) {
            Logger.getLogger(FavouriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;

    }

    public boolean deleteAllHistory(String userID) {
        boolean rowDeleted = false;
        try {
            ConnectDB conn = new ConnectDB();
            Connection connection = conn.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ALL_HISTORY_BY_ID_SQL);
            statement.setString(1, userID);
            
            rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;

        } catch (SQLException ex) {
            Logger.getLogger(FavouriteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;
    }

}
