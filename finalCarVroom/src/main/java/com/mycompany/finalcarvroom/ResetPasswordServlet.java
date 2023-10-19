/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Halogen
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet"})
public class ResetPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
    // Update the password if it matches
    Authentication auth = new Authentication();
    PasswordManager pass_man = new PasswordManager();
    ManipulateDB mdb = new ManipulateDB();
    HttpSession user_session = request.getSession();
    String[] return_decode = auth.return_encoded_link(user_session.getAttribute("authenticator").toString());
    String norm_pass = request.getParameter("retypePass");
    String repeat_pass = request.getParameter("passAgain");
    String password_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    if(!norm_pass.equals(repeat_pass)){
        user_session.setAttribute("verify_reset", "not_same_password");
        System.out.println("Different Password!");
        response.sendRedirect("reset-password.jsp?id="+user_session.getAttribute("authenticator"));
        return;
        // Error in password;
    }
    else if(!norm_pass.matches(password_pattern)){
        user_session.setAttribute("verify_reset", "weak_pass");
        System.out.println("Your password sucks! Please change");
        response.sendRedirect("reset-password.jsp?id="+user_session.getAttribute("authenticator"));
        return;       
    }else if(pass_man.verifyPassword(norm_pass, mdb.getUserDetails(return_decode[0])[3])){
        // Check database for password
        System.out.println("Please enter a different password!");
        user_session.setAttribute("verify_reset", "same_pass");
        response.sendRedirect("reset-password.jsp?id="+user_session.getAttribute("authenticator"));
        return;
        
    }
    
    
    // Update Database
    mdb.updateProfileDetails(return_decode[0],pass_man.hashPassword(norm_pass) , "password");
    user_session.setAttribute("work", "change_pass_success");
    System.out.println("Password change was successful!");
    response.sendRedirect("login.jsp");
    return;   
    }
}
