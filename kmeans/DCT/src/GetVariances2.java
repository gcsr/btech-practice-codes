
public class GetVariances2 {
	
	static public double[] getVariances(double[][][] dctCoefficients){
		double[] varianceValues=new double[dctCoefficients.length];
		
		for(int i=0;i<dctCoefficients.length;i++){
			varianceValues[i]=getValue(dctCoefficients[i]);
		}
		
		double max=varianceValues[0];
		double min=varianceValues[0];
		
		
		for(int i=0;i<varianceValues.length;i++){
			if(max<varianceValues[i])
				max=varianceValues[i];
			if(min>varianceValues[i])
				min=varianceValues[i];
		}
		
		System.out.println("max is "+max);
		System.out.println("min is "+min);
		
		for(int i=0;i<varianceValues.length;i++){
			varianceValues[i]/=max;
		}
		
				
		return varianceValues;
	
	}

	static private double getValue(double[][] pixels){
		
		double sum=0.d;
		
		double first=pixels[0][0];
		
		for(int i=0;i<8;i++){
			
			for(int j=0;j<8;j++){
				
				sum+=((pixels[i][j]-first)*(pixels[i][j]-first));
			}
		}
		
		//System.out.println("sum is "+sum);
		double mean=sum/64;
		return mean;
		
	}

}
