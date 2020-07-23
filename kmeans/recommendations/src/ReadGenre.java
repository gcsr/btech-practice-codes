import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ReadGenre {
	
	BufferedReader br=null;
	List<Genre> genres=new ArrayList<Genre>();
	
	public ReadGenre(String inputFileName){
		try{
			br = new BufferedReader(new FileReader(inputFileName));
		}catch(Exception ex){
			
		}
		setGenres();
		
	}	
	
	public List getGenres(){
		return genres;
	}
	
	public void setGenres(){
		
		String sCurrentLine;
		try{
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine=sCurrentLine.replace('|',':');
				System.out.println(sCurrentLine);
				String[] pp=sCurrentLine.split(":");
				System.out.println(pp[0]+" "+pp[1]);				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//System.out.println("empty space");
			
	}
	
	public void printGenres(){
		Iterator iterator=genres.iterator();
		while(iterator.hasNext()){
			Genre gr=(Genre)iterator.next();
			System.out.println(gr.getId()+"      "+gr.getName());
		}
	}
	
	
	public static void main(String[] gcs){
		/*Scanner scanner=new Scanner(System.in);
		System.out.println("Enter File Name For loading Genre");
		String fileName=scanner.next();*/
		new ReadGenre("D:/papers/harish/personalized/ml-100k/work/u.genre");
	}
	

}
