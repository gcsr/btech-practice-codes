import java.rmi.Naming;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;


public class FibonacciClient
{
	public static void main(String gcs[])
	{
		try
		{
			String[] gcfs=Naming.list("rmi://localhost/");
			for(String g:gcfs)
			{
				System.out.println(g);
			}
		
		Object calculator=Naming.lookup("rmi://localhost/fibonacci");
		
		Fibonacci cal=(Fibonacci)calculator;
			System.out.println("the fibonacci number is "+cal.getFibonacci(23));
		//BigInteger index=new BigInteger("3");
		
		//BigInteger f=calculator.getFibonacci(23);
		
		
		
		}
		catch(NotBoundException e)
		{
			e.printStackTrace();
		}
		catch(RemoteException e)
		{
			e.printStackTrace();
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		
		
	
		
	}
	
}