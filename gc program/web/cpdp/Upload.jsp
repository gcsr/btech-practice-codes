<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.util.DbConnector"%>
<%@page import="java.io.*"%>
<%@page import="java.nio.*"%>
<%@page import="java.nio.channels.*"%>
<%@page import="javax.servlet.http.Part"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
	<head>
		<title>
			Uploading File
		</title>
	</head>
	
	<body>
<%
out.write("before connections");
Connection con = DbConnector.getConnection();
out.write("got connections");
try{ 
 out.write(request.getParts().size());
 for (Part part: request.getParts()) {    	
    System.out.println("inside");	
	String fileName="";
	for (String cd : part.getHeader("content-disposition").split(";")) {
          if (cd.trim().startsWith("filename")) {
            fileName=cd.substring(cd.indexOf('=') + 1).trim()
                    .replace("\"", "");
          }
    }

    out.write("filename is "+fileName);
    
	PreparedStatement pstm = null;
    String sql = "select * from transaction where user_id='"+session.getAttribute("userid")+"' and fileName='"+fileName+",";
    pstm = con.prepareStatement(sql);
    ResultSet rs = pstm.executeQuery();
    if(rs.next()){
		out.print("file alredy exist");
		rs.close();
		continue;
	}
	rs.close();
	pstm.close();
	out.write("pstm closed");
	InputStream is = request.getPart(part.getName()).getInputStream();
	int BUFFER_LENGTH = 4096;
	 System.out.println(fileName);
       FileOutputStream os = new FileOutputStream("D:/tomcat/webapps/cpdp/" + fileName);
       byte[] bytes = new byte[BUFFER_LENGTH];
       int read = 0;
       while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
           os.write(bytes, 0, read);
       }
       os.flush();
       is.close();
       os.close();
	
	File f=new File("D:/tomcat/webapps/cpdp/"+fileName); 
	FileInputStream fim=new FileInputStream(f);
    FileChannel fc=fim.getChannel();
    long size=f.length();
    System.out.println("length is "+size);
    int sizeOfThread=(int)(size/4);
    int sizeOfFinaThread=(int)(size-sizeOfThread*3);        
    ByteBuffer[] buffers=new ByteBuffer[4];
    buffers[0]=ByteBuffer.allocate(sizeOfThread);
    buffers[1]=ByteBuffer.allocate(sizeOfThread);
    buffers[2]=ByteBuffer.allocate(sizeOfThread);
    buffers[3]=ByteBuffer.allocate(sizeOfFinaThread);
    long ff=fc.read(buffers);
        
    System.out.println("reading buffers "+ff);
    FileOutputStream fout1=new FileOutputStream(new File("D:/tomcat/webapps/server1/files/"+fileName));
    FileOutputStream fout2=new FileOutputStream(new File("D:/tomcat/webapps/server2/files/"+fileName));
    FileOutputStream fout3=new FileOutputStream(new File("D:/tomcat/webapps/server3/files/"+fileName));
    FileOutputStream fout4=new FileOutputStream(new File("D:/tomcat/webapps/server4/files/"+fileName));
    FileChannel fc1=fout1.getChannel();
    FileChannel fc2=fout2.getChannel();
    FileChannel fc3=fout3.getChannel();
    FileChannel fc4=fout4.getChannel();
    buffers[0].flip();
    buffers[1].flip();
    buffers[2].flip();
    buffers[3].flip();
    ff=fc1.write(buffers[0]);
    System.out.println("reading buffers "+ff);
    ff=fc2.write(buffers[1]);
    System.out.println("reading buffers "+ff);
    ff=fc3.write(buffers[2]);
    System.out.println("reading buffers "+ff);
    ff=fc4.write(buffers[3]);
    System.out.println("reading buffers "+ff);
    fc1.close();
    fout1.close();
    fc2.close();
    fout2.close();
    fc3.close();
    fout3.close();
    fc4.close();
    fout4.close();
    fc.close();
/*    FileOutputStream outFile = new FileOutputStream("D:/pikini.jpg");
    FileChannel fcc = outFile.getChannel( );
    ff=fcc.write(buffers);
    outFile.close();
    fcc.close( );*/
	
	
	String query="insert into transaction values('"+session.getAttribute("userid")+"','"+fileName+"')";
	pstm = con.prepareStatement(query);
    int p = pstm.executeUpdate();
	out.write("uploaded file "+fileName);
	}
	con.close();
}
catch(Exception ex){
	ex.printStackTrace();
}
%>
                                         
</body>
</html>
