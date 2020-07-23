
public class FFAnd {
	public static void main(String[] gcs){
		byte b=(byte)127;
		System.out.println(b);
		int x=0XFF;
		byte h=b;
		System.out.println(h);
		System.out.println(b & x);
		byte p=(byte)(b & x);
		System.out.println(p);
	}
}
