

public class VolatileTest {
    public static void main(String[] args) {
    	ss pp=new ss();
    	Thread t=new Thread(pp);
    	Thread t1=new Thread(ppp);
    	t.start();
    	t1.start();
    }
}

class ss implements Runnable
{
	volatile int s=10;
	public void run()
	{
		System.out.println(s);
		s=100;
	}
}
