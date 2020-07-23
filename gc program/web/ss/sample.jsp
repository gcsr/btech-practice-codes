<%-- 
    Document   : jsp3dbarchart
    Created on : 1 Sep, 2008, 12:19:04 PM
    Author     : komal
--%>


  
<%@ page import="java.awt.*" %>
<%@ page import="org.jfree.chart.*" %>
<%@ page import="org.jfree.chart.title.*" %>
<%@ page import="org.jfree.data.general.DefaultPieDataset" %>
<%@ page import="org.jfree.ui.*" %>
<%@ page import="org.jfree.chart.plot.*" %>
<%@ page import="org.jfree.util.*" %>




<%
String f="320.8";
String g="20";
String h="50";
String i="10";
String j="20";
String k="10";



float a=Float.parseFloat(f);



  DefaultPieDataset pieDataset = new DefaultPieDataset();
  pieDataset.setValue("One", new Float(a));
  JFreeChart chart = ChartFactory.createPieChart3D
  ("3D Pie Chart", pieDataset, true,true,true);
  PiePlot3D p=(PiePlot3D)chart.getPlot();
  p.setForegroundAlpha(0.5f);
  ChartFrame frame1=new ChartFrame("3D Pie Chart",chart);
  frame1.setVisible(true);
  frame1.setSize(1100,800);
  response.sendRedirect("index1.jsp?message=success");
            
%>



