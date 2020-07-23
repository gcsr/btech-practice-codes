
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%!

private String send(int src,int dst){

int srcZNo=0,dstZNo=0;
double srcZnDepth=0,dstZnDepth=0;
String res="";

try{


Connection con = DbConnector.getConnection();
PreparedStatement pstm = null;
String sql = "select ZONENO,ZONEDEPTH from ZONE where ZONENO in(select ZONENO from NODE where NODENO="+src+")";
pstm = con.prepareStatement(sql);
ResultSet rs = pstm.executeQuery();

if(rs.next()){
srcZNo=(int)rs.getDouble(1);
srcZnDepth=rs.getDouble(2);
}


res+="source is "+(srcZNo+" ");

rs.close();
pstm.close();


PreparedStatement pstm1 = null;
sql = "select ZONENO,ZONEDEPTH from ZONE where ZONENO in(select ZONENO from NODE where NODENO="+dst+")";
pstm1= con.prepareStatement(sql);
rs = pstm1.executeQuery();

int cost=0;

if(rs.next()){
dstZNo=(int)rs.getDouble(1);
dstZnDepth=rs.getDouble(2);

}

//res+=("zone depth executed");

rs.close();
pstm1.close();


PreparedStatement pstm2 = null;

if(srcZnDepth<dstZnDepth)
sql = "select ZONENO from ZONE WHERE ZONEDEPTH BETWEEN "+srcZnDepth+" and "+dstZnDepth+"order by ZONEDEPTH";
else
sql = "select ZONENO from ZONE WHERE ZONEDEPTH BETWEEN "+dstZnDepth+" and "+srcZnDepth+"order by ZONEDEPTH DESC";
pstm2= con.prepareStatement(sql);
rs = pstm2.executeQuery();


//res+=("lessthan executed");

while(rs.next()){
cost++;
res+="zone "+((int)rs.getDouble(1)+"->");
}



res+="destination is "+(dstZNo);
res+="packet travels "+cost+" zone(s).";
res+=("<br>");
rs.close();
pstm2.close();


return res;
}
catch(Exception ex){
	ex.printStackTrace();
	return res;
}


}
%>
<%
int srcNo=Integer.parseInt(request.getParameter("srcNodeName"));
String[] dstNo=request.getParameterValues("dstNodeName");

for(int i=0;i<dstNo.length;i++)
{
	out.write(send(srcNo,Integer.parseInt(dstNo[i])));
}
%>
