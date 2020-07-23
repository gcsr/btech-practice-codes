package multithreading;


class SimpleThread extends Thread{
	int threadNo;
	
	public SimpleThread(int threadNo){
		this.threadNo=threadNo;
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println("Thread "+threadNo);
			/*try{
				Thread.currentThread().sleep(1000);
			}catch(Exception ex){
				
			}*/
		}
	}
}

public class MultiThreadExamples {
	public static void main(String[] gcs){
		SimpleThread thread1=new SimpleThread(1);
		SimpleThread thread2=new SimpleThread(2);
		thread1.start();
		thread2.start();
		System.out.println("Main method");
		
	}
}

/*
 * Thread 2
 * Thread 1
 * Thread 1
 * Thread 1
 * Thread 2
 * 
 */

















