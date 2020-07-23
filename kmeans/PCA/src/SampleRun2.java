import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import main.java.com.mkobos.pca_transform.PCA;
import Jama.Matrix;

/** An example program using the library */
public class SampleRun2 {
    public static void main(String[] args){
        System.out.println("Running a demonstration program on some sample data ...");
        /** Training data matrix with each row corresponding to data point and
        * each column corresponding to dimension. */
        //C:\Users\gc\Desktop\harish\fwddocumentsforpca\Dataset\Data set
        //String resource="Load_history.csv";
        String resource="ercot";//"C:\\Users\\gc\\Desktop\\bids\\";//
        ArrayList<ArrayList<String>> res=null;
        try{
        	res=ReadFromExcel.readFromFolder(resource);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
       // ArrayList<String> res=ReadFromCSV.readFromCSV(resource);
        ArrayList<double[]> doubleRes=new ArrayList<double[]>();
        Iterator<ArrayList<String>> itr=res.iterator();
        while(itr.hasNext()){
			ArrayList<String> temp=itr.next();
			//System.out.println("inside if "+temp.size());
			int length=temp.size();
			double[] row=new double[length];
    		
			for(int i=0;i<length;i++){
				if(i==0){
					Date d=new Date(temp.get(i));
					
					//System.out.println(d);
					row[i]=d.getTime();
				}
					
				else
					row[i]=Double.parseDouble(temp.get(i));
			}
			doubleRes.add(row);
			
		}
		
        
        System.out.println("Case1 : 60% and 40%");
        System.out.println("Case2 : 70% and 30%");
        System.out.println("Choose your option");
        
        Scanner scanner=new Scanner(System.in);
        int constraint=scanner.nextInt();
        
        
        double div=0.7;
		if(constraint==1){
			
		}else
			div=0.6;
      
        
        int seven=(int)(doubleRes.size()*(div));
        System.out.println("seven is "+seven);
        double[][] train=new double[seven][doubleRes.get(0).length];
        double[][] test=new double[doubleRes.size()-seven][doubleRes.get(0).length];
        int counter=0;
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
        ReadFromCSV.writeDataSets("resultsercot", transformedData);
    }
}