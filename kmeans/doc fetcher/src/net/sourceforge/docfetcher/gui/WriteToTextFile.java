package net.sourceforge.docfetcher.gui;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sourceforge.docfetcher.model.search.ResultDocument;


public class WriteToTextFile {
	public static synchronized void writeToFile(String fileName,ResultDocument[] list){
		DateFormat dateFormat = new SimpleDateFormat();
		
		try{
		
			FileWriter writer = new FileWriter(fileName,true);		

			for(int i=0;i<list.length;i++){
				writer.write(list[i].getTitle());
				writer.write("\t");
				
				writer.write(String.valueOf(list[i].getScore()));
				writer.write("\t");
				
				String.format("%,d KB",list[i].getSizeInKB());
				writer.write("\t");
				
				writer.write(list[i].getFilename());
				writer.write("\t");
				
				writer.write(list[i].getType());
				writer.write("\t");
				
				
				
				writer.write(list[i].getPath().getCanonicalPath());
				writer.write("\t");
				
				writer.write(list[i].getAuthors());
				writer.write("\t");
				
				if(list[i].getLastModified()!=null){
					writer.write(dateFormat.format(list[i].getLastModified()));
					writer.write("\t");
				}
				
				writer.write("\r\n");
				
			}
		
		
			writer.close();
		}catch(Exception ex){
			
		}
	}
}
