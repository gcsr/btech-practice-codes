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

public class CannyEdgeFrame extends JFrame
{

	JButton chooserButton,findButton,drawImage1,drawImage2,catButton,decodedButton;	
	JTextField fileField,catField,passwordField,decodedField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;
	CannyEdgeDetector detector;
	String catPassword="";
	SteganographyMain steno;
	String result="";
	
	boolean foundCatPassword=false;
	int rotations;
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new CannyEdgeFrame();
		

	}
	
	public CannyEdgeFrame()
	{
		super("canny edge frame");
		setSize(1200,500);		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		
		passwordField=new JTextField("enter password");
		
		chooserButton=new JButton("choose file");		
		findButton=new JButton("embed data");
		//numberField=new JTextField("",5);
		fileField=new JTextField("",30);
		
		catField=new JTextField("",3);
		catButton=new JButton("Rotate");
		decodedField=new JTextField("url",30);
		decodedButton=new JButton("select for decoding");
		
		catButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				if(!foundCatPassword){
					String password=passwordField.getText();
					catPassword=password;
					
					try{
						rotations=Integer.parseInt(catField.getText());
						int i=0;
						while(i<rotations){
							catPassword=catPassword.substring(1)+catPassword.charAt(0);
							i++;
						}
						JOptionPane.showConfirmDialog(CannyEdgeFrame.this,"rotated password is "+catPassword);
						
					}catch(Exception ex){
						JOptionPane.showConfirmDialog(CannyEdgeFrame.this,"enter valid rotaion number");
					}
					foundCatPassword=true;
				}
			}
		});
		
		topPanel.add(passwordField);
		topPanel.add(fileField);
		
		topPanel.add(chooserButton);
		topPanel.add(catField);
		topPanel.add(catButton);
		//topPanel.add(numberField); 
		middlePanel=new DrawImage();
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	    bottomPanel=new JPanel();
	    fileChooser=new JFileChooser();
	  		
	  		chooserButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(CannyEdgeFrame.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
	  				  							
	  				
	  			}
	  		});
	  		
	  		
	  		
	  		findButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				
	  				
	  				try{
	  					
	  					BufferedImage img = null;
	  					try
	  					{
	  					    img = ImageIO.read(new File(fileField.getText()));
	  					}
	  					catch (IOException e) 
	  					{
	  					}
	  					
	  					/*detector = new CannyEdgeDetector();
	  					//adjust its parameters as desired 
	  					detector.setLowThreshold(0.5f); 
	  					detector.setHighThreshold(1f); 
	  					//apply it to an image 
	  					detector.setSourceImage(img); 
	  					detector.process(); 
	  					BufferedImage edges = detector.getEdgesImage();
	  					System.out.println("edge type is "+edges.getType());*/
	  					steno=new SteganographyMain(fileField.getText(),catPassword);	  					
	  					
	  					
	  					
	  				}catch(Exception ex)
	  				{
	  					ex.printStackTrace();
	  					System.exit(0);
	  				}
	  				
	  				
	  			}
	  		});
	  		
	  	//clusterTotalField=new JTextField("",5);
	  	//clusterNumberField=new JTextField("",5);
	  	
	  	drawImage1=new JButton("edge data");
	  	drawImage2=new JButton("embedded data");
	  	drawImage1.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event)
  			{
  				try{
  					middlePanel.updateImage(steno.getEmbeddedImage());  					
  				}
  				catch(Exception ex){
  					
  				}
  				
  				
  			}
  		});
	  	
	  	drawImage2.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event)
  			{
  				
  				/*try{
  					String ss=steno.decoded(decodedField.getText());	  					
  					result=PassToBinary.reverse(ss);
  					JOptionPane.showConfirmDialog(CannyEdgeFrame.this,"password is "+result);
  					reverseCatMap();
  					//steno.printing();
  				}
  				catch(Exception ex){
  					ex.printStackTrace();  					
  				}*/
  				
  				new NewDecoder(steno,rotations);
  				
  				
  				
  			}
  		});
	  	
	  	
	  	/*decodedButton.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event)
  			{
  				
  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  				int result=fileChooser.showOpenDialog(CannyEdgeFrame.this);
  				
  				if(result==JFileChooser.CANCEL_OPTION)
  					return;
  				
  				
  				decodedField.setText(fileChooser.getSelectedFile().getAbsolutePath());
  				middlePanel.updateImage(decodedField.getText());
  				
  			}
  		});	
  		*/
  		
	  		
		topPanel.add(findButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		
		
		bottomPanel.add(findButton);
		bottomPanel.add(drawImage1);		
		
		bottomPanel.add(drawImage2);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
	
	}
	
	private void reverseCatMap(){
		try{
			int rotations=Integer.parseInt(catField.getText());
			int i=0;
			int length=result.length();
			
			while(i<rotations){
				result=result.charAt(length-1)+result.substring(0,length-1);
				i++;
			}
			JOptionPane.showConfirmDialog(CannyEdgeFrame.this,"password is "+result);
			
		}catch(Exception ex){
			JOptionPane.showConfirmDialog(CannyEdgeFrame.this,"enter valid rotaion number");
		}
	}
	
	

}