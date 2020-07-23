import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FilterOutputStream;

class Base64OutputStream extends FilterOutputStream
{
	private int[] inbuf=new int[3];
	private int col=0;
	private int i=0;
	public Base64OutputStream(OutputStream out)
	{
		super(out);
	}
	public void write(int c)throws IOException
	{
		inbuf[i]=c;
		i++;
		
		if(i==3)
		{
			super.write(toBase64[(inbuf[0]&0xFC)>>2]);
			super.write(toBase64[((inbuf[0]&0x03)<<4)|((inbuf[1]&0xF0)>>4)]);
			super.write(toBase64[((inbuf[1]&0x0F)<<2)|((inbuf[1]&0xC0)>>6)]);
			super.write(toBase64[inbuf[2]&0x3F]);
			col+=4;
			i=0;
			if(col>=76)
			{
				super.write('\n');
				col=0;
			}
		}
		
		
	}
	private static char[] toBase64={
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
		'Q','R','S','T','U','V','W','X','Y','Z','a','b','c',
		'd','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s',
		't','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};
	
}