

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class XLSXSheetDivision extends JFrame{
	private JButton loadButton;
	
	private JButton runRScript;
	private JButton evaluateLearning;
	HashMap<String,ArrayList<TransitionObject>> finalResult=null;
	HashMap<String,ArrayList<TransitionObject2>> finalResult2=null;
	
	HashMap<String, List<String>> data;
	public static final String DIRECTORY="C://Users//gc//Desktop//results//";
	String displayText="";
	JLabel displayLabel=new JLabel("please send me some text\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	
	
	public static void main(String[] gcs){
		XLSXSheetDivision xd=new XLSXSheetDivision();
		xd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		xd.setSize(500, 500);
		xd.setVisible(true);
		
	}
	
	class ButtonPanel extends JPanel{
		public ButtonPanel(){
			Box vertical1 = Box.createVerticalBox();
			loadButton=new JButton("Load XLXS file");			
			runRScript=new JButton("Run R script");
			evaluateLearning=new JButton("Evaluate");
			vertical1.add(loadButton);
			vertical1.add(Box.createVerticalStrut(15));
			
			vertical1.add(runRScript);
			vertical1.add(Box.createVerticalStrut(15));
			
			vertical1.add(evaluateLearning);
			add(vertical1);
			loadButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					//ReadFromCSV.readFromCSV(DIRECTORY+"U11.csv");
				}
			});
			
			
			
			runRScript.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					Thread t=new Thread(new ExecuteR());
					t.start();
					
					try{
						Thread.currentThread().sleep(20000);
						t.interrupt();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			});
			
			evaluateLearning.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					
					
					
					HashMap<String,ArrayList<String>> test=ReadFromCSV.getTestData();
					HashMap<String,ArrayList<ProbabilityElement>> result=ReadFromCSV.getProbabilityElements();//readExcelData();
					
					HashMap<String,ArrayList<ProbabilityElement2>> result2=ReadFromCSV.getProbabilityElement2s();//readExcelData();
					
					finalResult=ReadFromCSV.fullCheck2(result, test);
					finalResult2=ReadFromCSV.fullCheck3(result2, test);
					String[] fileNames=new String[]{"Complete","Sunday","Monday",
							"Tuesday","Wednesday","Thursday","Friday","Saturday"};
					
					
					/*ArrayList<String> series=test.get("Thursday");
					for(String ff:series){
						System.out.println(ff);
					}*/
					
					
					displayText="<html>";
					for (String key:fileNames){//Map.Entry<String, ArrayList<TransitionObject>> entry : finalResult.entrySet()) {
						//String key=entry.getKey();
						System.out.println(key);
						displayText+="<h1>Results for the day : ----------------------------------"+key+"<br><br></h1>";
						displayText+="<h2><br><br><br>Evaluation 1: ------------------------------------------<br></h2>";
						
						
						ArrayList<TransitionObject> value=finalResult.get(key);
						ArrayList<TransitionObject2> value2=finalResult2.get(key);
						Iterator<TransitionObject2> itr2=value2.iterator();
						Iterator<TransitionObject> itr=value.iterator();
						String currentApp="";String nextApp="";
						ArrayList<ResultsList> results=new ArrayList<ResultsList>();
						
						while(itr.hasNext()){
							TransitionObject temp=itr.next();
							
							//displayText+="<h4>Current App : "+temp.getCurrentElement()+"<br></h4>";
							//System.out.println(temp.getCurrentElement());
							//displayText+="<h4>Next App : "+temp.getNextElement()+"<br></h4>";
							//System.out.println(temp.getNextElement());
							//displayText+="<h4>Positives : "+temp.getFavour()+"<br></h4>";
							//System.out.println(temp.getFavour());
							//displayText+="<h4>Negatives : "+temp.getOppose()+"<br></h4>";
							//System.out.println(temp.getOppose());
							//displayText+="<br>";
							results.add(new ResultsList(temp.getCurrentElement(),temp.getNextElement(),temp.getFavour(),temp.getOppose()));
						}
						
						while(itr2.hasNext()){
							TransitionObject2 temp=itr2.next();
							
							displayText+="<h4>Current App : "+temp.getCurrentElement()+"<br></h4>";
							//System.out.println(temp.getCurrentElement());
							
							String temptemp=""+temp.getNextElement()[0]+", "+temp.getNextElement()[1]+", "+temp.getNextElement()[2];
							if(temptemp.contains("null"));
							temptemp=temptemp.substring(0,temptemp.indexOf(", null"));
							displayText+="<h4>Next App : "+temptemp+"<br></h4>";
							//System.out.println(temp.getNextElement().toString());
							displayText+="<h4>Positives : "+temp.getFavour()+"<br></h4>";
							//System.out.println(temp.getFavour());
							displayText+="<h4>Negatives : "+temp.getOppose()+"<br></h4>";
							//System.out.println(temp.getOppose());
							displayText+="<br>";
							//results.add(new ResultsList(temp.getCurrentElement(),temp.getNextElement(),temp.getFavour(),temp.getOppose()));
						}
						
						
						
						Collections.sort(results);
						if(results.size()!=0){
							ResultsList rlt=results.get(0);
							displayText+="<br><h2><br><br><br>First Prediction : --------------------------------------------"+"<br><br></h2>";
							displayText+="<h4>Current App : "+rlt.getCurrentApp()+"<br></h4>";
							displayText+="<h4>Next App : "+rlt.getNextApp()+"<br></h4>";
							displayText+="<h4>Positives : "+rlt.getPositives()+"<br></h4>";
							displayText+="<h4>Negatives : "+rlt.getNegatives()+"<br><br></h4>";
							
							displayText+="<h2><br><br><br>Top Five Prediction : ----------------------------------------------"+"<br><br></h2>";
						
							int positives=0;
							int negatives=0;
							int i=0;
							while(i<results.size() && i<5){
								rlt=results.get(i);
								displayText+="<h4>Current App : "+rlt.getCurrentApp()+"<br></h4>";								
								displayText+="<h4>Next App : "+rlt.getNextApp()+"<br></h4>";								
								displayText+="<h4>Positives : "+rlt.getPositives()+"<br></h4>";								
								displayText+="<h4>Negatives : "+rlt.getNegatives()+"<br></h4>";								
								displayText+="<br>";
								i++;
								
							}
						}
			
						
					}
					displayText+="</html>";
					displayLabel.setText(displayText);
				}
			});	
			
		}
	}
	
	public XLSXSheetDivision(){
		super("Markov model - Learning next possible app");
		setLayout(new BorderLayout());
		ButtonPanel buttonPanel=new ButtonPanel();
		JScrollPane sPane=new JScrollPane(displayLabel);
		sPane.getVerticalScrollBar().setUnitIncrement(15);
		add(sPane,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.EAST);
		
	}
	
	class ExecuteR implements Runnable{
		public void run(){
			try{
				Process child=Runtime.getRuntime().exec("Rscript C:\\Users\\gc\\Desktop\\markov\\markovFit.R");
				int code = child.waitFor();

	            switch (code) {
	                case 0:
	                    System.out.println("code finished running");
	                	//normal termination, everything is fine
	                    break;
	                case 1:
	                   break;
	            }
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
