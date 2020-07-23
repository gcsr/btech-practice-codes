/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

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
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String fileName =request.getParameter("fileName"); 
    	
    	response.setContentType("APPLICATION/OCTET-STREAM"); 
    	response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");        
        String ip = request.getParameter("ip");
        String file = request.getParameter("filename");
        
        File f=new File("D:/"+ip+"/"+fileName);
        if(!f.exists()){
        	out.write("cloud or file is not available at this time");
        }
        
        else{
        	FileInputStream fileInputStream = new FileInputStream("D:/"+ip+"/"+fileName);
  		  
        	int i; 
        	while ((i=fileInputStream.read()) != -1) {
        	out.write(i); 
        	} 
        	fileInputStream.close(); 
        	out.close();
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
