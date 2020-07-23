import java.io.File;


public class UserSimilarity {
	String fileName="E:/old laptop/d drive/papers/harish/personalized/latent/latent";
	
	double[][] userData;
	double[][] similarityMatrix;
	int usersSize=0;
	
	public static void main(String[] gcs){
		//new UserSimilarity();
	}
	public UserSimilarity(double[][] userData){
		usersSize=userData.length;
		this.userData=userData;
		similarityMatrix=new double[usersSize][usersSize];
		//loadFiles();
		calculateSimilarities();
		//printSimilarityMatrix();
	}	
	
	double numerator=0.0,denominator=0.0;
	
	double den1=0.0,den2=0.0;
	
	private synchronized double returnSimilarity(double[] user1,double[] user2){
		numerator=0.0;den1=0.0;den2=0.0;
		
		for(int i=0;i<user1.length;i++){
			numerator+=user1[i]*user2[i];
			den1+=user1[i]*user1[i];
			den2+=user2[i]*user2[i];
		}
		
		return (numerator)/(Math.sqrt(den1)*Math.sqrt(den2));
	}
	
	private void loadFiles(){
		
		for(int i=0;i<usersSize;i++){
			userData[i]=DataReadng.readData(fileName+i+".dat");
		}
		
	}
	
	private void calculateSimilarities(){
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<usersSize;j++){
				similarityMatrix[i][j]=returnSimilarity(userData[i],userData[j]);
			}
		}
	}
	
	private void printSimilarityMatrix(){
		for(int i=0;i<1;i++){
			for(int j=942;j<usersSize;j++){
				System.out.print("\t"+similarityMatrix[i][j]);
			}
			System.out.println();
		} 
		
		
		for(int i=942;i>941;i--){
			for(int j=0;j<1;j++){
				System.out.print("\t"+similarityMatrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public double[][] getSimilarities(){
		return similarityMatrix;
	}
	
	public double[][] getLatentFeatures(){
		return userData;
	}
	
}
