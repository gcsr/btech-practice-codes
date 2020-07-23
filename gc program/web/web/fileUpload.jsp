<%@page import="java.sql.ResultSet"%>
<%@page import="com.util.DbConnector"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
                    <li><a href="userHome.jsp">User Home</a></li>
                    <li><a href="report.jsp">Report</a></li>
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
      
                                    <%if (request.getParameter("msg") != null) {
                                            out.println(request.getParameter("msg"));
                                        }
                                        Connection con = DbConnector.getConnection();
                                        PreparedStatement pstm = null;
                                        String sql = "select count(*) from cloud where status like 'Active'";
                                        pstm = con.prepareStatement(sql);
                                        ResultSet rs = pstm.executeQuery();
                                        String a = "";
                                        if (rs.next()) {
                                            a = rs.getString(1);
                                        }%>
                                    <h3>File Upload</h3>
                                    <form name="naaame" action="/web/UploadActions" enctype="multipart/form-data" method="post">
                                         <table>
											<tr>
												<td>User ID</td>
												<td> <input type ="text" value="<%=session.getAttribute("userid")%>" name="userid" readonly />    </td>
											</tr>
                                            <tr>
												<td>Choose File</td>
												<td> <input type="file" name="fileUp"/> </td>
											</tr>
                                            <tr>
												<td><input name="Submit" type="submit" value="Upload" /></td>
											</tr>
                                        </table>
                                    </form>
                               
        <div id="footer">
            <p>Copyright &copy; 2012 JP Infotech. All rights Reserved.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>
