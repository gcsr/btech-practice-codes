import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
	public static void main(String[] gcs){
		BufferedReader br = null;

		String fileContentsText="";
			try {

				String sCurrentLine;

				br = new BufferedReader(new FileReader("C:\\Users\\gc\\Desktop\\eHealthRequest.txt"));
				

				while ((sCurrentLine = br.readLine()) != null) {
					fileContentsText+=sCurrentLine;
				}
				
				System.out.println(fileContentsText);
				fileContentsText=fileContentsText.substring(2);
				
				String temp="";
				for(int i=0;i<fileContentsText.length();i+=1){
					int ttt=fileContentsText.charAt(i);
					System.out.println((int)ttt);
					/*if(ttt=='\t'){
						System.out.println("inside if");
						while(ttt!='\t' && i<fileContentsText.length()){
							i++;
							ttt=fileContentsText.charAt(i);
							
						}
					}
					temp+=fileContentsText.charAt(i);
					
					System.out.println(temp);*/
					if(ttt!=0)
					temp+=(char)ttt;
				}
				fileContentsText=temp;
				System.out.println(fileContentsText);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			System.out.println("in parse string");
			System.out.println("in parse string");
			try{
				String inss=fileContentsText.substring(5,fileContentsText.indexOf("request type"));
				String insurabilityRequestType=""+fileContentsText.charAt(fileContentsText.indexOf("period")-1);
				String period=fileContentsText.substring(fileContentsText.indexOf("period=")+7,
					fileContentsText.indexOf("InsurabilityContactType"));
				String insurabilityContactType=fileContentsText.substring(fileContentsText.indexOf("InsurabilityContactType")+24);
				System.out.println(inss+" length is "+inss.length());
				System.out.println(insurabilityRequestType+" length is "+insurabilityRequestType.length());
				System.out.println(period+" length is "+period.length());
				System.out.println(insurabilityContactType+" length is "+insurabilityContactType.length());
				
			}catch(Exception ex){
				//parsingException=false;
			}
			

	}
}
