package sam;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	
	public  static void main(String[] gcs){
		//int sol=kDifference(new int[]{5,1,5,3,2,4},2);
		//System.out.println(sol);
		System.out.println(isPossible(1,4,5,9));
	}
	
	public static String isPossible(int a,int b,int c, int d){
		int sum=a+b;
		
		System.out.println(a+" "+b+" "+c+" "+d);
		
		if(a==c && b==d){
			System.out.println("yes");
			return "Yes";
		}
		else if(a==d && b==c){
			return "Yes";
		}
		
		if((a>c && a>d )|| (b>c && b>d)){
			//return false;
		}else{
			if(isPossible(a,sum,c,d).equals("Yes") ||isPossible(sum,b,c,d).equals("Yes"))
				return "Yes";
		}		
		
		return "No";
		
	}
	
	
		static int kDifference(int[] a, int k) {
	        int[] newArray;
	        List<Integer> list=new ArrayList<Integer>();
	        
	        
	        for(int i=0;i<a.length;i++){
	        	if(!list.contains(a[i])){
	        		System.out.println(a[i]);
	        		list.add(a[i]);
	        	}
	            
	        }
	        
	        if(!list.contains(k)){
        		list.add(k);
        	}
	        //newArray[a.length]=k;
	        newArray=new int[list.size()];
	        int length=newArray.length;
	        for(int i=0;i<length;i++){
	        	newArray[i]=list.get(i);
	        }
	        
	        int counter=0;
	        for(int i=0;i<length;i++){
	            for(int j=0;j<length;j++){
	                if(i!=j){
	                	//System.out.println((newArray[i]-newArray[j]));
	                    if((newArray[i]-newArray[j])==k)
	                        counter++;
	                }else
	                	continue;
	            }
	        }
	        
	        return counter;

	    }
	

}
