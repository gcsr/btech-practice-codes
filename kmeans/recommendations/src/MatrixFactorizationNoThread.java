import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;


public class MatrixFactorizationNoThread{	
	
	public static final int equationLength=19; 
	double xValues[][];
	double yValues[];
	double[] expectedValues;
	double[] thetaValues=new double[19];
	//double theta1=.5,theta2=.5;//,theta3=.5,theta4=.5;
	
	int expectedValuesLength;
	double diff=0;
	
	public MatrixFactorizationNoThread(UserRating u){
		List<MovieRating> mrs=u.getMovieRatings();
		expectedValuesLength=mrs.size();
		yValues=new double[expectedValuesLength];		
		Iterator itr=mrs.iterator();		
		int i=0;
		xValues=new double[expectedValuesLength][equationLength];
		while(itr.hasNext()){
			MovieRating mr=(MovieRating)itr.next();
			//System.out.println(mr);
			yValues[i]=mr.getRating();
			//System.out.print(yValues[i]);
			xValues[i]=mr.getGenre();
			//printGenre(xValues[i]);
			i++;
		}
		assignInitialValues();
		//printEquation();
		findExpectedValues();
		int ppp=0;
		while(!isHypothesysSatisfied() && ppp<10){
			updateThetaValues();
			findExpectedValues();
			//printValues();
			//printEquation();
			ppp++;
		}
	
		
	}
	
	public double[] getFactors(){
		return thetaValues;
	}
	
	/*private void printEquation(){		
		System.out.println(theta1+"+"+theta2+"x+");
	}*/
	
	private void assignInitialValues(){
		for(int i=0;i<equationLength;i++){
			thetaValues[i]=1;
		}
		expectedValues=new double[expectedValuesLength];
	}
	
	private void updateThetaValues(){
		double substractor=0.0;
		for(int i=0;i<equationLength;i++){
			substractor=0.0;
			for(int j=0;j<expectedValuesLength;j++){
				substractor+=(expectedValues[j]-yValues[j])*xValues[j][i];
			}			
			System.out.println("\t"+substractor);
			thetaValues[i]-=((substractor*0.5)/expectedValuesLength);
		}
		
		System.out.println();
		
	}	
	
	private boolean isHypothesysSatisfied(){
		double difffff=diff;
		diff=0;
		for(int i=0;i<expectedValues.length;i++){
			diff+=(expectedValues[i]-yValues[i])*(expectedValues[i]-yValues[i]);
		}		
		
		diff+=diff/(2*expectedValues.length);
		
		//System.out.println("diff is "+diff);
		
		/*if(diff<.5)
		return true;
		*/
		if(difffff-diff<0 && difffff!=0)
			return true;
		
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

	private void printValues(){
		//System.out.println("theta values are");
		for(double x:thetaValues)
			System.out.print("\t"+x);
		
		System.out.println("");
		/*
		//System.out.println("expected values are");
		
		for(double x:expectedValues)
			System.out.print("\t"+x);
		
		System.out.println("");
		
		System.out.println("y values are");
		
		for(double x:yValues)
			System.out.print("\t"+x);
		
		System.out.println("");*/
	}
	
	private void printGenre(double[] xx){
		for(double x:xx){
			System.out.print("\t"+x);
		}
		System.out.println();
	}
	
}
