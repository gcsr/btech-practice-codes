import java.io.*;
import java.security.MessageDigest;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;

public class Digesthread extends Thread
{
	File input;
	public Digesthread(File input)
	{
		this.input=input;
	}
	public void run()
	{
		try{
		
		FileInputStream in=new FileInputStream(input);
		MessageDigest sha=MessageDigest.getInstance("SHA");
		DigestInputStream din=new DigestInputStream(in,sha);
		int b;

      	while ((b = din.read( )) != -1) ;

      	din.close( );

      	byte[] digest = sha.digest( );

      	StringBuffer result = new StringBuffer(input.toString( ));

       	result.append(": ");

      	for (int i = 0; i < digest.length; i++) {

        result.append(digest[i] + " ");

      	}

      	System.out.println(result);

    	}

    	catch (IOException ex) {

      	System.err.println(ex);

    	}

    	catch (NoSuchAlgorithmException ex) {

      	System.err.println(ex);

    	}

		
	}
	public static void main(String[] gcs)
	{
		String[] ss={"Test.java","whois.java"
		};
		
		for(int i=0;i<ss.length;i++)
		{
			File f=new File(ss[i]);
			Thread t=new Digesthread(f);
			t.start();
		}
	}
}