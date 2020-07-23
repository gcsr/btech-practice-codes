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


<script language="javascript" type="text/javascript">



function valid()
{
var aa=document.s.unn.value;
if(aa=="")
{
alert("Enter Name");
document.s.unn.focus();
return false;
}

var a=document.s.uidd.value;
if(a=="")
{
alert("Enter User ID");
document.s.uidd.focus();
return false;
}
var b=document.s.passs.value;
if(b=="")
{
alert("Enter Password");
document.s.passs.focus();
return false;
}


var k = document.s.mobb.value;
if(k=="")
{
alert("Enter mobile number");
document.s.mobb.focus();
return false;
}
if(isNaN(k))
{
alert("Enter mobile number in numbers");
document.s.mobb.focus();
return false;
}
if(k.charAt(0)!=9)
{
alert("Enter the correct mobile no");
document.s.mobb.focus();
return false;
}
if(k.length!=10)
{
alert("Enter 10 digits");
document.s.mobb.focus();
return false;
}

var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
var m=emailfilter.test(document.s.eidd.value);
if(m==false)
{
alert("Please enter a valid Email Id");
document.s.eidd.focus();
return false;
}

}

</script>
</head>
<body id="page1" onLoad="new ElementMaxHeight();">
<%
 java.util.Date now = new java.util.Date();
	 String DATE_FORMAT1 = "DD/MM/yyyy";
	 SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
     String strDateNew1 = sdf1.format(now);
	 %>
<div class="tail-bottom">
	<div id="main">
<!-- header -->
		<header>
			<div class="nav-box">
				<nav>
					
					<ul>
						<li><a href="admin.jsp">Admin</a></li>
					<li><a href="tpa.jsp">Tpa</a></li>
					<li><a href="owner.jsp">User</a></li>
					<li><a href="signup.jsp">Signup</a></li>
					</ul>
				</nav>
			</div>
			
   <img src="images/logo.jpg" width="600" height="100">
			
		</header>
		<div class="wrapper indent">
<!-- content -->
			<section id="content"><div class="ic"></div>
				 <table>
      <tr> 
        <td width="300"></td>
        <td> <strong><em><font color="#990000" size="+1" face="Times New Roman"  
	  style="text-decoration:underline">User Registration</font></em></strong> 
        </td>
      </tr>
    </table>
	
	
	<table>
	<tr>
	<td width="300"></td>
	<td><form name="s" action="sign_up1.jsp" method="get" onSubmit="return valid()">
      <table  align="center" cellpadding="6" cellspacing="6" width="400" height="300">
        <td> 
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>Name</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="unn" class="b"></td>
        </tr>
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>User ID</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="uidd" id="name"></td>
        </tr>
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>Password</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="password" name="passs" class="b"></td>
        </tr>
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>Mobile</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="mobb" class="b"></td>
        </tr>
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>Email ID</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="eidd" class="b"></td>
        </tr>
        <tr> 
          <td><font face="Times New Roman"  size="+1"><strong>Date</strong></font></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="date" class="b" value="<%=strDateNew1%>"></td>
        </tr>
        <tr> 
          <td></td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="s" value="submit" class="b1" > 
            &nbsp;&nbsp;&nbsp; <input type="reset" name="r" value="clear" class="b1"></td>
        </tr></td>
        <td></td>
      </table>
    </form></td>
	</tr></table>
    
     
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
						
					</ul>
					
					
				</div>
			</aside>
		</div>
<!-- footer -->
		
	</div>
</div>
</body>
</html>