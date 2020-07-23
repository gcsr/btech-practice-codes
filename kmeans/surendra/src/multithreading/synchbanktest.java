package multithreading;

import multithreading.synbank;
import multithreading.syntransferrunnable;


public class synchbanktest
{
	public static void main(String[] gc)
	{
		synbank b=new synbank(10,100);
		for(int i=0;i<10;i++)
		{
			syntransferrunnable r=new syntransferrunnable(b,i,100);
			Thread t=new Thread(r);
			t.start();
		}
	}
}