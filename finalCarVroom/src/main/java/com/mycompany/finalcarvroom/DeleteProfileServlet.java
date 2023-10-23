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
@WebServlet(name = "DeleteProfileServlet", urlPatterns = {"/DeleteProfileServlet"})
public class DeleteProfileServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
                response.setContentType("text/plain");
                HttpSession user_session = request.getSession();
                ManipulateDB mDb = new ManipulateDB();
                ConnectDB cDb = new ConnectDB();
                HistoryDAO hisdao = new HistoryDAO();
                
                // Search for Profile
                String[] profile_acq = mDb.getUserDetails((String)user_session.getAttribute("userId"));
                // Delete Profile from the face of existance
                if(mDb.checkEmailExist(cDb.getConnection(), profile_acq[2])){
                    System.out.println("Email found! Deleting account!");
                    boolean deletion = mDb.deleteUserData(profile_acq[2]);
                    boolean deletion_fav_tab = mDb.deleteFavData(profile_acq[0]);
                    boolean deletion_history = hisdao.deleteAllHistory(profile_acq[0]);
                    if(mDb.checkEmailExist(cDb.getConnection(), profile_acq[2]) == false && deletion_history == true && deletion_fav_tab == true && deletion == true){
                        System.out.println("Successful Deletion of User Account!");
                        user_session.setAttribute("work", "delete_success");
                        response.sendRedirect("login.jsp");
                        return;
                        
                    }else{
                        System.out.println("Email Deletion of User Account has failed!");
                        user_session.setAttribute("update_prof", "user_fail");
                        response.sendRedirect("ProfileServlet");
                        return;
                    }
                }

		
	}

}
