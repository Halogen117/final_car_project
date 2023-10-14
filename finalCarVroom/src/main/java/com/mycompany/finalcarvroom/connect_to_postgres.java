/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
/**
 *
 * @author Halogen
 */
public class connect_to_postgres {
    private static final String user = "postgres";
    private static final String password = "admin";
    private static final String url = "jdbc:postgresql://localhost/carpark?user="+user+"&password="+password+"";
    
    public connect_to_postgres(){
    
    }
    public Connection connect(){
        Connection conn_to = null;
        
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn_to = DriverManager.getConnection(url);
            System.out.println("Connected to the PostgreSQL server successfully.");
        
        
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn_to;
    }
    
    public void retrieve_data(){
        
        try{
            Connection conn = connect();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM carparkinformation");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData meta_data = rs.getMetaData();
            int col_num = meta_data.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col_num; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + meta_data.getColumnName(i));
                }
                System.out.println("");
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public String retrieve_carpark_details(String carpark_number){
try{
            Connection conn = connect();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM carparkinformation WHERE car_park_no = '"+carpark_number+"'");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData meta_data = rs.getMetaData();
            int col_num = meta_data.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col_num; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + meta_data.getColumnName(i));
                    return meta_data.getColumnName(i);
                }
                System.out.println("");
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    return "";
    }
    
}

