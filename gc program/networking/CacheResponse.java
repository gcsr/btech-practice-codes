import java.io.InputStream;
import java.util.Map;
import java.util.List;

public abstract class CacheResponse
{
	public abstract InputStream getBody();
	public abstract Map<String,List<String>> getHeaders();
}