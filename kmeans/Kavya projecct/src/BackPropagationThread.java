import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;


public class BackPropagationThread extends Thread{
	
	String[] options;
	public BackPropagationThread(String[] options){
		this.options=options;
	}
	
	public void run(){
		try{
			//Reading training arff file
				FileReader trainreader = new FileReader(options[0]);
				Instances train = new Instances(trainreader);
				train.setClassIndex(train.numAttributes()-3);

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
				
				mlp.buildClassifier(train);
				
				
				FileReader testReader = new FileReader(options[1]);
				Instances test = new Instances(trainreader);
				test.setClassIndex(test.numAttributes()-3);
				
				Evaluation eTest = new Evaluation(test);
				eTest.evaluateModel(mlp, train);
				
				String strSummary = eTest.toSummaryString();
				System.out.println(strSummary);
				 
			}
			catch(Exception ex){
				ex.printStackTrace();
			}

	}
}
