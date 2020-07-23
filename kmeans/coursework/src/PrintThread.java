
public class PrintThread implements Runnable{

	@Override
	public void run() {
		boolean loopCondition=true;
		
		while(loopCondition){
			try{
				wait();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
	}
	
}
