import sun.misc.Perf;

public class stopwatch
{
	private Perf hirestimer;
	private long freq;
	private long starttime;
	
	public stopwatch()
	{
		hirestimer=Perf.getPerf();
		freq=hirestimer.highResFrequency();
		System.out.println(freq);
	}
	public void start()
	{
		starttime=hirestimer.highResCounter();
		
	}
	public long stop()
	{
		return (hirestimer.highResCounter()-starttime)*1000000000L/freq;
	}
	
	public long getresolution()
	{
		long diff,count1,count2;
		
		count1=hirestimer.highResCounter();
		count2=hirestimer.highResCounter();
		while(count1==count2)
		count2=hirestimer.highResCounter();
		
		diff=(count2-count1);
		
		count1=hirestimer.highResCounter();
		count2=hirestimer.highResCounter();
		while(count1==count2)
		count2=hirestimer.highResCounter();
		
		diff+=(count2-count1);
		
		count1=hirestimer.highResCounter();
		count2=hirestimer.highResCounter();
		while(count1==count2)
		count2=hirestimer.highResCounter();
		
		diff+=(count2-count1);
		
		
		count1=hirestimer.highResCounter();
		count2=hirestimer.highResCounter();
		while(count1==count2)
		count2=hirestimer.highResCounter();
		
		diff+=(count2-count1);
		
		return (diff*1000000000L)/(4*freq);
		
	}
}