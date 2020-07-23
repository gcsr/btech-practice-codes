package multithreading;


public class transferrunnable implements Runnable
{
	private bank bnk;
	int fromaccount;
	private double maxamount;
	public transferrunnable(bank b,int from,double max)
	{
		bnk=b;
		fromaccount=from;
		maxamount=max;
	}
	public void run()
	{
		try
		{
			while(true)
			{
				
				
				int toaccount=(int)(bnk.size()*Math.random());
				double amount=maxamount*Math.random();
				bnk.transfer(fromaccount,toaccount,amount);
				Thread.sleep((int)(1000*Math.random()));
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("era");
			
		}
	}
}