
public class GradientDecent {	
	double xValues[]=new double[]{2.0,3.0,4.0,5.0,12.0,6.0,8.0,13.0,14.0,20.0,18.0,23.0,25.0,15.0,19.0,33.0,35.0,36.0,1.0,39.0};
	double yValues[]=new double[]{9.6,11.6,13.6,15.6,29.6,17.6,21.6,31.6,33.6,45.6,41.6,51.6,55.6,35.6,43.6,71.6,75.6,77.6,7.6,83.6};
	double[] expectedValues=new double[xValues.length];
	
	double theta1=.5,theta2=.5;//,theta3=.5,theta4=.5;	
	
	public GradientDecent(){
		assignInitialValues();
		printEquation();
		findExpectedValues();
		int ppp=0;
		while(!isHypothesysSatisfied()){
			updateThetaValues();
			findExpectedValues();
			printEquation();
			ppp++;
		}
		
		System.out.println("the required equation is");
		printEquation();
	}
	
	private void printEquation(){
		
		System.out.println(theta1+"+"+theta2+"x+");//+theta3+"xx+"+theta4+"xxx");
	}
	
	private void assignInitialValues(){
		double theta1=.5;//,theta2=.5,theta3=.5,theta4=.5;
	}
	
	private void updateThetaValues(){
		double sum=0;
		for(int i=0;i<yValues.length;i++){
			sum+=(expectedValues[i]-yValues[i]);
		}
		sum/=yValues.length;
		theta1=theta1-0.00005*(sum);
		
		sum=0;
		
		for(int i=0;i<yValues.length;i++){
			sum+=(expectedValues[i]-yValues[i])*xValues[i];
		}
		sum/=yValues.length;
		theta2=theta2-0.00005*(sum);
		
		/*sum=0;
		for(int i=0;i<yValues.length;i++){
			sum+=(expectedValues[i]-yValues[i])*(xValues[i]*xValues[i]);
		}
		sum/=yValues.length;
		theta3=theta3-0.5*(sum);
		
		
		sum=0;
		for(int i=0;i<yValues.length;i++){
			sum+=(expectedValues[i]-yValues[i])*(xValues[i]*xValues[i]*xValues[i]);
		}
		sum/=yValues.length;
		theta4=theta4-0.5*(sum);
		
		
		*/
	}
	
	
	private void findCostFunctionValue(){
		
	}
	
	private boolean isHypothesysSatisfied(){
		double diff=0;
		for(int i=0;i<expectedValues.length;i++){
			diff+=(expectedValues[i]-yValues[i])*(expectedValues[i]-yValues[i]);
		}		
		
		diff+=diff/(2*expectedValues.length);
		
		System.out.println("diff is "+diff);
		
		if(diff==0)
		return true;
		
		
		return false;
	}
	
	private void findExpectedValues(){
		for(int i=0;i<xValues.length;i++){
			expectedValues[i]=theta1+theta2*xValues[i];//+theta3*(xValues[i]*xValues[i])+theta4*(xValues[i]*xValues[i]*xValues[i]);
		}
	}
	
	public static void main(String[] gcs){
		new GradientDecent();
	}

}
