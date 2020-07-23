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
		          function check1()
		          {
		            var userid= document.newuser.elements[0].value;
                            var password= document.newuser.elements[1].value;
                            var gender= document.newuser.elements[2].value;
                            var age= document.newuser.elements[4].value;
                            var phone= document.newuser.elements[5].value;
		            var emailid= document.newuser.elements[6].value;
                            var atpos=emailid.indexOf("@");
                            var dotpos=emailid.lastIndexOf(".");
                            
		             if (userid.length==0)
		             {  alert("please enter userid");
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
                             if (gender.length==0)
		             {  alert("please enter gender");
		                return false;
		             }
		             if (gender.length<3)
		             {  alert("gender should be atleast 3 characters");
		                return false;
		             }
                               if (age.length==0)
		             {  alert("please enter age");
		                return false;
		             }
                              if (age<18 || age>100  )
		             {  alert("please enter age between 18 and 100");
		                return false;
		             }                             
                             if (phone.length==0)
		             {  alert("please enter phone");
		                return false;
		             }
                           
                              if (emailid.length==0)
		             {  alert("please enter emailid");
		                return false;
		             }
		            if (atpos<1 || dotpos<atpos+2 || dotpos+2>=emailid.length)
                            {
                            alert("Not a valid e-mail address");
                            return false;
                            }
                            
                              return true;
                              
                          }
                             </script>
        <div id="menu-wrapper">
            <div id="menu">
                <ul>
                    <li><a href="adminHome.jsp">Admin Home</a></li>
                    <li><a href="createCloud.jsp">Create Cloud</a></li>
                    <li><a href="cloudDetails.jsp">Cloud Details</a></li>                    
                    <li class="current_page_item"><a href="newUser.jsp">Create Member</a></li>
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
                                <h3>New Member</h3>
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                    <%if (request.getParameter("msg") != null) {
                                                                out.println(request.getParameter("msg"));
                                                            }%>
                                    <form name="newuser" method="post" action="NewUserReg" >
                                        <table width="90%">
                                            <tr style="height: 40px;"><td>User id:</td><td><input type="text" size="30" style="height: 25px;" id="_name" name="_name"/></td></tr>
                                            <tr style="height: 40px;"><td>Password:</td><td><input type="password" size="30" style="height: 25px;" id="_password" name="_password"/></td></tr>
                                            <tr style="height: 40px;"><td>Gender:</td><td><input type="radio" name="sex" id="sex" value="male" /> Male
                                                    <input type="radio" name="sex" id="sex" value="female" /> Female</td></tr>
                                            <tr style="height: 40px;"><td>Age:</td><td><input type="text" size="30" style="height: 25px;" id="_age" name="_age"/></td></tr>
                                            <tr style="height: 40px;"><td>Phone:</td><td><input type="text" size="30" style="height: 25px;" id="_phone" name="_phone"/></td></tr>
                                            <tr style="height: 40px;"><td>Email-Id:</td><td><input type="text" size="30" style="height: 25px;" id="_emailid" name="_emailid"/></td></tr>


                                            <tr style="height: 40px;"><td></td><td><input type="submit" value="Submit" style="height: 25px;width: 90px;"  onClick="return check1();"/></td></tr>
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
