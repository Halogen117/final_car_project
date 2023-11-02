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
@WebServlet(name = "forgetPassword", urlPatterns = {"/forgetPassword"})
public class forgetPassword extends HttpServlet {

    protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
        String email_retr = request.getParameter("inputEmail");
        HttpSession user_session = request.getSession();
        // Check if email exist in the database
        System.out.println(email_retr);
        // If email exists, proceed
        ManipulateDB mdb = new ManipulateDB();
        ConnectDB conn = new ConnectDB();
        
        if(mdb.checkEmailExist(conn.getConnection(), email_retr)){
            System.out.println("Email Exists!");
            Authentication auth = new Authentication();
            
            try{
                String email_message = String.format("""
                                    Dear User: %s,
                                       
                                    To change your password, click the link here!
                                    http://localhost:8080/finalCarVroom/reset-password.jsp?id=%s
                                    \n
                                    Ignore this email if you need not reset your password!
                                    \n
                                    Best Regards, 
                                    Car Park Near U""",mdb.getUserDetails(mdb.getNameIdDB(email_retr)[0])[2], auth.encoder_link(email_retr));
                
                new Gmailer().sendMail("Change your password", email_message,email_retr);
                user_session.setAttribute("verify_email", "success");
                System.out.println("Successfully sent!");
                response.sendRedirect("login.jsp");
                return; 
                
        }catch(Exception e){
            System.out.println(e);
        }
        }else{
                // No email
                user_session.setAttribute("verify_email", "fail");
                System.out.println("The email you inserted is invalid. Please retype!");
                response.sendRedirect("forgot-password.jsp");
                return;  
        }
        
        

        
    }
}



