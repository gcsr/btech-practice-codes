<%@ page import="java.sql.*,java.io.*"  %>
<%@ page import="java.util.date.*,java.util.text.DateFormat.*,java.text.ParseException.*"%>
<%@page import="com.oreilly.servlet.*,java.sql.*,java.lang.*,databaseconnection.*,java.text.SimpleDateFormat,java.util.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%

String asw=(String)session.getAttribute("ccc");
  Blob b=null;
  String a=null;
  
  


try
{
	
Connection con = databasecon.getconnection();
PreparedStatement ps=con.prepareStatement("select fileid,fname from fileupload where id='"+asw+"'" );
ResultSet rs=ps.executeQuery();
if(rs.next())
{
b = rs.getBlob(1);
a=rs.getString(2);
String filename =null;
			filename+=".doc";
			byte[] ba = b.getBytes(1, (int)b.length());
			response.setContentType("application/msword");
			response.setHeader("Content-Disposition","attachment; filename=\""+a+"\"");
			OutputStream os = response.getOutputStream();
			os.write(ba);
			os.close();
			ba = null;
}

}
catch(Exception e)
{
out.println(e.getMessage());
}
	




%>
</body>
</html>
