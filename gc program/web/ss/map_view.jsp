<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@page import="com.oreilly.servlet.*,java.sql.*,java.lang.*,databaseconnection.*,java.text.SimpleDateFormat,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"  errorPage="Error.jsp"%>
<%@page import=" java.security.MessageDigest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Fast Nearest Neighbor Search with Keywords</title>
<meta name="keywords" content="City Construction, Web Template, XHTML CSS" />
<meta name="description" content="City Construction, Web Template, XHTML CSS layout provided by TemplateMo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
		content_main2.C1{background-color:#CCCCCC;}
		form.f1{background-color:#CCCCCC;}
		form1.f5{background-color:#CCCCCC;}
		input.sub{background-color:#009900;}
		input.sub1{background-color:#990000;}
		input.sub2{background-color:#009900;}
		input.sub3{background-color:#CC3366;}
		input.sub4{background-color:#FF6666;}
		input.sub5{background-color:#99FFFF;}
		content_maina1.a{background-color:#CCCCCC;}
		td.d{color:#FF9900;}
		tr.r{color:#FF9900;}
		tr.r1{color:#000000;}
		</style>
		 <script src="https://maps.googleapis.com/maps/api/js?sensor=false&language=en"></script>
		 <script>
		 		 		 
			 var map;
			 function initialize() {
				 if(navigator.geolocation){
					 // timeout at 60000 milliseconds (60 seconds)
					 var options = {timeout:60000};
					 navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
				 }else{
					 alert("Sorry, browser does not support geolocation!");
				 } 
			 }

			function showLocation(position) {
				 var mapOptions = {
					 scaleControl: true,
					 center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
					 zoom: 10,
					 mapTypeId: google.maps.MapTypeId.ROADMAP
				 };

				map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
				
				

				var marker = new google.maps.Marker({
					 map: map,
					 position: map.getCenter()
				 });
				 var infowindow = new google.maps.InfoWindow();
				 infowindow.setContent('<b>This is your current location...</b>');
				 google.maps.event.addListener(marker, 'click', function() {
					 infowindow.open(map, marker);
				 });
			 }
				function errorHandler(err) {
				 if(err.code == 1) {
					 alert("Error: Access is denied!");
				 }else if( err.code == 2) {
					 alert("Error: Position is unavailable!");
				 }
			 }
			 google.maps.event.addDomListener(window, 'load', initialize);
		 </script>

</head>
<body>
<%

String name=(String)session.getAttribute("name");
//System.out.println(name);

%>
<div id="templatmeo_container">
	        <div id="site_title">
        	<h1><a href="#">
            	
                <span><font color="#FFFFFF" face="Times New Roman, Times, serif" size="+2"><strong><em>Fast Nearest Neighbor Search with Keywords</em></strong></font></span>
            </a></h1>
        </div>
    
  

    
    <div id="templatemo_menu">
        <ul id="templatemo_left_menu">
		 
      <li><a href="logout.jsp" ><span></span>Logout</a></li>
            
       
      
	  
	  <li><a href="javascript:history.go(-1)"><span></span>Back</a></li>
	  
	  <li><a href="index1.jsp" ><span></span>Home</a></li>
           
        </ul>
        
		<ul id="templatemo_right_menu">
            <li><a href="#" class="first">Sitemap</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </div> <!-- end of menu -->
    
    <div id="templatemo_banner">
    	
    </div> <!-- end of banner -->
    
    <div id="templatemo_content">
    
    	<table  width="800">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<th>Welcome:<font color="#FF0000"><strong><%=name%></strong></font></th>

		<tr><td><div id="map_canvas" style="width: 500px;height: 300px;"></div></td>
		<td><img src="images/powered-by-google-on-white.png"></td>
		</tr>
		
		</table>
    </div> <!-- end of templatemo_content -->
    
	<div id="templatemo_footer">
    	
        <ul class="footer_menu">
        	<li><a href="#" class="first"><span></span>Home</a></li>
            <li><a href="#"><span></span>Projects</a></li>
            <li><a href="#"><span></span>Services</a></li>
            <li><a href="#"><span></span>Partners</a></li>
            <li><a href="#"><span></span>Contact</a></li>
        </ul>
        
         <a href="#"><strong>Fast Nearest Neighbor Search with Keywords</strong></a>  
	</div> <!-- end of footer -->
</div> <!-- end of tempatemo_container -->
</body>
</html>