import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.concurrent.Callable;


public class MatrixFactorization implements Callable<double[]>{	
	
	public static final int equationLength=19; 
	double xValues[][];
	double yValues[];
	double[] expectedValues;
	double[] thetaValues=new double[19];
	double theta1=.5,theta2=.5;//,theta3=.5,theta4=.5;
	
	int expectedValuesLength;
	double diff=0;
	
	
	public MatrixFactorization(double xValues[][],double[] yValues,int expectedValuesLength){
		
		/*for(int ij=0;ij<1682;ij++){
			System.out.print("\t"+yValues[ij]);
			/*for(int jk=0;jk<19;jk++){
				System.out.print("\t"+xValues[ij][jk]);
			}
			System.out.println();
		}*/
		
		
		double[][] temp1=new double[expectedValuesLength][19];
		double[] temp2=new double[expectedValuesLength];
				
		int i=0;
		int j=0;
		
		while(i<expectedValuesLength && j<yValues.length){
			
			if(yValues[j]>0){
				//System.out.println(i);
				temp2[i]=yValues[j];
				//System.out.println(xValues[j]);
				//System.out.println(temp1[i]);
				temp1[i]=xValues[j];
				i++;
			}
			
			j++;
		
		}
		this.xValues=temp1;
		this.yValues=temp2;
		this.expectedValuesLength=expectedValuesLength;
		
		/*for(int ij=0;ij<expectedValuesLength;ij++){
			System.out.print("\t"+yValues[ij]);
			for(int jk=0;jk<19;jk++){
				System.out.print("\t"+xValues[ij][jk]);
			}
			System.out.println();
		}*/
		
		assignInitialValues();
		//printEquation();
		findExpectedValues();
		//printValues();
		
	}
	
  	
	
	public double[] returnFactors(){
		return thetaValues;
	}
	
	private void printEquation(){		
		System.out.println(theta1+"+"+theta2+"x+");
	}
	
	private void assignInitialValues(){
		for(int i=0;i<equationLength;i++){
			thetaValues[i]=0;
		}
		expectedValues=new double[expectedValuesLength];
	}
	
	private void updateThetaValues(){
		
		for(int i=0;i<equationLength;i++){
			double substractor=0.0;
			for(int j=0;j<expectedValuesLength;j++){
				substractor+=(expectedValues[j]-yValues[j])*xValues[j][i];
			}			
			thetaValues[i]-=((substractor*0.005)/expectedValues.length);
		}
		
	}
	
	
	private void findCostFunctionValue(){
		
	}
	
	private boolean isHypothesysSatisfied(){
		
		double difffff=diff;
		diff=0;
		
		for(double x:thetaValues)
			diff+=x;
		
		diff*=0.005;
		
		for(int i=0;i<expectedValues.length;i++){
			diff+=(expectedValues[i]-yValues[i])*(expectedValues[i]-yValues[i]);
		}		
		
		diff+=diff/(2*expectedValues.length);
		
		//System.out.println("diff is "+diff);
		
		if((difffff-diff<0 && difffff!=0)|| diff<10){
			//System.out.println("diff "+diff);
			System.out.println("condition satisfied");
			return true;
			
		}
		
		
		
		return false;
	}
	
	private void findExpectedValues(){
		
		for(int i=0;i<expectedValuesLength;i++){
			expectedValues[i]=0;
			for(int j=0;j<equationLength;j++){
				expectedValues[i]+=xValues[i][j]*thetaValues[j];
			}
		}
	}
	
	public static void main(String[] gcs){
		//new MatrixFactorization();
	}

	@Override
	public double[] call() throws Exception {
		// TODO Auto-generated method stub
		//int ppp=0;
		
		//printValues();
		while(!isHypothesysSatisfied()){
			updateThetaValues();
			findExpectedValues();
			//printValues();
			//printEquation();
			//ppp++;
		}
		
		//printValues();
		return thetaValues;
	}
	
	private void printValues(){
		System.out.println();
		
		for(double x:expectedValues)
			System.out.print("\t"+Math.round(x));
	
			
		System.out.println();
		
		for(double x:yValues)
			System.out.print("\t"+x);
	
		System.out.println();
	}

}
