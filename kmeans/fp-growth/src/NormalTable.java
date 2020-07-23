import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class NormalTable extends JFrame{
	public NormalTable(List<Normal> normals,String[] columnNames,String heading){
		super(heading);
		JTable transactionsTable=new JTable(new NormalTableModel(normals,columnNames));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);	
	}
}
