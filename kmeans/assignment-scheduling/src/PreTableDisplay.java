import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PreTableDisplay extends JFrame{
	public PreTableDisplay(List<Job> jobs, String heading){	
		super(heading);
		JTable transactionsTable=new JTable(new PreTableDisplayModel(jobs));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);		
	}
}
