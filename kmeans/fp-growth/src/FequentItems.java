import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FequentItems extends JFrame{
	public FequentItems(List<Graph> subGraphs,String[] columnNames,String heading){
		super(heading);
		JTable transactionsTable=new JTable(new FrquentItemsModel(subGraphs,columnNames));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);	
	}
}
