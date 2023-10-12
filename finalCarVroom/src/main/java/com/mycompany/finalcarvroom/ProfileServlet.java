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
/**
 *
 * @author Halogen
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})

public class ProfileServlet extends HttpServlet {
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          // Java code to process the request
        
        Connection connectDB = null;
        
          manipulateDb mDb = new manipulateDb();
          
          String urlDB = "jdbc:postgresql://localhost/carparkInformation";
          String user = "postgres";
          String password = "68709904";
          
          try {
            connectDB = DriverManager.getConnection(urlDB, user, password);
            System.out.println("Connected to PostgreSQL server");
          
          }
          catch (SQLException se) {
            se.printStackTrace();
          }
          
          String [] userDetails = mDb.getUserDetails(connectDB, user);  //user must change to user ID (Global variable)
          
          // Retrieve current user session
          HttpSession user_session = request.getSession();
          // Set username to current session
          user_session.setAttribute("username", userDetails[1]);
          user_session.setAttribute("email", userDetails[2]);
          user_session.setAttribute("contact", userDetails[4]);
          response.sendRedirect("profile.jsp");
      }
  }

