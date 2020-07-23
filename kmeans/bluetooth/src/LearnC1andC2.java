import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class LearnC1andC2 {
	
	public static final int equationLength=19; 
	double xValues[];
	double yValues[];
	double[] expectedValues;
	//double[] thetaValues=new double[19];
	double theta1=-.1,theta2=-.1;//,theta3=.5,theta4=.5;
	double alpha=.003;
	int m=0;
	double diff=0;
	double rssi1m=0;
	
	int maxCount=5000;
	
	double[][] results;
	double[] differences;
	
	int ppp;
	
	public LearnC1andC2(String input,double rssi1m){
		
		String result="";
		this.rssi1m=rssi1m;
		
		System.out.println("input is "+input);
		
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
		differences=new double[maxCount];
		results=new double[maxCount][2];
		
		
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
		ppp=0;
		
		while(!isHypothesysSatisfied() && ppp<maxCount-1){
			updateThetaValues();
			findExpectedValues();
			printValues();
			ppp++;
		}
		
		System.out.println("the required equation is");
		printEquation();
	
		
	}
	
	public LearnC1andC2(double xVaues[],double[] yValues,double rssi1m){
		this.rssi1m=rssi1m;
		this.xValues=xValues;
		this.yValues=yValues;
		this.m=yValues.length;
		
		assignInitialValues();
		printEquation();
		findExpectedValues();
		printValues();
		int ppp=0;
		
		while(!isHypothesysSatisfied() && ppp<maxCount){
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
		System.out.println(theta1+"+"+theta2+"x+");
	}
	
	private void assignInitialValues(){
		
	}
	
	private void updateThetaValues(){
		
		double sum =0;
		double loopSum=0;
		
		
		loopSum=0.0;
		double x=0;
		
		for(int j=0;j<m;j++){
			loopSum+=rssi1m;			
			x=theta1*theta1*Math.sin(45*xValues[j])*Math.sin(45*xValues[j])*Math.log10(xValues[j]);
			x+=theta2*Math.log10(xValues[j]);		
			loopSum+=x;
			loopSum-=yValues[j];
			loopSum*=(2*theta1*Math.sin(45*xValues[j])*Math.sin(45*xValues[j])*Math.log10(xValues[j]));
		}
		
		
		theta1-=(loopSum*alpha)/(m);
		
		results[ppp][0]=theta1;
		
		loopSum=0.0;
		
		for(int j=0;j<m;j++){
			
			loopSum+=rssi1m;
			x=theta1*theta1*Math.sin(45*xValues[j])*Math.sin(45*xValues[j])*Math.log10(xValues[j]);
			x+=theta2*Math.log10(xValues[j]);	
			loopSum+=x;			
			loopSum-=yValues[j];
			loopSum*=Math.log10(xValues[j]);
			
		}
		
		
		theta2-=(loopSum*alpha)/m;;
		
		results[ppp][1]=theta2;	
		
		
	}
	
	
	private boolean isHypothesysSatisfied(){
		
		double difffff=diff;
		diff=0;
		
			
		diff*=0.005;
		
		for(int i=0;i<expectedValues.length;i++){
			diff+=(expectedValues[i]-yValues[i])*(expectedValues[i]-yValues[i]);
		}		
		
		diff+=diff/(2*expectedValues.length);
		
		System.out.println("diff is "+diff);
		
		differences[ppp]=diff;
		
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
			x=0;			
			x=theta1*Math.sin(45*xValues[i]);
			x*=x;
			x*=Math.log10(xValues[i]);
			//System.out.println(x);
			expectedValues[i]+=rssi1m+x+10*theta2*Math.log10(xValues[i]);			
		}
	}
	
	public static void main(String[] gcs){
		/*double[] xa=new double[10];
		double[] ya=new double[10];
		
		LearnC1andC2 lc12=new LearnC1andC2(xa,ya,3);*/
		
		LearnC1andC2 c1c2;
		double[] result;
		
		//File f=new File("F:/final/");
		File f=new File("E:/old laptop/d drive/kmeans/bluetooth/");
		
		System.out.println(f.getAbsolutePath());
		Scanner scanner=new Scanner(System.in);
		double rssi1m=0;
		
		
		Map<String,double[]> values=new HashMap<String,double[]>();
		
		String[] listOfFiles=f.list();
		double[] resultWithRssi=new double[3];
		for(String x:listOfFiles){
			if(x.endsWith(".dat")){
				System.out.println("Enter Rssi value at 1 meter distance for room "+x);
				rssi1m=scanner.nextDouble();
				c1c2=new LearnC1andC2(f.getAbsolutePath()+"/"+x, rssi1m);
				result=c1c2.returnC1andC2();
				resultWithRssi[0]=result[0];
				resultWithRssi[1]=result[1];
				resultWithRssi[2]=rssi1m;		
				
				
				String roomId=x.substring(0,x.indexOf(".dat"));
				
				System.out.println("room id is "+roomId);
				
				values.put(roomId,resultWithRssi);
				System.out.println("for room "+roomId);					
				System.out.println("C1 is "+result[0]);
				System.out.println("C2 is "+result[1]);
				//double[] c1c2Values=values.get("1");
				//System.out.println(c1c2Values[0]+" "+c1c2Values[1]);
				
			}
			
			
		}
		//ResultsServer cpppp=new ResultsServer(values);
		
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
		
		/*double min=differences[0];
		int minIndex=0;
		
		int i=ppp/2;
		while(i<ppp){
			if(differences[i]<min){
				minIndex=i;
				min=differences[i];
			}
			i++;
				
		}
		
		System.out.println("diffference is "+differences[minIndex]);
		return results[minIndex];*/
		return new double[]{theta1,theta2};//results[minIndex];
	}

}
