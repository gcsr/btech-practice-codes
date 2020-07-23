
public class Decode {
	String cipherText;
	
	public Decode(String cipherText){
		
		this. cipherText=cipherText;
	}
	
	public String process(){
		cipherText=" "+cipherText+" ";
		System.out.println(cipherText);
		cipherText=PunctuationDealer.addSpace(cipherText);
		System.out.println(cipherText);
		cipherText=WordDealer.removeAuxillaryWords(cipherText);
		System.out.println(cipherText);
		//System.out.println("ciphertext after auxillary words is");
		cipherText=PunctuationDealer.removePunctionLetters(cipherText);
		System.out.println(cipherText);
		cipherText=cipherText.replaceAll("\\s+"," ");
		System.out.println(cipherText+" length "+cipherText.length());
		while(cipherText.charAt(0)==' ')
			cipherText=cipherText.substring(1,cipherText.length());
		
		while(cipherText.charAt(cipherText.length()-1)==' ')
			cipherText=cipherText.substring(0,cipherText.length()-1);
		
		
		String[] remainingWords=cipherText.split("\\s+");
		for(String s:remainingWords){
			System.out.println(s);
		}
		char[] characters=new char[remainingWords.length];
		System.out.println(cipherText);
		System.out.println("length is "+characters.length);
		for(int i=0;i<characters.length;i++){
			characters[i]=remainingWords[i].charAt(0);
		}
		
		System.out.println();
		for(char c:characters){
			System.out.print(c);
		}
		System.out.println();
		
		String[] binary=new String[characters.length/2];
		
		for(int i=0;i<binary.length;i++){
			binary[i]="";
			binary[i]=binary[i]+ReverseMap.getString(characters[2*i]);//Integer.parseInt(ReverseMap.getString(characters[2*i]),2);
			binary[i]=binary[i]+ReverseMap.getString(characters[2*i+1]);//Integer.parseInt(ReverseMap.getString(characters[2*i+1]),2);
			System.out.println(binary[i]);
		}
		
		String password="";
		int[] binaryNumbers=new int[binary.length];
		System.out.println();
		System.out.println("result ");
		for(int i=0;i<binaryNumbers.length;i++){
			binaryNumbers[i]=Integer.parseInt(binary[i],2);
			password+=(char)binaryNumbers[i];
		}
		System.out.println(password);
		System.out.println("ended result");
		return password;
		
		
		
	}
	
		

}
