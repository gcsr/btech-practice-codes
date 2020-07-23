
public class PunctuationDealer {
	
	static char[] punctuationLetters={',','.','!','@','(',')','{','}','\"','\'','_'};
	
	public static void main(String[] gcs){
		for(char s:punctuationLetters){
			System.out.println(s);
		}
	}
	
	static boolean checkNotPunctuationLetters(String letters){
		char[] charLetters=letters.toCharArray();
		for(int i=0;i<charLetters.length;i+=2){
			if(!check(charLetters[i])){
				return true;
			}
		}
		return false;
	}
	
	static boolean check(char c){
		for(char sp:punctuationLetters){
			if(c==sp)
				return true;
		}
		
		return false;
	}
	
	public static String removePunctionLetters(String cipherText){
		
		for(char s:punctuationLetters){
			//System.out.println(s);
			//System.out.println(mainText.indexOf(s));			
			String sp=""+s;
			cipherText=cipherText.replace(sp," ");
		}
		
		return cipherText;
		
	}

}
