<%@ page import="java.util.date.*,java.util.text.DateFormat.*,java.text.ParseException.*"%>
<%@page import="com.oreilly.servlet.*,java.sql.*,java.lang.*,databaseconnection.*,java.text.SimpleDateFormat,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>	 
<%@ page import="java.sql.*,databaseconnection.*"%>
<%@page import=" java.security.*"%>
<%@page import="javax.crypto.*"%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript">

</script>
</head>

<body>



 
<%

String id=request.getParameter("id");
String hotel=request.getParameter("hotel");
String famousfor=request.getParameter("famousfor");
String distance=request.getParameter("distance");





PreparedStatement psmt1=null;
try
{



Connection con = databasecon.getconnection();
psmt1=con.prepareStatement("insert into hotel   values(?,?,?,?)");


psmt1.setString(1,id);
psmt1.setString(2,hotel);
psmt1.setString(3,famousfor);
psmt1.setString(4,distance);




psmt1.executeUpdate();


response.sendRedirect("adminpage.jsp?message=success");


}
catch(Exception ex)
{

out.println("Error in connection : "+ex);

}



%>



</body>
</html>