<%@ page import="java.sql.*"%>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ page import="java.sql.*,databaseconnection.*"%>
<%@ page import="java.sql.*"%>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<html>
<head>
<title>Privacy-Preserving Public Auditing for
Secure Cloud Storage</title>
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">



</head>
<body id="page1" onLoad="new ElementMaxHeight();">
<%
 java.util.Date now = new java.util.Date();
	 String DATE_FORMAT1 = "dd/MM/yyyy";
	 SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
     String strDateNew1 = sdf1.format(now);
	 %>
<div class="tail-bottom">
	
  <div id="main"> 
    <!-- header -->
    <header> 
    <div class="nav-box"> <nav> 
      <ul>
        <li><a href="admin.jsp">Admin</a></li>
        <li><a href="tpa.jsp">Tpa</a></li>
        <li><a href="owner.jsp">User</a></li>
        <li><a href="signup.jsp">Signup</a></li>
      </ul>
      </nav> </div>
    <img src="images/logo.jpg" width="600" height="100"> </header> 
    <div class="wrapper indent">
<!-- content -->
			<section id="content"><div class="ic"></div>
				<h2>Welcome:<% String namew=(String)session.getAttribute("name");
		%> <%=namew%></h2>
		<table><td width="200"></td><td>
      <table border="1" bgcolor="#66CCFF">
          <tr> 
           
            <td bgcolor="#FFFFFF"><font color="#663366" size="+1">File Name</font></td>
			<td bgcolor="#FFFFFF"><font color="#663366" size="+1">File Id</font></td>
            <td bgcolor="#FFFFFF"><font color="#663366" size="+1">Size</font></td>
           <td bgcolor="#FFFFFF"><font color="#663366" size="+1">Date</font></td>
          <td bgcolor="#FFFFFF"><font color="#663366" size="+1">Status</font></td>
			<td bgcolor="#FFFFFF"><font color="#663366" size="+1">Packet Sending</font></td>
			
			  
          </tr>
          <%
		  String ccc=(String)session.getAttribute("id");
String s=null,s1=null,s2=null,s3=null,s4=null,s5=null,s6=null;
String x="waiting";
int id=0;
String id1=null;
try
{
Connection con = databasecon.getconnection();
PreparedStatement ps=con.prepareStatement("select fname,fid,size,date,id,status from fileupload where uid='"+ccc+"'");
ResultSet rs=ps.executeQuery();
	
while(rs.next())
 {
 s=rs.getString(1);
  s1=rs.getString(2);
   s2=rs.getString(3);
    s3=rs.getString(4);
	s4=rs.getString(6);
id1=rs.getString(5);

 %>
          <tr> 
            <td> 
              <font color="#0000000"><%=s%></font>
            </td>
            <td> 
                <font color="#0000000"><%=s1%></font>
            </td>
                <td> 
               <font color="#0000000"><%=s2%>kb</font>
            </td>
                 <td> 
               <font color="#0000000"><%=s3%></font>
            </td>
	<td> 
               <font color="#0000000"><%=s4%></font>
            </td>
	
                   <td>  <a href="packet.jsp?<%=id1%>"><font color="#0000000">CLICK</font></a></td>
			  
          </tr>
          <%
		  
}

}
 catch(Exception e1)
{
out.println(e1.getMessage());

}
%>
        </table></td>
    </table>  
	
	
    
     
				<div class="inside">
					
				</div>
				<div class="wrapper">
					<article class="col-1 maxheight">
						<div class="box maxheight">
							<div class="border-right maxheight">
								<div class="border-bot maxheight">
									<div class="border-left maxheight">
										<div class="left-top-corner maxheight">
											<div class="right-top-corner maxheight">
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</article>
					<article class="col-2 maxheight">
						<div class="box maxheight">
							<div class="border-right maxheight">
								<div class="border-bot maxheight">
									
								</div>
							</div>
						</div>
					</article>
				</div>
			</section>
<!-- aside -->
			<aside>
				<div class="inside">
					<ul class="insurance">
						<li><a href="user5.jsp"><font size="+2">Home</font></a></li>
					<li><a href="fileupload.jsp"><font size="+2">File Upload</font></a></li>
					<li><a href="detail.jsp"><font size="+2">Packet Sending</font></a></li>
						<li><a href="download.jsp"><font size="+2">Download</font></a></li>
					<li><a href="logout.jsp"><font size="+2">Logout</font></a></li>
					</ul>
					
					
				</div>
			</aside>
		</div>
<!-- footer -->
		
	</div>
</div>
</body>
</html>