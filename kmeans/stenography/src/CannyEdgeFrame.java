import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CannyEdgeFrame extends JFrame
{

	JButton chooserButton,findButton,drawImage1,drawImage2;	
	JTextField fileField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;
	CannyEdgeDetector detector;
	//SteganographyMain steno;
	
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
		chooserButton=new JButton(" choose file");		
		findButton=new JButton(" find Edges");
		//numberField=new JTextField("",5);
		fileField=new JTextField("",30);		
		topPanel.add(fileField);
		topPanel.add(chooserButton);		
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
	  				//steno=new SteganographyMain(fileField.getText(),"pppp");
	  				
	  				try{
	  					
	  					BufferedImage img = null;
	  					try
	  					{
	  					    img = ImageIO.read(new File(fileField.getText()));
	  					}
	  					catch (IOException e) 
	  					{
	  					}
	  					
	  					detector = new CannyEdgeDetector();
	  					//adjust its parameters as desired 
	  					detector.setLowThreshold(0.5f); 
	  					detector.setHighThreshold(1f); 
	  					//apply it to an image 
	  					detector.setSourceImage(img); 
	  					detector.process(); 
	  					BufferedImage edges = detector.getEdgesImage();
	  					middlePanel.updateImage(edges);
	  					
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
  				try
  				{
  					//BufferedImage img=steno.getEdgesImage();
  					//middlePanel.updateImage(img);
  				}
  				catch(Exception ex)
  				{
  					fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
  				}
  				
  				
  			}
  		});
	  	
	  	drawImage2.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event)
  			{
  				try
  				{
  					//BufferedImage img=steno.getEmbeddedImage();
  					//middlePanel.updateImage(img);
  				}
  				catch(Exception ex)
  				{
  					ex.printStackTrace();
  					fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
  				}
  				
  				
  			}
  		});
	  	
	  	
  		
	  		
		topPanel.add(findButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		bottomPanel.add(drawImage1);
		bottomPanel.add(drawImage2);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
	
	}
	
	

}
