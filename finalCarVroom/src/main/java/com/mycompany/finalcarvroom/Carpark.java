/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

/**
 *
 * @author mokda
 */
public class Carpark {

    public void setIs_central(boolean is_central) {
        this.is_central = is_central;
    }

    public void setOutside_central_rate(double outside_central_rate) {
        this.outside_central_rate = outside_central_rate;
    }

    public void setCentral_mon_to_fri_rate(double central_mon_to_fri_rate) {
        this.central_mon_to_fri_rate = central_mon_to_fri_rate;
    }

    public void setCentral_other_rate(double central_other_rate) {
        this.central_other_rate = central_other_rate;
    }

    public boolean isIs_central() {
        return is_central;
    }

    public double getOutside_central_rate() {
        return outside_central_rate;
    }

    public double getCentral_mon_to_fri_rate() {
        return central_mon_to_fri_rate;
    }

    public double getCentral_other_rate() {
        return central_other_rate;
    }

    public void setCarpark_id(String carpark_id) {
        this.carpark_id = carpark_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setX_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public void setY_coord(double y_coord) {
        this.y_coord = y_coord;
    }

    public void setCar_park_type(String car_park_type) {
        this.car_park_type = car_park_type;
    }

    public void setType_of_parking_system(String type_of_parking_system) {
        this.type_of_parking_system = type_of_parking_system;
    }

    public void setShort_term_parking(String short_term_parking) {
        this.short_term_parking = short_term_parking;
    }

    public void setFree_parking(String free_parking) {
        this.free_parking = free_parking;
    }

    public void setNight_parking(String night_parking) {
        this.night_parking = night_parking;
    }

    public void setCar_park_decks(int car_park_decks) {
        this.car_park_decks = car_park_decks;
    }

    public void setGantry_height(double gantry_height) {
        this.gantry_height = gantry_height;
    }

    public void setCar_park_basement(String car_park_basement) {
        this.car_park_basement = car_park_basement;
    }

    public String getCarpark_id() {
        return carpark_id;
    }

    public String getAddress() {
        return address;
    }

    public double getX_coord() {
        return x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public String getCar_park_type() {
        return car_park_type;
    }

    public String getType_of_parking_system() {
        return type_of_parking_system;
    }

    public String getShort_term_parking() {
        return short_term_parking;
    }

    public String getFree_parking() {
        return free_parking;
    }

    public String getNight_parking() {
        return night_parking;
    }

    public int getCar_park_decks() {
        return car_park_decks;
    }

    public double getGantry_height() {
        return gantry_height;
    }

    public String getCar_park_basement() {
        return car_park_basement;
    }

    public Carpark(String carpark_id, String address, double x_coord, double y_coord, String car_park_type, String type_of_parking_system, String short_term_parking, String free_parking, String night_parking, int car_park_decks, double gantry_height, String car_park_basement,boolean is_central,double outside_central_rate,double central_mon_to_fri_rate,double central_other_rate) {
        this.carpark_id = carpark_id;
        this.address = address;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.car_park_type = car_park_type;
        this.type_of_parking_system = type_of_parking_system;
        this.short_term_parking = short_term_parking;
        this.free_parking = free_parking;
        this.night_parking = night_parking;
        this.car_park_decks = car_park_decks;
        this.gantry_height = gantry_height;
        this.car_park_basement = car_park_basement;
        this.is_central=is_central;
        this.central_mon_to_fri_rate=central_mon_to_fri_rate;
        this.outside_central_rate=outside_central_rate;
        this.central_other_rate=central_other_rate;
    }
    private String carpark_id;
    private String address;
    private double x_coord;
    private double y_coord;
    private String car_park_type;
    private String type_of_parking_system;
    private String short_term_parking;
    private String free_parking;
    private String night_parking;
    private int car_park_decks;
    private double gantry_height;
    private String car_park_basement;
    private boolean is_central;
    private double outside_central_rate;
    private double central_mon_to_fri_rate;
    private double central_other_rate;
}
