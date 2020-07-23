
public class Sorting {
	
	public static void main(String[] gcs){
		
		int input1=50;
		int[] input2={1,12,5,111,200,1000,10,9,6,7,4};
		
		int length=input2.length;		
		for(int i=0;i<length-1;i++){
			for(int j=i+1;j<length;j++){
				if(input2[i]>input2[j]){					
					int temp=input2[i];
					input2[i]=input2[j];
					input2[j]=temp;
					
				}
			}
		}
		/*
		for(int g:input2){
			System.out.print(g+" ");
		}*/
		
		int count=0;
		
		int i=0;
		for(;i<length;i++){
			if(input1>input2[i]){
				input1-=input2[i];
				count++;
				
				//System.out.println(input1);
			}	
			else
				break;
		}
		
		//System.out.println("input1 is" +input1);
		i--;
		int temp=i;
		
		while(i<length){
			if((input1+input2[temp])>input2[i])
				i++;
			else
				break;
		}
		
		//System.out.println("i is "+i);
		//System.out.println("temp is "+temp);
		
		input1=input1+input2[temp]-input2[--i];
		//System.out.println("input2 temp is is" +input2[temp]);
		//System.out.println("input1 is" +input1);
		
		while(i>0 && input1>=0 ){
			if(input2[i]-input2[i-1]<=input1)
				input1=input1-input2[i]+input2[i-1];
			
			i--;
		}
		
		
		int[] result=new int[2];
		
		result[0]=count;
		result[1]=input1;
		
		//System.out.println();
		
		/*for(int g:result){
			System.out.print(g+" ");
		}*/
	}
	
	

}
