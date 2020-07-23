package multithreading;

import multithreading.bank;
import multithreading.transferrunnable;


public class unsynchbank
{
	public static void main(String[] gc)
	{
		bank b=new bank(10,100);
		for(int i=0;i<10;i++)
		{
			transferrunnable r=new transferrunnable(b,i,100);
			Thread t=new Thread(r);
			t.start();
		}
	}
}