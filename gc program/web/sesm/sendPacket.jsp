<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
Connection con = DbConnector.getConnection();
PreparedStatement pstm = null;
String sql = "select NODENO from NODE";
List<Integer> nodes=new LinkedList<Integer>();

pstm = con.prepareStatement(sql);
ResultSet rs = pstm.executeQuery();

while(rs.next()){	
	nodes.add((int)rs.getDouble(1));} 

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
                                <h3>Send Packet</h3>
                                <div style="clear: both;">&nbsp;</div>
                                <div class="entry">
                                  
                                    <form method="post" action="PacketProcessing.jsp" >
                                        <table width="90%">
											<tr style="height: 40px;">
												<td>Source Node No:</td>
												<td>
													<select name="srcNodeName" >
<%
Iterator iterator=nodes.iterator();
while(iterator.hasNext()){
		int nNo=(Integer)iterator.next();
%>	
														<option value="<%=nNo%>" > <%=nNo%></option>
	
<%
}
%>	
													</select>									
												</td>
											</tr>
                                            <tr style="height: 40px;">
												<td>Destination Node No:</td>
												<td>
												<select name="dstNodeName" multiple="multiple">
<%
iterator=nodes.iterator();
while(iterator.hasNext()){
		int nNo=(Integer)iterator.next();
%>	
														<option value="<%=nNo%>" > <%=nNo%></option>
	
<%
}
%>	
													</select>	

												</td>
											</tr>
										 	<tr>
												<td><input type="submit" value="Send Packet" /></td>
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
