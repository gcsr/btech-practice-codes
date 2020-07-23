
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%!
double radius=3;
double virOriginX=0;
double virOriginY=0;
double x=0;
double y=0;
double zoneIDX=0;
double zoneIDY=0;
double zoneCenterX=0;
double zoneCenterY=0;
double zoneDepth=0;
int zoneNo=0;
%>
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
                    <li class="current_page_item"><a href="createCloud.jsp">Create Cloud</a></li>
                    <li><a href="zoneDetails.jsp">Zone Details</a></li>                    
                    <li><a href="newNode.jsp">Create Node</a></li>
                    <li><a href="viewNode.jsp">Node Details</a></li>
					<li><a href="sendPacket.jsp">Send Packet</a></li>
                    <li><a href="index.jsp">Log out</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
		
<%
int zoneNo=Integer.parseInt(request.getParameter("zoneNo"));
x=Double.parseDouble(request.getParameter("zonePX"));
y=Double.parseDouble(request.getParameter("zonePY"));
zoneIDX=(x-virOriginX)/radius;
zoneIDY=(y-virOriginY)/radius;
zoneCenterX=virOriginX+(zoneIDX+0.5)*radius;
zoneCenterY=virOriginY+(zoneIDY+0.5)*radius;
if((zoneIDX-virOriginX)>(zoneIDY-virOriginY))
zoneDepth=zoneIDX-virOriginX;
else
zoneDepth=zoneIDY-virOriginY;
Connection con = DbConnector.getConnection();
PreparedStatement pstm = null;
String sql = "INSERT INTO ZONE VALUES("+zoneNo+","+zoneIDX+","+zoneIDY+","+zoneCenterX+","+zoneCenterY+","+0+","+zoneDepth+")";
pstm = con.prepareStatement(sql);
int s=pstm.executeUpdate();
out.write("zone "+zoneNo+"created");
%>		
       
        <div id="footer">
            <p>Copyright &copy; 2012 JP Infotech. All rights Reserved.</p>
        </div>
        <!-- end #footer -->
    </body>
</html>
<%
	pstm.close();
	con.close();
%>