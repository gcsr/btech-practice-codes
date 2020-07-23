



public class sleeptest
{
	//private static DecimalFormat df;
	public static void main(String[] gcs)
	{
		//df=new DecimalFormat("0.##");
		sleeptestm(1000);
		sleeptestm(500);
		sleeptestm(200);
		sleeptestm(100);
		sleeptestm(50);
		sleeptestm(20);
		sleeptestm(10);
		sleeptestm(5);
		sleeptestm(1);
	}
	private static void sleeptestm(int delay)
	{
		long timestart=System.nanoTime();
		try
		{
			Thread.sleep(delay);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		double timediff=((System.nanoTime()-timestart)/1000000L);
		System.out.println(timediff);
	}
}