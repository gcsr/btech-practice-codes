import java.util.Scanner;


public class AETG2 {
	public static void main(String[] gcs){
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("enter number of levels");
		
		int levels=scanner.nextInt();
		
		System.out.println("enter number of factors");
		
		int factors=scanner.nextInt();
		AETGImplementor aetg=new AETGImplementor(levels,factors,true);
		
	
	}
}
