/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;
import javax.mail.Session;
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
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})

public class ChangePasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currPassword = request.getParameter("currPassword");
        String newPassword = request.getParameter("newPassword");

        HttpSession user_session = request.getSession();
        Authentication checkPass = new Authentication();
        ManipulateDB mDB = new ManipulateDB();
        String userId_Session = (String) user_session.getAttribute("userId");

        boolean checkPwdSame = checkPass.checkCurrPassword(userId_Session, currPassword);
        if (checkPwdSame) {
            user_session.setAttribute("update_pwd", "success");
            String newPassHashed = PasswordManager.hashPassword(newPassword);
            mDB.updateProfileDetails(userId_Session, newPassHashed, "password");
            response.sendRedirect("ProfileServlet");
        } else {
            user_session.setAttribute("update_pwd", "wrong_pwd");
            System.out.println("Failed due to error");
            response.sendRedirect("changePassword.jsp");
        }
    }
}
