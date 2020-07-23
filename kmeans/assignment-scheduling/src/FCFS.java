import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class FCFS {
	
	int numberOfProcesses;
	LinkedList<Job> jobs=new LinkedList<Job>();
	double avgWaitTime=0;
	
	public FCFS(){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter number of jobs");
		numberOfProcesses=scanner.nextInt();
		int i=0;
		
		long jobTime=0;
		long arrivalTime=0;
		
		while(i<numberOfProcesses){
			System.out.println("Enter Time Required to finish job "+ (i+1));
			jobTime=scanner.nextLong();
			System.out.println("Enter Arrival Time for job "+ (i+1));
			arrivalTime=scanner.nextLong();
			jobs.add(new Job(arrivalTime,jobTime,i));
			i++;
		}
		
		Collections.sort(jobs);
		
		Iterator iterator=jobs.iterator();
		
		while(iterator.hasNext()){
			Job job=(Job)iterator.next();
			System.out.println(job.jobNumber);
			System.out.println(job.jobTime);
			
		}
		
	}
	
	
	
	public void schedule(){
		
		Iterator iterator=jobs.iterator();
		int timeElapsed=0;
		
		int jjj=0;
		while(iterator.hasNext()){
			
			Job job=(Job)iterator.next();
			timeElapsed+=job.jobTime;
			job.setServiceTime(job.arrivalTime+timeElapsed);			
			jjj++;
		}		
	}
	
	
	public void display(){
		TableDisplay display=new TableDisplay(jobs,"FCFS");
		display.setSize(800,600);
		display.setVisible(true);
		for(int i=0;i<jobs.size();i++){
			avgWaitTime+=(jobs.get(i).serviceTime-jobs.get(i).arrivalTime)-jobs.get(i).jobTime;
		}
		JOptionPane.showMessageDialog(null," Averge Waiting Time is "+avgWaitTime/(jobs.size()));
	}
	
}
