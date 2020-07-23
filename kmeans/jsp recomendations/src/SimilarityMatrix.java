

public class SimilarityMatrix {
	
	private static synchronized double returnSimilarity(double[] user1,double[] user2){
		double numerator=0.0,denominator=0.0;		
		double den1=0.0,den2=0.0;	
		
		for(int i=0;i<user1.length;i++){
			numerator+=user1[i]*user2[i];
			den1+=user1[i]*user1[i];
			den2+=user2[i]*user2[i];
		}
		
		
		if((Math.sqrt(den1)*Math.sqrt(den2))==0)
			return 0;
		return (numerator)/(Math.sqrt(den1)*Math.sqrt(den2));
	}
	
	
	public static synchronized double[][] getSimilaritiesMatrix(double[][] user1,double[][] user2){
			
		int usersSize=user1.length;
		
		double[][] similarityMatrix=new double[usersSize][usersSize];
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<usersSize;j++){
				similarityMatrix[i][j]=returnSimilarity(user1[i],user2[j]);
			}
		}
		
		for(int i=0;i<usersSize;i++){
			similarityMatrix[i]=getNormalizedValues(similarityMatrix[i]);
		}
		
		
		
		return similarityMatrix;
	}
	
	public static double[] getNormalizedValues(double[] lll){
		double sum=0.0;
		double[] result=lll;
		
		for(double s:lll)
			sum+=s;
		
		if(sum==0)
			return result;
		else{
			for(int i=0;i<lll.length;i++){
				result[i]=result[i]/sum;
			}
			
			return result;
		}
			
	}
	
	
}
