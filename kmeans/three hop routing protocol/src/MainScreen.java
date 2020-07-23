import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class MainScreen extends JFrame{
	JPanel network;
	JPanel data;
	JPanel graphs;
	
	
	ArrayList<GraphObject> objects=new ArrayList<GraphObject>();
	
	public static void main(String[] gcs){
		new MainScreen();
	}
	
	public void createUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(500, 500);
    	setResizable(false);
    	setLocation(300, 150);
    	setLayout(new BorderLayout());
    	JLabel background=new JLabel(new ImageIcon("E:\\old laptop\\d drive\\kmeans\\three hop routing protocol\\main background.png"));
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
    	JButton configureButton=new JButton("configure network");
    	configureButton.setBackground(Color.yellow);
    	configureButton.setBounds(50,100,200,30);
    	background.add(configureButton);
    	//background.add(tabbedPane);
    	configureButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			Server.configureNetwork();
    		}
    	});
    	
    	
    	JButton messageTransfer=new JButton("do network operations");
    	messageTransfer.setBackground(Color.yellow);
    	messageTransfer.setBounds(280,200,200,30);
    	background.add(messageTransfer);
    	
    	messageTransfer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			//Server.processTransfer();
    			String ans=JOptionPane.showInputDialog("Message Count");
    			int messageCount=0;
    			try{
    				messageCount=Integer.parseInt(ans);
    			}catch(Exception ex){
    				return;
    			}
    			
    			GraphObject object=new GraphObject();
    			clearAll();
    			long ff=System.currentTimeMillis();
    			Server.startMessagingNormal(messageCount);
    			long ss=System.currentTimeMillis();
    			object.setMessages(messageCount);
    			
    			object.setNormalTime(ss-ff);
    			
    			System.out.println(ss-ff);
    			clearAll();
    			ff=System.currentTimeMillis();
    			Server.startMessagingHybrid(messageCount);
    			ss=System.currentTimeMillis();
    			object.setBasestationTime(ss-ff);
    			System.out.println(ss-ff);
    			objects.add(object);
    			JOptionPane.showMessageDialog(null, "message transfer completed");
    		}
    	});
    	
    	JButton graphs=new JButton("graphs");
    	graphs.setBackground(Color.yellow);
    	graphs.setBounds(80,300,200,30);
    	background.add(graphs);
    	
    	
    	graphs.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			new DrawChart(objects).show();
    		}
    	});
    	
    	setVisible(true);
    
    	
	}
	
	private void clearAll(){
		Iterator<Node> itr=Server.nodesInNetwork.iterator();
		while(itr.hasNext()){
			itr.next().setMessageQueue(0);
		}
	}
	public MainScreen(){
		super("Threee hop routing protocol");
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
