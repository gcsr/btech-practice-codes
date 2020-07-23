
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%!
double radius=3;
double nodePX=0;
double nodePY=0;
double nodeNo=0;
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
                    <li><a href="createZone.jsp">Create Zone</a></li>
                    <li><a href="zoneDetails.jsp">Zone Details</a></li>                    
                    <li><a href="viewNode.jsp">Node Details</a></li>
					<li><a href="sendPacket.jsp">Send Packet</a></li>
                    <li><a href="index.jsp">Log out</a></li>
                </ul>
            </div>
            <!-- end #menu -->
        </div>
		
<%

											nodePX=Double.parseDouble(request.getParameter("nodePX"));
											nodePY=Double.parseDouble(request.getParameter("nodePY"));
											nodeNo=Double.parseDouble(request.getParameter("nodeNo"));
											boolean zoneFound=false;
                                            Connection con = DbConnector.getConnection();
                                            PreparedStatement pstm = null;
                                            String sql = "select * from Zone";
                                            pstm = con.prepareStatement(sql);
                                            ResultSet rs = pstm.executeQuery();
											double nodeZoneX=0;
											double nodeZoney=0;
											double zoneNo=0;
                                            while (rs.next()) {

                                        		double zoneCenterX=rs.getDouble(4);
												double zoneCenterY=rs.getDouble(5);
												
												double distance = Math.sqrt((zoneCenterX-nodePX)*(zoneCenterX-nodePX)+(zoneCenterY-nodePY)*(zoneCenterY-nodePY));		
												out.write("distance is "+distance+"<br>");
												if(distance<=radius){
													if(zoneFound==false){	
														zoneFound=true;											
														nodeZoneX=zoneCenterX;
														nodeZoney=zoneCenterY;
														zoneNo=rs.getDouble(1);
													}
													else{
													out.write("conflict node belonging to 2 zones<br>");
													return;
													}
												}
											}	
											if(zoneFound){
												PreparedStatement pstm1 = null;
		                                        sql = "insert into NODE values("+nodeNo+","+nodePX+","+nodePY+","+zoneNo+")";
												//out.write(sql);
		                                       	pstm1 = con.prepareStatement(sql);
		                                        int p = pstm1.executeUpdate();
												out.write("node "+nodeNo+"created");
												pstm1.close();																
												PreparedStatement pstm2 = null;
	                                            sql = "select ZONELDR from ZONE where ZONENO="+zoneNo;
	                                            pstm2 = con.prepareStatement(sql);
	                                            ResultSet rs2 = pstm2.executeQuery();
												if(rs2.next()){
													double node=rs2.getDouble(1);
													if(node==0){
														PreparedStatement pstm3 = null;
	                                            		sql = "update ZONE SET ZONELDR="+nodeNo+"where ZONENO="+zoneNo;
	                                            		pstm3 = con.prepareStatement(sql);
														int s=pstm3.executeUpdate();
														out.write("zone leader is set to "+nodeNo+"<br>");
													}	
												}
											}
											else{
												out.write("zone not found<br>");
												return;
											}													


%>
		
       
      
    </body>
</html>

<%
pstm.close();
con.close();
%>
