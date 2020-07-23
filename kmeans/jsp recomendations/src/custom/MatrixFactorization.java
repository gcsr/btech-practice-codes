package custom;

import java.util.Iterator;
import java.util.List;
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
	
	
	public MatrixFactorization(double xValues[][],double[] yValues){
		this.xValues=xValues;
		this.yValues=yValues;
		this.expectedValuesLength=yValues.length;
		//expectedValues=new double[expectedValuesLength];
		assignInitialValues();
		//printValues();
		//printEquation();
		findExpectedValues();
		/*int ppp=0;
		while(!isHypothesysSatisfied()){
			updateThetaValues();
			findExpectedValues();
			printEquation();
			ppp++;
		}
		
		System.out.println("the required equation is");
		printEquation();*/
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
			System.out.println("condition satisfied");
			return true;
			
		}
		
		
		
		return false;
	}
	
	private void findExpectedValues(){
		
		for(int i=0;i<expectedValuesLength;i++){
			expectedValues[i]=0;
			for(int j=0;j<equationLength;j++){
				//System.out.print(xValues[i][j]);
				//System.out.print(thetaValues[j]);
				//System.out.print(expectedValues[i]);
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
		int ppp=0;
		while(!isHypothesysSatisfied() && ppp<1000){
			updateThetaValues();
			findExpectedValues();
			//printValues();
			//printEquation();
			ppp++;
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
