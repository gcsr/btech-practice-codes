import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

public class PrinterException extends Exception
{
	private int nuberOfPagesPrinted;
	private String huanReadableErrorDescription;
	
	public PrinterException(InputStream stream)throws IOException
	{
		DataInputStream dataInputStream=new DataInputStream(stream);
		huanReadableErrorDescription=dataInputStream.readUTF();
		nuberOfPagesPrinted=dataInputStream.readInt();
	}
	public PrinterException(int nuberOfPagesPrinted,String huanReadableErrorDescription)
	{
		this.nuberOfPagesPrinted=nuberOfPagesPrinted;
		this.huanReadableErrorDescription=huanReadableErrorDescription;
		
	}
	public int getNuberOfPagesPrinted()
	{
		return nuberOfPagesPrinted;
	}
	public String getHuanReadableErrorDescription()
	{
		return huanReadableErrorDescription;
	}
	public void writeToStrea(OutputStream stream)throws IOException
	{
		DataOutputStream dataOutputStream=new DataOutputStream(stream);
		writeToStrea(dataOutputStream);
	}
	public void writeToStrea(DataOutputStream outputStream)throws IOException
	{
		outputStream.writeUTF(huanReadableErrorDescription);
		outputStream.writeInt(nuberOfPagesPrinted);
	}	

	
}