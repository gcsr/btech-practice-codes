<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Cloud</title>
        <link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Coda:400,800" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body>
        <script language="javascript">
		          function check()
		          {
		            var ip= document.forms[0].elements[0].value;
		            var user= document.forms[0].elements[1].value;
                            var password= document.forms[0].elements[2].value;
		            var status= document.forms[0].elements[3].value;
                            var re=/^[A-z]+$/; 
		             if (ip.length==0)
		             {  alert("please enter ip");
		                return false;
		             }
		             if (ip.length<6)
		             {  alert("ip should be atleast 6 characters");
		                return false;
		             }
		              if (user.length==0)
		             {  alert("please enter user");
		                return false;
		             }
		             if (user.length<3)
		             {  alert("user should be atleast 3 characters");
		                return false;
		             }
                             if(!re.test(user.value)) 
                             { 
                                  alert("please enter char only"); 
                                  return false;
                                }
                              if (password.length==0)
		             {  alert("please enter password");
		                return false;
		             }
		             if (password.length<3)
		             {  alert("password should be atleast 3 characters");
		                return false;
		             }
                              if (status.length==0)
		             {  alert("please choose status");
		                return false;
		             }
		             	             return true;          
		
		          }
		     </script>
        <div id="menu-wrapper">
            <div id="menu">
                <ul>
                    <li><a href="adminHome.jsp">Admin Home</a></li>
                    <li class="current_page_item"><a href="createCloud.jsp">Create Cloud</a></li>
                    <li><a href="cloudDetails.jsp">Cloud Details</a></li>                    
                    <li><a href="newUser.jsp">Create Member</a></li>
                    <li><a href="viewUser.jsp">Member Details</a></li>
                    <li><a href="index.jsp">Log out</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
        <div id="header-wrapper">
            <div id="header">
                <div id="logo">
                    <h1><a href="#">Cloud Computing Security: <span>From Single to Multi-Clouds</span></a></h1>                    
                </div>
            </div>
        </div>
        <div id="banner"><img src="images/img04.jpg" width="1000" height="350" alt="" /></div>
        <div id="wrapper">
            <!-- end #header -->
            <div id="page">
                <div id="page-bgtop">
                    <div id="page-bgbtm">
                        <div id="content">
                            <div class="post">       
                                <h3>Create Cloud</h3>
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                    <%if (request.getParameter("msg") != null) {
                                            out.println(request.getParameter("msg"));
                                        }%>
                                    <form method="post" action="CloudCreate">
                                        <table width="90%">
                                            <tr style="height: 40px;"><td>Cloud Ip:</td><td><input type="text" size="30" style="height: 25px;" id="ip" name="ip"/></td></tr>
                                            <tr style="height: 40px;"><td>User Name</td><td><input type="text" size="30" style="height: 25px;" id="user" name="user"/></td></tr>
                                            <tr style="height: 40px;"><td>Password</td><td><input type="text" size="30" style="height: 25px;" id="password" name="password"/></td></tr>
                                            <tr style="height: 40px;"><td>Status</td><td><input type="radio" name="status" id="status" value="Active" /> Active
                                                    <input type="radio" name="status" id="status" value="Disable" /> Disable</td></tr>
                                            <tr><td></td><td><input type="submit" value="Submit" onClick="return check();"/></td></tr>

                                        </table>
                                    </form>	
                                </div>
                            </div>
                            <div style="clear: both;">&nbsp;</div>
                        </div>
                        <!-- end #content -->

                        <!-- end #sidebar -->
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                </div>
            </div>
            <!-- end #page -->
        </div>
        <div id="footer">
            <p>Copyright &copy; 2012 JP Infotech. All rights Reserved.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>
