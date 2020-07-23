/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Action;

import com.util.Constant;
import com.util.DbConnector;
import com.util.SimpleFTPClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sabari
 */
public class FileDownload extends HttpServlet {

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
        SimpleFTPClient client = null;
        try {
            String val = request.getParameter("ip");
            String file = request.getParameter("filename");
            if(val=="" || file==""){
            response.sendRedirect("userHome.jsp?msg=Invalid Request");
            }
            Connection con = DbConnector.getConnection();
            PreparedStatement pstm = null;
            PreparedStatement pstm1 = null;
            PreparedStatement pstm2 = null;
            String sql = "select * from cloud where ip='"+val+"'";
            pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String user = (String) request.getSession().getAttribute("userid");
            if (rs.next()) {
               client = new SimpleFTPClient();
               client.setUser(rs.getString("user"));
               client.setPassword(rs.getString("password"));
               client.setHost(rs.getString("ip"));
               client.setRemoteFile(file);
               
               client.connect();
               if(client.downloadFile(Constant.file+file)){                   
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user);
                        String sq2 = "insert into transaction values('" + user + "','" + file + "','Success','"+val+"',now(),'Download')";
                        pstm1 = (PreparedStatement) con.prepareStatement(sq2);
                        pstm1.executeUpdate();
               }else{
                   String sq3 = "insert into transaction values('" + user + "','" + file + "','Failed','"+val+"',now(),'Download')";
                        pstm2 = (PreparedStatement) con.prepareStatement(sq3);
                        pstm2.executeUpdate();
               }
            }
            response.sendRedirect("userHome.jsp?msg=Check Status in Home");
        } catch (Exception e) {
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
