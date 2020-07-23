
public class MovieRating {
	private int movieId;
	private double[] genre;
	private int rating;
	
	public MovieRating(){
		
	}
	
	public MovieRating(int movieId, double[] genre, int rating){
		this.movieId=movieId;
		this.genre=genre;
		this.rating=rating;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public double[] getGenre() {
		return genre;
	}
	public void setGenre(double[] genre) {
		this.genre = genre;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	public String toString(){
		String str=""+getMovieId();
		str+="\tGenre";
		for(double x:genre){
			str+="\t"+x;
		}
		
		str+="\tRating";
		str+="\t"+getRating();
		return str;
	}
	
}
