
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%

try{

int nodeNo=Integer.parseInt(request.getParameter("nodeNo"));
Connection con = DbConnector.getConnection();
PreparedStatement pstm = null;
String sql = "delete from NODE where NODENO="+nodeNo;
pstm = con.prepareStatement(sql);
int updated = pstm.executeUpdate();
pstm.close();


PreparedStatement pstm1 = null;
sql = "select ZONENO from ZONE where ZONELDR="+nodeNo;
pstm1= con.prepareStatement(sql);
ResultSet rs = pstm1.executeQuery();

while(rs.next()){
update(rs.getInt("ZONENO"));
}

//res+=("zone depth executed");

rs.close();
pstm1.close();
con.close();


}
catch(Exception ex){
	ex.printStackTrace();

}

%>

<%!

private void update(int zoneNo){
try{
	System.out.println("in update");
	Connection con = DbConnector.getConnection();
	
	PreparedStatement pstm = null;
	String sql = "select ZONECENTERX,ZONECENTERY from ZONE where ZONENO="+zoneNo;
	pstm = con.prepareStatement(sql);
	ResultSet rs= pstm.executeQuery();
	
	rs.next();
	System.out.println("cmd not properly ended1");
	double zoneCenterx=rs.getDouble("ZONECENTERX");
	double zoneCentery=rs.getDouble("ZONECENTERy");
	
	rs.close();
	pstm.close();
	
	
	PreparedStatement pstm1 = null;
		System.out.println("cmd not properly ended1");
	sql = "select NODENO,NODEPOSX,NODEPOSY from NODE where ZONENO="+zoneNo;
	pstm1 = con.prepareStatement(sql);
	rs= pstm1.executeQuery();
	
	double distance=10000;	
	int zoneLDR=1000;;
	while(rs.next()){
		
		double nodeCenterx=rs.getDouble("NODEPOSX");
		double nodeCentery=rs.getDouble("NODEPOSY");
		
		double curDis=Math.sqrt((zoneCenterx-nodeCenterx)*(zoneCenterx-nodeCenterx)+(zoneCentery-nodeCentery)*(zoneCentery-nodeCentery));
		
		if(curDis<distance){
		distance=curDis;
		zoneLDR=rs.getInt("NODENO");
		}
		
	}
	
	pstm1.close();
	
	PreparedStatement pstm3 = null;
	
		System.out.println("cmd not properly ended1");
	if(zoneLDR==1000)	
		sql = "update ZONE set ZONELDR=null where ZONENO="+zoneNo;
	else
		sql = "update ZONE set ZONELDR="+zoneLDR+" where ZONENO="+zoneNo;	
	pstm3 = con.prepareStatement(sql);
	int updated = pstm3.executeUpdate();
	pstm3.close();
	con.close();
	
	
}catch(Exception ex){
	ex.printStackTrace();

}



}
%>


