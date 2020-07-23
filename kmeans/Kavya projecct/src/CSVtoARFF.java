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
		 Convert("data/holidays/weekends/2 train.CSV", "data/holidays/weekends/2 train.arff");
	 
	 }
}
