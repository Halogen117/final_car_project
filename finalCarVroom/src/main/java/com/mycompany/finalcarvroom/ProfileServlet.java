/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import org.json.JSONObject;

/**
 *
 * @author Halogen
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})

public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Java code to process the request
        HttpSession user_session = request.getSession();
        if (request.getParameter("updatePassword") != null) {
            response.sendRedirect("securityQn.jsp");
        } else if(user_session.getAttribute("work") == "delete_success"){  
            response.sendRedirect("login.jsp");
        }else{
            
            ManipulateDB mDb = new ManipulateDB();

            String currentUserId = (String) user_session.getAttribute("userId");
            String[] userDetails = mDb.getUserDetails(currentUserId);  //user must change to user ID (Global variable)

            // Retrieve current user session
            User currUserData = new User();
            currUserData.setFirst_name(userDetails[1]);
            currUserData.setLast_name(userDetails[1]);
            currUserData.setEmail(userDetails[2]);
            currUserData.setPhoneNum(userDetails[4]);
            // Send UserData obj to webpage
            request.setAttribute("CurrentUserData", currUserData);
            // Redirect to profile page or update profile page
            if (request.getParameter("updateProfile") != null) {
                System.out.println("Button pushed");
                request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
            } else {
                System.out.println("Button NOT pushed");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        }
    }
}
