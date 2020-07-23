import java.util.Random;
import java.util.Scanner;


public class AETG {
	public static void main(String[] gcs){
		System.out.println("For this we need 3 inputs factors,levels, number of test suites");
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("enter number of levels");
		
		int levels=scanner.nextInt();
		
		System.out.println("enter number of factors");
		
		int factors=scanner.nextInt();
		
		System.out.println("levels");
		for(int i=0;i<levels;i++){
			System.out.print(i+"\t");
		}
		
		System.out.println("factors");
		for(int i=levels;i<(levels+factors);i++){
			System.out.print(i+"\t");
		}
		
		
		System.out.println();
		System.out.println("enter number of test suites");
		
		System.out.println();
		
		long testSuites=scanner.nextLong();
		
		if(! (testSuites>Integer.MAX_VALUE)){
			int testSuites1=(int)testSuites;
			int[][] results=new int[testSuites1][2];
			long[] times=new long[50];
			
			long first,next;
			int k=0,j=0,l=0;
			
			Random r=new Random();
			int firstLow=0, firstHigh=levels-1;
			int secondLow=levels,secondHigh=(levels+factors-1);
			
			
			for(k=0;k<50;k++){
				first=System.currentTimeMillis();
				for(j=0;j<testSuites;j++){				
					results[j][0]= r.nextInt(firstHigh-firstLow) + firstLow;
					results[j][1]= r.nextInt(secondHigh-secondLow) + secondLow;
				}
				next=System.currentTimeMillis();
				times[k]=next-first;	
				//System.out.println(times[k]);
			}
			
			System.out.println("Do you want see final test suites 1 for yes 0 for no");
			int DYST=scanner.nextInt();
			
			printResults(results,times,DYST);
		}
		else{
			long[] times=new long[50];
			
			long first,next;
			int k=0,j=0,l=0;
			
			Random r=new Random();
			int firstLow=0, firstHigh=levels-1;
			int secondLow=levels,secondHigh=(levels+factors-1);
			
			int s,sp;
			for(k=0;k<50;k++){
				first=System.currentTimeMillis();				
				for(long p=0;p<testSuites;p++){									
						s= r.nextInt(firstHigh-firstLow) + firstLow;
						sp= r.nextInt(secondHigh-secondLow) + secondLow;					
				}
				next=System.currentTimeMillis();
				times[k]=next-first;
				//System.out.println(times[k]);
			}
			
			
			printResults2(times);
		}
		
	}
	
	public static void printResults2(long[] times){
		
		
		long min,max,avg=0;
		min=times[0];
		max=times[0];
		
		
		for(int i=0;i<times.length;i++){
			avg+=times[i];
			
			if(min>times[i])
				min=times[i];
			if(max<times[i])
				max=times[i];
		}
		
		avg=(avg/times.length);
		
		System.out.println("nubmer of test cases : "+times.length);
		System.out.println("Best Time : "+min);
		System.out.println("Worst Time : "+max);
		System.out.println("Average Time : "+avg);
	}

	
	
	public static void printResults(int[][] results,long[] times,int DYST){
		if(DYST==1){
			for(int i=0;i<results.length;i++){
				for(int j=0;j<results[0].length;j++){
					System.out.print(results[i][j]+"\t");
				}
				System.out.println();
			}
		}
		
		long min,max,avg=0;
		min=times[0];
		max=times[0];
		
		
		for(int i=0;i<times.length;i++){
			avg+=times[i];
			
			if(min>times[i])
				min=times[i];
			if(max<times[i])
				max=times[i];
		}
		
		avg=(avg/times.length);
		
		System.out.println("nubmer of test cases : "+times.length);
		System.out.println("Best Time : "+min);
		System.out.println("Worst Time : "+max);
		System.out.println("Average Time : "+avg);
	}
}
