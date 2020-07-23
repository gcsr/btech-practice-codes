package net.sourceforge.docfetcher.model.search;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ResultParser {
	
	int leftRight=0;
	public ResultParser(int leftRight){
		this.leftRight=leftRight;
	}
	public ArrayList<DocResult> print(ResultDocument rsd,Query query,boolean isPhraseQuery){
		//int leftRight=200;
		int previousStart=-400,previousEnd=-400;
		ArrayList<DocResult> results=new ArrayList<DocResult>();
		String fileName=rsd.getPath().getCanonicalPath();
		PDDocument pddDocument=null;
		int[] pageCounts=getPageCounts(fileName);
		try{
			
			pddDocument =PDDocument.load(fileName);
			
			
			PDFTextStripper textStripper=null;
			DocResult rs=null;
			
			//int lastpage = textStripper.getEndPage();
			//String page= null;
			textStripper=new PDFTextStripper();
			int pageCount=pddDocument.getNumberOfPages();
			textStripper.setStartPage(1);
			textStripper.setEndPage(pageCount);
			String pagesText=textStripper.getText(pddDocument);
			int pagesTextLength=0;
			HighlightedString hls=HighlightService.highlight(query, isPhraseQuery,pagesText);
			List<Range> ranges=hls.getRanges();
			Iterator<Range> iterator=ranges.iterator();
			int start=0;int end=0;
			
			int length=0;
			String para="";
			while(iterator.hasNext()){
				Range range=iterator.next();
				start=range.start;
				end=range.length+start;
				rs=new DocResult();
				
				length=query.toString().length();
				if(start>previousStart && end<previousEnd){
					System.out.println("skipping");
					continue;
				}
				if(start<leftRight){
					para=pagesText.substring(0,start+length);
					previousStart=0;
				}else{
					if((start-leftRight)<previousEnd){
						para=pagesText.substring(previousEnd,start+length);
						previousStart=previousEnd;						
					}else{
						para=pagesText.substring(start-leftRight,start+length);
						previousStart=start-leftRight;
					}
				}
				
				if((end+leftRight)<pagesTextLength){
					para+=pagesText.substring(end);
					previousEnd=pagesText.length();
				}else{
					para+=pagesText.substring(end,end+leftRight);
					previousEnd=end+leftRight;
				}
				rs.setFileName(fileName);
				rs.setKeyword(query.toString());//+" "+previousStart+" - "+previousEnd);
				
				rs.setEnd(previousEnd);
				rs.setStart(previousStart);
				rs.setParagraph(para);
				rs.setPageNumber(getPageNumber(pageCounts,start,end));
				results.add(rs);
			}
			
		}catch(Exception ex){
			
		}finally{
			try{
				if(pddDocument!=null){
					pddDocument.close();
				}
			}catch(Exception ex){
				
			}
		}
		
		//return results;
		return flterResults(results);

	}
	
	private String getPageNumber(int[] pageCounts,int start, int end){
		int i=0;
		while(pageCounts[i]<start && i<pageCounts.length){
			i++;
		}
		
		String pages="";
		int j=i;
		
		while(pageCounts[j]<end && j<pageCounts.length){
			j++;
		}
		if(i==j){
			pages="Page no "+(i+1);
		}
		else{
			pages+=" - "+(j+1);
			pages="Pages "+pages;
		}
		
		return pages;
	}
	
	private int[] getPageCounts(String path){
		int pageCounts[]=null;;
		
		try{
			PDDocument pddDocument =PDDocument.load(path);
		 
			PDFTextStripper textStripper=null;
			String page="";
			//int lastpage = textStripper.getEndPage();
			//String page= null;
			textStripper=new PDFTextStripper();
			
			int total=0;
			int pageCount=pddDocument.getNumberOfPages();
			pageCounts=new int[pageCount];
			for(int i=1; i<pageCount ; i++){
				textStripper=new PDFTextStripper();
				textStripper.setStartPage(i);
				textStripper.setEndPage(i);				
				page = textStripper.getText(pddDocument);
				total+=page.length();
				pageCounts[i-1]=total;
			}
			
			pddDocument.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
			return pageCounts;
			
			
	}
	
	public ArrayList<DocResult> flterResults(ArrayList<DocResult> input){
		ArrayList<DocResult> results=new ArrayList<DocResult>();
		Iterator<DocResult> itr=input.iterator();
		System.out.println("in filter results");
		while(itr.hasNext()){
			DocResult temp=itr.next();
			if(results.size()==0){
				results.add(temp);
			}else{
				DocResult temp2=results.get(results.size()-1);
				if(temp2.getEnd()==temp.start || temp2.getEnd()==(temp.start-1)){
					String tempPage=temp2.getPageNumber();
					String temp2Page=temp.getPageNumber();
					if(tempPage.contains("Page no ")){						
							if(temp2Page.contains("Page no")){
								int object1No=Integer.parseInt(tempPage.substring(8));
								int object2No=Integer.parseInt(temp2Page.substring(8));
								if(object1No==object2No){
									temp2.setParagraph(temp2.getParagraph()+temp.getParagraph());
								}else{
									temp2.setParagraph(temp2.getParagraph()+temp.getParagraph());
									temp2.setPageNumber("Pages "+object1No+" - "+object2No);
								}
							}else{
								int firstPage=Integer.parseInt(temp2Page.substring(temp2Page.indexOf(" ")+1,temp2Page.indexOf("-")-1));
								int secondPage=Integer.parseInt(temp2Page.substring(temp2Page.indexOf("-")+2));
								int object1No=Integer.parseInt(tempPage.substring(8));
								temp2.setParagraph(temp2.getParagraph()+temp.getParagraph());
								temp2.setPageNumber("Pages "+object1No+" - "+secondPage);
								
							}
					}else{
						if(temp2Page.contains("Page no")){
							
							int firstPage=Integer.parseInt(tempPage.substring(tempPage.indexOf(" ")+1,tempPage.indexOf("-")-1));
							int secondPage=Integer.parseInt(tempPage.substring(tempPage.indexOf("-")+2));					
							
							int object2No=Integer.parseInt(temp2Page.substring(8));
							
								temp2.setParagraph(temp2.getParagraph()+temp.getParagraph());
								temp2.setPageNumber("Pages "+firstPage+" - "+object2No);
							
						}else{
							int firstPage=Integer.parseInt(tempPage.substring(tempPage.indexOf(" ")+1,tempPage.indexOf("-")-1));
							int secondPage=Integer.parseInt(tempPage.substring(tempPage.indexOf("-")+2));
							
							int first2Page=Integer.parseInt(temp2Page.substring(temp2Page.indexOf(" ")+1,temp2Page.indexOf("-")-1));
							int second2Page=Integer.parseInt(temp2Page.substring(temp2Page.indexOf("-")+2));
							
							temp2.setParagraph(temp2.getParagraph()+temp.getParagraph());
							temp2.setPageNumber("Pages "+firstPage+" - "+second2Page);
							
						}
					}
					
				}else{
					results.add(temp);
				}
			}
		
		}
		return results;
	}
}
