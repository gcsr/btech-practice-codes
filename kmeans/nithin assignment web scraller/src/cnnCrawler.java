import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.REMatch;
import gnu.regexp.REMatchEnumeration;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class cnnCrawler{
	//private static ArrayList<String> links=new ArrayList<String>()
	private static String currentURL="http://www.cnn.com";
	
	public static void main(String[] args){
		StringBuffer basePage = new StringBuffer();
		// Connect to CNN and get the document
		basePage = getBasePageContents(currentURL);	
		basePage=new StringBuffer(getNavigtion(basePage.toString()));	
		String[][] results=getLinks(basePage.toString());			
		for(int i=0;i<results.length;i+=2){
			System.out.print(i+1+" : "+results[i][1]+"\t");			
			System.out.println(i+2+" : "+results[i+1][1]);
			
		}
	
	}

	/*for(int i=0;i<results.length;i++){
	try{
		//	System.out.println(results[i].length);
		System.out.println(results[i][0]+"\t"+results[i][1]);
	}catch(Exception ex){
		
	}
	//	System.out.println(results[i][0]+"\t"+results[i][1]);
}*/
	
	/*// Look at the area of interest (The "MORE FROM CNN" section)
	//basePage = initialIsolateBasePageContents(basePage);
	// Pull all of the URLs out
	basePage = getInfo(basePage, "&nbsp;<a href=\"[^\"]*|/b> <a href=\"[^>]*|/b><ahref=\"[^>]*");
	//System.out.println("hi "+basePage.toString());
	basePage = getInfo(basePage, "\"/[^(\")]*");
	basePage = getInfo(basePage,"\"[^&]*");
	// Go to the URLs and pull out the information of interest and
	// write to file.
	goToURLs(basePage);*/
	


	// Method: getBasePageContents
	//
	// This method opens a connection to the webpage we are interested in and stores
	// all of the text on the page

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
	public static StringBuffer initialIsolateBasePageContents(StringBuffer basePage){
		try{
			RE document = new RE(basePage);
			// 	Define the left and right isolators
			String sLeft = new String("MORE FROM CNN[//w//W]*");
			RE leftCntxt = new RE(sLeft);
			RE rightCntxt= new RE("><b>SPORTS");
			StringBuffer sLIsolator = new StringBuffer("");
			int iLIsolatorIndex = 0;
			RE regLIsolator = new RE(leftCntxt);
			REMatch ctxtLMatch = regLIsolator.getMatch(basePage);
			sLIsolator.append(ctxtLMatch.toString());
			System.out.println("PRINTING MATCHES "+ctxtLMatch.toString());
			iLIsolatorIndex = ctxtLMatch.getStartIndex();
			// 	Find the Right Isolator
			StringBuffer sRIsolator = new StringBuffer();
			RE regRIsolator = new RE(rightCntxt);
			int iRIsolatorIndex = 0;
			REMatch ctxtRMatch = regRIsolator.getMatch(basePage);
			sRIsolator.append(ctxtRMatch.toString());
			iRIsolatorIndex = ctxtRMatch.getStartIndex();
			basePage.delete(iRIsolatorIndex, basePage.length());
			basePage.delete(0, iLIsolatorIndex);
			return(basePage);
		}
		catch(REException e){
			System.out.println("RE Exception");
			return(null);
		}
	}
	
	// 	Method: getInfo
	//
	// This method applies the specified regular expression to the string passed in
	
	public static StringBuffer getInfo(StringBuffer textToSearch, String regExp){
		try{
			StringBuffer sIsolated = new StringBuffer("");
			int iLIsolatorIndex = 0;
			String sLeft = new String(regExp);
			RE leftCntxt = new RE(sLeft);
			RE regLIsolator = new RE(leftCntxt);
			REMatchEnumeration ctxtLMatch = regLIsolator.getMatchEnumeration(textToSearch);
			while (ctxtLMatch.hasMoreMatches()){
				sIsolated.append(ctxtLMatch.nextMatch().toString());
				sIsolated.append("\n");
			}
			return(sIsolated);
		}
		catch(REException e){
			System.out.println("RE Exception");
			return(null);
		}
	}
	public static void goToURLs(StringBuffer textToSearch)
	{
		try{
			StringBuffer interestingDoc = new StringBuffer("");
			StringBuffer sInfoForFile = new StringBuffer("");
			int numPage=0;
			FileOutputStream fCnnOut;
			PrintStream pCnnOut;
			String sLeft = new String("/[^\"]*");
			RE leftCntxt = new RE(sLeft);
			String sIsolated = new String();
			int iLIsolatorIndex = 0;
			RE regLIsolator = new RE(leftCntxt);
			REMatchEnumeration ctxtLMatch = regLIsolator.getMatchEnumeration(textToSearch);
			fCnnOut = new FileOutputStream("cnnCrawlerOutput.txt");
			pCnnOut = new PrintStream(fCnnOut);
			while (ctxtLMatch.hasMoreMatches())
			{
				numPage++;
				sIsolated = "http://www.cnn.com";
				sIsolated += (ctxtLMatch.nextMatch().toString());
				interestingDoc = connectToURLs(sIsolated);
				sInfoForFile = getDocInfo(interestingDoc, sIsolated, numPage);
				pCnnOut.println (sInfoForFile);
			}
			pCnnOut.close();
			System.out.println("You may view the output in file: cnnCrawlerOutput.txt.");
		}
		catch(REException e){
			System.out.println("RE Exception");
		}
		catch (Exception e)
		{
			System.out.println ("Error writing file.");
		}
	}
	
	// 	Method: connectToURLs
	// 	This method opens a URL and returns the text of the page

	public static StringBuffer connectToURLs(String urlText){
		try{
			URL cnnBaseDoc = new URL(urlText);
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
	
	// 	Method: getDocInfo
	//
	// 	This method returns the interesting information that we were asked to parse out
	// 	including: Date, Place, Headline, URL, and First paragraph.

	public static StringBuffer getDocInfo(StringBuffer doc, String URL, int ID){
		StringBuffer importantInfoToReturn = new StringBuffer("");
		StringBuffer Headline = new StringBuffer("");
		StringBuffer Date = new StringBuffer("");
		StringBuffer Place = new StringBuffer("");
		StringBuffer FirstParagraph = new StringBuffer("");
		URL = URL.substring(0, (URL.length()-1));
		Date.append(getInfo(doc, "name=\"DATE\" content=\"[^>]*"));
		if(Date.length() > 0){
			Date.delete(0,21);
			Date.delete((Date.length()-1), Date.length());
		}
		else{
			Date.append("No date Reported.");
		}
		Place.append(getInfo(doc, "<p><b>[^(<p>)]*|<p><b>[^-]*"));
		if(Place.length() > 0){
			Place.delete(0,6);
		}
		else{
			Place.append("No location Reported.");
		}
		Headline.append(getInfo(doc, "<title>CNN.com - [^-]*"));
		if(Headline.length() > 0){
			Headline.delete(0,17);
			Headline.delete((Headline.length()-1), Headline.length());
		}
		else{
			Headline.append("No headline Reported.");
		}
		FirstParagraph.append(getInfo(doc, "DESCRIPTION\" content=[^>]*"));
		if(FirstParagraph.length() > 0){
			FirstParagraph.delete(0, 22);
			FirstParagraph.delete(FirstParagraph.length()-1, FirstParagraph.length());
		}
		importantInfoToReturn.append("\n");
		importantInfoToReturn.append((ID + " | "));
		importantInfoToReturn.append((Headline + " | "));
		importantInfoToReturn.append((URL + " | "));
		importantInfoToReturn.append((Date + " | "));
		importantInfoToReturn.append((Place + " | "));
		importantInfoToReturn.append((FirstParagraph));
		return(importantInfoToReturn);
	}
}