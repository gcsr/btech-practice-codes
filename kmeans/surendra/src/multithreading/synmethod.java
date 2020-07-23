package multithreading;

import java.util.concurrent.locks.*;

public class synmethod
{
	private Lock banklock;
	private Condition sufficientfunds;
	
	public synmethod(int n,double inbal)
	{
		accounts=new double[n];
		
		for(int i=0;i<accounts.length;i++)
		{
			accounts[i]=inbal;
		}
		banklock=new ReentrantLock();
		sufficientfunds=banklock.newCondition();
		
	}
	public void transfer(int from,int to,double amount)throws InterruptedException
	{
		banklock.lock();
		try
	{
		while(accounts[from]<amount)
		{
		
		System.out.println(Thread.currentThread()+" is waiting");
		sufficientfunds.await();
		}
		
		System.out.println(Thread.currentThread());
		
		accounts[from]-=amount;
		
		System.out.println("from  "+from+"  to  "+to+"  "+amount);
		accounts[to]+=amount;
		System.out.println("totalbalance "+gettotalbalance());
		sufficientfunds.signalAll();
	
	}
	finally
	{
		banklock.unlock();
	}
	}
	public double gettotalbalance()
	{
		banklock.lock();
		try
	{
		double sum=0;
		for(double a:accounts)
		sum+=a;
		
		return sum;
	}
	finally
	{
		banklock.unlock();
	}	
	}
	
	public int size()
	{
		return accounts.length;
	}
	
	private double[] accounts;
	
}