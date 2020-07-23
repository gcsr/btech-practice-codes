<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.oreilly.servlet.*,java.sql.*,java.lang.*,databaseconnection.*,java.text.SimpleDateFormat,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"  errorPage="Error.jsp"%>
<%@page import=" java.security.MessageDigest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Fast Nearest Neighbor Search with Keywords</title>
<meta name="keywords" content="City Construction, Web Template, XHTML CSS" />
<meta name="description" content="City Construction, Web Template, XHTML CSS layout provided by TemplateMo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
		content_main2.C1{background-color:#CCCCCC;}
		form.f1{background-color:#CCCCCC;}
		form1.f5{background-color:#CCCCCC;}
		input.sub{background-color:#009900;}
		input.sub1{background-color:#990000;}
		input.sub2{background-color:#009900;}
		input.sub3{background-color:#CC3366;}
		input.sub4{background-color:#FF6666;}
		input.sub5{background-color:#99FFFF;}
		content_maina1.a{background-color:#CCCCCC;}
		td.d{color:#FF9900;}
		tr.r{color:#FF9900;}
		tr.r1{color:#000000;}
		</style>
</head>
<body>
<div id="templatmeo_container">
	        <div id="site_title">
        	<h1><a href="#">
            	
                <span><font color="#FFFFFF" face="Times New Roman, Times, serif" size="+2"><strong><em>Fast Nearest Neighbor Search with Keywords</em></strong></font></span>
            </a></h1>
        </div>
    
  

    
    <div id="templatemo_menu">
        <ul id="templatemo_left_menu">
            
      <li><a href="index1.jsp" ><span></span>Home</a></li>
            
      
	  
	  <li><a href="javascript:history.go(-1)"><span></span>Back</a></li>
           
        </ul>
        
		<ul id="templatemo_right_menu">
            <li><a href="#" class="first">Sitemap</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </div> <!-- end of menu -->
    
    <div id="templatemo_banner">
    	
    </div> <!-- end of banner -->
    
    <div id="templatemo_content">
    
    	<table  width="800">
		<tr>
		<td><form name="form" method="post" action="inserthotel.jsp">
		
		
		 <%
			  
			 
			  
			  
				int n=0,s=0;
 				Connection con=null;
				Statement st=null;
				ResultSet rs=null;
				String sql="select max(id) from hotel";
				try
	{
		//con=databasecon.getconnection();
con = databasecon.getconnection();
		st=con.createStatement();
		rs=st.executeQuery(sql);
		if(rs.next())
		{
		if(rs.getInt(1)==0)
		n=1;
		else
		n=rs.getInt(1)+1;
		session.setAttribute("id", n );
		}
				%>

		
		<table width="300" height="200"  >
		
		<tr> 
                  <td colspan="2" align="justify"><strong><font  color="#FF0000" size="3">Hotel- 
                    Registration</font></strong></td>
                </tr>
                <tr> 
                  <td ><font color="#FFFFFF"><strong>Id:</strong></font></td>
                  <td><input type="text" name="id"  value="<%=n%>" ></td>
                </tr>
               <tr> 
                  <td ><font color="#FFFFFF"><strong>Hotel:</strong></font></td>
                  <td><input type="text" name="hotel" ></td>
                </tr>
                
				<tr> 
                  <td ><font color="#FFFFFF"><strong>Famous_for:</strong></font></td>
                  <td><input type="text" name="famousfor" ></td>
                </tr>
                
             
				<tr> 
                  <td ><font color="#FFFFFF"><strong>Distance:</strong></font></td>
                  <td><input type="text" name="distance" ></td>
				  <td ><font color="#FFFFFF">km</font></td>
                </tr>
			 
                <tr> 
                  <td><input type="submit" value="Enter" class="sub"></td>
                  <td><input type="reset" value="Clear" class="sub1"></td>
				  <td><a href="distance.jsp">distancesearch</a></td>
                </tr>
		</table>
		<%
}
catch(Exception e)
	{
		System.out.println(e);
	}
	  finally
	{
		con.close();
		st.close();
	}
 
%>
		</form>
		</td><td><img src="images/form.png"></td>
		</tr>
		
		</table>
    </div> <!-- end of templatemo_content -->
    
	<div id="templatemo_footer">
    	
        <ul class="footer_menu">
        	<li><a href="#" class="first"><span></span>Home</a></li>
            <li><a href="#"><span></span>Projects</a></li>
            <li><a href="#"><span></span>Services</a></li>
            <li><a href="#"><span></span>Partners</a></li>
            <li><a href="#"><span></span>Contact</a></li>
        </ul>
        
         <a href="#"><strong>Fast Nearest Neighbor Search with Keywords</strong></a>  
	</div> <!-- end of footer -->
</div> <!-- end of tempatemo_container -->
</body>
</html>