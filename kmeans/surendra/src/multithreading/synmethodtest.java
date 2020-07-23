package multithreading;

import multithreading.synbank;
import multithreading.syntransferrunnable;


public class synmethodtest
{
	public static void main(String[] gc)
	{
		synmethod b=new synmethod(10,100);
		for(int i=0;i<10;i++)
		{
			methodrunnable r=new syntransferrunnable(b,i,100);
			Thread t=new Thread(r);
			t.start();
		}
	}
}