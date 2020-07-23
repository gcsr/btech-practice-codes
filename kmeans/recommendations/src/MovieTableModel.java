import java.util.List;
import javax.swing.table.AbstractTableModel;


public class MovieTableModel extends AbstractTableModel{	

    private Movie[] movies;
    private String[] columnNames = {"Movie Name", "Movie Id","Genre"};

    public MovieTableModel(Movie[] movies) {
        this.movies=movies;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return movies.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            return movies[rowIndex].getName();
        }
        else if(columnIndex==1){
        	return movies[rowIndex].getId();
        }
        
        else{
        	double[] d= movies[rowIndex].getGenreValues();
        	String s="";
        	for(double dd:d){
        		s+=(int)dd;
        	}
        	
        	return s;
        }
    }


}

