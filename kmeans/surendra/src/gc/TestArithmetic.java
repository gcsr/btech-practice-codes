package gc;

import surendra.Arithmetic;

public class TestArithmetic {
	public static void main(String[] gcs){
		
		System.out.println(Arithmetic.sum(4, 6));
		
		Arithmetic arithmetic=new Arithmetic();
		System.out.println(arithmetic.diff(50, 40));
		
		PrivateClass pc=new PrivateClass();
		pc.method2();
	
	}
}


class PrivateClass{
	
	private void method1(){
		
	}
	
	void method2(){
		
	}
	
	
}