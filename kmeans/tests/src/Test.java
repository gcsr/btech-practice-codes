
public class Test {
	
	public static void main(String[] gcs){
		String numStr="100";
		//int num=(int)numStr;
		int num=new Integer(numStr);
		int num2=Integer.valueOf(numStr).intValue();
		int num3=new String(numStr).toInt();
	
	}
	
	public static int f(){
		int a=0;
		return 1/a;
	}
}
