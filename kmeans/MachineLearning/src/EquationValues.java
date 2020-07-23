
public class EquationValues {
	
	double a,b,c,d;
	
	public static void main(String[] gcs){
				
		new EquationValues();
	}
	
	public EquationValues(){
		
		a=2;
		b=5.6;
		c=4;
		d=5;
		
		double[] xValues=new double[]{2,3,4,5,12,6,8,13,14,20,18,23,25,15,19,33,35,36,1,39};
		
		double[] yValues=new double[xValues.length];
		
		for(int i=0;i<yValues.length;i++){
			yValues[i]=a*xValues[i]+b;
					//a*xValues[i]*xValues[i]*xValues[i]+b*xValues[i]*xValues[i]
					//c*xValues[i]*+d;
		}
		
		System.out.print("{");
		for(int i=0;i<xValues.length;i++){
			System.out.print(xValues[i]+",");
		}
		System.out.print("}");
		
		System.out.println();
		System.out.print("{");
		for(int i=0;i<yValues.length;i++){
			System.out.print(yValues[i]+",");
		}
		System.out.print("}");
		
	}

}
