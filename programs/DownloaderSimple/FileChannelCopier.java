import java.io.*;
import java.nio.channels.*;
import java.net.URI;
import java.net.URL;
public class FileChannelCopier {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		try
		{
			URL url=new URL("http://r19---lax02x07.c.youtube.com/videoplayback?cp=U0hVTFZSU19KS0NONV9OTFdIOkJHUEhXa25SaURY&expire=1367675751&fexp=917000%2C926506%2C900325%2C916625%2C902534%2C932000%2C932004%2C906383%2C904479%2C901208%2C925714%2C929119%2C931202%2C900821%2C900823%2C912518%2C911416%2C930807%2C919373%2C906836%2C900824%2C912711%2C929606%2C910075&gcr=in&id=f279a8173ff7aab9&ip=180.151.113.98&ipbits=8&itag=22&key=yt1&ms=au&mt=1367652683&mv=m&newshard=yes&ratebypass=yes&signature=4476B94F1BBD7D70A3DA4CB6648922B658044EC1.5CCB77EE6F9960C0C7995E6DA6451BBA0111A864&source=youtube&sparams=cp%2Cgcr%2Cid%2Cip%2Cipbits%2Citag%2Cratebypass%2Csource%2Cupn%2Cexpire&sver=3&upn=WlT_bEehhA8&playretry=1&cpn=BquR8igTBCyoCqXF&ptk=AdityaMusic&ptchn=adityamusic&st=tcts&ir=1&rr=12&OBT_fname=videoplayback.5CCB77EE6F9960C0C7995E6DA6451BBA0111A864%26source%3Dyoutube%26sparams%3Dcp%252Cgcr%252Cid%252Cip%252Cipbits%252Citag%252Cratebypass%252Csource%252Cupn%252Cexpire%26sver%3D3%26upn%3DWlT_bEehhA8%26playretry%3D1%26cpn%3DBquR8igTBCyoCqXF%26ptk%3DAdityaMusic%26ptchn%3Dadityamusic%26st%3Dtcts%26ir%3D1%26rr%3D12");
			//URI uri=url.toURI();
			//System.out.println(uri.getScheme());
			//("http://r19---lax02x07.c.youtube.com/videoplayback?cp=U0hVTFZSU19KS0NONV9OTFdIOkJHUEhXa25SaURY&expire=1367675751&fexp=917000%2C926506%2C900325%2C916625%2C902534%2C932000%2C932004%2C906383%2C904479%2C901208%2C925714%2C929119%2C931202%2C900821%2C900823%2C912518%2C911416%2C930807%2C919373%2C906836%2C900824%2C912711%2C929606%2C910075&gcr=in&id=f279a8173ff7aab9&ip=180.151.113.98&ipbits=8&itag=22&key=yt1&ms=au&mt=1367652683&mv=m&newshard=yes&ratebypass=yes&signature=4476B94F1BBD7D70A3DA4CB6648922B658044EC1.5CCB77EE6F9960C0C7995E6DA6451BBA0111A864&source=youtube&sparams=cp%2Cgcr%2Cid%2Cip%2Cipbits%2Citag%2Cratebypass%2Csource%2Cupn%2Cexpire&sver=3&upn=WlT_bEehhA8&playretry=1&cpn=BquR8igTBCyoCqXF&ptk=AdityaMusic&ptchn=adityamusic&st=tcts&ir=1&rr=12&OBT_fname=videoplayback.5CCB77EE6F9960C0C7995E6DA6451BBA0111A864%26source%3Dyoutube%26sparams%3Dcp%252Cgcr%252Cid%252Cip%252Cipbits%252Citag%252Cratebypass%252Csource%252Cupn%252Cexpire%26sver%3D3%26upn%3DWlT_bEehhA8%26playretry%3D1%26cpn%3DBquR8igTBCyoCqXF%26ptk%3DAdityaMusic%26ptchn%3Dadityamusic%26st%3Dtcts%26ir%3D1%26rr%3D12");
		
			//File file=new File(uri);
			//FileInputStream fin=new FileInputStream(file);
			ReadableByteChannel inputChannel=Channels.newChannel((url.openStream()));
			//inputChannel.
			
			FileOutputStream fout = new FileOutputStream("pp.mp4 ",true);
			FileChannel outputChannel=fout.getChannel();
			
			outputChannel.transferFrom(inputChannel,0,1000000);
			
			inputChannel.close();
			outputChannel.close();
			
			// TODO Auto-generated method stub
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
