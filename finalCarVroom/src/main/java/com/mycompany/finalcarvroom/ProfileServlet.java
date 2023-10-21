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
        if (request.getParameter("updatePassword") != null) {
            response.sendRedirect("securityQn.jsp");
        } else {
            Connection connectDB = null;

            ManipulateDB mDb = new ManipulateDB();

            //String urlDB = "jdbc:postgresql://localhost/carparkInformation";
            String urlDB = "jdbc:postgresql://localhost/user_storage";
            String user = "postgres";
            //String password = "68709904";
            String password = "Pass1234";

            try {
                connectDB = DriverManager.getConnection(urlDB, user, password);
                System.out.println("Connected to PostgreSQL server");

            } catch (SQLException se) {
                se.printStackTrace();
            }
            HttpSession user_session = request.getSession();
            String currentUserId = (String) user_session.getAttribute("userId");
            String[] userDetails = mDb.getUserDetails(currentUserId);  //user must change to user ID (Global variable)

            // Retrieve current user session
            UserData currUserData = new UserData();
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
