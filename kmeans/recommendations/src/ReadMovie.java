import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ReadMovie {
	
	BufferedReader br=null;
	Movie[] movies=new Movie[1682];
	//List<Movie> movies=new ArrayList<Movie>();
	
	public ReadMovie(String inputFileName){
		try{
			br = new BufferedReader(new FileReader(inputFileName));
		}catch(Exception ex){
			
		}
		setMovies();
		printMovies();
		
	}	
	
	public Movie[] getMovies(){
		return movies;
	}
	
	public void setMovies(){
		
		String sCurrentLine;
		try{
			
			String name;				
			
			//int count=0;
			while ((sCurrentLine = br.readLine()) != null) {
				double []genreValues=new double[19];
				//sCurrentLine.re		
				/*count++;
				if(count>3)
					break;*/
				sCurrentLine=sCurrentLine.replace("||","|");
				//System.out.println(sCurrentLine);
				sCurrentLine=sCurrentLine.replace("|","no:separator:");				
				//System.out.println(sCurrentLine);
				String[] pp=sCurrentLine.split("no:separator:");
				//System.out.println(pp.length);
				int id=Integer.parseInt(pp[0]);
				
				if(id==267){					
					for(int i=0;i<19;i++){
						genreValues[i]=Double.parseDouble(pp[i+3]);	
					}					
					movies[id-1]=new Movie(id,"NAME",genreValues);					
				}
				else{
					name=pp[1];				
					for(int i=0;i<19;i++){
						genreValues[i]=Double.parseDouble(pp[i+4]);
						//System.out.print("\t"+Double.parseDouble(pp[i+4]));
					}
					
					//System.out.println();
					
					movies[id-1]=new Movie(id,name,genreValues);
				}
				
				
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//System.out.println("empty space");
			
	}
	
	public void printMovies(){
		/*Iterator iterator=movies.iterator();
		while(iterator.hasNext()){
			Movie mv=(Movie)iterator.next();
			System.out.print(mv.getId()+"\t"+mv.getName()+"\t");
			double[] s=mv.getGenreValues();
			for(double spsp:s){
				System.out.print(spsp+"\t");
			}
			
			System.out.println();
		}*/
		
		int count=0;
		for(Movie mv:movies){
			count++;
			System.out.print(mv.getId()+"\t"+mv.getName()+"\t");
			double[] s=mv.getGenreValues();
			for(double spsp:s){
				System.out.print(spsp+"\t");
			}
			
			System.out.println();
			if(count>1)
				break;
		}
		
	}
	
	
	public static void main(String[] gcs){
		/*Scanner scanner=new Scanner(System.in);
		System.out.println("Enter File Name For loading Genre");
		String fileName=scanner.next();*/
		new ReadMovie("E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u.item");
	}
	

}
