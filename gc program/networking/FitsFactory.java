import java.net.ContentHandlerFactory;
import java.net.ContentHandler;


public class FitsFactory implements ContentHandlerFactory
{
	public ContentHandler createContentHandler(String mimeType)
	{
		if(mimeType.equalsIgnoreCase("image/x-fits"))
			return new x_fits();
			
		return null;	
	}
	
}