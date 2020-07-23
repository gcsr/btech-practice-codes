<%@ page import="java.sql.*,databaseconnection.*"%>
<%
String ccc=(String)session.getAttribute("ccc");
String s1=(String)session.getAttribute("s1");
String by1=request.getParameter("by1");
String block1=request.getParameter("block1");
String by2=request.getParameter("by2");
String block2=request.getParameter("block2");
String by3=request.getParameter("by3");
	String block3=request.getParameter("block3");
String sat="sent to tpa";
try
{
Connection con1 = databasecon.getconnection();
PreparedStatement ps1=con1.prepareStatement("update fileupload set status='"+sat+"',block1='"+block1+"',block2='"+block2+"',block3='"+block3+"',meta1='"+by1+"',meta2='"+by2+"',meta3='"+by3+"' where id='"+ccc+"'");
ps1.executeUpdate();
 
}
 catch(Exception e11)
{
out.println(e11.getMessage());

}
response.sendRedirect("user5.jsp");
%>