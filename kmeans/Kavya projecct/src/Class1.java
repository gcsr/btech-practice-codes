import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;


public class Class1 {
	public static void simpleWekaTrain(String filepath){
		try{
		//Reading training arff file
			FileReader trainreader = new FileReader("1 train.arff");
			Instances train = new Instances(trainreader);
			train.setClassIndex(train.numAttributes()-1);

			//Instance of NN
			MultilayerPerceptron mlp = new MultilayerPerceptron();

			//Setting Parameters
			mlp.setLearningRate(0.1);
			mlp.setMomentum(0.2);
			mlp.setTrainingTime(2000);
			mlp.setHiddenLayers("50,30,20");
			//
			
			mlp.setGUI(true);
			
			mlp.buildClassifier(train);
			
			FileReader testreader = new FileReader("1 test.arff");
			Instances test = new Instances(testreader);
			test.setClassIndex(test.numAttributes()-1);

			
			Evaluation eTest = new Evaluation(test);
			eTest.evaluateModel(mlp, train);
			
			String strSummary = eTest.toSummaryString();
			System.out.println(strSummary);
			 
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] gcs){
		simpleWekaTrain("out.arff");
	}
}
