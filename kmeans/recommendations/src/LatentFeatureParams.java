import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LatentFeatureParams {
	private static final String fileName="E:/old laptop/d drive/papers/harish/personalized/latent/latent";
	
	BufferedReader br=null;
	ReadMovie moviesObject=null;
	Movie[] movies=new Movie[1682];
	String inputFileName="E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u1.BASE";
	private UserRating[] userRatings;	
	double[][] latentVectors;
	
	Movie[] filteredMovies;
	UserRating[] filteredUserRatings;
	
	public static void main(String[] gcs){
		
		new LatentFeatureParams();
	}
	
	public LatentFeatureParams(){
		moviesObject=new ReadMovie("E:/old laptop/d drive/papers/harish/personalized/ml-100k/work/u.item");		
		movies=moviesObject.getMovies();
		userRatings=new UserRating[943];
		for(int i=0;i<943;i++){
			userRatings[i]=new UserRating();
			userRatings[i].setUserId(i+1);
		}		
		
		latentVectors=new double[userRatings.length][19];
		getRatings();
		//findFactors();
		//printUserRatings(userRatings);
	}
	
	public void getRatings(){
		try{
			br = new BufferedReader(new FileReader(inputFileName));
		}catch(Exception ex){
			
		}
		setMovieRatings();
	
	}
	
	private void setMovieRatings(){
		
		String sCurrentLine;
		try{
			
			String name;				
			double []genreValues=new double[19];
			
			while ((sCurrentLine = br.readLine()) != null) {								
				String[] pp=sCurrentLine.split("\\s+");
				//System.out.println(pp.length);
				int id=Integer.parseInt(pp[0]);
				int movieId=Integer.parseInt(pp[1]);
				movies[movieId-1].addUser(id);
				userRatings[id-1].addRating(new MovieRating(movieId,movies[movieId-1].getGenreValues(),Integer.parseInt(pp[2])));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void printMovies(Movie[] movies){
		int count=0;
		for(Movie mv:movies){
			//count++;
			System.out.print(mv.getId()+"\t"+mv.getName()+"\t");
			double[] s=mv.getGenreValues();
			for(double spsp:s){
				System.out.print(spsp+"\t");
			}
			
			System.out.println();
			//if(count>1)
				//break;
		}
		
	}
	
	
	public void printUserRatings(UserRating[] userRatings){
		int count=0;
		MovieRating mrg;
		List<MovieRating> mr;
		Iterator iterator=null;
		
		for(UserRating ur:userRatings){
			System.out.println("user "+ur.getUserId());
			mr=ur.getMovieRatings();			
			iterator=mr.iterator();
			
			while(iterator.hasNext()){
				mrg=(MovieRating)iterator.next();
				System.out.println(mrg);
			}
			//count++;
			//if(count>1)
				//break;
		}
	}
	
	public double[][] findAndGetFactors(UserRating[] userRatings){
		
		int count=0;
		ExecutorService pool=Executors.newFixedThreadPool(20);	
		MatrixFactorization mf;		
		double[] thetaValues;
		latentVectors=new double[userRatings.length][19];
		try{			
			for(UserRating ur:userRatings){	
				synchronized(this){
					mf=new MatrixFactorization(ur);
					Future<double[]> result = pool.submit(mf);
					latentVectors[count]=result.get();
					String ff= fileName+count+".dat";
					WriteToFile.write(latentVectors[count],ff);
					//	DataReadng.readData(ff);
					count++;
					//	System.out.println("no");
				}
			}			
			pool.shutdown();
			
			
			//System.out.println("System is existing");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return latentVectors;
	}
	
	private void checkFactors(double[] thetaValues){
		System.out.println();
		for(double x:thetaValues){
			System.out.print("\t"+x);
		}
	}
	
	public double[][] gtFactors(){
		return latentVectors;
	}
	
	public UserRating[] getUserRatings(){
		return userRatings;
	}
	
	public Movie[] getMovies(){
		return moviesObject.getMovies();
	}
	
	public Movie[] getFilteredMovies(int[] userIds,int movieCount){
		filteredMovies=movies;
		filteredUserRatings=userRatings;
		for(Movie m:filteredMovies){
			m.filter(userIds);
		}
		
		Arrays.sort(filteredMovies);
		Movie[] newMovies=new Movie[movieCount];
		for(int i=0;i<movieCount;i++){
			newMovies[i]=filteredMovies[i];
		}
		
		filteredMovies=newMovies;
		//printMovies(filteredMovies);
		return newMovies;
	}
	
	public UserRating[] getFilteredUsers(int[] movieIds,int[] userIds){
		filteredUserRatings=new UserRating[userIds.length];
		
		int i=0;
		for(UserRating ur:userRatings){
			if(ifExists(ur.getUserId(),userIds)){
				filteredUserRatings[i]=ur;
				i++;
			}
		}
		
		
		for(UserRating ur:filteredUserRatings){
			ur.filter(movieIds);
		}
		//printUserRatings(filteredUserRatings);
		return filteredUserRatings;		
		
	}
	
	private boolean ifExists(int s,int[] users){
		for(int x:users){
			if(x==s)
				return true;
		}
		return false;
	}	
	
}


/*

query="insert into \"Movie_Genre\" values("+id+","
+ ""+genreValues[0]+","+genreValues[1]+","+genreValues[2]+","
	+genreValues[3]+","+genreValues[4]+","+genreValues[5]+","
	+genreValues[6]+","+genreValues[7]+","+genreValues[8]+","
	+genreValues[9]+","+genreValues[10]+","+genreValues[11]+","
	+genreValues[12]+","+genreValues[13]+","+genreValues[14]+","
	+genreValues[15]+","+genreValues[16]+","+genreValues[16]+","
	+genreValues[17]+","+genreValues[18]+")";		*/