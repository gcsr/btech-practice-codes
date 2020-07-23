import java.io.Serializable;

public class VideoFormat implements Serializable{
	private String url;
	private String contentType;
	private String videoType;
	private int noOfThreads;
	private long sizeOfThread;
	private long sizeOfFile;
	
	public VideoFormat()
	{
		this("","",0,0,0);
	}
	
	public VideoFormat(String contentType,String videoType,
			int noOfThreads,long sizeOfFile,long SizeOfThread)
	{
		setContentType(contentType);
		setVideoType(videoType);
		setNoOfThreads(noOfThreads);
		setSizeOfFile(sizeOfFile);
		setSizeOfThread(sizeOfThread);
	}
	
	public void setContentType(String contentType)
	{
		this.contentType=contentType;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public void setVideoType(String videoType)
	{
		this.videoType=videoType;
	}
	public String getVideoType()
	{
		return videoType;
	}
	
	public void setNoOfThreads(int noOfThreads)
	{
		this.noOfThreads=noOfThreads;
	}
	
	public int getNoOfThreads()
	{
		return noOfThreads;
	}
	public void setSizeOfFile(long sizeOfFile)
	{
		this.sizeOfFile=sizeOfFile;
	}
	
	public long getSizeOfFile()
	{
		return sizeOfFile;
	}
	
	public void setSizeOfThread(long sizeOfThread)
	{
		this.sizeOfThread=sizeOfThread;
	}
	
	public long getSizeOfThread()
	{
		return sizeOfThread;
	}
	
}
