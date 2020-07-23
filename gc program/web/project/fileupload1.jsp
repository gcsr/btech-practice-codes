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


<script type="text/javascript">

function valid()
{

var a=document.s.id.value;
if(a=="")
{
alert("Enter file id");
document.s.id.focus();
return false;
}

var b=document.s.key1.value;
if(b=="")
{
alert("Enter file name");
document.s.key1.focus();
return false;
}

var c=document.s.file.value;
if(c=="")
{
alert("choose file");
document.s.file.focus();
return false;
}


}

</script>
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
	  <%
	String c=(String)session.getAttribute("name");
	String idd=request.getParameter("id");
	String keyy=request.getParameter("key1");
	session.setAttribute("fn1",idd);
session.setAttribute("f",keyy);
	%>
    <img src="images/logo.jpg" width="600" height="100"> </header> 
    <div class="wrapper indent">
<!-- content -->
			<section id="content"><div class="ic"></div>
	<h2>Wlecome:<% String namew=(String)session.getAttribute("name3");
		%> <%=namew%></h2>
		 <table>
   
      <td width="400"> <form name="s" action="fileupload2.jsp" ENCTYPE="multipart/form-data" method="post" onSubmit="return valid()">
          <table align="center"  width="350">
            <tr> 
              <td width="50"></td>
              <td> <strong><em><font color="#990000" size="+1" face="Times New Roman"  
	  style="text-decoration:underline">Upload File</font></em></strong> </td>
            </tr>
            <tr> 
              <td><font face="Times New Roman"  size="+1"><strong>File Id</strong></font></td>
              <td><input type="text" class="a" name="id" value="<%=idd%>" /></td>
            </tr>
            <tr> 
              <td><font face="Times New Roman"  size="+1"><strong>File Name</strong></font></td>
              <td><input type="text" name="key1" value="<%=keyy%>"> </td>
            </tr>
           <tr> 
              <td><font face="Times New Roman"  size="+1"><strong>File</strong></font></td>
              <td><input type="file" name="file" class="b"></td>
            </tr>
            <tr> 
              <td></td>
              <td><input type="submit" name="s" value="Continue" class="b1" > &nbsp;&nbsp;&nbsp;&nbsp; 
                <input type="reset" name="r" value="clear" class="b1"></td>
            </tr>
          </table>
        </form></td>
    <td><img src="images/fileup.jpg"></td>
      </tr>
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