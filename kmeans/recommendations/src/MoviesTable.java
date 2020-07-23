import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MoviesTable extends JFrame{
	public MoviesTable(Movie[] movies){
		super("movies");
		JTable moviesTable=new JTable(new MovieTableModel(movies));
		JScrollPane scroll=new JScrollPane(moviesTable);
		moviesTable.setFillsViewportHeight(true);
		add(scroll);
		
	}

}
