import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class LearnC1andC2_2 {
	
	public static final int equationLength=19; 
	double xValues[];
	double yValues[];
	double[] expectedValues;
	//double[] thetaValues=new double[19];
	double theta1=-10,theta2=-.1;//,theta3=.5,theta4=.5;
	double alpha=0;
	int m=0;
	double diff=0;
	double rssi1m=0;
	
	public LearnC1andC2_2(String input,double rssi1m){
		
		String result="";
		this.rssi1m=rssi1m;
		
		try{
		 	FileInputStream fis = new FileInputStream(input);
		 	DataInputStream dis = new DataInputStream(fis);
		 	
		 	while(dis.available()>0){
		 		result+=(dis.readDouble())+" ";
		 	}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		
		System.out.println(result);
		String[] lines=result.split(" ");
		yValues=new double[lines.length/2];
		xValues=new double[lines.length/2];
		m=xValues.length;
		expectedValues=new double[m];
		
		int i=0,j=0,k=0;
		
		for(i=0;i<lines.length;i++){
			
			if(i%2==0){
				yValues[j]=Double.parseDouble(lines[i]);
				j++;
			}
			else{
				xValues[k]=Double.parseDouble(lines[i]);
				k++;
			}
		}
		
		assignInitialValues();
		printEquation();
		findExpectedValues();
		printValues();
		int ppp=0;
		
		while(!isHypothesysSatisfied() && ppp<100){
			updateThetaValues();
			findExpectedValues();
			printValues();
			ppp++;
		}
		
		System.out.println("the required equation is");
		printEquation();
	
		
	}
	
	public LearnC1andC2_2(double xVaues[],double[] yValues,double rssi1m){
		this.rssi1m=rssi1m;
		this.xValues=xValues;
		this.yValues=yValues;
		this.m=yValues.length;
		
		assignInitialValues();
		printEquation();
		findExpectedValues();
		printValues();
		int ppp=0;
		
		while(!isHypothesysSatisfied() && ppp<10){
			updateThetaValues();
			findExpectedValues();
			//printEquation();
			printValues();
			ppp++;
		}
		
		System.out.println("the required equation is");
		printEquation();
	}
	
	
	
	private void printEquation(){		
		System.out.println(theta1+"*x");
	}
	
	private void assignInitialValues(){
		
	}
	
	private void updateThetaValues(){
		
		double sum =0;
		double loopSum=0;	
		
		loopSum=0.0;
		double x=0;
		double y=0;
		for(int j=0;j<m;j++){
			x=0;
			
			System.out.println("x value  is "+xValues[j]);
			y=Math.log10(xValues[j]);
			System.out.println("y is "+y);
			x+=theta1*y;
			x-=yValues[j];
			x+=rssi1m;			
			x*=Math.log10(xValues[j]);
			
			System.out.println("x is "+x);
			
			loopSum+=x;
		}
		
		System.out.println("loop sum is "+loopSum);
		
		
		theta1-=(loopSum*alpha)/(m);
		
	}
	
		
	private boolean isHypothesysSatisfied(){
		
		double difffff=diff;
		diff=0;
		
			
		diff*=0.005;
		
		for(int i=0;i<expectedValues.length;i++){
			diff+=(expectedValues[i]-yValues[i])*(expectedValues[i]-yValues[i]);
		}		
		
		diff+=diff/(2*expectedValues.length);
		
		//System.out.println("diff is "+diff);
		
		if((difffff-diff<0 && difffff!=0)|| diff<1){
			System.out.println("condition satisfied");
			return true;
			
		}	
		
		return false;
	}
	
	private void findExpectedValues(){
		double x=0;
		for(int i=0;i<m;i++){
			expectedValues[i]=0;
			expectedValues[i]=rssi1m+theta1*Math.log10(xValues[i]);			
		}
	}
	
	public static void main(String[] gcs){
		/*double[] xa=new double[10];
		double[] ya=new double[10];
		
		LearnC1andC2 lc12=new LearnC1andC2(xa,ya,3);*/
		
		LearnC1andC2_2 c1c2;
		double[] result;
		
		File f=new File("E:/old laptop/d drive/kmeans/bluetooth/");
		
	/*	System.out.println(f.getAbsolutePath());
		Scanner scanner=new Scanner(System.in);
		double rssi1m=0;
		*/
		String[] listOfFiles=f.list();
		for(String x:listOfFiles){
			if(x.endsWith(".dat")){
				//System.out.println("Enter Rssi value at 1 meter distance for room "+x);
				//rssi1m=scanner.nextDouble();
				c1c2=new LearnC1andC2_2(f.getAbsolutePath()+"/"+x,189);// rssi1m);
				result=c1c2.returnC1andC2();
				System.out.println("for room "+x);					
				System.out.println("C1 is "+result[0]);
				System.out.println("C2 is "+result[1]);
			}
		}
		
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
	
	public double[] returnC1andC2(){
		return new double[]{theta1,theta2};
	}

}
