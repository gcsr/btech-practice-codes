

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

	final private String defaultImage="E:\\androidtemplates\\icons\\material-design-icons-master\\material-design-icons-master\\editor\\drawable-mdpi\\ic_attach_money_white_36dp.PNG";;
	JButton chooserButton,findButton;
	JTextField fileField,catField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;	
	String catPassword="";
	SteganographyMain steno;
	
	
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
		
		chooserButton=new JButton("choose file");		
		findButton=new JButton("Apply Color");
		//numberField=new JTextField("",5);
		fileField=new JTextField("",30);
		
		catField=new JTextField(defaultImage);
		topPanel.add(fileField);
		topPanel.add(catField);
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
	  				
	  				
	  				try{
	  					
	  					BufferedImage img = null;
	  					try
	  					{
	  					    img = ImageIO.read(new File(fileField.getText()));
	  					}
	  					catch (IOException e) 
	  					{
	  					}
	  					
	  					
	  					
	  					
	  				}catch(Exception ex)
	  				{
	  					ex.printStackTrace();
	  					System.exit(0);
	  				}
	  				
	  				
	  			}
	  		});
	  		
	  	//clusterTotalField=new JTextField("",5);
	  	//clusterNumberField=new JTextField("",5);
	  	
	  
	  	middlePanel.updateImage(fileField.getText());	
		topPanel.add(findButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		
		
		bottomPanel.add(findButton);
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
	
	}
	
	
	

}
