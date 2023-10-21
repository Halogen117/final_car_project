/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

/**
 *
 * @author mokda
 */
public class User {

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String user_id, String name, String email, String password, String phonenum, String sec1, String sec2, String sec3, String ans1, String ans2, String ans3) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phonenum = phonenum;
        this.sec1 = sec1;
        this.sec2 = sec2;
        this.sec3 = sec3;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setSec1(String sec1) {
        this.sec1 = sec1;
    }

    public void setSec2(String sec2) {
        this.sec2 = sec2;
    }

    public void setSec3(String sec3) {
        this.sec3 = sec3;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }
    private String user_id;
    private String name;
    private String email;
    private String password;
    private String phonenum;
    private String sec1;
    private String sec2;
    private String sec3;
    private String ans1;
    private String ans2;
    private String ans3;

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getSec1() {
        return sec1;
    }

    public String getSec2() {
        return sec2;
    }

    public String getSec3() {
        return sec3;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }
}
