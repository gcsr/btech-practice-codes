import java.net.*;
import java.rmi.*;

public class FibonacciServer
{
	public static void main(String gcs[])
	{
		try
		{
			FibonacciImpl f=new FibonacciImpl();
			Naming.rebind("fibonacci",f);
			System.out.println("fibonacci server ready");			
			
		}
		catch(RemoteException rex)
		{
			System.out.println("Exception in fibonacciimpl: main"+rex);
		}
		catch(MalformedURLException ex)
		{
			System.out.println("malforedurlexception: "+ex);
		}
			

			

	}
}