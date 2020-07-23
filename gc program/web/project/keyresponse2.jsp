<%@ page import="java.sql.*,databaseconnection.*"%>
<%
String ccc=request.getQueryString();
String sat="sent to client";
try
{
Connection con1 = databasecon.getconnection();
PreparedStatement ps1=con1.prepareStatement("update fileupload set status='"+sat+"' where id='"+ccc+"'");
ps1.executeUpdate();
 
}
 catch(Exception e11)
{
out.println(e11.getMessage());

}
response.sendRedirect("tpa2.jsp");
%>