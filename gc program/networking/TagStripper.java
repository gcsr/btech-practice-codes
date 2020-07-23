import javax.swing.text.html.HTMLEditorKit;
import java.io.Writer;
import java.io.IOException;

public class TagStripper extends HTMLEditorKit.ParserCallback
{
	Writer out;
	public TagStripper(Writer out)
	{
		this.out=out;
	}
	public void handleText(char[] text,int position)
	{
		try{
			out.write(text);
			out.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}