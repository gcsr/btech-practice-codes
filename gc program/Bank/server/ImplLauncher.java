import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.rmi.Naming;


public class ImplLauncher
{
	public static void main(String[] gcs)
	{
		Collection nameBalancePairs=getNameBalancePairs(gcs);
		Iterator iterator=nameBalancePairs.iterator();
		
		while(iterator.hasNext())
		{
			NameBalancePair nextNameBalancePair=(NameBalancePair)iterator.next();
			launchServer(nextNameBalancePair);
			
		}
		
	}
	
	private static void launchServer(NameBalancePair serverDescription)
	{
		try{
			Account_Impl account=new Account_Impl(serverDescription.balance);
			Naming.rebind(serverDescription.name,account);
			System.out.println("account "+serverDescription.name+" is successfully launched");
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static Collection getNameBalancePairs(String[] gcs)
	{
	
		ArrayList returnValue=new ArrayList();
		for(int i=0;i<gcs.length;i+=2)
		{
			NameBalancePair nextNameBalancePair=new NameBalancePair();
			nextNameBalancePair.name=gcs[i];
			
			int intValue=(new Integer(gcs[i+1])).intValue();
			nextNameBalancePair.balance=new Money(intValue);
			
			returnValue.add(nextNameBalancePair);
		}
		return returnValue;
	}
	
	
	private static class NameBalancePair{
		String name;
		Money balance;
	}
}