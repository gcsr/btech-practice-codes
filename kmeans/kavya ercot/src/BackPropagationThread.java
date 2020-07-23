import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
//import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;


public class BackPropagationThread extends Thread{
	
	String[] options;
	String[] columnNames;
	
	public BackPropagationThread(String[] options,String[] columnNames){
		this.options=options;
		this.columnNames=columnNames;
	}
	
	public void run(){
		try{
			//Reading training arff file
				FileReader trainreader = new FileReader(options[0]);
				Instances train = new Instances(trainreader);
				train.setClassIndex(train.numAttributes()-1);

				//Instance of NN
				MultilayerPerceptron mlp = new MultilayerPerceptron();

				//Setting Parameters
				
				mlp.setLearningRate(Double.parseDouble(options[2]));
				mlp.setMomentum(Double.parseDouble(options[3]));
				mlp.setTrainingTime(Integer.parseInt(options[4]));
				mlp.setHiddenLayers(options[5]);
				
				
				/*mlp.setLearningRate(0.1);
				mlp.setMomentum(0.2);
				mlp.setTrainingTime(2000);
				mlp.setHiddenLayers("50");
				*/
				mlp.setGUI(true);
				//System.out.println(mlp.globalInfo());
				//System.out.println(mlp.);
				
				System.out.println("options");
				System.out.println("momentum is "+mlp.getMomentum());
				System.out.println("hidden layers is "+mlp.getHiddenLayers());
				System.out.println("learning rate is "+mlp.getLearningRate());
				System.out.println("training time is "+mlp.getTrainingTime());
				
				
				mlp.buildClassifier(train);
				
				
				String[] options2=mlp.getOptions();
				
				System.out.println("after");
				
				System.out.println(mlp);
				System.out.println(mlp);
				
				FileReader testReader = new FileReader(options[1]);
				//FileReader testReader = new FileReader(options[1]);
				Instances test = new Instances(testReader);
				test.setClassIndex(test.numAttributes()-1);
				Iterator<Instance> testData=test.iterator();
				Instance ttt;
				
				
				Evaluation eTest = new Evaluation(train);
				eTest.evaluateModel(mlp, test);
				ArrayList<Prediction> pds=eTest.predictions();
				
				
				Iterator<Prediction> itr=pds.iterator();
				Prediction prediction;
				int dataSetLength=test.numAttributes();
				final double[][] displayData=new double[test.size()][dataSetLength+2];
				final double[] actuals=new double[test.size()];
				final double[] predictions=new double[test.size()];
				double[] temp=null;
				int counter=0;			
				
				while(itr.hasNext()){
					prediction=(itr.next());
					ttt=testData.next();
					temp=ttt.toDoubleArray();
					displayData[counter]=new double[dataSetLength+2];
					for(int kgp=0;kgp<dataSetLength;kgp++){
						displayData[counter][kgp]=temp[kgp];
					}
					displayData[counter][dataSetLength]=prediction.predicted();
					predictions[counter]=prediction.predicted();
					actuals[counter]=displayData[counter][dataSetLength-1];
					displayData[counter][dataSetLength+1]=Math.abs(displayData[counter][dataSetLength]-displayData[counter][dataSetLength-1]);
					counter++;
				}
				
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						
						new DisplayTable(displayData,columnNames);
						new DrawChart(actuals,predictions).setVisible(true);
					}
				});
				
				/*String[][] displayDataString=new String[displayData.length][dataSetLength+2];
				
				for(int i=0;i<displayData.length;i++){
					for(int j=0;j<(dataSetLength+2);j++){
						displayDataString[i][j]=""+displayData[i][j];
					}
				}
				WriteExcelData.writeToExcel(columnNames, displayDataString, "predictedvalues");*/
				//eTest.
				/*Enumeration<FastVector> ppp=eTest.predictions().elements();
				while(ppp.hasMoreElements()){
					FastVector temp=ppp.nextElement();
					System.out.println(temp.elementAt(3));
				}*/
				//String strSummary = eTest.toSummaryString();
				//System.out.println(strSummary);
				//eTest.eva
				 
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

	}
}
