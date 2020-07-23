
public class MainClass {
	//private String fileName="";
	double lambdaValue=0.1;
	double gammaValue=30.0;
	double netaValue=30.0;
	double betaValue=30.0;
	double learningParameter=0.0005;
	int usersSize;
	int itemFeaturesSize;
	int itemsSize;
	
	double[] result;

	double[][] ratings;
	double[][] calcRatings;
	double[][] latentFeatures;
	double[][] similarity;
	double[][] itemFeatures;
	double[][] trustValues;
	double[][] pInterest;	
	double[][] latentVectors;
	double[] usersAvg;
	double[] ltttl;
	double[] right;
	double[] pix;
	double[] temp;
	
	double[][] itemFeaturesTemp;
	
	public MainClass(double[][] ratings,double[][] latentFeatures,double[][] similarity,
			double[][] itemFeatures,double[][] trustValues){
		usersSize=latentFeatures.length;
		itemFeaturesSize=19;
		itemsSize=ratings[0].length;
		usersAvg=new double[usersSize];
		this.ratings=ratings;
		calcRatings=new double[usersSize][itemsSize];
		this.latentFeatures=latentFeatures;
		printLatentFeatures();
		
		this.similarity=similarity;
		this.itemFeatures=itemFeatures;
		printItemFeatures();
		this.trustValues=trustValues;
		//pInterest=new double[usersSize][itemsSize];
		itemFeaturesTemp=new double[itemsSize][itemFeaturesSize];
	}
	
	
	private double similarityMeasure(){
		
		pix=new double[itemFeaturesSize];
		//double[] similarityTerm=new SimilarityTerm[usersSize];
		double uDiff=0;
		for(int i=0;i<usersSize;i++){
			pix=latentFeatures[i];
			for(int j=0;j<usersSize;j++){
				if(j!=i){
					pix=difference(pix,multiply(similarity[i][j],latentFeatures[j]));
					uDiff+=(multiply(pix,pix));
				}
			}
		}
		return uDiff*gammaValue/2;
	}
	
	
	
