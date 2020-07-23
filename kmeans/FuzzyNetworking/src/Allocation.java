
public class Allocation {
	long startTime;
	int requestNo;
	int bandwidth;
	int reqRate;
	public Allocation(int reqRate,int bandwidth,long startTime,int requestNo){
		this.reqRate=reqRate;
		this.bandwidth=bandwidth;
		this.startTime=startTime;
		this.requestNo=requestNo;
	}
	
	public long getElapsedTime()
	{
		return System.currentTimeMillis()-startTime;
	}

}
