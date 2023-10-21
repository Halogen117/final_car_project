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
public class History {

    public void setHistory_ID(int history_ID) {
        this.history_ID = history_ID;
    }

    public int getHistory_ID() {
        return history_ID;
    }

   
    protected int history_ID;
    protected String user_ID;
    protected String carpark_ID;
    protected Date time_stamp;

    public History(int history_ID,String user_ID, String carpark_ID, Date time_stamp) {
        this.history_ID=history_ID;
        this.user_ID = user_ID;
        this.carpark_ID = carpark_ID;
        this.time_stamp = time_stamp;

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
