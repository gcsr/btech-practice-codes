package net.sourceforge.docfetcher.model.search;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class WriteResultsToDoc {
	
	public static void createAndUpdate(ArrayList<DocResult> results){
		
		try{
			XWPFDocument document= new XWPFDocument(); 
			File output=new File("D:\\output.doc");
			if(output.exists()){
				
			}
			else{
				
				//	Write the Document in file system
				FileOutputStream out = new FileOutputStream(output);
				document.write(out);
				out.close();
				System.out.println(
						"c"
							+ "reatedocument.docx written successully");
			}	
			
			XWPFTable tableTwo = document.createTable();
			XWPFTableRow tableTwoRowOne = tableTwo.getRow(0);
			tableTwoRowOne.getCell(0).setText("Parameter");
			tableTwoRowOne.createCell().setText("Value");
			
			Iterator<DocResult> itr=results.iterator();
			
			while(itr.hasNext()){
				DocResult dr=itr.next();
			
				XWPFTableRow tr = null;
			     tr=tableTwo.createRow();
			     tr.getCell(0).setText("Keyword");
			     
			     tr.getCell(1).setText(dr.getKeyword());
			     
			     XWPFTableRow tr1=tableTwo.createRow();
			     tr1.getCell(0).setText("FileName");
			     tr1.getCell(1).setText(dr.getFileName());//dr.getFileName()
			     
			     XWPFTableRow tr2=tableTwo.createRow();
			     tr2.getCell(0).setText("Page Number");
			     tr2.getCell(1).setText(dr.getPageNumber());
			     
			 
			     XWPFTableRow tr3=tableTwo.createRow();
			     tr3.getCell(0).setText("Para Graph");
			     tr3.getCell(1).setText(dr.getParagraph());;
			     
			}
			
			FileOutputStream out = new FileOutputStream(output);
			
			document.write(out);
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
}
