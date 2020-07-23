
public class CharsTable {
	
	public static char getChar(int intChar,double frequency){
		switch(intChar){
		case 0:
					return 'q';
		case 1:
					return 'j';
		case 2:if(frequency<=.2722)
					return 'z';
				else
					return 'x';
		case 3:if(frequency<=1.0074)
					return 'v';
				else if(frequency<=1.1016)
						return 'k';
				else return 'w';
		case 4:if(frequency<=1.7779)
					return 'y';
				else
					return 'f';
		
		case 5:
					return 'b';
				
		case 6:
					return 'g';
				
		case 7:if(frequency<=3.0034)
					return 'h';
				else if(frequency<3.0129)
					return 'm';
				else
					return 'p';
		case 8:if(frequency<=3.3844)
					return 'd';
				else
					return 'u';
		case 9:
					return 'c';
				
		case 10:if(frequency<=5.4893)
					return 'l';
				else
					return 's';
		case 11:if(frequency<=6.6544)
					return 'n';
				else
					return 't';
		case 12:
					return 'o';
				
		case 13:if(frequency<=7.5448)
					return 'i';
				else
					return 'r';
		case 14:	return 'a';
	
		case 15:	return 'e';
				
		}
		
		System.out.println("program error ");
		System.exit(1);
		return 'p';
	}	
	
}
