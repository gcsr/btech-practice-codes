import java.io.DataOutputStream;
import java.io.FileOutputStream;


public class WriteToFile {
	//private  String fileName="E:/old laptop/d drive/papers/harish/personalized/latent.dat";
	
	public synchronized static void write(double[] xxx,String fileName){
		try{
		 	FileOutputStream fos = new FileOutputStream(fileName);
		 	DataOutputStream dos = new DataOutputStream(fos);
			
			for(double x:xxx)
				dos.writeDouble(x);
			dos.close();
			fos.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
