package com.mycompany.finalcarvroom;

/**
 *
 * @author Sk37chy
 */
public class UserData {
    private String first_name;
    private String last_name;
    private String email;
    private String phoneNum;
    
    public UserData()
    {
        
    }
    public String getFirst_name(){
        return this.first_name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }
    public void setFirst_name(String username){
        this.first_name = username.substring(0, username.lastIndexOf(" "));
    }
    public void setLast_name(String username){
        this.last_name = username.substring(username.lastIndexOf(" ") + 1);
    }
    public void setEmail(String user_email){
        this.email = user_email;
    }
    public void setPhoneNum(String user_phoneNum){
        this.phoneNum = user_phoneNum;
    }
}
