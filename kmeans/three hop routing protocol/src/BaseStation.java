import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;


public class BaseStation extends JFrame{
	JPanel compose;
	JPanel inbox;
	JPanel sent;	
	Node node;

	public BaseStation(Node node){
		super(node.getName());
		this.node=node;
		createUI();
	}
	
	public static void main(String[] gcs){
		new BaseStation();
	}
	
	public void startNode(){
		
	}
	
	public void createUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(400, 400);
    	setLocation(300, 150);
    	setLayout(new BorderLayout());
    	JLabel background=new JLabel(new ImageIcon("E:\\old laptop\\d drive\\kmeans\\three hop routing protocol\\background.jpg"));
    	add(background);
    	//background.setLayout(new FlowLayout());
    	
    	//background.setLayout(new GridLayout(1,1));
    	//JTabbedPane tabbedPane = new JTabbedPane();
    	//inbox=new Inbox();
    	//sent=new Sent();
    	//compose=new Compose();
    	//tabbedPane.add(inbox,"inbox");
    	//tabbedPane.add(sent,"sent");
    	//tabbedPane.add(compose,"Compose");
    	JButton inbox=new JButton("inbox");
    	inbox.setBackground(Color.yellow);
    	inbox.setBounds(30,200,70,30);
    	background.add(inbox);
    	//background.add(tabbedPane);
    	inbox.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			ArrayList<Message> ffff=Server.getTo(node.getNodeNo());
    			System.out.println(node.getNodeNo());
    			new ShowMessages(ffff);
    		}
    	});
    	
    	JButton sent=new JButton("sent");
    	sent.setBackground(Color.yellow);
    	sent.setBounds(280,200,70,30);
    	background.add(sent);
    	
    	sent.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			ArrayList<Message> ffff=Server.getFrom(node.getNodeNo());
    			new ShowMessages(ffff);
    		}
    	});
    	
    	setVisible(true);
    	
	}
	public BaseStation(){
		super("Base Station");
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
	
	
}
