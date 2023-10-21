package com.mycompany.finalcarvroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})

public class RegisterServlet extends HttpServlet {

    //private ConnectDB connDB = new ConnectDB();
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //HttpSession user_session = request.getSession();
        //String norm_pass = request.getParameter("exampleInputPassword");
        //String repeat_pass = request.getParameter("exampleRepeatPassword");

        //if(norm_pass != repeat_pass){
        //response.sendRedirect("error.jsp");
        // Error in password;
        //}
        //Connection conn = connDB.getConnection();
        String[] createAcc = new String[11];
        createAcc[0] = "1";
        createAcc[1] = request.getParameter("firstName") + " " + request.getParameter("lastName");
        createAcc[2] = request.getParameter("inputEmail");
        createAcc[3] = PasswordManager.hashPassword(request.getParameter("inputPassword"));
        createAcc[4] = request.getParameter("phoneNumber");
        createAcc[5] = "What was the name of your first pet?";
        createAcc[6] = "What is your favorite movie?";
        createAcc[7] = "What is your lucky number?";
        createAcc[8] = request.getParameter("ans1");
        createAcc[9] = request.getParameter("ans2");
        createAcc[10] = request.getParameter("ans3");

        ManipulateDB mDb = new ManipulateDB();

        if (mDb.insertUserDb(createAcc) == 1) {
            System.out.println("Account Created");
            System.out.println("Account Name: " + createAcc[1]);
            //user_session.setAttribute("work", "worked");
            response.sendRedirect("login.jsp");

        } else {
            System.out.println("Failed to create account");
            //user_session.setAttribute("work", "failed");
            response.sendRedirect("login.jsp");
        }

    }

}
