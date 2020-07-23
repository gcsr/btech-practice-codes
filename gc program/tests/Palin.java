import java.lang.Math;

public class Palin
{
	public static void main(String[] gcs)
	{
	
		long ss=(long )Math.pow(26,3)-1;
		//97,122
		long lea=(long)Math.pow(26,2)-1;
				
		for(long x=ss;x>lea;x--)
		{
			long  re=x;
			String s="";
			while(re>0)
			{
			int se=(int)re%26;
			re=re/26;
			s+=(char)(97+se);
			}
			
			System.out.println(s);
			
		}
		
	

	}
}