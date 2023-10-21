package com.mycompany.finalcarvroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Creating a Servlet to fetch user login details,
// authenticate it and redirect user to main page if successful
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Get user's inputs
        String user_email = request.getParameter("userEmail");
        String user_password = request.getParameter("userPassword");
        String[] userNameId = new String[2];
        // Create obj to use method
        Authentication userAuth = new Authentication();
        ManipulateDB getNameId = new ManipulateDB();
        // Check for user authentication
        if (userAuth.userAuthLogin(user_email, user_password)) {
            // Get username from DB using email
            userNameId = getNameId.getNameIdDB(user_email);
            // Retrieve current user session
            HttpSession user_session = request.getSession();
            // Set username to current session
            user_session.setAttribute("userId", userNameId[0]);
            user_session.setAttribute("username", userNameId[1]);
            // Change page to main page
            response.sendRedirect("index.jsp");
        } else {
            // Failed authentication will direct them back to login page
            response.sendRedirect("404.jsp");
            //request.setAttribute("error", "Wrong username or password!");
            //request.getRequestDispatcher("login.jsp");
        }

    }

}
