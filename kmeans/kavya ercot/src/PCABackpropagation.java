import java.io.FileReader;

import java.util.ArrayList;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
//import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;


public class PCABackpropagation extends Thread{
	
	String[] options;
	public PCABackpropagation(String[] options){
		this.options=options;
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
				
				
				FileReader testReader = new FileReader(options[1]);
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
				double[][] displayData=new double[test.size()][dataSetLength+2];
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
					displayData[counter][dataSetLength+1]=Math.abs(displayData[counter][dataSetLength]-displayData[counter][dataSetLength-1]);
					counter++;
				}
				
				new DisplayTable(displayData);
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
