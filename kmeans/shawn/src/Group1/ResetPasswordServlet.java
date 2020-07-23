/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

import Group3.UserDB;
import entity.User;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shawn_000
 */
public class ResetPasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//String action = request.getParameter("action");
		String newPassword = request.getParameter("newpassword");
		String message = "";
		
		if (newPassword != null && !newPassword.isEmpty()) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if (user != null) {
				if (UserDB.update(user.getUsername(), getHash(newPassword)) == 0) {
					message = "Reset password failed.";
				} else {
					message = "Reset password successfully.";
					user.setPassword(newPassword);
				}
			}
		}
		
		request.setAttribute("message", message);
		getServletContext()
				.getRequestDispatcher("/User_activity.jsp")
				.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
