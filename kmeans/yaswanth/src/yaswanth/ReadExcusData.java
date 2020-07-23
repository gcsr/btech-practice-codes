package yaswanth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class ReadExcusData {
	public static void main(String[] gcs){
		
		int ft=0;
		
		String fileData = "";
		
		File file=null;
		System.out.println("printing");
		
		//true = append files
	        try {
	        	
	        	FileInputStream fis = new FileInputStream("D:\\desktops\\desktop 3\\yashwanth app\\Excus 7.docx");
	        	System.out.println("before iterator");
	        	XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
	        	System.out.println("before iterator");
	        	Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
	        	System.out.println("before iterator");
	        	while(bodyElementIterator.hasNext()) {
	        	  IBodyElement element = bodyElementIterator.next();
	              if("TABLE".equalsIgnoreCase(element.getElementType().name())) {
	            	  List<XWPFTable> tableList =  element.getBody().getTables();
	            	  for (XWPFTable table: tableList){
	            		 // System.out.println("Total Number of Rows of Table:"+table.getNumberOfRows());
	            		  List<XWPFTableRow> tableRows=table.getRows();
	            		  for(XWPFTableRow tr:tableRows){
	            			  XWPFTableCell cell1=tr.getCell(0);
	            			  XWPFTableCell cell2=tr.getCell(1);
	            			  //System.out.println(cell1.getText());
	            			  String data=(cell2.getText());
	            			  String court="";
	            			  String citation="";
	            			  String citationYear="";
	            			  String companyName="";
	        	        	  String fullData=data;
	        	        	  if(data.length()<4){
	        	        		 // System.out.println(fullData);
	        	        		 // continue;
	        	        		  ft++;
	        	        	  }
	        	        	  else{
	            			  if(data.contains(" - This case was")){
	            				  data=data.substring(0,data.lastIndexOf(" - This case was"));
	            			  }
	            			  
	            			 	
	            			  
	            			  if(data.contains("Tri.-")){
	            				  court=data.substring(data.lastIndexOf("Tri.-"));
	        	        			citation=data.substring(0,data.lastIndexOf("Tri.-"));
	        	        			citation=citation.substring(citation.lastIndexOf("-")+1);
	        	        			
	            			  }else{
	        	        			court=data.substring(data.length()-4);
	        	        			citation=data.substring(0,data.length()-4).substring(data.lastIndexOf("-")+1);
	            			  }
	        	        		
	            			 String dd="[1-2][0-9]\\d\\d";
	            			  Pattern p = Pattern.compile(dd);
	            			  Matcher m = p.matcher(citation);
	            			  
	            			  //System.out.println(m.find());
	            			  if(m.find()){
	            				  citationYear=m.group();
	            			  }

	            			  
	        	        	  String[] split=citation.split("vs");
	        	        	  if(split[0].contains("Ltd")||split[0].contains("Limited")||split[0].contains("Enterprises"))
	        	        		  companyName=split[0];
	        	        	  else if(split.length==2 && (split[0].contains("Commissioner")||(split[0].contains("Union"))))
	        	        		  companyName=split[1];
	        	        	  else if(split.length==2 && (split[1].contains("Commissioner")||(split[1].contains("Union"))))
	        	        		  companyName=split[0];
	        	        	  else
	        	        		  companyName=split[0];
	        	        	  citation=citation.substring(citation.indexOf(citationYear));
	        	        	  
	        	        	  
	        	        	  fileData+=citationYear+"\r\n";
	        	        	  fileData+=court+"\r\n";
	        	        	  fileData+=companyName+"\r\n";
	        	        	  fileData+=citation+"\r\n";
	        	        	  fileData+=fullData+"\r\n";
	        	        	  
	        	        	  
	        	        	  ft++;
	        	        	  System.out.println(ft);
	        	        	  
	        	        	  if(ft%1000==0){
	        	        		  try{
	        	      		   		
	        	        	    		file =new File("D:\\desktops\\desktop 3\\yashwanth app\\Excus 7."+ft/1000+".txt");
	        	        	    		
	        	        	    		//if file doesnt exists, then create it
	        	        	    		if(!file.exists()){
	        	        	    			file.createNewFile();
	        	        	    		}
	        	        	    		
	        	        	    		//true = append file
	        	        	    		
	        	        	    		  FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
	        	        	    	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        	        	    	      bufferWritter.write(fileData);
	        	        	    	      bufferWritter.close();	           	    	    
	        	        	    	      System.out.println("Done");
	        	        	    	      fileData="";
	        	        	    		
	        	        		        
	        	        	    	}catch(IOException e){
	        	        	    		e.printStackTrace();
	        	        	    	}
	        	        	  	}
	        	        			  
	        	        	  }
	        	        	  //System.out.println(fileData);
	        	        	  
	            			 	
	            			 
	            			  
	        	        		
	        	        	}
	        	        //System.out.println(table.getText());
	            	  	}
	        	  	}
	             
	            }
	        	fis.close();
	        	
	        	
	        } catch(Exception ex) {
	        	ex.printStackTrace();
	        } 
		
	}
}
/*if(citation.contains("2015")){
citationYear="2015";
}else if(citation.contains("2014")){
	citationYear="2014";
}else if(citation.contains("2013")){
	citationYear="2013";
}else if(citation.contains("2012")){
	citationYear="2012";
}else if(citation.contains("2011")){
	citationYear="2011";
}else if(citation.contains("2010")){
	citationYear="2010";
}else if(citation.contains("2009")){
	citationYear="2008";
}else if(citation.contains("2007")){
	citationYear="2007";
}else if(citation.contains("2006")){
	citationYear="2006";
}else if(citation.contains("2005")){
	citationYear="2005";
}else if(citation.contains("2004")){
	citationYear="2004";
}else if(citation.contains("2003")){
	citationYear="2003";
}else if(citation.contains("2002")){
	citationYear="2002";
}else if(citation.contains("2001")){
	citationYear="2001";
}else if(citation.contains("2000")){
	citationYear="2000";
}else if(citation.contains("2005")){
	citationYear="2005";
}else if(citation.contains("2005")){
	citationYear="2005";
}else if(citation.contains("2005")){
	citationYear="2005";
}else if(citation.contains("2005")){
	citationYear="2005";
}else if(citation.contains("2005")){
	citationYear="2005";
}
*/
