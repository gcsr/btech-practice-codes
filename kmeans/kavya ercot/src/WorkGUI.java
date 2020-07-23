import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class WorkGUI extends JFrame{
	JPanel backPropagation;
	JPanel conjugateGradient;
	JPanel graphs;
	private static final int STATIONS=9;
	
	public static void main(String[] gcs){
		new WorkGUI();
	}
	
	public void createUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(500, 400);
    	setLocation(300, 150);
    	
    	setLayout(new GridLayout(1,1));
    	JTabbedPane tabbedPane = new JTabbedPane();
    	backPropagation=new BackPropagation();
    	conjugateGradient=new ConjugateGradient();
    	graphs=new Graphs();
    	tabbedPane.add(backPropagation,"back propagation");
    	tabbedPane.add(conjugateGradient,"conjugate gradient");
    	tabbedPane.add(graphs,"graphs");
    	add(tabbedPane);
    	
    	setVisible(true);
    	
	}
	public WorkGUI(){
		super("Power allocation prediction");
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
	
	private class BackPropagation extends JPanel{
		
		JLabel stationLabel;
		JLabel dataLabel;
		JList stationList;
		ButtonGroup dataSelector; 
		
		JLabel rateLabel;
		JLabel momemtumLabel;
		JLabel timeLabel;
		JLabel hiddenLabel;
		
		
		JTextField rateField;
		JTextField momemtumField;
		JTextField timeField;
		JTextField hiddenField;
		
		JButton startButton=new JButton("start");
		
		
		BackPropagation(){
			setLayout(new BorderLayout());
			
			JPanel temp1=new JPanel();
			temp1.setLayout(new GridLayout(4,2));
			
			rateLabel=new JLabel("Learning rate");
			momemtumLabel=new JLabel("Momentum");
			timeLabel=new JLabel("Learning time");
			hiddenLabel=new JLabel("Hidden Layers");
			
			rateField=new JTextField(".1");
			momemtumField=new JTextField(".2");
			timeField=new JTextField("2000");
			hiddenField=new JTextField("50,30,20");
			
			temp1.add(rateLabel);
			temp1.add(rateField);
			
			temp1.add(momemtumLabel);
			temp1.add(momemtumField);
			
			temp1.add(timeLabel);
			temp1.add(timeField);
			
			temp1.add(hiddenLabel);
			temp1.add(hiddenField);
			
			
						
			stationLabel=new JLabel("station");
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(Box.createHorizontalStrut(10));
			horizontalBox.add(stationLabel);
			String[] stations=new String[STATIONS];
			for(int i=1;i<=STATIONS;i++){
				stations[i-1]=""+i;
			}
			stationList=new JList(stations);
			stationList.setSelectedIndex(0);
			stationList.setVisibleRowCount(1);
			JScrollPane scrollPane=new JScrollPane(stationList);
			
			horizontalBox.add(Box.createHorizontalStrut(5));
			horizontalBox.add(scrollPane);
			
			dataLabel=new JLabel("data set");
			horizontalBox.add(Box.createHorizontalStrut(20));
			
			//horizontalBox.add(dataLabel);
			
			final JRadioButton ercot=new JRadioButton("PCA");
			final JRadioButton kaggle=new JRadioButton("NonPCA");
			
			
			dataSelector=new ButtonGroup();
			dataSelector.add(ercot);
			dataSelector.add(kaggle);
			kaggle.setSelected(true);
			horizontalBox.add(Box.createHorizontalStrut(5));
			
			
			horizontalBox.add(ercot);

			horizontalBox.add(kaggle);
			
			add(horizontalBox,BorderLayout.NORTH);
			add(temp1,BorderLayout.CENTER);
			add(startButton,BorderLayout.SOUTH);
			
			startButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String[] options=new String[6];
					
					String fileNumber=(String)stationList.getSelectedValue();
					
					if(ercot.isSelected()){
						options[0]="pca/ercot/data/"+fileNumber+" train.arff";
						//options[1]="pca/ercot/data/"+fileNumber+" test.arff";
						options[1]="pca/ercot/data/prediction.arff";
					}else{
						options[0]="ercot/data/"+fileNumber+" train.arff";
						//options[1]="ercot/data/"+fileNumber+" test.arff";
						options[1]="ercot/data/prediction.arff";
					}
					
					String option2=rateField.getText();
					if(option2!=null && !option2.equals(""))
						options[2]=option2;
					else
						options[2]="0.1";
					
					String option3=momemtumField.getText();
					if(option3!=null && !option3.equals(""))
						options[3]=option3;
					else
						options[3]="0.2";
					
					String option4=timeField.getText();
					if(option4!=null && !option4.equals(""))
						options[4]=option4;
					else
						options[4]="2000";
					
					String option5=hiddenField.getText();
					if(option5!=null && !option5.equals(""))
						options[5]=option5;
					else
						options[5]="50";
					
					if(!ercot.isSelected()){
						String[] columnNames = {"year",
				                "month",
				                "day",
				                "hour",
				                "holiday",
				                "weekend",
				                "station",
				                "original",
				                "predicted",
				                "error"};
						new BackPropagationThread(options,columnNames).start();
					}else{
						String[] columnNames = {"col1",
				                "col2",
				                "col3",
				                "col4",
				                "original",
				                "predicted",
				                "error"};
						new BackPropagationThread(options,columnNames).start();
					}
					
				}
				
			});
		}
	}
	
	private class ConjugateGradient extends JPanel{
		JLabel stationLabel;
		JLabel dataLabel;
		JList stationList;
		ButtonGroup dataSelector; 
		
		JLabel rateLabel;
		JLabel momemtumLabel;
		JLabel timeLabel;
		JLabel hiddenLabel;
		
		
		JTextField rateField;
		JTextField momemtumField;
		JTextField timeField;
		JTextField hiddenField;
		
		JButton startButton=new JButton("start");
		
		public ConjugateGradient(){
			setLayout(new BorderLayout());
			
			JPanel temp1=new JPanel();
			temp1.setLayout(new GridLayout(4,2));
			
			rateLabel=new JLabel("Learning rate");
			momemtumLabel=new JLabel("Momentum");
			timeLabel=new JLabel("Learning time");
			hiddenLabel=new JLabel("Hidden Layers");
			
			rateField=new JTextField("Learning rate");
			momemtumField=new JTextField("Momentum");
			timeField=new JTextField("Learning time");
			hiddenField=new JTextField("100");
			
			temp1.add(rateLabel);
			temp1.add(rateField);
			rateField.setEditable(false);
			
			temp1.add(momemtumLabel);
			temp1.add(momemtumField);
			momemtumField.setEditable(false);
			
			temp1.add(timeLabel);
			temp1.add(timeField);
			timeField.setEditable(false);
			
			temp1.add(hiddenLabel);
			temp1.add(hiddenField);
			
			
						
			stationLabel=new JLabel("station");
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(Box.createHorizontalStrut(10));
			horizontalBox.add(stationLabel);
			String[] stations=new String[STATIONS];
			for(int i=1;i<=STATIONS;i++){
				stations[i-1]=""+i;
			}
			stationList=new JList(stations);
			
			stationList.setVisibleRowCount(1);
			JScrollPane scrollPane=new JScrollPane(stationList);
			
			horizontalBox.add(Box.createHorizontalStrut(5));
			horizontalBox.add(scrollPane);
			
			dataLabel=new JLabel("data set");
			horizontalBox.add(Box.createHorizontalStrut(20));
			
			//horizontalBox.add(dataLabel);
			
			final JRadioButton ercot=new JRadioButton("ercot");
			final JRadioButton kaggle=new JRadioButton("kaggle");
			
			ercot.setSelected(true);
			dataSelector=new ButtonGroup();
			dataSelector.add(ercot);
			dataSelector.add(kaggle);
			horizontalBox.add(Box.createHorizontalStrut(5));
			
			
			//horizontalBox.add(ercot);

			//horizontalBox.add(kaggle);
			
			add(horizontalBox,BorderLayout.NORTH);
			add(temp1,BorderLayout.CENTER);
			add(startButton,BorderLayout.SOUTH);
			
			startButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					String[] options=new String[6];
					String fileNumber=(String)stationList.getSelectedValue();
					
					if(ercot.isSelected()){
						options[0]="ercot/data/"+fileNumber+" train.arff";
						options[1]="ercot/data/"+fileNumber+" test.arff";
					}else{
						options[0]="ercot/data/"+fileNumber+" train.arff";
						options[1]="ercot/data/"+fileNumber+" test.arff";
					}
					
					
					String option5=hiddenField.getText();
					if(option5!=null && !option5.equals(""))
						options[2]=option5;
					else
						options[2]="100";
							
					new ConjugateGradientThread(options).start();
					
				}
				
			});

		}
	}
	
	private class Graphs extends JPanel{
		
	}
	
}
