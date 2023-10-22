/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sk37chy
 */

@WebServlet(name = "SecurityQnServlet", urlPatterns = {"/SecurityQnServlet"})

public class SecurityQnServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Authentication checkAns = new Authentication();
            HttpSession user_session = request.getSession();
            String firstAns = request.getParameter("ans1");
            String secondAns = request.getParameter("ans2");
            String thirdAns = request.getParameter("ans3");
            
            boolean results = checkAns.verification((String)user_session.getAttribute("userId"),
                    firstAns, secondAns, thirdAns);
            if(results){
                user_session.setAttribute("security_qn", "pass");
                response.sendRedirect("changePassword.jsp");
            }
            else
            {
                user_session.setAttribute("security_qn", "wrong");
                response.sendRedirect("securityQn.jsp");
            }
        }
}
