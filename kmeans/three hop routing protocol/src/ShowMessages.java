import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ShowMessages extends JFrame implements WindowListener{
	public ShowMessages(ArrayList<Message> messages){
		//messages=MainServer.getTo(node.getNodeNo());
		setSize(400, 400);
    	setLocation(300, 150);
    	
		JTable inboxTable=null;
		inboxTable=new JTable(new InboxTableModel(messages));
		
		        
		JScrollPane scroll=new JScrollPane(inboxTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		inboxTable.setFillsViewportHeight(true);
		//inboxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		add(scroll);	
		setVisible(true);
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
