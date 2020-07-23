
public class TryHaarFilter {
	
	public static void main(String[] gcs){
		//create the filtering object
		HaarFilter haar = new HaarFilter(256); 
		//adjust its parameters as desired 
		haar.setFractionalBits(0);
		//obtain some 2D byte data 
		byte[] data = new byte[256 * 256]; 
		//filter the data using Haar wavelets 
		int[] filter = haar.filter(data, null); 
		//perhaps do some processing with the filter ... 
		//convert the filter back into a data array 
		haar.invert(filter, data);
	}

}
