import java.util.List;

import javax.swing.AbstractListModel;


public class MovieListModel  extends AbstractListModel{

	Movie[] movies;
	
	public MovieListModel(Movie[] movies){
		this.movies=movies;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return movies.length;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return movies[index].getId()+"-"+movies[index].getName();
	}

}
