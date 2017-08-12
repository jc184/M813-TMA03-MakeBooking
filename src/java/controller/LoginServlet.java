/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author james
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    // <editor-fold defaultstate="expanded" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        doPost(request, response);
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
        /**
         * Takes user login details from index login form checks them against
         * entries in the database, and accepts or rejects the login on this
         * basis
         */
        Connection loginConnection = null;
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String dbName = "flightbookingsystem";
        String driver = "com.mysql.jdbc.Driver";
        String dbUserName = "root";
        String dbPassword = "stcallans";

        //int customerID = 0;
        String loginName = "";
        String loginPassword = "";
        String loginQuery = "";
        Statement loginStmt = null;
        ResultSet loginRslt = null;
        HttpSession session;
        session = request.getSession(true);

        try {
            Class.forName(driver).newInstance();
            loginConnection = DriverManager.getConnection(dbUrl + dbName, dbUserName, dbPassword);
            if (request.getParameter("loginName") != null && !request.getParameter("loginName").equals("")
                    && request.getParameter("loginPassword") != null && !request.getParameter("loginPassword").equals("")) {
                loginName = request.getParameter("loginName");
                loginPassword = request.getParameter("loginPassword");
                loginQuery = "select CustomerId, loginName, loginPassword from customer where loginName='" + loginName + "' and loginPassword='" + loginPassword + "'";
                loginStmt = loginConnection.createStatement();
                loginRslt = loginStmt.executeQuery(loginQuery);
                int count = 0;
                while (loginRslt.next()) {
                    session.setAttribute("loginName", loginRslt.getString(2));
                    session.setAttribute("customerId", loginRslt.getInt(1));
                    count++;
                }
                if (count > 0) {
                    response.sendRedirect("makeBooking.jsp");
                } else {
                    response.sendRedirect("loginerror.jsp");
                }
            } else {
                response.sendRedirect("loginerror.jsp");
            }

            loginConnection.close();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

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
