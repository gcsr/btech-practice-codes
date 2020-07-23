<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>CPDP</title>
        <link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Coda:400,800" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body>
        <script language="javascript">
		          function check()
		          {
		            var username= document.Member.elements[0].value;
		            var password= document.Member.elements[1].value;
		             if (username.length==0)
		             {  alert("please enter username");
		                return false;
		             }
		            
		             if (password.length==0)
		             {  alert("please enter password");
		                return false;
		             }
		             
		             return true;          
		
		          }
		     </script>
        
        <div id="header-wrapper">
            <div id="header">
                <div id="logo">
                    <h1><a href="#">Cooperative Provable Data Possession</a></h1>                    
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
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                    <%if (request.getParameter("msg") != null) {
                                            out.println(request.getParameter("msg"));
                                        }%>
                                    <h3>User Login</h3>
                                    <form action="UserLogin" method="post" name="Member">
                                        <table width="100%">
                                            <tr style="height: 40px;"><td>User id:</td><td><input type="text" size="30" style="height: 25px;" id="name_" name="name_"/></td></tr>
                                            <tr style="height: 40px;"><td>Password:</td><td><input type="password" size="30" style="height: 25px;" id="password" name="password"/></td></tr>                                

                                            <tr style="height: 70px;">
                                                <td></td><td><input type="submit" value="Login" style="height: 25px;width: 90px;" onClick="return check();"/></td><td><a href="newUser.jsp">New User</a></td></tr>

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
