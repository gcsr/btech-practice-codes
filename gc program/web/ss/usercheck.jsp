<%@page import="com.oreilly.servlet.*,java.sql.*,databaseconnection.*,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%

Statement st = null;
ResultSet rs = null;
String email = request.getParameter("email");
String password = request.getParameter("password");
String userid=null;
String name=null;

try{
	 Connection con = databasecon.getconnection();
	st = con.createStatement();
	String qry =" select  * from user where email='"+email+"' and password='"+password+"'  "; 
	rs = st.executeQuery(qry);
	if(!rs.next()){
	out.println("Enter correct emailId, password");
	
	}
	else{
	userid=rs.getString("userid");
	session.setAttribute("userid",userid);
	name=rs.getString("name");
	session.setAttribute("name",name);
	session.setAttribute("email",email);
	session.setAttribute("password",password);
	response.sendRedirect("userpage.jsp");	
	}
	con.close();
	st.close();
}
catch(Exception ex){
	out.println(ex);
}
%>
</body>
</html>


