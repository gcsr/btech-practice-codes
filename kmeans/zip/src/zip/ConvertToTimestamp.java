package zip;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertToTimestamp {
	public static void main(String[] gcs){
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss a");

		try{
			Date parsedTimeStamp = dateFormat.parse("2/13/2015 3:11:26 PM");
			Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
			System.out.println(timestamp);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	    

	}
}
