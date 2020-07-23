import java.net.ContentHandler;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.InputStream;

public class tab_separated_values extends ContentHandler
{
	public Object getContent(URLConnection uc)throws IOException
	{
		String theLine;
		
		Vector lines=new Vector();
		
		InputStreamReader isr=new InputStreamReader(uc.getInputStream());
		
		
		SafeBufferReader in=new SafeBufferReader(isr);
		
		while((theLine=in.readLine())!=null)
		{
			String[] lineArray=lineToArray(theLine);
			
			lines.addElement(lineArray);
		}
		return lines;
	}
	private String[] lineToArray(String line)
	{
		int numFields=1;
		
		for(int i=0;i<line.length();i++)
			if(line.charAt(i)=='\t')
				numFields++;
				
		String[] fields=new String[numFields];
		
		int position=0;
		for(int i=0;i<numFields;i++)
		{
			StringBuffer buffer=new StringBuffer();
			while(position<line.length()&&line.charAt(position)!='\t')
			{
				buffer.append(line.charAt(position));
				position++;
			}
			fields[i]=buffer.toString();
			position++;
		}		
			
			return fields;
		
	}
	
}