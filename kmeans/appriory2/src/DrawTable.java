import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class DrawTable extends JFrame{
	public DrawTable(Results rs1,Results rs2){		
		JTable transactionsTable=new JTable(new DrawTableModel(rs1,rs2));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);		
	}

	
}
