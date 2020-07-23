import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.functions.MLPClassifier;
import weka.core.Instance;
import weka.core.Instances;





public class ConjugateGradientThread extends Thread{
	
	String[] options;
	public ConjugateGradientThread(String[] options){
		this.options=options;
	}
	
	public void run(){
		try{
			//Reading training arff file
				FileReader trainreader = new FileReader(options[0]);
				Instances train = new Instances(trainreader);
				System.out.println(train.numAttributes());
				train.setClassIndex(train.numAttributes()-1);


				
				MLPClassifier classifier=new MLPClassifier();
				 String[] options2 = weka.core.Utils.splitOptions("-G -N "+options[2]);
				 
				 
				 classifier.setOptions(options2);
				 
				 String[] ddd=classifier.getOptions();
				 for(String dddd:ddd)
					 System.out.println(dddd);
				
				//Instance of NN
				//MultilayerPerceptron mlp = new MultilayerPerceptron();

				//Setting Parameters
				
				
				/*mlp.setLearningRate(Double.parseDouble(options[2]));
				mlp.setMomentum(Double.parseDouble(options[3]));
				mlp.setTrainingTime(Integer.parseInt(options[4]));
				mlp.setHiddenLayers(options[5]);
				
				
				/*mlp.setLearningRate(0.1);
				mlp.setMomentum(0.2);
				mlp.setTrainingTime(2000);
				mlp.setHiddenLayers("50");
				
				mlp.setGUI(true);*/
				
				
				classifier.buildClassifier(train);
				System.out.println(classifier.toString());
				//classifier.r
				
				/*FileReader testReader = new FileReader(options[1]);
				Instances test = new Instances(testReader);
				test.setClassIndex(test.numAttributes()-1);
				Iterator<Instance> testData=test.iterator();
				Instance ttt;*/
				FileReader testReader = new FileReader(options[1]);
				Instances test = new Instances(testReader);
				test.setClassIndex(test.numAttributes()-1);
				Iterator<Instance> testData=test.iterator();
				Instance ttt;
				
				
				/*Evaluation eTest = new Evaluation(train);
				eTest.evaluateModel(classifier, test);
				
				
				
				ArrayList<Prediction> pds=eTest.predictions();*/
				Evaluation eTest = new Evaluation(train);
				eTest.evaluateModel(classifier, test);
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
				
				String strSummary = eTest.toSummaryString();
				System.out.println(strSummary);
				 
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

	}
	
	public static void main(String[] gcs){
		String[] options=new String[]{"out.arff","out.arff"};
		new ConjugateGradientThread(options).start();
	}
}
