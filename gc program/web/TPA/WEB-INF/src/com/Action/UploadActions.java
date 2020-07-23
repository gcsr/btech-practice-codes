package com.Action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.util.DbConnector;
 
@WebServlet(name = "UploadActions", urlPatterns = {"/web/UploadActions"})
@MultipartConfig
public class UploadActions extends HttpServlet {
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    String user = request.getParameter("userid");
	    
	    Connection con = null;
        PreparedStatement pstm = null;
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
	    
	    
	    Part filePart = request.getPart("fileUp");
	    String fileName = getFileName(filePart);
	    System.out.println("filename is "+fileName);
	    
	    try {
            /*
             * Parse the request
            */ 
            con = DbConnector.getConnection();
            String sql1 = "select * from transaction where filename='"+fileName+"'";
            System.out.println(">>>>>>>>>>" + sql1);
            pstm = con.prepareStatement(sql1);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>.loop");
                response.sendRedirect("fileUpload.jsp?msg=File Name Already Exist");
                return;
            } 
            else {
            	OutputStream out = null;
            	InputStream filecontent = null;
            	PrintWriter writer = response.getWriter();
           		out = new FileOutputStream(new File("D:/"+fileName));
           		filecontent = filePart.getInputStream();
           		int read = 0;
           		final byte[] bytes = new byte[1024];

           		while ((read = filecontent.read(bytes)) != -1) 
	            out.write(bytes, 0, read);	        
	            writer.write("New file " + fileName + " created ");
           		
	            out.close();
	            
	            /*} catch (FileNotFoundException fne) {
	    		fne.printStackTrace();
	        	writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        	writer.write("<br/> ERROR: " + fne.getMessage());

	        
	    		} finally {
	        	if (out != null) {
	            
	        	}*/
	            if (filecontent != null) {
	            	filecontent.close();
	            }
	            
       		}
	
            String sq1 = "select * from cloud where status='Active'";
            pstm3 = con.prepareStatement(sq1);
            ResultSet rs1 = pstm3.executeQuery();
            String cld="";
            while (rs1.next()) {
              cld=rs1.getString("ip");
              System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user);
              
              File f=new File("D:/"+cld);
              if(!f.exists())
            	  f.mkdirs();
             
              FileInputStream inFile = new FileInputStream("D:/"+fileName);
              FileOutputStream outFile = new FileOutputStream(new File("D:/"+cld+"/"+fileName));
              FileChannel inChannel = inFile.getChannel( );
              FileChannel outChannel = outFile.getChannel( );             
              inChannel.transferTo(0, inChannel.size( ), outChannel);
              inChannel.close( );
              outChannel.close( );

              String sq2 = "insert into transaction values('" + user + "','" + fileName + "','Success','"+cld+"','Upload')";
              pstm1 = (PreparedStatement) con.prepareStatement(sq2);
              pstm1.executeUpdate();
              } 
              
           File f=new File("D:/"+fileName);
           if(f.exists())
        	   f.delete();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("fileUpload.jsp?msg=Cloud Not Connected");
            // log("Error encountered while uploading file", ex);
        }

	    
	}

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}

