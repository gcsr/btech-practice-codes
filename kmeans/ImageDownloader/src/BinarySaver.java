import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;


public class BinarySaver
{
	public static void main(String[] gcs)
	{
		
	}
	public static void saveBinaryFile(String urlString)throws IOException
	{
		URL url=null;	
		try{
			
			url=new URL(urlString);
			//saveBinaryFile(url);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
			//url.o
			URLConnection con=url.openConnection();			
			String contentType=con.getContentType();
			int contentLength=con.getContentLength();
			
			System.out.println(contentType);
				if(contentType.startsWith("text/"))
					throw  new IOException("not a binary file");
					
					
			
			InputStream raw=con.getInputStream();
			InputStream in=new BufferedInputStream(raw);
			
			byte[] data=new byte[contentLength];
			int bytesRead=0;
			int offset=0;
			
			while(offset<contentLength)
			{
				bytesRead=in.read(data,offset,data.length-offset);
				if(bytesRead==-1)break;
				offset+=bytesRead;
			}
			in.close();
			
			if(offset!=contentLength)
				throw new IOException("only "+offset+"  read while expected is "+contentLength);
				
			String fileName=url.getFile();
			fileName=fileName.substring(fileName.lastIndexOf("/")+1);
			FileOutputStream fout=new FileOutputStream(fileName);
			
			fout.write(data);
			fout.flush();
			fout.close();	
	}
}