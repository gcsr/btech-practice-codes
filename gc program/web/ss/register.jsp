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
<script language="javascript" type="text/javascript" src="datetimepicker.js">

//Date Time Picker script- by TengYong Ng of http://www.rainforestnet.com
//Script featured on JavaScript Kit (http://www.javascriptkit.com)
//For this script, visit http://www.javascriptkit.com 

</script>
<style type="text/css">
		content_main2.C1{background-color:#CCCCCC;}
		form.f1{background-color:#CCCCCC;}
		form1.f5{background-color:#CCCCCC;}
		input.sub{background-color:#009900;}
		input.sub1{background-color:#990000;}
		input.sub2{background-color:#009900;}
		content_maina1.a{background-color:#CCCCCC;}
		td.d{color:#FF9900;}
		tr.r{color:#FF9900;}
		tr.r1{color:#000000;}
		</style>
		<script type="text/javascript">
function validation()
{

name=document.form.name.value;
if(name=="")
{
alert("enter name");
document.form.name.focus();
return false;
}



if(!isNaN(name))
{
alert("enter name:");
document.form.name.focus();
return false;
}

dob=document.form.dob.value;
if(dob=="")
{
alert("select dob");
document.form.dob.focus();
return false;
}




gender=document.form.gender.value;
if(document.form.gender[0].checked==false&&document.form.gender[1].checked==false)
{
alert("select gender");
return false;
}


email=document.form.email.value;
if(email=="")
{
alert("enter email:");
document.form.email.focus();
return false;
}

var x=email.indexOf("@");
var y=email.indexOf(".");
if((x<1)||(y<x+2)||(y+2>=x.length))
{
alert("enter username@domain:");
document.form.email.focus();
return false;
}

password=document.form.password.value;
if(password=="")
{
alert("enter password:");
document.form.password.focus();
return false;
}

mobile=document.form.mobile.value;
if(mobile=="")
{
alert("enter mobile number");
document.form.mobile.focus();
return false;
}

if(isNaN(mobile))
{
alert("enter number:");
document.form.mobile.focus();
return false;
}


}


</script>
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
            
      <li><a href="#" class="current"><span></span>Registration</a></li>
            
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
    
    	<table width="800" >
		<tr>
		<td><form name="form" method="post" action="insert.jsp" onsubmit="return validation()">
		
		<%
				int n=0,s=0;
 				Connection con=null;
				Statement st=null;
				ResultSet rs=null;
				String sql="select max(userid) from user";
				try
	{
		//con=databasecon.getconnection();
		con = databasecon.getconnection();
		st=con.createStatement();
		rs=st.executeQuery(sql);
		if(rs.next())
		{
		if(rs.getInt(1)==0)
		n=111213;
		else
		n=rs.getInt(1)+1;
		session.setAttribute("userid", n );
		}
				%>

		
		<table height="300" width="300">
		
		
		 <tr> 
                  <td colspan="2" align="center"><font size="2"><b> 
                    <%
                                                       String message=request.getParameter("message");
                                                       if(message!=null && message.equalsIgnoreCase("success"))
                                                       {
                                                               out.println("<font color='#00FF66'><blink>Registered Successfully !</blink></font>");
                                                       }
                                               %>
                    </b></font></td>
                </tr>
               
		
		<tr> 
                  <td colspan="2" align="justify"><strong><font  color="#FF0000" size="3"> 
                    User_Registration</font></strong></td>
                </tr>
		
		
		<tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Userid:</font></strong></td>
                  <td><input  type="text" name="userid" value="<%=n%>" ></td>
                </tr>
				
		
		
		<tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Name:</font></strong></td>
                  <td><input  type="text" name="name" ></td>
                </tr>
				
		
		    <tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Gender:</font></strong></td>
                  <td><input type="radio"  name="gender" value="male"> <font color="#FFFFFF"><strong>Male</strong></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="radio" name="gender" value="female"> <font color="#FFFFFF"><strong>Female</strong></font></td>
                </tr>
				<tr> 
                  <td ><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">D.O.B:</font></strong></td>
                  <td><input id="demo1" type="text"  name="dob"><a href="javascript:NewCal('demo1','ddmmyyyy')"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
		</td>
                </tr>
				
				
<tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Email:</font></strong></td>
                  <td><input  type="text" name="email" ></td>
                </tr>
				<tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Password:</font></strong></td>
                  <td><input  type="password" name="password" ></td>
                </tr>  
            				
				
				<tr> 
                  <td><strong><font  color="#FFFFFF" size="+1" face="Times New Roman, Times, serif">Mobile:</font></strong></td>
                  <td><input  type="text" name="mobile" ></td>
                </tr>
				
				
                <tr> 
                  <td><input type="submit" value="done" class="sub"></td>
                  <td><input type="reset" value="Clear" class="sub1"></td>
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
		
		</form></td><td align="right"><img src="images/register.png" ></td>
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