import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Kmeans extends JFrame
{

	JButton chooserButton,findButton,drawImage;	
	JTextField fileField,numberField,clusterTotalField,clusterNumberField;
	DrawImage middlePanel;
	JFileChooser fileChooser;
	JPanel topPanel;
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method 	stub
		new Kmeans();
		

	}
	
	public Kmeans()
	{
		super("Handwritten Recognition GUI");
		setSize(1200,800);		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		chooserButton=new JButton(" choose file");		
		findButton=new JButton(" check for matches");
		numberField=new JTextField("",5);
		fileField=new JTextField("",30);		
		topPanel.add(fileField);
		topPanel.add(chooserButton);		
		//topPanel.add(numberField); 
		middlePanel=new DrawImage();
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	    
	    
	    fileChooser=new JFileChooser();
	    
	    middlePanel.updateImage("images/sig1.JPEG");
	    
	  		
	  		chooserButton.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(Kmeans.this);
	  				
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
	  				try
	  				{
	  					final BufferedImage outputImage = ImageIO.read(new File(fileField.getText()));
	  					middlePanel.updateImage(outputImage);
	  					//middlePanel.performOperations();
	  					/*int num=Integer.parseInt(numberField.getText());
	  					finish=new FindGreyCluster(fileField.getText(),num);
	  					num=finish.findGreyClusters();
	  					clusterTotalField.setText(""+num);*/
	  					Search s=new Search(fileField.getText());
	  					s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  					
	  					
	  				}catch(Exception ex)
	  				{
	  					ex.printStackTrace();
	  					System.exit(0);
	  				}
	  				
	  				
	  			}
	  		});
	  		
	  	clusterTotalField=new JTextField("",5);
	  	clusterNumberField=new JTextField("",5);
	  	
	  	drawImage=new JButton("cluster image");
	  	drawImage.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event)
  			{
  				try
  				{
  					/*
  					int num=Integer.parseInt(clusterNumberField.getText());
  					BufferedImage img=finish.returnImage(num);
  					middlePanel.updateImage(img);*/
  				}
  				catch(Exception ex)
  				{
  					fileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(fileField.getText());
  				}
  				
  				
  			}
  		});
  		
	  		
		topPanel.add(findButton);
		
		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		
		//pack();
		setVisible(true);
	
	}
	
	

}
