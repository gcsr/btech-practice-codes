package net.sourceforge.docfetcher.model.search;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateDocument {
	public static void createAndUpdate(String keyword,String fileName,String pagenumber, String paragraph){
		
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
			 XWPFParagraph paragraphOne = document.createParagraph();
		     XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		     paragraphOneRunOne.setItalic(true);
		     paragraphOneRunOne.setText(keyword);
		}catch(Exception ex){
			
		}
	}
	
	public static void main(String[] gcs){
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
			tableTwoRowOne.getCell(0).setText("hihihi 1");
			tableTwoRowOne.createCell().setText("hihihi 2");
			for (int i = 1; i < 4; i++) {
			    String node = "node";
			    String nodeVal = "";
			    XWPFTableRow tr = null;
			    node = node + (i + 1);
			    //nodeVal = tags.get(node).toString();
			    if (tr == null) {
			        tr = tableTwo.createRow();
			        tr.getCell(0).setText("hi there 1");
			        tr.getCell(1).setText("hi there 2");
			    }
			}
		     FileOutputStream out = new FileOutputStream(output);
		     
				document.write(out);
				out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
