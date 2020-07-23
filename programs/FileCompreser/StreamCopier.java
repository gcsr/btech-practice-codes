 import java.io.*;
 import java.nio.channels.*;
 public class StreamCopier 
 {
	
/*	 public static void main(String[] args) 
	 {
		 try {
			 	copy(System.in, System.out); 
			} 
		 catch (IOException ex) 
			{
		 		System.err.println(ex); 
			}
	 } */
	public static void copy(FileInputStream in,FileOutputStream out) 	throws IOException 
	 {
		/*long x=0;
		byte[] buffer = new byte[1024]; 
		while (true) 
		{ 
			try {
				  long bytesSkipped = 0;
				  long bytesToSkip = 882230816;
				  while (bytesSkipped < bytesToSkip) {
				    long n = in.skip(bytesToSkip - 								bytesSkipped);
				    if (n == -1) break;
				    bytesSkipped += n;
				  }
		System.out.println("bytesskipped is"+bytesSkipped);
				}
			catch (IOException ex) {
				  System.err.println(ex);
			}



			//in.skip(882230816);
			int bytesRead = in.read(buffer); 
			if (bytesRead == -1) break;
			x+=1024;

			8:35
			x+=1024;
			int i=0;
				for(int j:buffer)
					if(j==0)
						i++;
			//			 if(i<854*470*3)
			 out.write(buffer, 0, bytesRead);
			//if(x>100000000)
			//	break;
			//621318144//6870
			//621317893 bytes
			//46576250
			//15503302500
			//77664736
		 }
		System.out.println(x);*/
		//FileInputStream inFile = new FileInputStream(args[0]); 
		//FileOutputStream outFile = new FileOutputStream(args[1]); 
		FileChannel inChannel = in.getChannel( );
		 FileChannel outChannel = out.getChannel( );
		 inChannel.transferTo(0, inChannel.size( ), outChannel); 
		 inChannel.close( );
		 outChannel.close( ); 

		
	 }
 } 
