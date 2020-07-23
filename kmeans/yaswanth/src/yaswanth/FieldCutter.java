package yaswanth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldCutter {
	public static void main(String[] gcs){
		
	
		File folder=new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\");
		String fullData="";
		String sCurrentLine="";
		HashMap<String, ArrayList<Record>> filestoSave=new HashMap<String,ArrayList<Record>>();		
		boolean exceptionThrown=false;
		String[] list=folder.list();
		StringBuilder sb=new StringBuilder();
		int numberCount=0;;
		int companyCount=0;
		BufferedReader br=null;
		String data="";
		String court="";
		  	String citation="";
		  	String citationYear="";
		  	String companyName="";
		try {			
			br = new BufferedReader(new FileReader(folder+"\\onlyData.txt"));
			
			int i=0;
	
			while ((sCurrentLine = br.readLine()) != null) {
				
  			  	data=sCurrentLine;	  
  			  	if(data.length()<4){
  			  		//System.out.println(fullData);
  			  		// continue;	        	
  			  	}
  			  	else{
  			  		/*if(data.contains(" - This case was")){
  			  			data=data.substring(0,data.lastIndexOf(" - This case was"));
  			  		}*/
  			  		
  			  		
  			  		/*if(data.contains("Tri.-")){
  			  			court=data.substring(data.lastIndexOf("Tri.-"));
  			  			citation=data.substring(0,data.lastIndexOf("Tri.-"));
  			  			citation=citation.substring(citation.lastIndexOf("-")+1);
  			  			
  			  		}else{*/
  			  		numberCount++;
  			  		
  			  	 String ddd="197\\d|198\\d|199\\d|200\\d|2010|2011|2012|2013|2014|2015";
  			  	 Pattern pp = Pattern.compile(ddd);
  			  	 Matcher mm = pp.matcher(data);
  			  	 citation="";
   			  
  			  	 //System.out.println(m.find());
  			  	 ArrayList<String> matches=new ArrayList<String>();
  			  	 
  			  	 while(mm.find()){
  			  		 matches.add(mm.group());
  			  	 }	
  			  	 
  			  	 int size=matches.size();
  			  	 
  			  	 for(int ikp=size-1;ikp>=0;ikp--){
  			  		 try{
  			  			 citationYear=matches.get(ikp);
  			  			String temp=data.substring(data.lastIndexOf(""+citationYear));
  	  			  	    temp=temp.substring(5);
  	  			  	    citation+=citationYear+" ";
  	  			  	    
  	  			  	    System.out.println(temp);
  	  			  	    
  	  			  	    citation+=temp.substring(0,temp.indexOf(" "))+" ";
  				  		temp=temp.substring(temp.indexOf(" ")+1);
  				  		System.out.println(temp);
  				  		
  				  		citation+=temp.substring(0,temp.indexOf(" "))+" ";
  					  	temp=temp.substring(temp.indexOf(" ")+1);
  					  	System.out.println(temp);
  				  		
  					  	citation+=temp.substring(0,temp.indexOf(" "));
  					  	temp=temp.substring(temp.indexOf(" ")+1);  
  					  	System.out.println(temp);
  					  	
  					  	try{
  					  		int nextSpace=temp.indexOf(" ");
  					  		if(nextSpace>=0){
  					  			court=temp.substring(0,nextSpace);
  					  		}else{
  					  			court=temp;
  					  		}
  					  	}catch(Exception ex){
  					  		
  					  	}  	  			  		
  	  			  		
  	  			  		break;
  	  			  		
  			  		 }catch(Exception ex){
  			  			ex.printStackTrace(); 
  			  		 }
  			  	 }
  			  	   /* String temp=data.substring(data.lastIndexOf(""+citationYear));
  			  	    temp=temp.substring(5);
  			  	    citation+=citationYear+" ";
  			  	    
  			  	   // System.out.println(temp);
  			  	    
  			  	    citation+=temp.substring(0,temp.indexOf(" "))+" ";
			  		temp=temp.substring(temp.indexOf(" ")+1);
			  		//System.out.println(temp);
			  		
			  		citation+=temp.substring(0,temp.indexOf(" "))+" ";
				  	temp=temp.substring(temp.indexOf(" ")+1);
				  	//System.out.println(temp);
			  		
				  	citation+=temp.substring(0,temp.indexOf(" "));
				  	temp=temp.substring(temp.indexOf(" ")+1);  
				  	//System.out.println(temp);
  			  		
  			  		court=temp;
  			  		
  			  		System.out.println(temp);*/
  			  		/*citation=data.substring(data.lastIndexOf(" "))+citation;
  			  		data=data.substring(0,data.lastIndexOf(" "));
  			  		
  			  		citation=data.substring(data.lastIndexOf(" "))+citation;
			  		data=data.substring(0,data.lastIndexOf(" "));
			  		
			  		String token=data.substring(data.lastIndexOf(" ")+1);
			  		citation=token+citation;
  			  		//data=data.substring(0,data.lastIndexOf(" "));
  			  		
  			  		citationYear=token;	  */		
  			  		
  			  		
  			  		//citation=data.substring(0,data.lastIndexOf(court)-1).substring(data.lastIndexOf("-")+1);
  			  		//}
  			  		
  			  		//String dd="\\[(para.*?)\\] - .*?vs.*? [1-2][0-9]\\d\\d";
  			  		String dd=" - .*?vs.*? [1-2][0-9]\\d\\d";
  			  		Pattern p = Pattern.compile(dd);
  			  		Matcher m = p.matcher(data);
  			  		
  			    	//System.out.println(m.find());
  			  		if(m.find()){
  			  			companyCount++;
  			  			companyName=m.group();  
  			  			
  			  		}
  			  		
  			  		
  			  		
  			  		/*String[] split=citation.split("vs");
  			  		if(split[0].contains("Ltd")||split[0].contains("Limited")||split[0].contains("Enterprises"))
  			  			companyName=split[0];
  			  		else if(split.length==2 && (split[0].contains("Commissioner")||(split[0].contains("Union"))))
  			  			companyName=split[1];
  			  		else if(split.length==2 && (split[1].contains("Commissioner")||(split[1].contains("Union"))))
  			  			companyName=split[0];
  			  		else
  			  			companyName=split[0];
  			  		citation=citation.substring(citation.indexOf(citationYear));*/
  			  		System.out.println(citation);
  			  		//if(citation.contains("2015 (037) STR"))
  			  			//System.out.println(data);
  			  		int found=companyName.lastIndexOf(" - ");
  			  		if(found>=0)
  			  			companyName=(companyName.substring(companyName.lastIndexOf(" - ")+3));
  			  		
  			  		if(companyName.length()>5)
  			  		companyName=companyName.substring(0,companyName.length()-5);
  			  		if(companyName==null || companyName.equals("")){
  			  			companyName="Not Available";
  			  		}
  			  		
  			  		//System.out.println(citation);
  			  		//System.out.println(companyName);
  			  		//if(court.equals(""))
  			  			//break;
  			  		sb.append(citationYear+"\r\n");
  			  		sb.append(court+"\r\n");
  			  		sb.append(citation+"\r\n");
  			  		sb.append(companyName+"\r\n");
  			  		sb.append(sCurrentLine+"\r\n");
  			  		//System.out.println(numberCount);
  			  	
  			  		
				 						
  			  	}
  			  	
  			  	
			}
			//System.out.println(numberCount);
			 // System.out.println(companyCount);
			br.close();
		} catch (IOException e) {
			exceptionThrown=true;
			System.out.println(data);
			System.out.println(citationYear);
			e.printStackTrace();
		} catch(Exception ex){
			System.out.println(data);
			System.out.println(citationYear);
			ex.printStackTrace();
		}finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				
				ex.printStackTrace();
			}
		}
	
		if(!exceptionThrown)
		save(sb);
	
			
	}		//System.out.println(i);
	
	public static void save(StringBuilder sb){
		
		try{
			File file =new File("D:\\desktops\\desktop 3\\yashwanth app\\year\\finalFullCut.txt");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		
    		  FileWriter fileWritter = new FileWriter(file.getAbsolutePath());
    	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	      bufferWritter.write(sb.toString());
    	      bufferWritter.close();	           	    	    
    	      System.out.println("fullData is written");    	      
    		
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
  	
	}
		
	

}
