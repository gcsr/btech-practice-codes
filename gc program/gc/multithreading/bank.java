package multithreading;

public class bank
{
	public bank(int n,double inbal)
	{
		accounts=new double[n];
		
		for(int i=0;i<accounts.length;i++)
		{
			accounts[i]=inbal;
		}
		
	}
	public void transfer(int from,int to,double amount)
	{
		if(accounts[from]<amount)
		return;
		
		System.out.println(Thread.currentThread());
		
		accounts[from]-=amount;
		
		System.out.println("from  "+from+"  to  "+to+" "+amount);
		accounts[to]+=amount;
		System.out.println("totalbalance "+gettotalbalance());
	}
	public double gettotalbalance()
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