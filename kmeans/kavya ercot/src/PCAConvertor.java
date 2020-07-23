import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;


public class PCAConvertor {
	protected static void pca(String fileName) throws Exception
	  {
		BufferedReader reader = new BufferedReader(
	            new FileReader(fileName));
	    Instances data = new Instances(reader);
	    reader.close();
	    
	    if (data.classIndex() == -1)
	      data.setClassIndex(data.numAttributes() - 1);	
	      PrincipalComponents pca = new PrincipalComponents();

	      pca.setInputFormat(data);
	      //pca.setVarianceCovered(0.95)
	;
	      pca.setMaximumAttributes(4);
	      
	      Instances newData = Filter.useFilter(data, pca);
	     // System.out.println("newdata"+newData);
	      
	    ArffSaver saver = new ArffSaver();
	    saver.setInstances(newData);
	    saver.setFile(new File("pca/"+fileName));
	    //saver.setDestination(new File("/home/nvdia/Ranked_pca.arff"));  
	    saver.writeBatch();
	  }
	  
	  public static void main(String[] args) throws Exception {
		  String fileName1="",fileName2="prediction.arff";
		  /*for(int i=1;i<10;i++){
			 fileName1="ercot/data/"+i+" train.arff";
			 fileName2="ercot/data/"+i+" test.arff";
			 pca(fileName1);
			 pca(fileName2);
		 }*/
		  pca(fileName2);
	  
	  }
}
