/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Group3.UserDB;
import entity.User;

import java.io.*;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("Username");
		String password = request.getParameter("Password");

//        if(Username.equals("jsmith@toba.com") && Password.equals("letmein")){
//              
//            RequestDispatcher rs = request.getRequestDispatcher("Account_activity.html");
//            rs.forward(request, response);
//        }
//        else
//        {
//           RequestDispatcher rs = request.getRequestDispatcher("Login_failure.html");
//           rs.include(request, response);
//        }
		List<User> users = UserDB.findBy(username, getHash(password));
		if (users.size() == 1) {
			request.getSession().setAttribute("user", users.get(0));
			RequestDispatcher rs = request.getRequestDispatcher("User_activity.jsp");
			rs.forward(request, response);
		} else {
			RequestDispatcher rs = request.getRequestDispatcher("Login_failure.jsp");
			rs.include(request, response);
		}
	}
	
	public String getHash(String password) {
		byte[] salt=new byte[]{3,3,3,3,3,3,3,3};
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			byte[] bt=digest.digest(password.getBytes("UTF-8"));
			return new String(bt);
		}catch(Exception ex){
			return password;
		}
	 }
}
