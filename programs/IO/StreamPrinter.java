import java.io.IOException;

public class StreamPrinter {
	public static void main(String[] gcs)
	{
		try
		{
			while(true)
			{
				int datam=System.in.read();
				if(datam==-1)break;
				System.out.println(datam);
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

}
