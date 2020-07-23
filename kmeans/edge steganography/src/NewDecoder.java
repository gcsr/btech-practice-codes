import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewDecoder extends JFrame{

	JButton chooserButton,findButton,drawImage2;	
	JTextField fileField,catField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel;
	CannyEdgeDetector detector;
	String catPassword="";
	SteganographyMain steno;
	String result="";	
	int rotations;
	public NewDecoder(SteganographyMain steno,int rotations){
		
		super("canny edge frame");
		setSize(1200,700);		
		setLayout(new BorderLayout());
		this.rotations=rotations;
		this.steno=steno;
		
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		
		chooserButton=new JButton("choose file");
		topPanel.add(chooserButton);
		fileField=new JTextField("",30);
		topPanel.add(fileField);
		
		middlePanel=new DrawImage();
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	    
	    fileChooser=new JFileChooser();
	  		
	  		chooserButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(NewDecoder.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
	  				  							
	  				
	  			}
	  		}); 		
	  		
	  		
	  		
	  	
	  	
	  	drawImage2=new JButton("embedded data");
	  	
	  	drawImage2.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent event){
  				execute();
  			}
  		});
	  	
	  	
	  	
  		
	  		
		//topPanel.add(findButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		
		
		topPanel.add(drawImage2);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		
		//pack();
		setVisible(true);
	
	}
	
	private void reverseCatMap(){
		try{			
			int i=0;
			int length=result.length();
			
			while(i<rotations){
				result=result.charAt(length-1)+result.substring(0,length-1);
				i++;
			}
			JOptionPane.showConfirmDialog(NewDecoder.this,"password is "+result);
			
		}catch(Exception ex){
			JOptionPane.showConfirmDialog(NewDecoder.this,"enter valid rotaion number");
		}
	}
	
	public void execute(){
		
		try{  					
				
				String ss=steno.decoded(fileField.getText());	  					
				result=PassToBinary.reverse(ss);
				JOptionPane.showConfirmDialog(NewDecoder.this,"password is "+result);
				reverseCatMap();
				//steno.printing();
			}
			catch(Exception ex){
				ex.printStackTrace();  					
			}
			
			
			
		
	}
		
	

}
