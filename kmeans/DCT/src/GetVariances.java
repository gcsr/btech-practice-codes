
public class GetVariances {
	
	static public double[] getVariances(double[][][] dctCoefficients){
		double[] varianceValues=new double[dctCoefficients.length];
		for(int i=0;i<dctCoefficients.length;i++){
			varianceValues[i]=getValue(dctCoefficients[i]);
		}
		
		double max=varianceValues[0];
		System.out.println("max is "+max);
		
		for(int i=0;i<varianceValues.length;i++){
			if(max<varianceValues[i])
				max=varianceValues[i];
		}
		
		for(int i=0;i<varianceValues.length;i++){
			varianceValues[i]/=max;
		}
		
				
		return varianceValues;
	
	}

	static private double getValue(double[][] pixels){
		
		double sum=0.d;
		
		
		for(int i=0;i<8;i++){
			
			for(int j=0;j<8;j++){
				
				sum+=pixels[i][j];
			}
		}
		
		//System.out.println("sum is "+sum);
		double mean=sum/64;
		
		sum=0;
		
		for(int i=0;i<8;i++){
			
			for(int j=0;j<8;j++){
				
				sum=sum+((pixels[i][j]-mean)*(pixels[i][j]-mean));
			}
		}
		
		System.out.println(" "+pixels[0][1]+" "+pixels[2][1]);
		
		//return sum;
		System.out.println("sum is "+sum/64);
		return sum/64;
	}

}
