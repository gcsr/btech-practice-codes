import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class WriteToFile {

	public static void writeToFile(ArrayList<Double> list,String fileName){
		
		try{
		 	FileOutputStream fos = new FileOutputStream(fileName,true);
		 	DataOutputStream dos = new DataOutputStream(fos);
			
			for(int i=0;i<list.size();i++){
				dos.writeDouble(list.get(i));
			}
			dos.close();
			fos.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public static void readFromFile(String fileName){
		
		try{
		 	FileInputStream fis = new FileInputStream(fileName);
		 	DataInputStream dis = new DataInputStream(fis);
		 	
		 	while(dis.available()>0){
		 		System.out.println(dis.readDouble());
		 	}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	
	public static void main(String[] gcs){
		readFromFile("web.dat");
	}


}
