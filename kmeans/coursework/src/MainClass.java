
public class MainClass {
	public static void main(String[] gcs){
		
		RunningThread pt=new RunningThread('H');
		Thread t=new Thread(pt);
		t.start();
		
		pt=new RunningThread('F');
		t=new Thread(pt);
		t.start();
		
		pt=new RunningThread('G');
		t=new Thread(pt);
		t.start();
		
		try{
			Thread.currentThread().sleep(30000);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		System.exit(1);
		
	}
}
