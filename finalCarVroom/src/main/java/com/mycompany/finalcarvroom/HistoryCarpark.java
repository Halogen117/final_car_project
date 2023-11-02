/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.util.Date;

/**
 *
 * @author mokda
 */
public class HistoryCarpark extends History{

    public int getHistory_ID() {
        return history_ID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public void setCarpark_ID(String carpark_ID) {
        this.carpark_ID = carpark_ID;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }
    private String address;
    
    public HistoryCarpark(int history_ID,String user_ID, String carpark_ID, Date time_stamp,String address) {
        super(history_ID,user_ID, carpark_ID, time_stamp);
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public String getCarpark_ID() {
        return carpark_ID;
    }

    public Date getTime_stamp() {
        return time_stamp;
    }
    
}
