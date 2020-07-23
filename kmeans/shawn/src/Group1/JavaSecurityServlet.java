/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

import java.io.IOException;
import java.security.Security;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shawn_000
 */
public class JavaSecurityServlet extends HttpServlet { private static final long serialVersionUID = 1L;
    Security security = new Security();
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavaSecurityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("txtUserId");
        String password = request.getParameter("txtPassword");
        if(security.verifyPassword(userID, password))
            response.getOutputStream().println("Login Successful! Welcome "+userID+"!!!");
        else
            response.getOutputStream().println("Login failed! Please check the username/password and try again");
 
    }
}
