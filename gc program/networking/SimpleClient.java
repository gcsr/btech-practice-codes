import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;


public class SimpleClient
{
	public static void main(String[] gcs)
	{
		try{
		String s="localhost";
		InetAddress add=InetAddress.getByName(s);
		
		Socket soc=new Socket(s,8080);
		
		InputStream raw = soc.getInputStream();

		BufferedInputStream buffer=new BufferedInputStream(raw);
        InputStreamReader in = new InputStreamReader(buffer, "8859_1");

        int c; 

        while ((c = in.read( )) != -1) {

       // filter non-printable and non-ASCII as recommended by RFC 1288

        if ((c >= 32 && c < 127) || c == '\t' || c == '\r' || c == '\n') 

        { 

          System.out.write(c);

        }

      }
      in.close();
      soc.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}