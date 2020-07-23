
public class PictureObject {
	int noOfThreads;
	int threadsCompleted=0;
	int[] cmpl;
	public PictureObject(int noOfThreads)
	{
		this.noOfThreads=noOfThreads;
		cmpl=new int[noOfThreads];
	}

}
