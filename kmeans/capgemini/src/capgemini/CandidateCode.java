package capgemini;

public class CandidateCode 
{ 
	
	static int[] medals;
    public static int DistributingMedals(int input1,int[] input2,int[] input3,int[] input4,int input5)
    {
        //Write code here
    	if(input2.length!=input1||input3.length!=input1||input4.length!=input1)
    		return -1;
    	int max=-100;
    	int min=1000000;
    	for(int i=0;i<input1;i++){
    		if(input3[i]<min)
    			min=input3[i];
    		
    		if(input4[i]>max)
    			max=input4[i];
    	}
    	
    	int length=max-min+1;
    	
    	medals=new int[length];
    	
    	for(int i=0;i<input1;i++){
    		int first=input3[i]-min;
    		int last=input4[i]-min;
    		
    		while(first<=last && input2[i]<=100 && input2[i]>=1 && last<1000000 && first>=0){
    			medals[first]+=input2[i];
    			first++;    			
    		}
    	}
    	
    	int result=-1;
    	int sum=0;
    	for(int i=0;i<length;i++){
    		System.out.println(medals[i]);
    		sum+=medals[i];
    		if(sum>input5){
    			result=i+min;
    			break;
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] gcs){
    	int result=DistributingMedals(4,new int[]{15,22,33,14},new int[]{6,6,3,4},new int[]{10,11,12,13},234);
    	//int result=DistributingMedals(3,new int[]{1,4,5},new int[]{1,2,3},new int[]{10,6,5},40);
    	System.out.println(result);
    }
}