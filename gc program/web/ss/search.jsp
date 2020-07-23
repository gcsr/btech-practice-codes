<%@page import="com.oreilly.servlet.*,java.sql.*,databaseconnection.*,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
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
<%

String name=(String)session.getAttribute("name");
//System.out.println(name);

%>
<div id="templatmeo_container">
	        <div id="site_title">
        	<h1><a href="#">
            	
                <span><font color="#FFFFFF" face="Times New Roman, Times, serif" size="+2"><strong><em>Fast Nearest Neighbor Search with Keywords</em></strong></font></span>
            </a></h1>
        </div>
    
  

    
    <div id="templatemo_menu">
        <ul id="templatemo_left_menu">
		 
      <li><a href="#" class="current" ><span></span>User</a></li>
            
       
      
	  
	  <li><a href="javascript:history.go(-1)"><span></span>Back</a></li>
	  
	  <li><a href="logout.jsp" ><span></span>Logout</a></li>
           
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
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<th>Welcome:<font color="#FF0000"><strong><%=name%></strong></font></th>
		<tr>
		<td><form name="form" method="post" action=""><table width="500" height="200"  >
		
		
		<th><font color="#FFFFFF" size="+1"><strong>Hotels and its famous_dish</strong></font></th>
      <tr> 
          <td><font  size="+1" style="times new roman" color="#FF0000">Hotel_Name</font> 
          </td>
		   <td><font  size="+1" style="times new roman" color="#FF0000">Famous</font> 
          </td>
		  <td></td>
          <td><font  size="+1" style="times new roman" color="#FF0000">Distance</font> 
          </td>
		  
		  </tr>
		  
		    <%



ResultSet rs=null;
PreparedStatement psmt1=null;


//String a = (String)session.getAttribute("name");
//String b = (String)session.getAttribute("age");
String hotel=null;
String distance=null;

String famousfor=null;

String famousfor1=request.getParameter("famousfor");


String a="There is no such item";

//String d = request.getParameter("click.jsp");

/*java.util.Date now = new java.util.Date();
	String date=now.toString();
	 String DATE_FORMAT = "dd-MM-yyyy";
	 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
     String strDateNew = sdf.format(now) ;
*/
try{

Connection con = databasecon.getconnection();
psmt1=con.prepareStatement("select * from hotel where famousfor like '"+'%'+famousfor1+'%'+"' ");

//int s = psmt1.executeUpdate();
rs=psmt1.executeQuery();



while(rs.next())
{
hotel=rs.getString("hotel");
distance=rs.getString("distance");
famousfor=rs.getString("famousfor");


%>
		<tr> 
          <td><font  size="+1" style="times new roman" color="#FFFFFF"> <%=hotel%> </font></td>
		  <td><font  size="+1" style="times new roman" color="#FFFFFF"> <%=famousfor%> </font></td>
		  <td></td>
          <td><font   size="+1" style="times new roman" color="#FFFFFF" > <%=distance%>km</font></td>
		  </tr>
			  
	<%	  
		  
}




con.close();
psmt1.close();
}






catch(Exception ex)
{

out.println("Error in connection : "+ex);

}




  




%>	
	
	
		
		</table>
		</form>
		</td><td><img src="images/menu.png" height="200" width="200"></td>
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