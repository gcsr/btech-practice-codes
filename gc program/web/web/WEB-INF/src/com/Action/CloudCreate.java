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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sabari
 */
public class CloudCreate extends HttpServlet {

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
        try {
            conn = (Connection) DbConnector.getConnection();
            String name = request.getParameter("user").trim();
            String password = request.getParameter("password").trim();
            String ip = request.getParameter("ip").trim();
            String status = request.getParameter("status");
            if (name != null && name != "" && password != null && password != "" && ip != null && ip != "" && status != null ) {
                String sql = "insert into cloud values('" + ip + "','" + name + "','" + password + "','" + status + )";
                System.out.println(">>" + sql);
                pst = (PreparedStatement) conn.prepareStatement(sql);
                int a = pst.executeUpdate();
                if (a > 0) {
                    response.sendRedirect("createCloud.jsp?msg=Registration Done");
                } else {
                    response.sendRedirect("createCloud.jsp?msg=UserId Already Exit");
                }
            } else {
                response.sendRedirect("createCloud.jsp?msg=Fill All The Fileds");
            }
        } catch (Exception e) {
e.printStackTrace();
            response.sendRedirect("createCloud.jsp?msg=UserId Already Exit");
            e.printStackTrace();

        }finally{
            try {
                if(conn!=null)
                conn.close();
                if(pst !=null)
                    pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CloudCreate.class.getName()).log(Level.SEVERE, null, ex);
            }
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
