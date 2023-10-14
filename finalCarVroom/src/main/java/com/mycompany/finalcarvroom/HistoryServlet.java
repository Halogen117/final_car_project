/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author mokda
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {"/getHistory", "/insertHistory"})
public class HistoryServlet extends HttpServlet {

    private HistoryDAO historyDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init() {
        historyDAO = new HistoryDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if ("/getHistory".equals(action)) {
            try {
                PrintWriter out = response.getWriter();
                
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                
                String userID = request.getParameter("userID");
                String days = request.getParameter("days");
                
                List<History> historyCarpark = historyDAO.getHistory(userID, days);
                JSONArray jsArray = new JSONArray(historyCarpark);
                out.print(jsArray);
                out.flush();
            } catch (SQLException ex) {
                Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            switch (action) {
                case "/insertHistory":
                    insertHistory(request, response);
                    break;
                case "/deleteHistory":
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertHistory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String userID = request.getParameter("userID");
        String carparkID = request.getParameter("carparkID");
        historyDAO.insertHistory(userID, carparkID);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
