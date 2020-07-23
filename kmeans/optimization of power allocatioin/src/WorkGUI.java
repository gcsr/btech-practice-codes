import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;


public class WorkGUI extends JFrame{
	JPanel network;
	JPanel data;
	JPanel graphs;
	
	
	public static void main(String[] gcs){
		new WorkGUI();
	}
	
	public void createUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(400, 400);
    	setResizable(false);
    	setLocation(300, 150);
    	
    	setLayout(new GridLayout(1,1));
    	JTabbedPane tabbedPane = new JTabbedPane();
    	network=new network();
    	data=new Data();
    	graphs=new Graphs();
    	tabbedPane.add(network,"Establish network");
    	tabbedPane.add(data,"data");
    	tabbedPane.add(graphs,"graphs");
    	add(tabbedPane);
    	
    	setVisible(true);
    	
	}
	public WorkGUI(){
		super("Power allocation prediction");
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try{
		    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    	}catch(Exception ex){
		    		ex.printStackTrace();
		    	}
		    	createUI();
		    	
		    }
		});
		
		

	}
	
	private class network extends JPanel{	
		JLabel nodesLabel;
		JTextField nodesField;
		JButton start;
		network(){
			setLayout(new BorderLayout());
			//setLayout(new GridLayout(1,2));
			JPanel panel=new JPanel();
			nodesLabel=new JLabel("No of Nodes");
			nodesField=new JTextField("Nodes No");
			Box box=Box.createHorizontalBox();
			box.add(nodesLabel);
			box.add(nodesField);
			start=new JButton("start");
			box.add(start);
			add(box,BorderLayout.NORTH);
			start.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					try{
						int noOfNodes=Integer.parseInt(nodesField.getText());
						MainServer.createNetwork(noOfNodes);
						
					}catch(Exception ex){
						
					}					
				}
			});
		}
	}
	
	private class Data extends JPanel{
		
		List<Message> messages=new ArrayList<Message>();
		JTable inboxTable=null;
		public Data(){			
			
			messages=MainServer.getFrom(null);
			System.out.println(messages);
			inboxTable=new JTable(new InboxTableModel(messages));
			JScrollPane scroll=new JScrollPane(inboxTable);
			inboxTable.setFillsViewportHeight(true);
			add(scroll);		
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 6*1000);
			
		}
		
		public void updateTable(){
			//System.out.println("update table");
			AbstractTableModel tableModel = (AbstractTableModel) inboxTable.getModel();
			tableModel.fireTableDataChanged();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 6*1000);
			

		}
	}
	
	private class Graphs extends JPanel{
		public Graphs(){
			Box box=Box.createHorizontalBox();
			JButton graphs=new JButton("create existing - proposed graph"); 
			box.add(graphs);
			add(box);
			MainServer server=new MainServer();
			JavaServer server2=new JavaServer();
			graphs.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					MainServer.displayDivisons();
				}
			});
		}
	}
	
}
