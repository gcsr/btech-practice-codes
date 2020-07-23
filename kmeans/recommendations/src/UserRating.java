import java.util.ArrayList;
import java.util.List;


public class UserRating {
	private int userId;
	private List<MovieRating> movieRatings=new ArrayList<MovieRating>();
	int[] movieIds;
	
	public UserRating(){
		
	}
	
	public UserRating(int userId, List<MovieRating> movieRatings){
		this.userId=userId;
		this.movieRatings=movieRatings;
	}
	
	public void filter(int[] movieIds){
		int size=movieRatings.size();
		this.movieIds=movieIds;
		
		for(int i=0;i<size;i++){
			MovieRating s=(MovieRating)movieRatings.get(i);
			
			if(!ifExists(s.getMovieId())){
				movieRatings.remove(i);
				size-=1;
				i-=1;
			}
		}
	}
	
	private boolean ifExists(int s){
		for(int x:movieIds){
			if(x==s)
				return true;
		}
		return false;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<MovieRating> getMovieRatings() {
		return movieRatings;
	}
	public void setMovieRatings(List<MovieRating> movieRatings) {
		this.movieRatings = movieRatings;
	}
	
	
	public void addRating(MovieRating movieRating){
		this.movieRatings.add(movieRating);
	}	
	
	public int getRating(int movieId){
		int size=movieRatings.size();
				
		for(int i=0;i<size;i++){
			MovieRating s=(MovieRating)movieRatings.get(i);
			
			if(s.getMovieId()==movieId){
				return s.getRating();
			}
		}
		
		return 0;
	}
}
