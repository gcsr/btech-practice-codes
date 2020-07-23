import java.net.CacheRequest;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class SimpleCacheRequest extends CacheRequest
{
	ByteArrayOutputStream out=new ByteArrayOutputStream();
	
	public OutputStream getBody()throws IOException
	{
		return out;
	}
	
	public void abort()
	{
		out=null;
	}
	
	public byte[] getData()
	{
		if(out==null)
			return null;
		else
			return out.toByteArray();	
	}
}