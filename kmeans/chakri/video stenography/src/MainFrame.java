import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class MainFrame extends JFrame{	

	JButton chooserButton,findButton,drawImage1,drawImage2,encodeButton,decodeButton;	
	JTextField fileField,frameNoField;
	JPasswordField passwordField;
	PlayerScreen middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;
	boolean playing=false;
	
	//SteganographyMain steno;
	public static void main(String[] args) {
		
		new MainFrame();		

	}
	
	public MainFrame(){
		super("Video Steganography");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,500);		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		chooserButton=new JButton("Choose File");		
		findButton=new JButton("play");
		
		//numberField=new JTextField("",5);
		fileField=new JTextField("D:/ca",30);
		frameNoField=new JTextField("",4);
		topPanel.add(fileField);
		topPanel.add(chooserButton);
		
		passwordField=new JPasswordField("passeord");
		topPanel.add(passwordField);
		//topPanel.add(numberField); 
		middlePanel=new PlayerScreen(fileField.getText());
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	    bottomPanel=new JPanel();
	    
	    
	    
	    
	    fileChooser=new JFileChooser();	  		
	  		chooserButton.addActionListener(new ActionListener(){
	  			public void actionPerformed(ActionEvent event){
	  				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  				int result=fileChooser.showOpenDialog(MainFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath().replace('\\','/'));
	  				middlePanel.update(fileField.getText());
	  				  							
	  				
	  			}
	  		});
	  		
	  		
	  		
	  		findButton.addActionListener(new ActionListener(){
	  			public void actionPerformed(ActionEvent event){
	  					playing=!playing;
	  					if(playing)
	  						findButton.setText("pause");
	  					else
	  						findButton.setText("play");
	  					
	  					middlePanel.setPlay(playing);
	  					//findButton.
	  			}
	  		});
	  		
	  encodeButton=new JButton("encode");
	  	encodeButton.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent ev){
	  			String frameNoString=frameNoField.getText();
	  			int frameNo=0;
	  			try{
	  				frameNo=Integer.parseInt(frameNoString);
	  			}catch(Exception e){
	  				JOptionPane.showMessageDialog(MainFrame.this,"enter valid frame no");
	  				return;
	  			}
	  			middlePanel.encode(frameNo,passwordField.getText());
	  		}
	  	});
		decodeButton=new JButton("decode");
		decodeButton.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent ev){
	  			String frameNoString=frameNoField.getText();
	  			int frameNo=0;
	  			try{
	  				frameNo=Integer.parseInt(frameNoString);
	  			}catch(Exception e){
	  				JOptionPane.showMessageDialog(MainFrame.this,"enter valid frame no");
	  				return;
	  			}
	  			String password=middlePanel.decode(frameNo);
	  			JOptionPane.showMessageDialog(MainFrame.this,"password is "+password);
	  		}
	  	});
		topPanel.add(findButton);
		topPanel.add(frameNoField);
		topPanel.add(encodeButton);
		
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
	
		bottomPanel.add(decodeButton);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		setVisible(true);
		//pack();
		
	
	}
	


}