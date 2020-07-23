
public class TestAWords {
	
	public static void main(String[] gcs){
		
		String[] pp=new String[]{"1","1 2 3 4 ","1 2 3","1 2"};
		String[] ss=sort(pp);
		for(String s:ss){
			System.out.println(s);
		}
	}
	
	private static String[] sort(String[] input){
		
		int length=input.length;
		
		for(int i=0;i<length-1;i++){
			for(int j=i+1;j<length;j++){
				if(noOfSpaces(input[i])<noOfSpaces(input[j])){
					String temp=input[i];
					input[i]=input[j];
					input[j]=temp;
				}
			}
		}
		
		return input;
	}
	
	private static int noOfSpaces(String s){
		String[] sp=s.split(" ");
		return sp.length;
	}
	

}
