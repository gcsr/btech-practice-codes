package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Logics;
import controller.ChooserButtonListener;
import controller.FilterButtonListener;
import controller.PlayButtonListener;
import controller.RecordButtonListener;

public class MainClassFrame extends JFrame
{

	JButton chooserButton,processButton,playButton,recordButton;	
	JTextField fileField,catField,passwordField,decodedField;
	double maxFrequency=0;
	
	JPanel topPanel,bottomPanel;
	JLabel fileLabel=new JLabel("Audio File");
	JLabel frequencyLabel=new JLabel("Frequency");
	
	JLabel filterOrderLabel=new JLabel("Filter Order");
	JTextField filterOrderField=new JTextField("");
	JButton filterButton=new JButton("Process");
	
	JLabel fcf1Label=new JLabel("FCF1");
	JTextField fcf1Field=new JTextField("");
	
	
	JTextField rateField=new JTextField("",6);;
	JLabel toLabel=new JLabel("To");
	
	String result="";
	
	
	int rotations;
	
	public static void main(String[] args) 
	{
		try
		     {
		       javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		     }
		 catch (Exception e) {
		    e.printStackTrace();
		 }
		// TODO Auto-generated method stub
		new MainClassFrame();
		

	}
	
	public MainClassFrame()
	{
		super("Java - Sounds");
		setSize(450,200);
		setLocation(400,350);
		setResizable(false);
		setLayout(new BorderLayout());
		topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout());		
		chooserButton=new JButton("choose file");		
		processButton=new JButton("Process");
		recordButton=new JButton("Record");
		playButton=new JButton("play");
		//numberField=new JTextField("",5);
		fileField=new JTextField("sample.au",20);
		
		catField=new JTextField("",6);
		
		
		add(new MiddlePanel(),BorderLayout.CENTER);
		
		topPanel.add(fileLabel);
		topPanel.add(fileField);		
		topPanel.add(chooserButton);
		topPanel.add(recordButton);
		topPanel.add(playButton);
		
		//topPanel.add(numberField); 
		bottomPanel=new JPanel();
	    
	  	
	  		
	  	
	  		
		topPanel.add(processButton);
		//bottomPanel.add(clusterTotalField);
		//bottomPanel.add(clusterNumberField);
		
		
		
		bottomPanel.add(rateField);
		bottomPanel.add(toLabel);		
		bottomPanel.add(catField);
		bottomPanel.add(processButton);
		
		add(topPanel,BorderLayout.NORTH);
		
		add(bottomPanel,BorderLayout.SOUTH);
		//pack();
		setVisible(true);
		processButton.addActionListener(new ActionListener()
  		{
  			public void actionPerformed(ActionEvent event){
  				String text=catField.getText();
  				Logics.changeSamplingRate(text);	
  			}
  		});	
		addListeners();
		rateField.setEditable(true);
		maxFrequency=Logics.getMaxFrequency(fileField.getText());
		rateField.setText(""+Math.round(maxFrequency));
		//rateField.setText(""+maxFrequency);
		//rateField.setText(Logics.processAudioFile(fileField.getText()));
		rateField.setEditable(false);
		
	}
	
	class MiddlePanel extends JPanel{
		public MiddlePanel(){
			setLayout(new GridLayout(3,2,10,10));
			add(filterOrderLabel);
			add(filterOrderField);
			add(fcf1Label);
			add(fcf1Field);
			add(new JLabel("  "));
			add(filterButton);
			
			
		}
	}
	
	
	  public void addListeners(){
		  ChooserButtonListener chooserButtonListener=new ChooserButtonListener(fileField,rateField);
		  chooserButton.addActionListener(chooserButtonListener);
		  FilterButtonListener filterButtonListener=new FilterButtonListener(filterOrderField,fcf1Field);
		  filterButton.addActionListener(filterButtonListener);
		  PlayButtonListener playButtonListener=new PlayButtonListener();
		  playButton.addActionListener(playButtonListener);
		  RecordButtonListener recordButtonListener=new RecordButtonListener(fileField,rateField);
		  recordButton.addActionListener(recordButtonListener);
	  }
	
	  
	 
}
