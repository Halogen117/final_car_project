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

    protected String user_ID;
    protected String carpark_ID;
    protected Date time_stamp;

    public History(String user_ID, String carpark_ID, Date time_stamp) {
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
