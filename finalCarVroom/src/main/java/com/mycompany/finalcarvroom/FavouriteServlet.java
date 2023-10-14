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
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author mokda
 */
@WebServlet(name = "FavouriteServlet", urlPatterns = {"/insertFavourite", "/deleteFavourite", "/getFavourite"})
public class FavouriteServlet extends HttpServlet {

    private FavouriteDAO favouriteDAO;

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
        favouriteDAO = new FavouriteDAO();
    }

    //Ignore this method
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HttpSession user_session = request.getSession();

        PrintWriter out = response.getWriter();



        manipulateDb mDb = new manipulateDb();
        String userID = request.getParameter("userID");
        String carparkID = request.getParameter("carparkID");
        System.out.println("inertion");
        mDb.insertFavDb(null, userID, carparkID);
        out.println("<html><body><b>Successfully Normal Inserted"
                + "</b></body></html>");
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
        if ("/getFavourite".equals(action)) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String userID = request.getParameter("userID");
            List<String> listFavourite = favouriteDAO.getAllFavourites(userID);
            JSONArray jsArray = new JSONArray(listFavourite);
            out.print(jsArray);
            out.flush();
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
        String action = request.getServletPath();
        switch (action) {
            case "/insertFavourite":
                insertFavourite(request, response);
                break;
            case "/deleteFavourite":
                deleteFavourite(request, response);
                break;
        }


    }

    private void insertFavourite(HttpServletRequest request, HttpServletResponse response) {
        String userID = request.getParameter("userID");
        String carparkID = request.getParameter("carparkID");
        favouriteDAO.insertFavourite(userID, carparkID);
    }

    private void deleteFavourite(HttpServletRequest request, HttpServletResponse response) {
        String userID = request.getParameter("userID");
        String carparkID = request.getParameter("carparkID");
        favouriteDAO.deleteFavourite(userID, carparkID);
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
