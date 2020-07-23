import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LBPValuesGUI extends JFrame implements MouseListener
{

	JTextField file1Field;
	JButton chooser1Button,findButton,	drawLBPImage,cropImage;
	JButton cropLeft,cropRight,cropTop,cropBottom,save;
	JButton cropLeftLine,cropRightLine;
	JFileChooser fileChooser;
	JPanel topPanel,bottomPanel;
	DrawImage middlePanel;
	//DrawHistogram histogram2;
	boolean findLBPValues;
	StandardDeviation deviation;
	//SoberOperator soberOperator;
	BufferedImage image;

	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new LBPValuesGUI();
		

	}
	
	public LBPValuesGUI()
	{
		super("GUI");
		
		setSize(1200,1400);
		
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		file1Field=new JTextField("can\'t enter a value",30);
		file1Field.setEditable(false);		
		chooser1Button=new JButton(" choose file1");		
		findButton=new JButton(" find grids");
		
		
		topPanel.add(file1Field);
		topPanel.add(chooser1Button);
		
		
		
		topPanel.add(findButton);
		
		
		
		
		middlePanel=new DrawImage();
		
		JScrollPane scroll = new JScrollPane (middlePanel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    
	    bottomPanel=new JPanel();
	    cropImage=new JButton("crop");
	    	  	
	    cropLeft=new JButton("Crop Left");
	    cropRight=new JButton("Crop Right");
	    
	    cropLeftLine=new JButton("Crop Left line");
	    cropRightLine=new JButton("Crop Right Line");
	    
	    cropTop=new JButton("Crop Top");
	    cropBottom=new JButton("Crop Bottom");
	    save=new JButton("Save Picture");   
	    fileChooser=new JFileChooser();
	  		
	  		chooser1Button.addActionListener(new ActionListener()
	  		{
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	  				int result=fileChooser.showOpenDialog(LBPValuesGUI.this);
	  				
	  				if(result==JFileChooser.CANCEL_OPTION)
	  					return;
	  				
	  				
	  				file1Field.setText(fileChooser.getSelectedFile().getAbsolutePath());
	  				middlePanel.updateImage(file1Field.getText());	  				
	  				image=middlePanel.getImage();
	  			}
	  		});
	  		
	  		
	  		
	  		      
		
		
	  	bottomPanel.add(cropImage);
	  	bottomPanel.add(cropLeft);
	  	bottomPanel.add(cropRight);
	  	bottomPanel.add(cropTop);
	  	bottomPanel.add(cropBottom);
	  	bottomPanel.add(save);
		drawLBPImage=new JButton("LBPImage");
		bottomPanel.add(drawLBPImage);
		bottomPanel.add(cropLeftLine);
	  	bottomPanel.add(cropRightLine);
		cropImage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//if(findLBPValues){						
					middlePanel.cropImage();			
					
				/*}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}*/


				
			}
		});

		cropLeft.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//if(findLBPValues){						
					middlePanel.cropImageLeft();			
					
		
					/*}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}*/


				
			}
		});
		
		cropRight.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
	//			if(findLBPValues){						
					middlePanel.cropImageRight();			
					
				/*}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}*/


				
			}
		});
		
		cropTop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
//				if(findLBPValues){						
					middlePanel.cropImageTop();			
					
	/*			}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}
*/

				
			}
		});
		
		cropBottom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
	//			if(findLBPValues){						
					middlePanel.cropImageBottom();			
					
		/*		}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}
*/

				
			}
		});
		
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//if(findLBPValues){				
					String st="D:/tomcat/webapps/nolimits/images/defaultcut.jpg";//file1Field.getText();
					middlePanel.saveImage(st.substring(0,st.lastIndexOf('.'))+"cut.jpg");			
					
				/*}
				else{
					JOptionPane.showMessageDialog(null, "find pixel values", "error", JOptionPane.ERROR_MESSAGE);
				}*/


				
			}
		});
		
		cropLeftLine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
										
					middlePanel.line(2,0);
					middlePanel.lineLeftCut();			
					
			}
		});

		
		cropRightLine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				
				middlePanel.line(2,0);
				middlePanel.lineRightCut();

				
			}
		});



		add(topPanel,BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		new PixelsFrame(image,e.getX(),e.getY());
		//new PixelsFrame(image,e,getX(),e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	

}
