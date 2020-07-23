package tests;

public class timemills
{
	public static void main(String[] gcs)
	{
		long beforetime,aftertime,sleeptime;
		
		beforetime=System.currentTimeMillis();
		System.out.println(beforetime);
		aftertime=System.currentTimeMillis();
		
		System.out.println(aftertime-beforetime);
		
	}
}