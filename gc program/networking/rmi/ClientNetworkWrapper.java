import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientNetworkWrapper extends NetworkBaseClass implements PrinterConstants
{
	String serverachine;
	int serverPort;
	public ClientNetworkWrapper()
	{
		this(DEFAULT_SERVER_NAME,DEFAULT_SERVER_PORT);
	}
	public ClientNetworkWrapper(String serverachine,int serverPort)
	{
		this.serverachine=serverachine;
		this.serverPort=serverPort;
	}
	
	public void sendDocuentToPrinter(InputStream strea)throws ConnectionException,PrinterException
	{
		sendDocuentToPrinter(strea,DEFAULT_DOCUMENT_TYPE,DEFAULT_PRINT_TWO_SIDED,DEFAULT_PRINT_QUALITY);
		
	}
	public void sendDocuentToPrinter(InputStream strea,int docType,boolean printTwoSided,int printQuality)throws ConnectionException,PrinterException
	{
		DocumentDescription docuentToSend;
		try{
			docuentToSend=new DocumentDescription(strea,docType,printTwoSided,printQuality);
		}catch(IOException e)
		{
			throw new ConnectionException();
		}
		sendDocuentToPrinter(docuentToSend);
	}
	private void sendDocuentToPrinter(DocumentDescription docuentToSend)throws ConnectionException,PrinterException
	{
		Socket connection=null;
		try{
			connection=new Socket(serverachine,serverPort);
			
		}
		
	}
}