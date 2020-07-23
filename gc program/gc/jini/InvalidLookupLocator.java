package basic;


import net.jini.core.discovery.LookupLocator;

public class InvalidLookupLocator
{
	LookupLocator lookup;
	public static void main(String[] gcs)
		{
			new InvalidLookupLocator();
		}
	public InvalidLookupLocator()
		{
			try{
			lookup=new LookupLocator("jini://localhost");
			}
			catch(java.net.MalformedURLException e)
			{
				e.printStackTrace();
			}
		}
}