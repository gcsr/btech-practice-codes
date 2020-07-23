import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CNNFinalCrawler{
	private static ArrayList<String> toFile=new ArrayList<String>();
	private static String currentURL="http://www.cnn.com";
	
	public static void main(String[] args){
		StringBuffer basePage = new StringBuffer();
		// Connect to CNN and get the document
		/*basePage = getBasePageContents(currentURL);	
		basePage=new StringBuffer(getNavigtion(basePage.toString()));	
		String[][] results=getLinks(basePage.toString());*/
		String[][] results=new String[4][2];
		Scanner scanner=new Scanner(System.in);
		//System.out.println("Enter number of headlines per page");
		int numberOfHeadlines=3;//scanner.nextInt();
		System.out.println();
		int input=0;
		results[0][0]="/politics";
		results[0][1]="Politics";
		
		
		results[1][0]="/tech";
		results[1][1]="Technology";
		
		results[2][0]="/sport";
		results[2][1]="Sport";
		
		results[3][0]="/travel";
		results[3][1]="Travel";
		
		
		while(true){
			/*System.out.print(1+" : Home\t");			
			System.out.println(2+" : "+results[1][1]);
			try{
				for(int i=2;i<results.length;i+=4){			
					System.out.print(i+1+" : "+results[i][1]+"\t\t");
					System.out.print(i+2+" : "+results[i+1][1]+"\t\t");
					System.out.print(i+3+" : "+results[i+2][1]+"\t\t");
					System.out.println(i+4+" : "+results[i+3][1]);			
				}
			}catch(Exception ex){
				
			}*/
			
			for(int i=0;i<results.length;i+=1){			
				System.out.println(i+1+" : "+results[i][1]);					
			}
			
			
			System.out.println("5 : write to file");
			System.out.println("Enter your option");
			input=scanner.nextInt();
			if(input==5){
				writeToFile();
			}
			else if(input>4)
				break;
			else if(input<=results.length)
				writeOutput(input,results,numberOfHeadlines);
			
			
	   }		
		
		
	}
	
	public static void writeOutput(int option, String[][] values,int numberofIputs){
		/*if(option==1){
			String basePage = getBasePageContents(currentURL).toString();
			toFile.add("\r\nHome\r\n");
			//System.out.println(basePage.toString());
			String regexPattern="<span class=\"cd__headline-text\">.*?</span>";
			Pattern r = Pattern.compile(regexPattern);
		      // Now create matcher object.
		    Matcher m = r.matcher(basePage);
		    ArrayList<String> links=new ArrayList<String>();
		    int i=0;
		    String one="";
		    while(m.find( ) && i<numberofIputs) {
		        // return m.group(0);
		    	one=m.group(0);
		    	toFile.add(one.substring(one.indexOf(">")+1,one.lastIndexOf("</span>")));
		    	//links.add(m.group(0));
		    	i++;
		       // System.out.println(m.group(0) );
		         //System.out.println("Found value: " + m.group(2) );
		    } 
		    //return getDetails(links);

		}*///else{
			String basePage = getBasePageContents(currentURL+values[option-1][0]).toString();
			toFile.add("\r\n"+values[option-1][1]+"\r\n");
			//System.out.println(basePage.toString());
			String regexPattern="<span class=\"cd__headline-text\">.*?</span>";
			Pattern r = Pattern.compile(regexPattern);
		      // Now create matcher object.
		    Matcher m = r.matcher(basePage);
		    ArrayList<String> links=new ArrayList<String>();
		    int i=0;
		    String one="";
		    while(m.find( ) && i<numberofIputs) {
		        // return m.group(0);
		    	one=m.group(0);
		    	toFile.add(one.substring(one.indexOf(">")+1,one.lastIndexOf("</span>")));
		    	//links.add(m.group(0));
		    	i++;
		       // System.out.println(m.group(0) );
		         //System.out.println("Found value: " + m.group(2) );
		    } 
		    //return getDetails(links);

		//}
		
	}
	
	public static void writeToFile(){
		try{
			File file =new File("cnncrawler.txt");
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		
    		  StringBuffer sb=new StringBuffer();
    		  FileWriter fileWritter = new FileWriter(file.getAbsolutePath(),true);
    	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	      Iterator<String> strItr=toFile.iterator();
    	      while(strItr.hasNext()){
    	    	  sb.append(strItr.next()+"\r\n");
    	      }
    	      bufferWritter.write(sb.toString());
    	      bufferWritter.close();	           	    	    
    	      System.out.println("Data is written file");   
    	      toFile=new ArrayList<String>();
    		
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}

	
	public static StringBuffer getBasePageContents(String myURL){
		try{
			// 	Set base document to CNN, open connection,
			// and copy the source text into a buffer
			URL cnnBaseDoc = new URL(myURL);
			cnnBaseDoc.openConnection();
			BufferedReader cnnBaseBuffer = new BufferedReader(
					new InputStreamReader(
							cnnBaseDoc.openStream()));
			String cnnBaseInputLine;
			StringBuffer tempDocument = new StringBuffer();
			while ((cnnBaseInputLine = cnnBaseBuffer.readLine()) != null){
				tempDocument.append(cnnBaseInputLine);
			}
			cnnBaseBuffer.close();
			return(tempDocument);
		}
		catch(MalformedURLException e) {
			System.out.println("Unable to create URL object");
			return(null);
		}
		catch(IOException e){
			System.out.println("Unable to open URL");
			return(null);
		}
	}
	
	// 	Method: initialIsolateBasePageContents
	//
	// 	This method isolates us to store only the section we are interest in --
	// 	the "MORE FROM CNN" section
	//
	
	public static String getNavigtion(String buffer){
		String regexPattern="<nav.*?</nav>";
		Pattern r = Pattern.compile(regexPattern);

	      // Now create matcher object.
	    Matcher m = r.matcher(buffer);
	    if (m.find( )) {
	         return m.group(0);
	        // System.out.println("Found value: " + m.group(1) );
	         //System.out.println("Found value: " + m.group(2) );
	    } else {
	    	return "";
	         //System.out.println("NO MATCH");
	    }
	}
	
	public static String[][] getLinks(String basePage){
		String regexPattern="<a.*?>.*?</a>";
		Pattern r = Pattern.compile(regexPattern);

	      // Now create matcher object.
	    Matcher m = r.matcher(basePage);
	    ArrayList<String> links=new ArrayList<String>();
	    while(m.find( )) {
	        // return m.group(0);
	    	links.add(m.group(0));
	       // System.out.println(m.group(0) );
	         //System.out.println("Found value: " + m.group(2) );
	    } 
	    return getDetails(links);
	}
	
	private static String[][] getDetails(ArrayList<String> links){
		ArrayList<String> linkRs=new ArrayList<String>();
		Iterator<String> linksIterator=links.iterator();
		String link="";
		while(linksIterator.hasNext()){
			link=linksIterator.next();
			int middleStart=link.indexOf('>');
			int end=link.lastIndexOf("</a>");
			String middleText=link.substring(middleStart+1,end);
			String url="";
			String text="";
			int hrefIndex=0;
			String result="";
			if(middleText.contains("<"));
			
			else{
				hrefIndex=link.indexOf("href");
				link=link.substring(hrefIndex);
				
				link=link.substring(link.indexOf("\"")+1);
				
				url=link.substring(0,link.indexOf("\""));
				
				link=link.substring(link.indexOf(">")+1);
				text=middleText;
				//if(text.equals(arg0))
				//System.out.println(url);
				//System.out.println(text);
				result=url+"      "+text;
				//System.out.println(result);
				linkRs.add(result);
				//System.out.println(middleText+"\t"+url);
			}
			
			
		
		}
		String[][] results=new String[linkRs.size()][2];
		Iterator<String>linksRIterator=linkRs.iterator();
		int i=0;
		while(linksRIterator.hasNext()){
			link=linksRIterator.next();
			results[i]=link.split("      ");
			//System.out.println(results[i].length);
			i++;
		}
		
		return results;
	}
	
	

}