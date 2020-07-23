import java.io.DataInputStream;
import java.io.FileInputStream;


public class ReadFromFile {
	
	public synchronized static double[] read(String fileName){
		try{
			
			double[] result=new double[19];
			
		 	FileInputStream fis = new FileInputStream(fileName);
		 	DataInputStream dis = new DataInputStream(fis);
			
			for(int x=0;x<19;x++)
				result[x]=dis.readDouble();
			dis.close();
			fis.close();
			
			return result;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

}
