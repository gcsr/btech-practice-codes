import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
		super("ananymous client");
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
		ArrayList<Message> messages=new ArrayList<Message>();
		JTable inboxTable=null;
		public Inbox(){
			setLayout(new BorderLayout());
			messages=MainServer.getTo(node.getNodeNo());
			inboxTable=new JTable(new InboxTableModel(messages));
			
			        
			JScrollPane scroll=new JScrollPane(inboxTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
			inboxTable.setFillsViewportHeight(true);
			//inboxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			if(node.getNodeNo()==MainServer.MAINSERVERNODE){
				JButton button=new JButton("process messages");
				add(button,BorderLayout.NORTH);
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						//printMessages(MainServer.getTo(node.getNodeNo()));
						//System.out.println("this is it");
						ArrayList<Message> ffff=MainServer.processMessages(MainServer.getTo(node.getNodeNo()));
						new ShowDecryptedMessages(ffff);
					}
				});
			}
			add(scroll,BorderLayout.CENTER);	
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 5*1000);
		}
		
		public void updateTable(){
			//System.out.println("in update table");
			
			if(node.getNodeNo()==MainServer.MAINSERVERNODE){
				//printMessages(MainServer.getTo(node.getNodeNo()));
				List<Message> ffff=MainServer.getTo(node.getNodeNo());//processMessages(MainServer.getTo(node.getNodeNo()));
				while(messages.size()!=0){
					messages.remove(0);
				}
				messages.addAll(ffff);
			}else{
				ArrayList<Message> ffff=MainServer.getTo(node.getNodeNo());
				while(messages.size()!=0){
					messages.remove(0);
				}
				messages.addAll(ffff);
			}
			
			
			AbstractTableModel tableModel = (AbstractTableModel) inboxTable.getModel();
			tableModel.fireTableDataChanged();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 5*1000);
			

		}
		
	
	}
	
	class Compose extends JPanel{
		JLabel toLabel;
		JList toList;
		JTextArea textMessage;
		JButton sendButton;
		JButton browse;
		public Compose(){
			setLayout(new BorderLayout());
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(1,3));
			
			toLabel=new JLabel("No of Nodes");
			
			String[] app=MainServer.getTolist(node.getNodeNo());
			//System.out.println(app.length);
			//System.out.println(app[0]);
			browse=new JButton("browse file");
			toList=new JList(app);
			toList.setVisibleRowCount(2);
			toList.setSelectedIndex(0);
			
			//Box box=Box.createHorizontalBox();
			panel.add(toLabel);
			//box.createHorizontalStrut(10);
			JScrollPane scrollPane=new JScrollPane(toList);
			
			panel.add(scrollPane);
			
			//add(panel,BorderLayout.NORTH);
			textMessage=new JTextArea("",8,20);
			add(textMessage,BorderLayout.CENTER);
			
			sendButton=new JButton("send");
			add(sendButton,BorderLayout.SOUTH);
			add(browse,BorderLayout.NORTH);
			
			sendButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					//String selectedNode=(String)toList.getSelectedValue();
					//System.out.println("selected node"+selectedNode+"jjjjjjjj");
					Message message=new Message();
					message.setFrom(node.getNodeNo());
					//message.setTo(Integer.parseInt(selectedNode));
					message.setMessage(textMessage.getText());
					message.setType("chat");
					message.setFileName("");
					message.setUniqueId(MainServer.uniqueId);
					MainServer.uniqueId++;
					
					String hash=MainServer.getHash(""+message.getUniqueId(), node.getHash().getBytes());
					
					Message[] ppp=MainServer.messageToChunks(message,hash);
					String sss=getPaths(ppp);
					
					for(int i=0;i<ppp.length;i++){
						MainServer.addMessage(ppp[i]);
					}	
					
					try{
						JOptionPane.showMessageDialog(NetworkNode.this, sss);
					}catch(Exception ex){
						
					}
					
				}
			});
			
			
			browse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JFileChooser fileChooser=new JFileChooser();
			  		
			  		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			  		int result=fileChooser.showOpenDialog(null);
			  		if(result==JFileChooser.CANCEL_OPTION)
			  					return;
			  		String path=(fileChooser.getSelectedFile().getAbsolutePath());
			  		
			  		Path pathObject = Paths.get(path);
			  		byte[] data =null;
			  		try{
			  			
			  			data=Files.readAllBytes(pathObject);
			  			
			  		}catch(Exception ex){
			  			
			  		}
			  		
			  		int dialogButton = JOptionPane.YES_NO_OPTION;
			  		int dialogResult = JOptionPane.showConfirmDialog(NetworkNode.this, "Please confirm your action of sending", "Confimation", dialogButton);
			  		if(dialogResult == 0) {
			  			Message message=new Message();
						message.setFrom(node.getNodeNo());
						//message.setTo(Integer.parseInt(selectedNode));
						message.setMessage(new String(data));						
						message.setType(path.substring(path.lastIndexOf("."))+1);
						
						message.setFileName(fileChooser.getSelectedFile().getName());
						message.setUniqueId(MainServer.uniqueId);
						MainServer.uniqueId++;
						message.setData(data);
						
						String hash=MainServer.getHash(""+message.getUniqueId(), node.getHash().getBytes());
						
						//MainServer.processFile(message.getFileName(), message.getType(), message.getMessage());
						
						Message[] ppp=MainServer.messageToChunksBytes(message,hash);
						String sss=getPaths(ppp);
						
						for(int i=0;i<ppp.length;i++){
							MainServer.addMessage(ppp[i]);
						}	
						
						try{
							JOptionPane.showMessageDialog(NetworkNode.this, sss);
						}catch(Exception ex){
							
						}
					
			  		} else {
			  		  System.out.println("No Option");
			  		} 
				}
			});
			
			
		}
	}
	
	private String getPaths(Message[] msgs){
		String result="";
		for(Message msg:msgs){
			int[] route=msg.getRoute();
			result+=node.getNodeNo()+" ";
			for(int i:route)
				result+=i+" ";
			result+="\n";
		}
		
		return result;
	}
	
	class Sent extends JPanel{
		ArrayList<Message> messages=new ArrayList<Message>();
		JTable inboxTable=null;
		public Sent(){
			setLayout(new BorderLayout());
			messages=MainServer.getFrom(node.getNodeNo());
			inboxTable=new JTable(new InboxTableModel(messages));
			
			        
			JScrollPane scroll=new JScrollPane(inboxTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
			inboxTable.setFillsViewportHeight(true);
			//inboxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			add(scroll,BorderLayout.CENTER);	
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 5*1000);
		}
		
		public void updateTable(){
			//System.out.println("in update table");
			
			if(node.getNodeNo()==MainServer.MAINSERVERNODE){
				//printMessages(MainServer.getTo(node.getNodeNo()));
				List<Message> ffff=MainServer.getFrom(node.getNodeNo());//processMessages(MainServer.getTo(node.getNodeNo()));
				while(messages.size()!=0){
					messages.remove(0);
				}
				messages.addAll(ffff);
			}else{
				ArrayList<Message> ffff=MainServer.getFrom(node.getNodeNo());
				while(messages.size()!=0){
					messages.remove(0);
				}
				messages.addAll(ffff);
			}
			
			
			AbstractTableModel tableModel = (AbstractTableModel) inboxTable.getModel();
			tableModel.fireTableDataChanged();
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
				    // Your database code here
					  updateTable();
				  }
				}, 5*1000);
			

		}
		
	
	}
	
	

	
	
}
