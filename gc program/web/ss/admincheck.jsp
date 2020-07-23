<%@ page import="java.sql.*" import="databaseconnection.*" errorPage="" %>

<%
String username=request.getParameter("username");

String password=request.getParameter("password");

String a="admin";
String b="admin";

	
	if((a.equals(username)) && (b.equals(password)))
	{
	response.sendRedirect("adminpage.jsp");
	}
	else
	{
	out.println("enter correct username and password");
	}



%>
