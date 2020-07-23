package multithreading;

import java.util.concurrent.locks.*;

public class methodbank
{
	private Lock banklock;
	private Condition sufficientfunds;
	
	public methodbank(int n,double inbal)
	{
		accounts=new double[n];
		
		for(int i=0;i<accounts.length;i++)
		{
			accounts[i]=inbal;
		}
		banklock=new ReentrantLock();
		sufficientfunds=banklock.newCondition();
		
	}
	public synchronized void transfer(int from,int to,double amount)throws InterruptedException
	{
		
		while(accounts[from]<amount)
		{
		
		   wait();
		}
		
		System.out.println(Thread.currentThread());
		
		accounts[from]-=amount;
		
		System.out.println("from  "+from+"  to  "+to+"  "+amount);
		accounts[to]+=amount;
		System.out.println("totalbalance "+gettotalbalance());
		notifyAll();
	
	
	
	}
	public synchronized double gettotalbalance()
	{
		double sum=0;
		for(double a:accounts)
		sum+=a;
		
		return sum;
		
	}
	
	public int size()
	{
		return accounts.length;
	}
	
	private double[] accounts;
	
}