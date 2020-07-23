import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class SubItems extends JFrame{
	public SubItems(Graph[][] subSubGraphs,String[] columnNames,String heading){
		super(heading);
		JTable transactionsTable=new JTable(new SubItemsModel(subSubGraphs,columnNames));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);	
	}
}
