<%@ page import="java.sql.*,databaseconnection.*"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%

String userid=(String)session.getAttribute("id");
String emailid=(String)session.getAttribute("email");
System.out.println(emailid);


int a=(int) (Math.random() * 100000);

String c =Integer.toString(a);

String e="key is=";


String host="", user="", pass="";

host ="smtp.gmail.com"; //"smtp.gmail.com";

user ="customerservice404@gmail.com"; //"YourEmailId@gmail.com" // email id to send the emails

pass ="customer@404"; //Your gmail password

String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

String to =emailid; // out going email id

String from ="customerservice404@gmail.com"; //Email id of the recipient

String subject ="welcome";

String messageText ="Login key is=<b>"+c+"</b>";

session.setAttribute("userid",userid);
session.setAttribute("password",c);
session.setAttribute("emailid",emailid);
boolean sessionDebug = true;

Properties props = System.getProperties();
props.put("mail.host", host);
props.put("mail.transport.protocol.", "smtp");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.", "true");
props.put("mail.smtp.port", "465");
props.put("mail.smtp.socketFactory.fallback", "false");
props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
Session mailSession = Session.getDefaultInstance(props, null);
mailSession.setDebug(sessionDebug);
Message msg = new MimeMessage(mailSession);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = {new InternetAddress(to)};
msg.setRecipients(Message.RecipientType.TO, address);
msg.setSubject(subject);
msg.setContent(messageText, "text/html"); // use setText if you want to send text
Transport transport = mailSession.getTransport("smtp");
transport.connect(host, user, pass);
try {
transport.sendMessage(msg, msg.getAllRecipients());
//out.println("message successfully sended"); // assume it was sent
//response.sendRedirect("request2.jsp");
}
catch (Exception err) {

out.println("message not successfully sended"); // assume it’s a fail
}
transport.close();


%>
<%
String status="accept";


	try{
	
		Connection con = databasecon.getconnection();
		PreparedStatement ps=con.prepareStatement("update user set key1='"+c+"' where id='"+userid+"'");
		ps.executeUpdate();
		//response.sendRedirect("request.jsp?success");

	}
	catch(Exception e2)
	{
		out.println(e2.getMessage());
	}
	%>
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

var a=document.s.pass.value;
if(a=="")
{
alert("Enter ur Key");
document.s.pass.focus();
return false;
}


}

</script>

</head>
<body id="page1" onLoad="new ElementMaxHeight();">

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
				 <table>
      <tr> 
        <td width="300"></td>
        <td>
		
		
		
			<table>
      <tr> 
        <td width="300"></td>
        <td> <font color="#339900" size="+1">&nbsp;Public auditability key is sent your mail</font>
		<table>
		<tr>
		<td>
		<form id="user1" method="get" action="user4.jsp" name="s" onSubmit="return valid()">
			<table>
				<tr>
				<td>
				public auditability key </td>
				<td><input type="text" name="pass"></td>
				</tr>
				
				<tr>
				<td>
				</td>
				<td>
				<input type="submit" name="user" value="submit">
			</td></tr></table>
			</form>
</td>
			<td><img src="images/privacy.jpg"></td></tr></table>
        </td>
      </tr>
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
						
					</ul>
					
					
				</div>
			</aside>
		</div>
<!-- footer -->
		
	</div>
</div>
</body>
</html>