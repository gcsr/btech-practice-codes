<%@ page import="org.jfree.chart.*"%>

<%@ page import="org.jfree.chart.entity.*"%>

<%@ page import="org.jfree.data.general.*"%>

<%@ page import="org.jfree.chart.plot.PiePlot" %>

<%@ page import="org.jfree.data.jdbc.JDBCCategoryDataset"%>

<%@page import="org.jfree.chart.plot.CategoryPlot"%>

<%@page import="org.jfree.chart.plot.XYPlot"%> 

<%@page import="org.jfree.data.xy.XYDataset"%> 

<%

String query="select Time, mem_free from server_status where server_name like 'z2bizadmin01.zlt'";

JDBCCategoryDataset dataset=new JDBCCategoryDataset("jdbc:mysql://localhost/server_info", "com.mysql.jdbc.Driver","root", "optus123");

dataset.executeQuery(query);


//(final XYDataset dataset){
final JFreeChart chart = ChartFactory.createLineChart("zlt Admin server memory status", "Time", "MEM Free", dataset, PlotOrientation.VERTICAL, true, true, false);

final CategoryPlot plot = (CategoryPlot) chart.getPlot();

plot.setBackgroundPaint(Color.white);

try

{

    final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

    final File file1 = new File("D:/apache-tomcat-6.0.18/webapps/Fast Nearest Neighbor Search with Keywords/images/linechart.png");

    ChartUtilities.saveChartAsPNG(file1, chart, 1000, 800, info);

} catch (Exception e) {

System.out.println(e);

}