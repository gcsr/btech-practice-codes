import java.net.ResponseCache;
import java.util.Map;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.net.URI;
import java.net.URLConnection;
import java.io.IOException;
import java.util.List;
import java.net.RequestCache;


public class MemoryCache extends ResponseCache
{
	private Map<URL,SimpleCacheResponse> responses=
		new ConcurrentHashMap<URL,SimpleCacheResponse>();
	private int maxEntries=100;
	
	public MemoryCache()
	{
		this(100);
	}
	public MemoryCache(int ent)
	{
		this.maxEntries=ent;
	}
	
	public RequestCache put(URI uri,URLConnection uc)throws IOException
	{
		if(responses.size()>=maxEntries)
			return null;
		
		String cacheControl=uc.getHeaderField("Catche-Control");
		
		if(cacheControl!=null&&cacheControl.indexOf("no-cache")>=0)
			return null;
			
		SimpleCacheRequest request=new SimpleCacheRequest();
		
		SimpleCacheResponse response=new SimpleCacheResponse(request,uc);
		
		responses.put(uri,response);
		
		return request;
		
				
	}
	
	public ResponseCache get(URI uri,String requestMethod,Map<String,List<String>> requestHeaders)throws IOException
	{
		
	}
		
}