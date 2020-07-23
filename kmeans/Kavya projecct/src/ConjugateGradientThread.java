import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MLPClassifier;
import weka.core.Instances;





public class ConjugateGradientThread extends Thread{
	
	String[] options;
	public ConjugateGradientThread(String[] optins){
		this.options=options;
	}
	
	public void run(){
		try{
			//Reading training arff file
				FileReader trainreader = new FileReader("1 train.arff");
				Instances train = new Instances(trainreader);
				System.out.println(train.numAttributes());
				train.setClassIndex(train.numAttributes()-1);


				MLPClassifier classifier=new MLPClassifier();
				 String[] options = weka.core.Utils.splitOptions("-G -N 100");
				 
				 
				 classifier.setOptions(options);
				 
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
				//classifier.r
				
				FileReader testReader = new FileReader("1 test.arff");
				Instances test = new Instances(testReader);
				test.setClassIndex(test.numAttributes()-1);
				
				Evaluation eTest = new Evaluation(test);
				eTest.evaluateModel(classifier, train);
				
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
