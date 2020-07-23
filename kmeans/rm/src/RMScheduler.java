/********************************************************************************************
 * Author:Sindhu
 * This is basic RM-sheduler wriiten which preempts tasks by its period
 * ****************************************************************************************/


import java.util.Scanner;

public class RMScheduler {
	public static void main(String[] args){
		System.out.println("Enter the number of tasks");
		Scanner scanner=new Scanner(System.in);
		
		int n=scanner.nextInt();
		int[] periodTimes=new int[n];
		int[] exeTimes=new int[n];
		String[] taskNames=new String[n];
		int i=0;
		
		while(i<n){
			System.out.println("Enter Task "+(i+1)+" Details");
			System.out.println("Task Name:");
			taskNames[i]=scanner.next();
			System.out.println("Task Execution Time:");
			exeTimes[i]=scanner.nextInt();
			System.out.println("Task Period Time:");
			periodTimes[i]=scanner.nextInt();
			i++;
		}
		
		if(!checkForRM(exeTimes,periodTimes,1)){
			System.out.println(" Given Tasks are not RM-schedulable");
			return;
		}
		
		/*if(!isSchedulable(exeTimes,periodTimes)){
			System.out.println(" Given Tasks are not RM-schedulable");
			return;
		}*/
		
		int totalExecutionTime=0;
		int totalPeriodTime=0;
		
		
		int[] copy=periodTimes.clone();
		
		int[] preemption=findOrder(copy);
		
		for(i=0;i<n;i++){
			//System.out.print(periodTimes[i]+"\t");
			totalExecutionTime+=exeTimes[preemption[i]];
			totalPeriodTime+=periodTimes[preemption[i]];
			if(totalExecutionTime>totalPeriodTime)
				break;
		}
		
		if(i==n){
			
			System.out.println("RM-schedule is");
			for(i=0;i<n;i++){
				System.out.print(taskNames[preemption[i]]+"\t");		
			}
			System.out.println();

		}else{
			System.out.println(" There is no valid RM-schedule");
			return;
		}
		
		
	}
	
	static int[] findOrder(int[] periodTimes){
	
		
		int length=periodTimes.length;
		int[] result=new int[length];
		
		
		
		for(int i=0;i<length;i++){
			result[i]=i;			
		}
		
		/*System.out.println("before");
		for(int i=0;i<length;i++){
			System.out.print(periodTimes[i]+"\t");
		}
		System.out.println();
		
		for(int i=0;i<length;i++){
			System.out.print(result[i]+"\t");
		}
		System.out.println();
		
		*/
		for(int i=0;i<length-1;i++){
			for(int j=i;j<length;j++){
				if(periodTimes[i]>periodTimes[j]){
					int temp=periodTimes[i];
					periodTimes[i]=periodTimes[j];
					periodTimes[j]=temp;
					
					temp=result[i];
					result[i]=result[j];
					result[j]=temp;
				}
			}
		}
		
		
		/*System.out.println("after");
		for(int i=0;i<length;i++){
			System.out.print(periodTimes[i]+"\t");
		}
		System.out.println();
		
		for(int i=0;i<length;i++){
			System.out.print(result[i]+"\t");
		}
		System.out.println();
		*/
		
		return result;
		
	}
	
	
	
	static boolean isSchedulable(int[] exeTimes,int[] periodTimes){
		int length=exeTimes.length;
		
		int i=0;
		double result=0;
		
		while (i<length){
			result+=(exeTimes[i]*1.0/periodTimes[i]);
			i++;
		}
		
		
		double result1=1.0*length*(Math.pow(2, 1.0/length)-1);
		
		System.out.println("left is "+result+" right is "+result1);
		if(result<result1)
			return true;
		else
			return false;		
	}
	
	static boolean checkForRM(int[] exeTimes,int[] periodTimes,double value){
		
		double result=0;
		
		int length=exeTimes.length;
		int i=0;
		while (i<length){
			result+=(exeTimes[i]*1.0/periodTimes[i]);
			i++;
		}
		if (result>value) return false;
		else return true;
		
	}
}
