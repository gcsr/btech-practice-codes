import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
public class MemoryMapCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		RandomAccessFile file=new RandomAccessFile("D:/gc personal/videos/julai.avi","r");
		FileChannel channel=file.getChannel();
		
		ByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
		
		RandomAccessFile file2=new RandomAccessFile("julai","w");
		FileChannel channel2=file2.getChannel();
		
		//ByteBuffer buffer2=channel2.map(FileChannel.MapMode.READ_ONLY,0,file.length());
		
		while(buffer.hasRemaining())
			channel2.write(buffer);
		
		
		

	}

}
