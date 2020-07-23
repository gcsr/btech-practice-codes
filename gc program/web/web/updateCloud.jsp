<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
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
        <div id="menu-wrapper">
            <div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#">Admin Home</a></li>
                    <li><a href="createCloud.jsp">Create Cloud</a></li>
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
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                    <%if (request.getParameter("msg") != null) {
                                                    out.println(request.getParameter("msg"));
                                                }%>
                                    <h2>Update Cloud</h2>
                                    <form method="post" action="UploadCloud">
                                        <table border="1" width="100%" cellpadding="0" cellspacing="0">
                                            <%

                                                Connection con = DbConnector.getConnection();
                                                PreparedStatement pstm = null;
                                                String sql = "select * from cloud where ip='" + request.getParameter("ip") + "'";
                                                pstm = con.prepareStatement(sql);
                                                ResultSet rs = pstm.executeQuery();
                                                if (rs.next()) {%>
                                            <tr>
                                                <td>Ip address</td><td><input type="text" id="ip" name="ip" value="<%=rs.getString("ip")%>"/></td>
                                            </tr>
                                            <tr>
                                                <td>Id</td><td><input type="text" id="user" name="user" value="<%=rs.getString("user_id")%>" /></td>
                                            </tr><tr>
                                                <td>Password</td><td><input type="text" id="password" name="password" value="<%=rs.getString("password")%>"/></td>
                                            </tr><tr>
                                                <td>Status</td><td><input type="text" id="status" name="status" value="<%=rs.getString("status")%>"/></td>
                                            </tr>
                                            <tr><td></td><td><input type="submit" value="Submit"/></td></tr>

                                            <%}
                                            %>
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
