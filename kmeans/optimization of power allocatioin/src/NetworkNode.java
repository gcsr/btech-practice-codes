import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;


public class NetworkNode extends JFrame{
	JPanel compose;
	JPanel inbox;
	JPanel sent;	
	Node node;
	
	
	public NetworkNode(Node node){
		super(node.getName());
		this.node=node;
		createUI();
	}
	
	public static void main(String[] gcs){
		new NetworkNode();
	}
	
	public void startNode(){
		
	}
	
	public void createUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(400, 400);
    	setLocation(300, 150);
    	
    	setLayout(new GridLayout(1,1));
    	JTabbedPane tabbedPane = new JTabbedPane();
    	inbox=new Inbox();
    	sent=new Sent();
    	compose=new Compose();
    	tabbedPane.add(inbox,"inbox");
    	tabbedPane.add(sent,"sent");
    	tabbedPane.add(compose,"Compose");
    	add(tabbedPane);
    	
    	setVisible(true);
    	
	}
	public NetworkNode(){
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
	
	class Inbox extends JPanel{
		List<Message> messages=new ArrayList<Message>();
		JTable inboxTable=null;
		public Inbox(){
			messages=MainServer.getTo(node.getName());
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
				}, 2*1000);
		}
		
		public void updateTable(){
			//System.out.println("in update table");
			List<Message> ffff=MainServer.getTo(node.getName());
			while(messages.size()!=0){
				messages.remove(0);
			}
			
			messages.addAll(ffff);
			AbstractTableModel tableModel = (AbstractTableModel) inboxTable.getModel();
			tableModel.fireTableDataChanged();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 2*1000);
			

		}
		
	
	}
	
	class Compose extends JPanel{
		JLabel toLabel;
		JList toList;
		JTextArea textMessage;
		JButton sendButton;
		public Compose(){
			setLayout(new BorderLayout());
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(1,2));
			toLabel=new JLabel("No of Nodes");
			
			String[] app=MainServer.getTolist(node.getName());
			//System.out.println(app.length);
			//System.out.println(app[0]);
			toList=new JList(app);
			toList.setVisibleRowCount(2);
			toList.setSelectedIndex(0);
			
			//Box box=Box.createHorizontalBox();
			panel.add(toLabel);
			//box.createHorizontalStrut(10);
			JScrollPane scrollPane=new JScrollPane(toList);
			
			panel.add(scrollPane);
			
			add(panel,BorderLayout.NORTH);
			textMessage=new JTextArea("",8,20);
			add(textMessage,BorderLayout.CENTER);
			
			sendButton=new JButton("send");
			add(sendButton,BorderLayout.SOUTH);
			
			sendButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String selectedNode=(String)toList.getSelectedValue();
					//System.out.println("selected node"+selectedNode+"jjjjjjjj");
					Message message=new Message();
					message.setFrom(node.getName());
					message.setTo(selectedNode);
					message.setMessage(textMessage.getText());
					message.setType("chat");
					
					MainServer.addMessage(message);
					
				}
			});
		}
	}
	
	class Sent extends JPanel{
		List<Message> messages=new ArrayList<Message>();
		JTable inboxTable=null;
		public Sent(){			
			
			messages=MainServer.getFrom(node.getName());
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
				}, 2*1000);
			
		}
		
		public void updateTable(){
			List<Message> ffff=MainServer.getFrom(node.getName());
			while(messages.size()!=0){
				messages.remove(0);
			}
			
			messages.addAll(ffff);
			AbstractTableModel tableModel = (AbstractTableModel) inboxTable.getModel();
			tableModel.fireTableDataChanged();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
					  if(node.getName().equals("Fusion Node")){
						  
					  }else if(node.getName().equals("Sensor Node")){
						  
					  }else{
						  Message message=new Message();
						  message.setFrom(node.getName());
						  message.setTo("Sensor Node");
						  Random rd=new Random();
						  message.setMessage("sensor loss"+" "+node.getSensingLoss()+rd.nextDouble()+" fusion loss"+" "+node.getFusionLoss()+rd.nextDouble());
						  MainServer.addMessage(message);
					  }
				  }
				}, 2*1000);
			

		}
	}


}
