
public class ReverseMap {
	
	public static String getString(char s){
		
		switch(s){
		
		case 'q':
			return "0000";		
		case 'j':
			return "0001";		
		case 'z':		
		case 'x':
			return "0010";		
		case 'v':		
		case 'k':
		case 'w':
			return "0011";		
		case 'y':		
		case 'f':
			return "0100";
		
		case 'b':
			return "0101";		
		
		case 'g':
			return "0110";		
		case 'h':		
		case 'm':		
		case 'p':
			return "0111";
				
		case 'd':		
		case 'u':
			return "1000";
		
		case 'c':
			return "1001";
		
		
		case 'l':		
		case 's':
			return "1010";
		
		case 'n':		
		case 't':
				return "1011";
		case 'o':
			return "1100";
		
		case 'r':
		case 'i':	
			return "1101";	
			
		case 'a':	return "1110";

		case 'e':	return "1111";

		
		}
		return "";
	}

}