	private double trustDiff(){
		pix=new double[itemFeaturesSize];
		//double[] similarityTerm=new SimilarityTerm[usersSize];
		double uDiff=0;
		for(int i=0;i<usersSize;i++){
			pix=latentFeatures[i];
			for(int j=0;j<usersSize;j++){
				if(j!=i){
					pix=difference(pix,multiply(trustValues[i][j],latentFeatures[j]));
					uDiff+=(multiply(pix,pix));
				}
			}
		}
		return uDiff*betaValue/2;
	
	}
	
	
	private double ratingsDiff(){
		
		double secondDiff=0;		
		double firstDiff=0;
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemFeaturesSize;j++){
				secondDiff+=(latentFeatures[i][j]*latentFeatures[i][j]);
			}
		}
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemFeaturesSize;j++){
				secondDiff+=(itemFeatures[i][j]*itemFeatures[i][j]);
			}
		}
		
		secondDiff*=lambdaValue/2;
		
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){
				firstDiff+=(ratings[i][j]-calcRatings[i][j])*(ratings[i][j]-calcRatings[i][j]);
			}
		}
		
		firstDiff/=2;
		
		return (firstDiff);//+secondDiff);
	}
	
	
	
	public void learn(){
		int coint=0;
		calculateRatings();
		while(!isHypothesysSatisfied() && coint<20){
			System.out.println("loop "+coint);
			//printLatentFeatures();
			//displayRatings();
			updateValues();
			findExpectedValues();	
			calculateRatings();			
			//printLatentFeatures();
			coint++;
		}
	}
	
	double diff=0;
	
	private boolean isHypothesysSatisfied(){
		double oldDiff=diff;
		
		diff=0;
		diff+=ratingsDiff()+similarityMeasure()+trustDiff();
		
		System.out.println("difference is "+diff);
		//displayRatings();
		
		if(Double.isNaN(diff))
			return true;
		
		if(oldDiff-diff==0 || diff<10 )//|| oldDiff>diff )
			return true;
		return false;
	}
	
	private void updateValues(){
		latentVectors=latentFeatures;
		for(int i=0;i<usersSize;i++){
			latentVectors[i]=difference(latentVectors[i],multiply(learningParameter, getUserDifferenceTerm(i)));
		}
		
		for(int i=0;i<itemsSize;i++){
			itemFeaturesTemp[i]=updateItemFeatures(i);
		}
		
		latentFeatures=latentVectors;
		for(int i=0;i<itemsSize;i++){
			itemFeatures[i]=difference(itemFeatures[i],multiply(learningParameter,updateItemFeatures(i)));
		}
		//updateRatingsDiff();
	}
	
	private double[] getUserDifferenceTerm(int user){
		return add(updateRatingsDiff(user),add(updateSimilarityDiff(user),updateTrustDiff(user)));//add(updateRatingsDiff(user),updateSimilarityDiff(user));//,));updateTrustDiff(user)
	}
	
	private void findExpectedValues(){
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){
				if(ratings[i][j]!=0){
					calcRatings[i][j]=multiply(latentFeatures[i],itemFeatures[j]);
				}
			}
		}
	}
	
	private double[] updateItemFeatures(int item){
		double[] pppp=new double[itemFeaturesSize];
		for(int i=0;i<itemFeaturesSize;i++){
			double substractor=0.0;
			for(int j=0;j<usersSize;j++){
				substractor+=(calcRatings[j][item]-ratings[j][item])*latentFeatures[j][i];
			}			
			pppp[i]=substractor;
		}		
		
		return pppp;
	}
	
	private double[] updateRatingsDiff(int user){
		
		double[] pppp=new double[itemFeaturesSize];
			for(int i=0;i<itemFeaturesSize;i++){
				double substractor=0.0;
				for(int j=0;j<itemsSize;j++){
					substractor+=(calcRatings[user][j]-ratings[user][j])*itemFeatures[j][i];
				}			
				pppp[i]=substractor;
			}
			return pppp;
	}
	
	private double[] updateTrustDiff(int user){
		
		
		ltttl=new double[itemFeaturesSize];
		
		right=new double[itemFeaturesSize];
		
		
		for(int i=0;i<usersSize;i++){
			if(i!=user)
			ltttl=add(ltttl,multiply(trustValues[user][i],latentFeatures[i]));
		}
		
		for(int i=0;i<usersSize;i++){
			if(i!=user){
				temp=new double[itemFeaturesSize];
				for(int j=0;j<usersSize;j++){
					if(j!=i){
						temp=add(temp,multiply(trustValues[i][j],latentFeatures[j]));
					}
				}
				
				temp=difference(latentFeatures[i],temp);
				right=add(right,multiply(trustValues[i][user],temp));
			}
			
		}
		
		
		ltttl=difference(latentFeatures[user],ltttl);
		
	
		return difference(multiply(betaValue,ltttl),multiply(betaValue,right));
	}
	
	
	private double[] updateSimilarityDiff(int user){
		
		
		ltttl=new double[itemFeaturesSize];
		
		right=new double[itemFeaturesSize];
		
		
		for(int i=0;i<usersSize;i++){
			if(i!=user)
			ltttl=add(ltttl,multiply(similarity[user][i],latentFeatures[i]));
		}
		
		for(int i=0;i<usersSize;i++){
			if(i!=user){
				double[] temp=new double[itemFeaturesSize];
				for(int j=0;j<usersSize;j++){
					if(j!=i){
						temp=add(temp,multiply(similarity[i][j],latentFeatures[j]));
					}
				}	
				
				temp=difference(latentFeatures[i],temp);
				right=add(right,multiply(similarity[i][user],temp));
			}
			
		}
		
		
		ltttl=difference(latentFeatures[user],ltttl);
		
	
		return difference(multiply(gammaValue,ltttl),multiply(gammaValue,right));
	}
	
	
	private double[] multiply(double x, double[] xy){
		int length=xy.length;
		
		result=new double[length];
		for(int i=0;i<length;i++){
			result[i]=xy[i]*x;
		}
		
		return result;
	}
	
	
	
	private double multiply(double[] a ,double[] b){
		int length=a.length;
		double result=0.0;
		
		for(int i=0;i<length;i++){
			result+=a[i]*b[i];
		}
		return result;
	}
	
	private double[] difference(double[] a ,double[] b){
		int length=a.length;
		result=new double[length];
		
		for(int i=0;i<length;i++){
			result[i]=a[i]-b[i];
		}
		return result;
	}
	
	private double[] add(double[] a ,double[] b){
		int length=a.length;
		result=new double[length];
		
		for(int i=0;i<length;i++){
			result[i]=a[i]+b[i];
		}
		return result;
	}
	
	public double[][] getLatentFeatures(){
		return latentFeatures;
	}
	
	public double[][] getItemFeatures(){
		return itemFeatures;
	}
	
	private void calculateRatings(){
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){
				if(ratings[i][j]!=0)
				calcRatings[i][j]=multiply(latentFeatures[i],itemFeatures[j]);
			}
		}
	}
	
	private void calculateExpectedRatings(){
		findAvgs();
		
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){
				if(ratings[i][j]!=0)
				calcRatings[i][j]=usersAvg[i]+multiply(latentFeatures[i],itemFeatures[j]);
			}
		}
	}
	

	
	private void printLatentFeatures(){
		System.out.println("Latent Features");
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemFeaturesSize;j++){
				System.out.print("\t"+latentFeatures[i][j]);
			}
			System.out.println();
		}
	}
	
	private void findAvgs(){
		int count=0;
		double dd=0;
		
		for(int i=0;i<usersSize;i++){
			count=0;
			dd=0;
			for(int j=0;j<itemFeaturesSize;j++){
				if(calcRatings[i][j]!=0){
					count++;
					dd+=calcRatings[i][j];
				}
			}
			if(count!=0)
				usersAvg[i]=dd/count;
		}
	}
	
	private void printItemFeatures(){
		System.out.println("Item Features");
		for(int i=0;i<itemsSize;i++){
			for(int j=0;j<itemFeaturesSize;j++){
				System.out.print("\t"+itemFeatures[i][j]);
			}
			System.out.println();
		}
	}
	
	
	private void displayRatings(){
		System.out.println("");
		System.out.println("");
		System.out.println("Ratings");
		for(int i=0;i<usersSize;i++){
			for(int j=0;j<itemsSize;j++){
				System.out.print("\t"+ratings[i][j]);
			}
			System.out.println();
			
			for(int j=0;j<itemsSize;j++){
				System.out.print("\t"+calcRatings[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public void getUserFeatures(){
		
	}
	
	public double getRating(int user,int movieId){
		double[] userResult=latentFeatures[user];
		double[] itemResult=itemFeatures[movieId];
		double result=0;
		for(int i=0;i<userResult.length;i++){
			result+=userResult[i]*itemResult[i];
		}
		
		return result;
	}

	
}

/*public void getRatings(){
fileName="E:/old laptop/d drive/papers/harish/personalized/latent/latent";
try{
	br = new BufferedReader(new FileReader(fileName));
}catch(Exception ex){
	
}
setMovieRatings();

}

private void setMovieRatings(){

String sCurrentLine;
try{			
	while ((sCurrentLine = br.readLine()) != null) {								
		String[] pp=sCurrentLine.split("\\s+");
		ratings[Integer.parseInt(pp[0])][Integer.parseInt(pp[1])]=Double.parseDouble(pp[2]);				
	}
}catch(Exception ex){
	ex.printStackTrace();
}

}

private synchronized double returnSimilarity(double[] user1,double[] user2){
numerator=0.0;den1=0.0;den2=0.0;

for(int i=0;i<user1.length;i++){
	numerator+=user1[i]*user2[i];
	den1+=user1[i]*user1[i];
	den2+=user2[i]*user2[i];
}

return (numerator)/(Math.sqrt(den1)*Math.sqrt(den2));
}



private void calculateTrustValue(){
double[] rrr=new double[usersSize];

for(int i=0;i<usersSize;i++){
	for(int j=0;j<itemsSize;j++){				
		if(ratings[i][j]!=0)
		rrr[i]++;
	}
}

for(int i=0;i<usersSize;i++){
	for(int j=0;j<usersSize;j++){
		trustValues[i][j]=rrr[j]/(rrr[i]+rrr[j]);
	}
}
}


private void calcullateRatings(){
for(int i=0;i<usersSize;i++){
	for(int j=0;j<itemsSize;j++){
		calcRatings[i][j]=multiply(latentFeatures[i],itemFeatures[j]);
	}
}
}

private void calculateUserSimilarity(){
similarity=similarityObject.getSimilarities();
}

private void calculateUserPInterest(){
for(int i=0;i<usersSize;i++){
	for(int j=0;j<itemsSize;j++){
		pInterest[i][j]=returnSimilarity(latentFeatures[i],itemFeatures[i]);
	}
}
}

private double PIDiff(){
		pix=new double[itemFeaturesSize];
		//double[] similarityTerm=new SimilarityTerm[usersSize];
		double uDiff=0;
		for(int i=0;i<usersSize;i++){
			pix=latentFeatures[i];
			for(int j=0;j<usersSize;j++){
				pix=difference(pix,multiply(trustValues[i][j],latentFeatures[j]));
				uDiff+=(multiply(pix,pix));
			}
		}
		return uDiff*betaValue/2;
	
	}
	
	
	
*/



