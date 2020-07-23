<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.zip.*"%>
<%@ page import="java.sql.*,databaseconnection.*"%>
<%@page import="com.oreilly.servlet.*,java.sql.*,java.lang.*,databaseconnection.*,java.text.SimpleDateFormat,java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*" %>
<%@page import=" java.security.MessageDigest"%>
<%@page import=" java.security.*"%>
<%@page import="javax.crypto.*"%>


<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%

	java.util.Date now = new java.util.Date();
	String date=now.toString();
	 String DATE_FORMAT = "yyyy-MM-dd";
	 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
     String strDateNew = sdf.format(now) ;
 String un=(String)session.getAttribute("id");
String fn1=(String)session.getAttribute("fn1");
 String fid1=(String)session.getAttribute("f");

//status="ondatabase";


String saveFile="";
//String contentType1=request.getAttribute("file");
String contentType = request.getContentType();




if((contentType != null)&&(contentType.indexOf("multipart/form-data") >= 0)){
DataInputStream in = new DataInputStream(request.getInputStream());
int formDataLength = request.getContentLength();
byte dataBytes[] = new byte[formDataLength];
int byteRead = 0;
int totalBytesRead = 0;
while(totalBytesRead < formDataLength){
byteRead = in.read(dataBytes, totalBytesRead,formDataLength);
totalBytesRead += byteRead;
}


int ch;
    StringBuffer strContent = new StringBuffer("");
    FileInputStream fin = null;
	
	
String file = new String(dataBytes);
saveFile = file.substring(file.indexOf("filename=\"") + 10);
saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
int lastIndex = contentType.lastIndexOf("=");
String boundary = contentType.substring(lastIndex + 1,contentType.length());
int pos;
pos = file.indexOf("filename=\"");
pos = file.indexOf("\n", pos) + 1;
pos = file.indexOf("\n", pos) + 1;
pos = file.indexOf("\n", pos) + 1;
int boundaryLocation = file.indexOf(boundary, pos) - 4;
int startPos = ((file.substring(0, pos)).getBytes()).length;
int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
File ff = new File(saveFile);
FileOutputStream fileOut = new FileOutputStream(ff);
fileOut.write(dataBytes, startPos, (endPos - startPos));
fileOut.flush();
fileOut.close();


fin = new FileInputStream(ff);
      while ((ch = fin.read()) != -1)
        strContent.append((char) ch);
      fin.close();
	  String ss=(strContent.toString());





%>





<%
	String ccc=(String)session.getAttribute("name");
%>
    

<%

String status="waiting";
FileInputStream fis;
try{

Connection con = databasecon.getconnection();

PreparedStatement ps=con.prepareStatement("insert into fileupload(uid,fname,fid,size,date,ff,owner,fileid,status) values(?,?,?,?,?,?,'"+ccc+"',?,'"+status+"')");
		//File f = new File(saveFile);
				ps.setString(1,un);
                ps.setString(3,fn1);
                ps.setString(2,fid1);
fis = new FileInputStream(ff);


	  
	  
		 double size=ff.length();
		  double  size1=size/1024;
		  double size2=Math.round(size1*100.0)/100.0;
		  String size3=Double.toString(size2);
		  session.setAttribute("filesize",size3);
	
        ps.setBinaryStream(7, (InputStream)fis, (int)(ff.length()));
        ps.setString(4,size3);
ps.setString(5,strDateNew);
ps.setString(6,ss);


ps.executeUpdate();
			
		
			response.sendRedirect("user5.jsp?success");
				
		} 
		
		catch (Exception e) 
		{
			out.println(e.getMessage());
		}

	}
	
				
	
						
%>
