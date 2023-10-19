package com.mycompany.finalcarvroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import java.net.URLEncoder;

// Creating a Servlet to fetch user login details,
// authenticate it and redirect user to main page if successful
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Get user's inputs
        HttpSession user_session = request.getSession();
        String user_email = request.getParameter("userEmail");
        String user_password = request.getParameter("userPassword");
        String[] userNameId = new String[2];
        // Create class for database
        RetrieveUserData getDataFromDB = new RetrieveUserData();
        // Check for user authentication
        if (getDataFromDB.userAuthLogin(user_email, user_password)) {
            // Get username from DB using email
            userNameId = getDataFromDB.getNameIdDB(user_email);
            // Retrieve current user session
            
            // Set username to current session
            user_session.setAttribute("userId", userNameId[0]);
            user_session.setAttribute("username", userNameId[1]);
            Cookie urlCookie = new Cookie("userId", URLEncoder.encode( userNameId[0], "UTF-8" ));
            Cookie urlCookie_2 = new Cookie("userId", URLEncoder.encode( userNameId[1], "UTF-8" ));
            response.addCookie(urlCookie);
            response.addCookie(urlCookie_2);
            // Change page to main page
            response.sendRedirect("index.html");
        } else {
            // Failed authentication will direct them back to login page
            user_session.setAttribute("work", "login_failed");
            System.out.println("Login has failed try again");
            response.sendRedirect("login.jsp");
        }

    }

}
