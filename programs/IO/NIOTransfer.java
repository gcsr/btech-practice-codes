import java.io.IOException;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NIOTransfer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		long startTime=System.currentTimeMillis();
		FileInputStream inFile=new FileInputStream(args[0]);
		FileOutputStream outFile=new FileOutputStream(args[1]);
		
		FileChannel inChannel=inFile.getChannel();
		FileChannel outChannel=outFile.getChannel();
		
		inChannel.transferTo(0,inChannel.size(),outChannel);
		inChannel.close();
		outChannel.close();
		long endTime=System.currentTimeMillis();
		
		System.out.println("time taken to copy using channels is "+(endTime-startTime));

	}

}
