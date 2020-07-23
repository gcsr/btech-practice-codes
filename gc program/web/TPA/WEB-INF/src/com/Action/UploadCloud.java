/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Action;

import com.util.DbConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sabari
 */
public class UploadCloud extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PreparedStatement pst = null;
        Connection conn = null;
        String ip="";
        try {
            conn = (Connection) DbConnector.getConnection();
            String name = request.getParameter("user").trim();
            String password = request.getParameter("password").trim();
             ip = request.getParameter("ip").trim();
            String status = request.getParameter("status");
            String sql="update cloud set user_id='"+name+"', password='"+password+"', status='"+status+"' where ip='"+ip+"'";
            System.out.println(">>"+sql);
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.executeUpdate();
            response.sendRedirect("updateCloud.jsp?ip="+ip+"+&msg= Updated Successfully");
        } catch (Exception e) {
		e.printStackTrace();
            response.sendRedirect("updateCloud.jsp?ip="+ip+"+&msg=Failed");
            e.printStackTrace();

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
