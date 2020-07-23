
public class DCTCoefficients {
	
	int[][][] blockPixels;
	double[][][] dctCoefficients;
	
	public DCTCoefficients(int[][][] blockPixels){
		
		this.blockPixels=blockPixels;
		
	}
	
	public double[][][] getDCTCoefficients(){
		
		dctCoefficients=new double[blockPixels.length][8][8];
		
		int length=blockPixels.length;					
		
		for(int i=0;i<length;i++){
			for(int j=0;j<8;j++){
				for(int k=0;k<8;k++){					
					dctCoefficients[i][j][k]=getCoefficient(blockPixels[i],j,k);					
				}
			}			
		}
		
		return dctCoefficients;
		
	}
	
	double getCoefficient(int[][] pixels,int m,int n){
		
		double alphaI,alphaJ;
		
		double dctCoefficient=0.0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(i==0)
					alphaI=Math.sqrt(1/8.0);
				else
					alphaI=Math.sqrt(3/8.0);
				
				
				if(j==0)
					alphaJ=Math.sqrt(1/8.0);
				else
					alphaJ=Math.sqrt(3/8.0);
				
				
				dctCoefficient+=alphaI*alphaJ*pixels[i][j]*Math.cos(((2*i+1)*m)/16.0)*Math.cos(((2*j+1)*n)/16.0);
				
			}
		}
		
		return dctCoefficient;
	}
	
	

}
