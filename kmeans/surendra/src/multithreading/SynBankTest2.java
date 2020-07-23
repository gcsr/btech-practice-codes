package multithreading;


import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynBankTest2 {
	public static void main(String[] gc)
	{
		Bank b=new Bank(10,1000);
		for(int i=0;i<10;i++)
		{
			TransferRunnable r=new TransferRunnable(b,i,2000);
			Thread t=new Thread(r);
			t.start();
		}
	}
}


class Bank
{
	private double[] accounts;
	
	public Bank(int n,double inbal)
	{
		accounts=new double[n];
		
		for(int i=0;i<accounts.length;i++)
		{
			accounts[i]=inbal;
		}
		
	}
	public void transfer(int to,int from,double amount)throws InterruptedException
	{
		//object th
		synchronized(this){
				while(accounts[from]<amount)
					wait();
				System.out.println(Thread.currentThread());
				accounts[from]-=amount;
				
				synchronized(this){
					System.out.printf("%10.2f from %d to %d",amount,from,to);
					accounts[to]+=amount;
					//IO
				}
				
				
				
				System.out.println("totalbalance "+gettotalbalance());	
				notifyAll();
		}
		
		
	}
	public synchronized  double gettotalbalance()
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
	
	
}
class TransferRunnable implements Runnable
{
	private Bank bnk;
	int fromaccount;
	private double maxamount;
	public TransferRunnable(Bank b,int from,double max)
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