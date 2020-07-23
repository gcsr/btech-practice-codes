import java.util.Scanner;


public class Scheduler {
	
	public static void main(String[] gcs){
		System.out.println("Select Scheduler");
		Scanner scanner=new Scanner(System.in);
		
		//System.out.println("");
		
		
		while(true){
		
			System.out.println("\n option 1: FCFS \n option 2: SJF\n option 3: Priority\n option "
					+ "4: Round Robin\n option 5: Premeptive SJF \n option 6: Premptive Priority \n other : exit");
			int option=scanner.nextInt();
			
			
			switch(option){
				case 1: FCFS fcfs=new FCFS();
						fcfs.schedule();
						fcfs.display();
						break;
					 
				case 2: 
						SJF sjf=new SJF();
						sjf.schedule();
						sjf.display();
						break;
				case 3: 				
						Priority priority=new Priority();
						priority.schedule();
						priority.display();
						break;
						
				case 4: 
						RoundRobin robin=new RoundRobin();
						robin.schedule();
						break;
				case 5: 
						SJFPre sjfpre=new SJFPre();
						sjfpre.schedule();
						sjfpre.display();
						break;
						
				case 6: 
						PriorityPre pripre=new PriorityPre();
						pripre.schedule();
						pripre.display();
						break;
					
				default : System.out.println("You have chosen to exit");
					  	  System.out.println("System is exiting");
					  	  System.exit(1);
			}		
		}
		
		
	
	}

}
