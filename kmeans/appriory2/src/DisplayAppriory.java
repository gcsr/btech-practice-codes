import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class DisplayAppriory extends JFrame implements WindowListener{
	public DisplayAppriory(List<ApprioryTransaction> filtered){		
		JTable transactionsTable=new JTable(new DisplayApprioryTableModel(filtered));
		JScrollPane scroll=new JScrollPane(transactionsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transactionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		transactionsTable.getColumnModel().getColumn(0).setPreferredWidth(900);
		transactionsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		//transactionsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		transactionsTable.setFillsViewportHeight(true);
		add(scroll);		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
