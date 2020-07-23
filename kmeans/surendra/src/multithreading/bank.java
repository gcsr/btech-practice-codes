package multithreading;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class bank
{
	private double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;
	
	public bank(int n,double inbal)
	{
		accounts=new double[n];
		
		for(int i=0;i<accounts.length;i++)
		{
			accounts[i]=inbal;
		}
		bankLock=new ReentrantLock();
		sufficientFunds=bankLock.newCondition();
		
	}
	public void transfer(int from,int to,double amount)throws InterruptedException
	{
		bankLock.lock();
			try{
				while(accounts[from]<amount)
					sufficientFunds.await();
				System.out.println(Thread.currentThread());
				accounts[from]-=amount;
				System.out.printf("%10.2f from %d to %d",amount,from,to);
				accounts[to]+=amount;
				System.out.println("totalbalance "+gettotalbalance());	
				sufficientFunds.signalAll();	
				
			}
			finally{
				bankLock.unlock();
			}
		
		
	}
	public double gettotalbalance()
	{
		bankLock.lock();
		try{
			double sum=0;
			for(double a:accounts)
			sum+=a;
		
			return sum;
		}
		finally{
			bankLock.unlock();
		}
		
	}
	
	public int size()
	{
		return accounts.length;
	}
	
	
}