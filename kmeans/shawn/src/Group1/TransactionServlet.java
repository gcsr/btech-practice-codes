/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group1;

import Group3.AccountDB;
import Group3.TransactionDB;
import Group3.UserDB;
import entity.Account;
import entity.Transaction;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shawn_000
 */
public class TransactionServlet extends HttpServlet {

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
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Transaction</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet Transaction at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
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
		processRequest(request, response);
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
		//processRequest(request, response);
		String action = request.getParameter("action");
		int fromAccountId = Integer.parseInt(request.getParameter("from-account"));
		int toAccountId = Integer.parseInt(request.getParameter("to-account"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String message = "";

		if (fromAccountId > 0 && toAccountId > 0 && amount > 0) {
			Account fromAccount = AccountDB.findBy(fromAccountId);
			Account toAccount = AccountDB.findBy(toAccountId);
			if (toAccount == null) {
				message = "AccountId '" + toAccountId + "' does not exist.";
				request.setAttribute("message", message);
				getServletContext()
						.getRequestDispatcher("/Accounts.jsp")
						.forward(request, response);
				return;
			}

			Transaction transaction = new Transaction();
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setAmount(amount);
			transaction.setDatetime(new Date());

			if (fromAccount.debit(amount) == 0) {
				message = "AccountId '" + fromAccountId + "' has balance of '" + fromAccount.getBalance() + "' < '" + amount + "'.";
				request.setAttribute("message", message);
				getServletContext()
						.getRequestDispatcher("/Accounts.jsp")
						.forward(request, response);
				return;
			}

			if (TransactionDB.insert(transaction) == 0) {
				message = "Can not process transaction.";
			} else {				
				AccountDB.update(fromAccountId, fromAccount.getBalance());
				toAccount.credit(amount);
				AccountDB.update(toAccountId, toAccount.getBalance());
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				session.setAttribute("user", UserDB.findBy(user.getUsername()));
				message = "Process transaction successfully.";
			}
		}

		request.setAttribute("message", message);
		getServletContext()
				.getRequestDispatcher("/Accounts.jsp")
				.forward(request, response);
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
