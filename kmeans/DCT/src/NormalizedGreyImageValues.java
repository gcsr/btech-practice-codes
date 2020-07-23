
public class NormalizedGreyImageValues {
	
	static public double[] getNormalizedGreyValues(double[][][] blockPixels)
	{
		double[] normalizedValues=new double[blockPixels.length];
		for(int i=0;i<blockPixels.length;i++){
			normalizedValues[i]=getValue(blockPixels[i]);
		}
		
		
		double max=normalizedValues[0];
		
		for(int i=0;i<normalizedValues.length;i++){
			if(max<normalizedValues[i])
				max=normalizedValues[i];
		}
		
		for(int i=0;i<normalizedValues.length;i++){
			normalizedValues[i]/=max;
		}
		
		return normalizedValues;
	}
	
	static private double getValue(double[][] pixels){
		
		double sum=0;
		
		
		for(int i=0;i<8;i++){
			
			for(int j=0;j<8;j++){
				
				sum+=pixels[i][j];
			}
		}
		
		return (sum/64);
		
		
	}

}
