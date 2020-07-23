import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Priority {
	
	int numberOfProcesses;
	LinkedList<Job> jobs=new LinkedList<Job>();
	
	double avgWaitTime=0;
	
	public Priority(){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter number of jobs");
		numberOfProcesses=scanner.nextInt();
		int i=0;
		
		long jobTime=0;
		int priority;
				
		while(i<numberOfProcesses){
			System.out.println("Enter Time Required to finish job "+ (i+1));
			jobTime=scanner.nextLong();	
			
			System.out.println("Enter Priority for job "+ (i+1));
			priority=scanner.nextInt();	
			
			jobs.add(new Job(System.currentTimeMillis(),jobTime,i,priority));
			i++;
		}
		
		Collections.sort(jobs);
		
		Iterator iterator=jobs.iterator();
		
		while(iterator.hasNext()){
			Job job=(Job)iterator.next();
			System.out.println("Job Number "+job.jobNumber +" Job Priority "+job.priority);
						
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
		TableDisplay display=new TableDisplay(jobs,"Priority");
		display.setSize(800,600);
		display.setVisible(true);
		for(int i=0;i<jobs.size();i++){
			avgWaitTime+=(jobs.get(i).serviceTime-jobs.get(i).arrivalTime)-jobs.get(i).jobTime;
		}
		
		JOptionPane.showMessageDialog(null," Averge Waiting Time is "+avgWaitTime/(jobs.size()));
	}


}
