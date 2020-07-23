/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

import Group3.UserDB;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shawn_000
 */
public class Validate2 extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/NewCustomer.jsp";

		String action = request.getParameter("action");
		if (action == null) {
			action = "join";

		}
		switch (action) {
			case "join":
				url = "/NewCustomer.jsp";
				break;
			case "add":
				String Firstname = request.getParameter("Firstname");
				String Lastname = request.getParameter("Lastname");
				String Phone = request.getParameter("Phone");
				String Address = request.getParameter("Address");
				String City = request.getParameter("City");
				String State = request.getParameter("State");
				String Zipcode = request.getParameter("Zipcode");
				String Email = request.getParameter("Email");
				User user = new User("", "", Firstname, Lastname, Phone, Address, City, State, Zipcode, Email);
				String message;
				if (Firstname == null || Lastname == null || Phone == null
						|| Address == null || City == null || State == null
						|| Zipcode == null || Email == null || Firstname.isEmpty()
						|| Lastname.isEmpty() || Phone.isEmpty() || Address.isEmpty()
						|| City.isEmpty() || State.isEmpty() || Zipcode.isEmpty()
						|| Email.isEmpty()) {
					message = "Please fill out all of the form fields.";
					url = "/NewCustomer.jsp";
				} else {
					message = "";
					url = "/Success2.jsp";
					UserDB.insert(user);
				}
				request.setAttribute("user", user);
				request.setAttribute("message", message);
				break;
		}
		getServletContext()
				.getRequestDispatcher(url)
				.forward(request, response);

	}

}
