
public class MatchTable {
	
	public static char getChar(int no,double frequency){
		
		switch(no){
		case 0:
			return 'Q';
		case 1:
			return 'J';
		case 2:
			if(frequency<=0.2722)
				return 'Z';
			else
				return 'X';
		case 3:
			if(frequency<1.0744)
				return 'V';
			if(frequency>=1.0744 && frequency<=1.1016)
				return 'K';
			else
				return 'W';
			
		case 4:
			if(frequency<=1.7779)
				return 'Y';
			else
				return 'F';
		case 5:
			return 'B';
		case 6:
			return 'G';
		case 7:
			if(frequency<3.0034)
				return 'H';
			if(frequency>=3.0034 && frequency<=3.0129)
				return 'M';
			else
				return 'P';
			
		case 8:
			if(frequency<=3.3844)
				return 'D';
			else
				return 'U';

		case 9:
			return 'C';
		case 10:
			if(frequency<=5.4893)
				return 'L';
			else
				return 'S';
			
			
		case 11:
			if(frequency<=6.6544)
				return 'N';
			else
				return 'T';
		case 12:
			return 'O';
		case 13:
			if(frequency<=7.5448)
				return 'I';
			else
				return 'R';
			
		case 14:
			return 'A';
		case 15:
			return 'E';
		default:
			
			System.out.println("error");
			System.exit(1);
			return 'E';
		
			
		}
		
	}

}
