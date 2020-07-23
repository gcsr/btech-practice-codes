import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class RoundRobin {
	
	int numberOfProcesses;
	LinkedList<Job> jobs=new LinkedList<Job>();
	double avgWaitTime=0;
	int quantum=0;
	int[] waitingTime;
	boolean[] checks;
	long[] jobTimes;
	
	public RoundRobin(){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter number of jobs");
		numberOfProcesses=scanner.nextInt();
		int i=0;
		
		long jobTime=0;
		
		while(i<numberOfProcesses){
			System.out.println("Enter Time Required to finish job "+ (i+1));
			jobTime=scanner.nextLong();
			jobs.add(new Job(System.currentTimeMillis(),jobTime,i));
			i++;
		}		
		
		System.out.println("Enter Time Slot");
		
		quantum=scanner.nextInt();
		
		//Collections.sort(jobs);
		
		Iterator iterator=jobs.iterator();
		
		while(iterator.hasNext()){
			Job job=(Job)iterator.next();
			System.out.println(job.jobNumber);
			System.out.println(job.jobTime);
			
		}
		
	}
	
	
	
	public void schedule(){
		
		Iterator iterator=jobs.iterator();
		int count=0;
		
		jobTimes=new long[jobs.size()];
		
		int pkl=0;
		while(iterator.hasNext()){
			Job jb=(Job)iterator.next();	
			jobTimes[pkl]=jb.jobTime;
			pkl++;
		}
		
		System.out.println("number of loop iterations is "+count);
		
		int i=0;
		waitingTime=new int[jobs.size()];
		checks=new boolean[jobs.size()];
		
		int looper=0;
		
		printAll();
		int temp=quantum;
		while(true){
			quantum=temp;
			//System.out.println(" in while loop");
			
			if(checks[looper]==true){
				looper++;
				looper%=(jobs.size());
				continue;
			}
			
			else{
				if(jobTimes[looper]>=quantum)
				jobTimes[looper]-=quantum;
				else{
					quantum=(int)jobTimes[looper];
					jobTimes[looper]-=quantum;
				}
				if(jobTimes[looper]<=0)
					checks[looper]=true;
				updateTimes(looper);				
			}			
			
			i++;
			
			looper++;
			
			looper%=(jobs.size());
			printAll();
			
			if(allDone()){
				break;
			}
			
		}
		
		double dd=0;
		for(int x:waitingTime)
			dd+=x;
		
		JOptionPane.showMessageDialog(null," Averge Waiting Time is "+dd/(jobs.size()));
	}
	
	
	private boolean allDone(){
		boolean result=true;
		for(int i=0;i<jobs.size();i++){
			if(checks[i]==false)
				return false;		
		}
		
		return true;
	}
	
	private void updateTimes(int left){
		for(int i=0;i<jobs.size();i++){
			if(i==left || checks[i]== true );
			else{
				waitingTime[i]+=quantum;
			}
		}
	}
	
	private void printAll(){
		System.out.println();
		System.out.println("Remaining Times");
		for(int i=0;i<jobs.size();i++){
			System.out.print("\t"+jobTimes[i]);
		}
		
		System.out.println();
		
		System.out.println("Waiting Times");
		
		
		for(int i=0;i<jobs.size();i++){
			System.out.print("\t"+waitingTime[i]);
		}
		
		System.out.println();
	}
	public void display(){
		TableDisplay display=new TableDisplay(jobs,"RoundRobin");
		display.setSize(800,600);
		display.setVisible(true);
		JOptionPane.showMessageDialog(null," Averge Waiting Time is "+avgWaitTime/(jobs.size()));
	}
	
}
