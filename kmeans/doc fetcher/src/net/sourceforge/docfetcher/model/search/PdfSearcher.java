package net.sourceforge.docfetcher.model.search;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfSearcher {
	
	DocResult rs;
	
	
	
	public ArrayList<DocResult> print(String text, String fileName){
		
		ArrayList<DocResult> results=new ArrayList<DocResult>();
		
		
		
		
		String page=null;
		ArrayList<Integer> pageNumbers;
		PDDocument pddDocument =null;
		PDFTextStripper textStripper=null;
		int found=0;
		int pageCount=0;
		String para="";
		String nextProcess="";
		String firstHalf=null;
		String nextHalf;
		int first;
		
		
		int end;
		System.out.println("searching for text "+text+" in file "+fileName);
		try{
			pddDocument =PDDocument.load(fileName);
			
			//int lastpage = textStripper.getEndPage();
			//String page= null;
			found= 0;
			pageCount=pddDocument.getNumberOfPages();
			
			pageNumbers=new ArrayList<Integer>();
			//System.out.println(" last page is "+pageCount);
			
			for(int i=1; i<pageCount ; i++){
				textStripper=new PDFTextStripper();
				textStripper.setStartPage(i);
				textStripper.setEndPage(i);				
				page = textStripper.getText(pddDocument);
				//System.out.println(page);
			
				/*System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println(page);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();*/
				
				found = page .indexOf(text);	
				
				if(found>=0){
					para="";
					/*System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println(page);
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();*/
					//int f=page.indexOf("\n");
					//System.out.println(f);
					nextProcess="";
					
					firstHalf=page.substring(0,found);
					nextHalf=page.substring(found+text.length());
					
					first=firstHalf.lastIndexOf("\n");
					if(first<0){
						end=page.indexOf("\n");
						if(end<0){
							para=page;
							
						}else{
							para=page.substring(0,page.indexOf("\n"));
							nextProcess=page.substring(page.indexOf("\n")+1);
						}
					}
					else{
						end=nextHalf.indexOf("\n");
						if(end<0){
							para=page;
						}else{
							int s=firstHalf.indexOf("\n");
							para=page.substring(s,s+page.substring(s+1).indexOf("\n"));
							nextProcess=page.substring(s+page.substring(s+1).indexOf("\n")+1);
						}
					}
					
					//String para="";//=page.substring(page.substring(0,found).lastIndexOf("\n"),page.substring(found).indexOf("\n"));
					
					rs=new DocResult();
					rs.setFileName(fileName);
					rs.setKeyword(text);
					rs.setParagraph(para);
					rs.setPageNumber(i+"");
					results.add(rs);
					if(!nextProcess.equals(""))
						processSubString(i,text,nextProcess,fileName,results);
				}else{
					continue;
				}
				
				//textStripper.
				
				//pageNumbers.add(found);
			}
			pddDocument.close();
			
			return results;
			//WriteResultsToDoc.createAndUpdate(results);
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return results;
	}
	
	public void processSubString(int i,String text,String page,String fileName, ArrayList<DocResult> results){
		
		
		ArrayList<Integer> pageNumbers;
		PDDocument pddDocument =null;
		PDFTextStripper textStripper=null;
		int found=0;
		int pageCount=0;
		String para="";
		String nextProcess="";
		String firstHalf=null;
		String nextHalf;
		int first;
		
		try{
			found = page.indexOf(text);	
			
			if(found>=0){
				para="";
				

				/*System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println(page);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();*/
				
				
			//	int f=page.indexOf("\n");
				//System.out.println(page);
				nextProcess="";
				//System.out.println(found);
				firstHalf=page.substring(0,found);
				nextHalf=page.substring(found+text.length());
				
				first=firstHalf.lastIndexOf("\n");
				if(first<0){
					int end=page.indexOf("\n");
					if(end<0){
						para=page;
						
					}else{
						para=page.substring(0,page.indexOf("\n"));
						nextProcess=page.substring(page.indexOf("\n")+1);
					}
				}
				else{
					int end=nextHalf.indexOf("\n");
					if(end<0){
						para=page;
					}else{
						int s=firstHalf.lastIndexOf("\n");
						para=page.substring(s,s+page.substring(s+1).indexOf("\n"));
						nextProcess=page.substring(s+page.substring(s+1).indexOf("\n")+1);
					}
				}
				
				//String para="";//=page.substring(page.substring(0,found).lastIndexOf("\n"),page.substring(found).indexOf("\n"));
				rs=new DocResult();
				rs.setFileName(fileName);
				rs.setKeyword(text);
				rs.setParagraph(para);
				rs.setPageNumber(i+"");
				results.add(rs);
				
				if(!nextProcess.equals(""))
					processSubString(i,text,nextProcess,fileName,results);
				else
					return;
			}else{
				return;
			}
		}catch(Exception ex){
			
		}
	
	}
}
