/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.util.Random;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
class RandomStringGenerator{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
                response.setContentType("text/plain");
                HttpSession user_session = request.getSession();
                ManipulateDB mDb = new ManipulateDB();
                ConnectDB cDb = new ConnectDB();
                String norm_pass = request.getParameter("inputPassword");
                String repeat_pass = request.getParameter("repeatPassword");
                // Regex
                String name_pattern = "^[a-zA-Z0-9]{1,}$";
                String password_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
                String phone_pattern = "^(\\d{4} ?\\d{4})$";
                if(!request.getParameter("firstName").matches(name_pattern)){
                    user_session.setAttribute("work", "first_name_blank");
                    System.out.println("First Name should not be blank!");
                    response.sendRedirect("register.jsp");
                    return;
                    
                }else if(!request.getParameter("lastName").matches(name_pattern)){
                    user_session.setAttribute("work", "last_name_blank");
                    System.out.println("Last Name should not be blank!");
                    response.sendRedirect("register.jsp");
                    return;       
                }else if(!request.getParameter("phoneNumber").matches(phone_pattern)){
                    user_session.setAttribute("work", "phone_no");
                    System.out.println("Phone Number format not correct");
                    response.sendRedirect("register.jsp");
                    return;   
                }
                // Password Error Checking
                else if(!norm_pass.equals(repeat_pass)){
                    user_session.setAttribute("work", "not_same_password");
                    System.out.println("Different Password!");
                    response.sendRedirect("register.jsp");
                    return;
                    // Error in password;
                }
                else if(!norm_pass.matches(password_pattern)){
                    user_session.setAttribute("work", "pass_req_no");
                    System.out.println("Your password sucks! Please change");
                    response.sendRedirect("register.jsp");
                    return;       
                }else if(mDb.checkEmailExist(cDb.getConnection(),request.getParameter("inputEmail"))){
                    user_session.setAttribute("work", "same_email");
                    System.out.println("Email exists in the database!");
                    response.sendRedirect("register.jsp");
                    return;       
                }
                
                System.out.println("Passed!");
                String [] createAcc = new String[11];
                RandomStringGenerator gen = new RandomStringGenerator();
                System.out.println(gen.generateRandomString(16));
                createAcc[0] = gen.generateRandomString(16);
		createAcc[1] = request.getParameter("firstName") +" "+ request.getParameter("lastName");
                createAcc[2] = request.getParameter("inputEmail");
                createAcc[3] = PasswordManager.hashPassword(request.getParameter("inputPassword"));
                createAcc[4] = request.getParameter("phoneNumber");      
                createAcc[5] = "What was the name of your first pet?";
                createAcc[6] = "What is your favorite movie?";
                createAcc[7] =  "What is your lucky number?";
                createAcc[8] = request.getParameter("ans1");       
                createAcc[9] = request.getParameter("ans2"); 
                createAcc[10] = request.getParameter("ans3"); 
                
                
                int result = mDb.insertUserDb(createAcc);
                if(result == 1){
                    user_session.setAttribute("work", "worked");
                    response.sendRedirect("login.jsp");
                    return;
                }else if(result == 0){
                    user_session.setAttribute("work", "email_exist");
                    response.sendRedirect("register.jsp");
                    return;
                        
                        
                }else if(result == -1){
                    user_session.setAttribute("work", "phone_exist");
                    response.sendRedirect("register.jsp");
                    return;
                        
                        
                }else if(result == -2){
                    user_session.setAttribute("work", "unknown_error");
                    response.sendRedirect("register.jsp");
                    return;
                        
                        
                }else{
                    user_session.setAttribute("work", "failed");
                    response.sendRedirect("login.jsp");
                    return;
                    
                }
                
                
                
                
                
		
		
	}
    
}