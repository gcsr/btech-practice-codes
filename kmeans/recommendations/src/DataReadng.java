import java.io.*;

import java.io.File;

public class DataReadng {

	/**
	 * @param args
	 */
	
	public static void main(String[] gcs){
		long[] ss=new long[3];
		//readData(3,ss,"d:/desi.dat");
	}
	
	public synchronized static double[] readData(String fileName){
		double[] s=new double[19];
		try{			
			FileInputStream fis = new FileInputStream(fileName);
			DataInputStream dis = new DataInputStream(fis);
			//System.out.println();
			//	BufferedReader reader=new BufferReader(dis);
			for(int i=0;i<19;i++){
				s[i]=dis.readDouble();				
			}
			//System.out.println();
			dis.close();
			fis.close();
			
			return s;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}		
		return null;
	}

}
