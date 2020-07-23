import java.util.*;

public class Slope {

	/**
	 * @param args
	 */
	int Slope()
	{
		return 5;
	}
	
	public static void main(String[] args) {
		int k=0;
        int input1=100;
        int input2=150;
        for(int i=input1;i<=input2;i++)
        {
        	if(i<100)
        		continue;
        	int j=i;
        	
        	
        	List revarr=new ArrayList<Integer>();
        	
        	while(j>0)
        	{
        		revarr.add(j%10);
        		j=j/10;
        	}
        	
        	List arr=new ArrayList<Integer>();
        	
        	for(int kp=(revarr.size()-1);kp>-1;kp--)
        	{
        		arr.add(((Integer)revarr.get(kp)).intValue());
        	}
        	
        	
        	
        	for(int ip=1;ip<(arr.size()-1);ip++)
        	{
        		if( ( ((Integer)arr.get(ip)).intValue() > ((Integer)arr.get(ip+1)).intValue() )&& ( ((Integer)arr.get(ip)).intValue() > ((Integer)arr.get(ip-1)).intValue() ) )
        		{
        			System.out.println(((Integer)arr.get(ip-1)).intValue()+" "+((Integer)arr.get(ip)).intValue()+" "+((Integer)arr.get(ip+1)).intValue());
        			//k+=((Integer)arr.get(ip)).intValue();
        			k++;
        		}
        		else
        		if( ( ((Integer)arr.get(ip)).intValue() < ((Integer)arr.get(ip+1)).intValue() )&& ( ((Integer)arr.get(ip)).intValue() < ((Integer)arr.get(ip-1)).intValue() ) )
        		{
        			System.out.println(((Integer)arr.get(ip-1)).intValue()+" "+((Integer)arr.get(ip)).intValue()+" "+((Integer)arr.get(ip+1)).intValue());
        			//k+=((Integer)arr.get(ip)).intValue();
        			k++;
        		}

        		
        	}
        }
        System.out.println(k);
		// TODO Auto-generated method stub

	}

}
