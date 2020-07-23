import java.util.ArrayList;

import java.util.Iterator;

import main.java.com.mkobos.pca_transform.DataReader;
import main.java.com.mkobos.pca_transform.PCA;
import Jama.Matrix;

/** An example program using the library */
public class SampleRun {
    public static void main(String[] args){
        System.out.println("Running a demonstration program on some sample data ...");
        /** Training data matrix with each row corresponding to data point and
        * each column corresponding to dimension. */
        //C:\Users\gc\Desktop\harish\fwddocumentsforpca\Dataset\Data set
        String resource="Load_history.csv";
        ArrayList<String> res=ReadFromCSV.readFromCSV(resource);
        ArrayList<double[]> doubleRes=new ArrayList<double[]>();
        Iterator<String> itr=res.iterator();
        
        String temp="";
        int length=0;
        String[] splitString=null;
        int counter=0;
        double[] row=null;
        int k=0;
        while(itr.hasNext()){
        	temp=itr.next();
        	splitString=temp.split(",");
        	length=splitString.length;  
        	if(k==0)
        		k=length;
        	if(k==length){
        		row=new double[length];
        		boolean good=true;
        		for(counter=0;counter<length;counter++){
        			if(splitString[counter].equals("")){
        				good=false;
        				break;
        			}
        			row[counter]=Double.parseDouble(splitString[counter]);
        		}
        		if(good)
        			doubleRes.add(row);
        	}
        }
        
        
        int seven=(int)(doubleRes.size()*(7.0/10));
        System.out.println("seven is "+seven);
        double[][] train=new double[seven][doubleRes.get(0).length];
        double[][] test=new double[doubleRes.size()-seven][doubleRes.get(0).length];
        counter=0;
        for(counter=0;counter<seven;counter++){
        	train[counter]=doubleRes.get(counter);
        }
        
        for(counter=seven;counter<doubleRes.size();counter++){
        	test[counter-seven]=doubleRes.get(counter);
        }
        
        System.out.println(train.length);
        System.out.println(test.length);
        int i=0;
        for(double[] ss:train){
        	if(ss.length==0)
        		System.out.println(i);
        	i++;
        }
        
        //Matrix read = DataReader.read(br, false);
        Matrix trainingData = new Matrix(train);/*new double[][] {
            {1, 2, 3, 4, 5, 6},
            {6, 5, 4, 3, 2, 1},
            {2, 2, 2, 2, 2, 2}});*/
        PCA pca = new PCA(trainingData);
        /** Test data to be transformed. The same convention of representing
        * data points as in the training data matrix is used. */
        Matrix testData = new Matrix(test);/*new double[][] {
                {1, 2, 3, 4, 5, 6},
                {1, 2, 1, 2, 1, 2}});
        /** The transformed test data. */
        Matrix transformedData =
            pca.transform(testData, PCA.TransformationType.WHITENING);
        /*System.out.println("Transformed data (each row corresponding to transformed data point):");
        for(int r = 0; r < transformedData.getRowDimension(); r++){
            for(int c = 0; c < transformedData.getColumnDimension(); c++){
                System.out.print(transformedData.get(r, c));
                if (c == transformedData.getColumnDimension()-1) continue;
                System.out.print(", ");
            }
            System.out.println("");
        }*/
        ReadFromCSV.writeDataSets("results", transformedData);
    }
}