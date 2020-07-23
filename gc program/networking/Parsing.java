import javax.swing.text.html.HTMLEditorKit;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Parsing
{
	public static void main(String[] gcs)throws IOException
	{
		ParserGetter kit=new ParserGetter();
		HTMLEditorKit.Parser parser=kit.getParser();
		
		HTMLEditorKit.ParserCallback callback=new TagStripper(new OutputStreamWriter(System.out));
		
		URL url=new URL("http://localhost:8080");
		InputStream in=new BufferedInputStream(url.openStream());
		InputStreamReader inr=new InputStreamReader(in);
		parser.parse(inr,callback,false);
		
	}
}