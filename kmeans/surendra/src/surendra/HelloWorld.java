package surendra;

public class HelloWorld {
	public static void main(String[] gcs){
		System.out.println("Hi There");
		HelperClass helperClass=new HelperClass();
		//helperClass.;//.helperMethod();
		int result=helperClass.helperMethod2();
		System.out.println(result);
		HelperClass.helperMethod3();
		
	}
}

// Java HelloWorld
//HelloWorld.main();

//public : Access Modifier
// void : return type
//helperMethod : method name

class HelperClass{
	
	public void helperMethod(){
		System.out.println("This is Helper method");
	}
	
	public int helperMethod2(){
		
		return 22;
		
	}
	
	public static void helperMethod3(){
		System.out.println("This method can be called without creating object");
	}
}
