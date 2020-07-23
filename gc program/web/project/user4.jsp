<%@ page import="java.sql.*,databaseconnection.*"%>

<%


	
   
	String a=(String)session.getAttribute("id");
	String b=request.getParameter("pass");
String name3=null;

	try{
		Connection con = databasecon.getconnection();
		PreparedStatement ps=con.prepareStatement("select name from user where id='"+a+"' AND key1='"+b+"'");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
		name3=rs.getString(1);
		
		//role=rs.getString(3);
		session.setAttribute("name3",name3);
			
		response.sendRedirect("user5.jsp");
		
		}
		else
		{
		out.println("enter correct id and password");
		}
		

	}
	catch(Exception e2)
	{
		out.println(e2.getMessage());
	}
	
%>

