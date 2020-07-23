import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

public class HostLookup
{
	public static void main(String[] gcs)
	{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter IPAddresses and names \n enter exit or quit to close");
		
		try{
			while(true)
			{
				String host=in.readLine();
				
				if(host.equalsIgnoreCase("exit")||host.equalsIgnoreCase("quit"))
					break;
				System.out.println(lookup(host));	
					
					
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}
	private static String lookup(String host)
	{
		InetAddress node;
		try{
			node=InetAddress.getByName(host);
		}
		catch(UnknownHostException e)
		{
			return "cannot find"+host;
			
		}
		if(isHostName(host))
		{
			return node.getHostAddress();
		}
		else{
			return node.getHostName();
		}
		
	}
	private static boolean isHostName(String host)
	{
		if(host.indexOf(':')!=-1) return false;
		
		char[] ca=host.toCharArray();
		
		for(int i=0;i<ca.length;i++)
		{
			if(!(Character.isDigit(ca[i])||ca[i]=='.'))
				return false;
		}
		
		return true;
	}
	
	
}