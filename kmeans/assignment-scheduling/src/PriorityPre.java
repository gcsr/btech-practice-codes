import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class PriorityPre{
	
	int numberOfProcesses;
	LinkedList<Job> jobs=new LinkedList<Job>();
	double avgWaitTime=0;
	long totalTime=0;
	long[] completed;
	
	public PriorityPre(){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter number of jobs");
		numberOfProcesses=scanner.nextInt();
		int i=0;
		
		completed=new long[numberOfProcesses];
		
		long jobTime=0;int priority=0;
		long arrivalTime=0;
				
		while(i<numberOfProcesses){
			
			System.out.println("Enter Arrival Time job "+ (i+1));
			arrivalTime=scanner.nextLong();
			
			System.out.println("Enter Priority "+ (i+1));
			priority=scanner.nextInt();
			
			System.out.println("Enter Time Required to finish job "+ (i+1));
			jobTime=scanner.nextLong();
			totalTime+=jobTime;
			completed[i]=jobTime;
			
			
			jobs.add(new Job(arrivalTime,jobTime,i,priority));
			i++;
		}
		
		
	}
	
	private void printAll(){
		System.out.println();
		System.out.println("Remaining Times");
		for(int i=0;i<jobs.size();i++){
			System.out.print("\t"+completed[i]);
		}
		
		System.out.println();
		
		System.out.println("Waiting Times");	
		
		
		System.out.println();
	}
	
public void schedule(){
		
		long counter=0;
		
		while(true){
			if(allDone())
				break;
			System.out.println(" counter "+counter);
			printAll();
			int result=checkNextJob(counter);
			if(completed[result]>0){
				completed[result]-=1;
				if(completed[result]<=0){
					jobs.get(result).serviceTime=counter+1;				
				}			
				
			}
			
			counter++;
		}
		
		/*Job job;
		int i=0;
		while(i<jobs.size()){
			job=jobs.get(i);
			job.serviceTime+=job.arrivalTime;
			i++;			
		}*/
		
		
		
	}
	
	private boolean allDone(){
		boolean result=true;
		for(int i=0;i<jobs.size();i++){
			if(completed[i]>0)
				return false;		
		}
		
		return true;
	}
	
	private int checkNextJob(long counter){

		int i=0;
		int result =0;
		Job job=null;
		long temp=completed[0];
		
		while(i<jobs.size()){
			job=jobs.get(i);
			if(completed[i]>0){
				if(job.arrivalTime<=counter){
					
					if(jobs.get(i).priority<jobs.get(result).priority || completed[result]<=0){
						result=i;
					}
				}
			}			
			i++;
						
		}
		
		return result;
	}

	
	public void display(){
		PreTableDisplay display=new PreTableDisplay(jobs,"PrePriority");
		display.setSize(800,600);
		display.setVisible(true);
		
		for(int i=0;i<jobs.size();i++){
			avgWaitTime+=(jobs.get(i).serviceTime-jobs.get(i).arrivalTime)-jobs.get(i).jobTime;
		}
		JOptionPane.showMessageDialog(null," Averge Waiting Time is "+avgWaitTime/(jobs.size()));
	}



}
