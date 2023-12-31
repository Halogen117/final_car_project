/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

/**
 *
 * @author Sk37chy
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/UpdateProfileServlet"})

public class UpdateProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession user_session = request.getSession();
        String userId = (String) user_session.getAttribute("userId");
        
        String[] updateAcc = new String[3];
        updateAcc[0] = request.getParameter("updateFirstName") + " " + request.getParameter("updateLastName");
        updateAcc[1] = request.getParameter("updateEmail");
        updateAcc[2] = request.getParameter("updatePhoneNum");

        System.out.println("Updated username: " + updateAcc[0]);
        System.out.println("Updated email: " + updateAcc[1]);
        System.out.println("Updated phone num: " + updateAcc[2]);

        //Retrieve user accounts
        ManipulateDB mDb = new ManipulateDB();
        ConnectDB cDb = new ConnectDB();
        
        // Email regex
        String email_regex = "^[A-Za-z0-9+_.-]+@[^@]+\\.com$";
        String phone_pattern = "^(\\d{4} ?\\d{4})$";
        if(!updateAcc[1].matches(email_regex)){
            System.out.println("Email does not match pattern! Please retype!");
            user_session.setAttribute("update_prof", "email_incorr");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return;
        /*}
        else if(mDb.checkEmailExist(cDb.getConnection(), updateAcc[1])){
            System.out.println("Email is found in the database! Please retype!");
            user_session.setAttribute("update_prof", "email_repeat");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return; 
        */
        }else if(!updateAcc[2].matches(phone_pattern)){
            user_session.setAttribute("update_prof", "phone_no");
            System.out.println("Phone Number format not correct");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return;   
        }/*else if(mDb.checkContactExist(cDb.getConnection(), updateAcc[2])){
            System.out.println("Phone Number is found in the database! Please retype!");
            user_session.setAttribute("update_prof", "phone_no_repeat");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return;
        }*/
        int checkData = mDb.updateCheck(cDb.getConnection(),userId,updateAcc[0],updateAcc[1],updateAcc[2]);
        if(checkData == 1){
            mDb.updateProfileDetails(userId, updateAcc[0], "name");
            mDb.updateProfileDetails(userId, updateAcc[1], "email");
            mDb.updateProfileDetails(userId, updateAcc[2], "phonenum");

            user_session.setAttribute("username", updateAcc[0]);
            user_session.setAttribute("update_prof", "success");
            response.sendRedirect("ProfileServlet");
            return;
        }
        else if(checkData == -1)
        {
            System.out.println("Email is found in the database! Please retype!");
            user_session.setAttribute("update_prof", "email_repeat");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return; 
        }
        else if(checkData==-2){
            System.out.println("Phone Number is found in the database! Please retype!");
            user_session.setAttribute("update_prof", "phone_no_repeat");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return;
        }
        else if(checkData==-3){
            System.out.println("Email and Phone Number is found in the database! Please retype!");
            user_session.setAttribute("update_prof", "email_phone_no_repeat");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
            return;
        }
        else{
            System.out.println("System Error!");
            user_session.setAttribute("update_prof", "sysErr");
            response.sendRedirect("ProfileServlet?updateProfile=Manage+Profile");
        }
    }
}
