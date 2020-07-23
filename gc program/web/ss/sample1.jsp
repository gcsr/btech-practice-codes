<%-- 
    Document   : jsp3dbarchart
    Created on : 1 Sep, 2008, 12:19:04 PM
    Author     : komal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
  

<%@ page import="org.jfree.chart.*" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="org.jfree.data.general.DefaultPieDataset" %>
<%@ page import="org.jfree.data.xy.*" %>
<%@ page import="org.jfree.data.*" %>


<%

String distance="320.8";
String speed="20.8";

double val1 = Double.parseDouble(distance);
double val2 = Double.parseDouble(speed);

   XYSeries series = new XYSeries("Average Weight");
  
  series.add(val1,val2);
  XYDataset xyDataset = new XYSeriesCollection(series);
  JFreeChart chart = ChartFactory.createXYLineChart
  ("XYLine Chart using JFreeChart", "Distance", "Speed",
 xyDataset, PlotOrientation.VERTICAL, true, true, false);
  ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
  frame1.setVisible(true);
  frame1.setSize(300,300);
  
            
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
		double time=val1/val2;
		out.println(time+"hours");
		%>
    </body>
</html>

