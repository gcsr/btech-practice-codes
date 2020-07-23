
import sun.misc.Perf;
public class timers
{
	public static void main(String gcs[])
	{
		j3dtimersolution();
		nanoresolution();
		stopwatch sw=new stopwatch();
		System.out.println("stopwatch resol is "+sw.getresolution());
	}
	private static void nanoresolution()
	{
		long total,count1,count2;
		count1=System.nanoTime();
		count2=System.nanoTime();
				while(count1==count2)
				{				
		count2=System.nanoTime();
					}
		total=(count2-count1);
		
			System.out.println(total);
		count1=System.nanoTime();
		count2=System.nanoTime();
		while(count1==count2)
		count2=System.nanoTime();
		total+=(count2-count1);
		
			System.out.println(total);
		
		count1=System.nanoTime();
		count2=System.nanoTime();
		while(count1==count2)
		count2=System.nanoTime();
		total+=(count2-count1);
		
			System.out.println(total);
		
		count1=System.nanoTime();
		count2=System.nanoTime();
		while(count1==count2)
		count2=System.nanoTime();
		total+=(count2-count1);
		
		System.out.println("nsec "+total/4);
		
		
	}
	private static void j3dtimersolution()
	{
		
		long total,count1,count2;
		count1=System.currentTimeMillis();
		count2=System.currentTimeMillis();
				while(count1==count2)
				{				
		count2=System.currentTimeMillis();
				}
		total=(count2-count1);
		
			System.out.println(total);
		count1=System.currentTimeMillis();
		count2=System.currentTimeMillis();
		while(count1==count2)
		count2=System.currentTimeMillis();
		total+=(count2-count1);
		
			System.out.println(total);
		
		count1=System.currentTimeMillis();
		count2=System.currentTimeMillis();
		while(count1==count2)
		count2=System.currentTimeMillis();
		total+=(count2-count1);
		
			System.out.println(total);
		
		count1=System.currentTimeMillis();
		count2=System.currentTimeMillis();
		while(count1==count2)
		count2=System.currentTimeMillis();
		total+=(count2-count1);
		
		System.out.println("mse c  "+total/4);
		
	}
}