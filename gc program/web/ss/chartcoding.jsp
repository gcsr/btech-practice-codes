<%@page import="com.oreilly.servlet.*,java.sql.*,databaseconnection.*,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
<%@ page import="org.jfree.chart.*" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="org.jfree.data.general.DefaultPieDataset" %>
<%@ page import="org.jfree.data.xy.*" %>
<%@ page import="org.jfree.data.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
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
  </head>

  <body onload="initialize()">
  <%

String name=(String)session.getAttribute("name");
//System.out.println(name);

String distance=request.getParameter("distance");

String speed=request.getParameter("speed");

double val1 = Double.parseDouble(distance);
double val2 = Double.parseDouble(speed);

double time=val1/val2;

   XYSeries series = new XYSeries("Distance Speed");
  
  series.add(val1,val2);
  XYDataset xyDataset = new XYSeriesCollection(series);
  JFreeChart chart = ChartFactory.createXYLineChart
  ("Distance with respect to speed", "Distance", "Speed",
 xyDataset, PlotOrientation.VERTICAL, true, true, false);
  ChartFrame frame1=new ChartFrame("Distance-Speed-Time=Chart",chart);
  frame1.setVisible(true);
  frame1.setSize(300,300);
  


%>
  <div id="templatmeo_container">
	        <div id="site_title">
        	<h1><a href="#">
            	
                <span><font color="#FFFFFF" face="Times New Roman, Times, serif" size="+2"><strong><em>Fast Nearest Neighbor Search with Keywords</em></strong></font></span>
            </a></h1>
        </div>
    
  

    
    <div id="templatemo_menu">
        <ul id="templatemo_left_menu">
		
		
		
      
		
		 
      <li><a href="javascript:history.go(-1)"><span></span>Back</a></li>
            
      <li><a href="index1.jsp" ><span></span>Home</a></li>
            
       
      <li><a href="logout.jsp" ><span></span>Logout</a></li>
			
      
           
        </ul>
        
		<ul id="templatemo_right_menu">
            <li><a href="#" class="first">Sitemap</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </div> <!-- end of menu -->
    
    <div id="templatemo_banner">
    	
    </div> <!-- end of banner -->
    
    <div id="templatemo_content">
<table><tr><td><table><tr>
    
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5>Welcome:<font color="#00FFFF" ><blink><%=name%></blink></font></h5>
      	
	</tr>
	
	<tr> 
                  <td><strong><font  color="#FFFFFF"  face="Times New Roman, Times, serif">Distance:</font></strong></td>
                  <td><strong><font   color="#0033FF" size="+1" face="Times New Roman, Times, serif"><%=distance%></font></strong></td>
				  <td><strong>Km</strong></td>
                </tr>
				<tr> 
                  <td><strong><font  color="#FFFFFF"  face="Times New Roman, Times, serif">Speed:</font></strong></td>
                  <td><strong><font   color="#00CC99" size="+1" face="Times New Roman, Times, serif"><%=speed%></font></strong></td>
				  <td><strong>Kmph</strong></td>
                </tr>
				<tr> 
                  <td><strong><font  color="#FFFFFF"  face="Times New Roman, Times, serif">TimeTaken:</font></strong></td>
                  <td><strong><font   color="#FF0000" size="+1" face="Times New Roman, Times, serif"><%=time%></font></strong></td>
				  <td><strong>Hours</strong></td>
                </tr>
				  
	
	</table></td><td><img src="images/Graph.png" width="150" height="150"></td></tr>
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