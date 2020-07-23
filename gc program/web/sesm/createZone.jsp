<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>SESM</title>
        <link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Coda:400,800" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body>       
        <div id="menu-wrapper">
            <div id="menu">
                <ul>
                    <li><a href="adminHome.jsp">Admin Home</a></li>
                    <li><a href="zoneDetails.jsp">Zone Details</a></li>                    
                    <li><a href="newNode.jsp">Create Node</a></li>
                    <li><a href="viewNode.jsp">Node Details</a></li>
					<li><a href="sendPacket.jsp">Send Packet</a></li>
                    <li><a href="index.jsp">Log out</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
        <div id="header-wrapper">
            <div id="header">
                <div id="logo">
                    <h1>SESM</h1>                    
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
                                <h3>Create Zone</h3>
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                  
                                    <form method="post" action="ZoneProcessing.jsp">
                                        <table width="90%">
											<tr style="height: 40px;"><td>Zone No:</td><td><input type="text" size="30" style="height: 25px;"name="zoneNo"/></td></tr>
                                            <tr style="height: 40px;"><td>Zone Pos X:</td><td><input type="text" size="30" style="height: 25px;"name="zonePX"/></td></tr>
										<tr style="height: 40px;"><td>Zone Pos Y:</td><td><input type="text" size="30" style="height: 25px;" name="zonePY"/></td></tr>
                                        <tr>
												<td><input type="submit" value="Create Zone" /></td>
										</tr> 
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
      
    </body>
</html>
