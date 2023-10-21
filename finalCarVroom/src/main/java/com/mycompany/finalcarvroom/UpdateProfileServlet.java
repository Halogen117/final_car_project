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

        ManipulateDB mDb = new ManipulateDB();
        mDb.updateProfileDetails(userId, updateAcc[0], "name");
        mDb.updateProfileDetails(userId, updateAcc[1], "email");
        mDb.updateProfileDetails(userId, updateAcc[2], "phonenum");
        
        user_session.setAttribute("username", updateAcc[0]);
        
        response.sendRedirect("ProfileServlet");
    }
}
