import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;


public class CSVtoARFF {

	 /**
	 * takes 2 arguments:
	 * - CSV input file
	 * - ARFF output file
	 * @param sourcepath
	 * @param destpath
	 * @throws java.lang.Exception
	 */
	 public static void Convert(String sourcepath,String destpath) throws Exception
	 {
	 // load CSV
		 CSVLoader loader = new CSVLoader();
		 loader.setSource(new File(sourcepath));
		 Instances data = loader.getDataSet();
	 
		 // save ARFF
		 ArffSaver saver = new ArffSaver();
		 saver.setInstances(data);
		 saver.setFile(new File(destpath));
		// saver.setDestination(new File(destpath));
		 saver.writeBatch();
	 }
	 public static void main(String args[]) throws Exception
	 {
		 
		 //Convert("prediction.CSV", "ercot/data/prediction.arff");
			// Convert("ercot/data/1 train.CSV", "ercot/data/1 train.arff");
			// Convert("ercot/data/1 test.CSV", "ercot/data/1 test.arff");
		 for(int i=1;i<10;i++){
			 //Convert("ercot/data/"+i+" train.CSV", "ercot/data/"+i+" train.arff");
			 Convert("ercot/data/"+i+" test.CSV", "ercot/data/"+i+" test.arff");
		 }
	 
	 }
}
