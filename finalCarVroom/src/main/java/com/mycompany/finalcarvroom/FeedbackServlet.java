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
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackServlet"})
public class FeedbackServlet extends HttpServlet {
    
    
    protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
        System.out.println("PASSED AWAY");
        HttpSession user_session = request.getSession();
        String feedback = request.getParameter("input-feedback");
        String user_id = user_session.getAttribute("username").toString();
        System.out.println(feedback);
        ManipulateDB mdb = new ManipulateDB();
        System.out.println(user_id);
        if(mdb.insertFeedbackDb(user_id, feedback)==1){
            user_session.setAttribute("add_feedback", "success");
            System.out.println("Feedback was successful!");
            response.sendRedirect("profile.jsp");
            return;   
            
        }
        
        
        
        
        
        
        
        
    
    }

}
