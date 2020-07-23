import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class NIOCopier {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		
		FileInputStream inFile=new FileInputStream(args[0]);
		FileOutputStream outFile=new FileOutputStream(args[1]);
		long startTime=System.currentTimeMillis();
		FileChannel inChannel=inFile.getChannel();
		FileChannel outChannel=outFile.getChannel();
		for(ByteBuffer buffer=ByteBuffer.allocate(1024*1024);inChannel.read(buffer)!=-1;buffer.clear())
		{
			buffer.flip();
			while(buffer.hasRemaining())outChannel.write(buffer);
		}
		inChannel.close();
		outChannel.close();
		long endTime=System.currentTimeMillis();
		
		System.out.println("time taken to copy using buffers is "+(endTime-startTime));
		
		// TODO Auto-generated method stub
		

	}

}