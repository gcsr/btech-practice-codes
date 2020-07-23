
import java.util.ArrayList;


public interface BoardInterfaceImpl {
	
	public Pieces getPieceData();
	public boolean getGameProgress();

	public ArrayList<int[]> getAllMoves();
	
	public int RowNumber();
	public int ColumnNumber();

}
