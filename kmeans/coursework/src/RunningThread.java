
public class RunningThread implements Runnable{
	
	char whatTo;
	
	RunningThread(char whatTo){
		this.whatTo=whatTo;
	}
	public void run(){
		BinarySemaphore sem=new BinarySemaphore();
		
			if(whatTo=='G')
				sem.writeG();
			else if(whatTo=='H')
				sem.writeH();
			else{
				sem.writeF();
			}
		
	}
}
