import multithreading.methodbank;
import multithreading.methodrunnable;


public class methodbanktest
{
	public static void main(String[] gc)
	{
		methodbank b=new methodbank(10,100);
		for(int i=0;i<10;i++)
		{
			methodrunnable r=new methodrunnable(b,i,100);
			Thread t=new Thread(r);
			t.start();
		}
	}
}