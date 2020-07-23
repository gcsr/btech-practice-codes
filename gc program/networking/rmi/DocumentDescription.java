import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import  java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class DocumentDescription
{
	private DataInputStream actualDocuent;
	private int docType;
	private boolean printTwoSided;
	private int printQuality;
	private int length;
	
	public DocumentDescription(InputStream source)throws IOException
	{
		 readFroStrea(source);
	}
	private void readFroStrea(InputStream source)throws IOException
	{
	     BufferedInputStream buffer=new BufferedInputStream(source);
	     DataInputStream dataInputStream=new DataInputStream(buffer);
	     
	     readetadataFroStrea(dataInputStream);
	     ByteArrayOutputStream teporaryBuffer=new ByteArrayOutputStream();
	     //copy();
	}
	private void readetadataFroStrea(DataInputStream dataInputStream)throws IOException
	{
		docType=dataInputStream.readInt();
		printTwoSided=dataInputStream.readBoolean();
		printQuality=dataInputStream.readInt();
		length=dataInputStream.readInt();
	}
}