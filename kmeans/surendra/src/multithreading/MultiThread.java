package multithreading;



class Type1 extends Thread{
	public void run(){
		System.out.println("thread method");
	}
}

class Type2 implements Runnable{
	
	public Type2(){
		
	}
	public void start(){
		
	}
	public void run(){
		System.out.println("thread method");
	}
	
	/*public void stop(){
		
	}*/
}

public class MultiThread {
	
	public static void main(String[] gcs){
		
		Thread type2=new Thread(new Type2());
		type2.start();
		System.out.println(" main method");
		//Thread t=new Thread(type1);
	}
	//t.
}
