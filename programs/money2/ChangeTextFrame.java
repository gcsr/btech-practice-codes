import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.event.*;

public class ChangeTextFrame extends JFrame {
	Image picture;
	JPanel top,bottom;
	CentralPanel central;
	JButton widthUp,widthDown,heightUp,heightDown;
	JButton xZoomUp,xZoomDown,yZoomUp,yZoomDown,alphaButton;
	
	ButtonListener buttonListener=new ButtonListener();
	TextFieldListener textFieldListener=new TextFieldListener();
	
	
	JTextField width,height,xZoom,yZoom,alphaField;
	String image="word.JPG";
	int intWidth=1400,intHeight=800,intZoomx=1400,intZoomy=800,alphaValue=100;
	public ChangeTextFrame()
	{
		setName("select best view");
		setLayout(new BorderLayout());
		createPanels();
		add(top,BorderLayout.NORTH);
		add(central,BorderLayout.CENTER);
		add(bottom,BorderLayout.SOUTH);
	}
	private void createPanels()
	{
		createTopPanel();
		createCentralPanel();
		createBottomPanel();
	}
	
	private void createTopPanel()
	{
		top=new JPanel();
		top.setLayout(new FlowLayout());
		widthUp=new JButton("increase width");
		widthDown=new JButton("decrease width");
		heightUp=new JButton("increase height");
		heightDown=new JButton("decrease height");
		
		widthUp.addActionListener(buttonListener);
		widthDown.addActionListener(buttonListener);
		heightUp.addActionListener(buttonListener);
		heightDown.addActionListener(buttonListener);
		top.add(widthUp);
		top.add(widthDown);
		top.add(heightUp);
		top.add(heightDown);
		width=new JTextField("1400");
		height=new JTextField("0800");
		width.addActionListener(textFieldListener);
		height.addActionListener(textFieldListener);
		top.add(width);
		top.add(height);
		
		alphaButton=new JButton("increase");
		alphaField=new JTextField("100");
		top.add(alphaButton);
		top.add(alphaField);
		alphaButton.addActionListener(buttonListener);
		alphaField.addActionListener(textFieldListener);
		
	}
	private void createCentralPanel()
	{
		central=new CentralPanel();
	}
	
	private void createBottomPanel()
	{
		bottom=new JPanel();
		bottom.setLayout(new FlowLayout());
		xZoomUp=new JButton("increase xZoom");
		xZoomDown=new JButton("decrease xZoom");
		yZoomUp=new JButton("increase yZoom");
		yZoomDown=new JButton("decrease yZoom");
		bottom.add(xZoomUp);
		bottom.add(xZoomDown);
		bottom.add(yZoomUp);
		bottom.add(yZoomDown);
		xZoomUp.addActionListener(buttonListener);
		yZoomUp.addActionListener(buttonListener);
		xZoomDown.addActionListener(buttonListener);
		yZoomDown.addActionListener(buttonListener);
		xZoom=new JTextField("1400");
		yZoom=new JTextField("0800");
		xZoom.addActionListener(textFieldListener);
		yZoom.addActionListener(textFieldListener);
		bottom.add(xZoom);
		bottom.add(yZoom);
	}
	
	
	
	public class CentralPanel extends JPanel
	{
		Image orig,cropped;
		public CentralPanel()
		{
			System.out.println(getClass());
			URL url=getClass().getResource("word.JPG");
			try
			{
				orig=createImage((ImageProducer)url.getContent());
				MediaTracker mt=new MediaTracker(this);
				mt.addImage(orig,0);
				mt.waitForID(0);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			createPicture();
		}
		public void createPicture()
		{
			Insets insets=new Insets(20,20,20,20);
			FilteredImageSource fis=new FilteredImageSource(orig.getSource(),new DissolveEdgeFilter(alphaValue,insets)); 
		    fis=new FilteredImageSource(createImage(fis).getSource(),new CropImageFilter(0,150,intWidth,intHeight));
			fis=new FilteredImageSource(createImage(fis).getSource(),new ReplicateScaleFilter(intZoomx,intZoomy));
			
			fis=new FilteredImageSource(orig.getSource(),new DissolveEdgeFilter(alphaValue,insets));
			//dissolveedgefiltered=createImage(fis);
			cropped=createImage(fis);
			repaint();	
			
		}
		
		public void paint(Graphics gc)
		{

			Insets i=getInsets();
			System.out.println("in paint");
			//	gc.drawImage(orig,i.left,i.top,this);
			gc.drawImage(cropped,i.left,i.top,this);
		}

	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			if(ev.getActionCommand()=="increase height")
			{
				intHeight++;
				height.setText(""+intHeight);
				central.createPicture();
				central.repaint();
			}	
			if(ev.getActionCommand()=="decrease height")
			{	
				intHeight--;
				height.setText(""+intHeight);
				central.createPicture();
				central.repaint();
			}
			if(ev.getActionCommand()=="decrease width")
			{	
				intWidth--;
				width.setText(""+intWidth);
				central.createPicture();
				central.repaint();

			}
			if(ev.getActionCommand()=="increase width")
			{
				intWidth++;
				width.setText(""+intWidth);
				central.createPicture();
				central.repaint();

			}
			if(ev.getActionCommand()=="increase xZoom")
			{
				intZoomx++;
				xZoom.setText(""+intZoomx);
				central.createPicture();
				central.repaint();
			}
			if(ev.getActionCommand()=="increase yZoom")
			{
				intZoomy++;
				yZoom.setText(""+intZoomy);
				central.createPicture();
				central.repaint();
			}
			if(ev.getActionCommand()=="decrease xZoom")
			{
				intZoomx--;
				xZoom.setText(""+intZoomx);
				central.createPicture();
				central.repaint();
			}
			if(ev.getActionCommand()=="decrease yZoom")
			{
				intZoomy--;
				yZoom.setText(""+intZoomy);
				central.createPicture();

				central.repaint();
			}
			
			if(ev.getActionCommand()=="increase")
			{
				alphaValue++;
				alphaField.setText(""+alphaValue);
				central.createPicture();
				central.repaint();
				
			}
			
		}
	}
	
	private class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			intWidth=Integer.parseInt(width.getText());
			intHeight=Integer.parseInt(height.getText());
			intZoomx=Integer.parseInt(xZoom.getText());
			intZoomy=Integer.parseInt(yZoom.getText());
			alphaValue=Integer.parseInt(alphaField.getText());
			central.createPicture();
			central.repaint();
			
		}
	}

}