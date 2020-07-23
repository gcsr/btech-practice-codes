import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ResultsTable extends JFrame{
	public ResultsTable(double[] lifts,double[] MDCGS,String heading,String[] columnaNames){
		super(heading);
		JTable transactionsTable=new JTable(new ResultsTableModel(lifts,MDCGS,columnaNames));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);	
	}
}
