
public class Job implements Comparable{
	long arrivalTime,serviceTime,waitingTime,jobTime;
	int priority,jobNumber=0;
	
	public Job(){
		
	}
	
	
	
	public Job(long arrivalTime, long jobTime,int jobNumber){
		this.arrivalTime=arrivalTime;
		this.jobTime=jobTime;
		this.jobNumber=jobNumber;
		//this.arrivalTime=arrivalTime;
	}
	
	public Job(long arrivalTime, long jobTime,int jobNumber,int priority){
		this.arrivalTime=arrivalTime;
		this.jobTime=jobTime;
		this.jobNumber=jobNumber;
		this.priority=priority;
		//this.arrivalTime=arrivalTime;
	}
	
	
	
	public Job(long arrivalTime,long jobTime,long serviceTime, int priority,int jobNumber){
		this.arrivalTime=arrivalTime;
		this.jobTime=jobTime;
		this.priority=priority;
		this.serviceTime=serviceTime;
		this.jobNumber=jobNumber;
	}
	
	public Job(long arrivalTime,int priority,long serviceTime,int jobNumber){
		this.priority=priority;
		this.arrivalTime=arrivalTime;
		this.serviceTime=serviceTime;
		this.jobNumber=jobNumber;
	}
	
	
	public void setServiceTime(long serviceTime){
		this.serviceTime=serviceTime;
	}



	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		
		Job comp=(Job)arg0;
		if(this.priority>comp.priority)			
		return 10;
		
		else if(this.priority==comp.priority){
			if(this.arrivalTime>comp.arrivalTime)
				return 1;
			else if(this.arrivalTime<comp.arrivalTime)
				return -4;
			else {
				if(this.jobTime>comp.jobTime)
					return 4;
				else
					return -4;
			}
		}
		
		else
			return -4;
	}
}
