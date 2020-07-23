package inheritance;

class BaseClass {
	
	public void baseClass(){
		System.out.println("Base Class Method");
	}
	
	public void display(){
		System.out.println("Base Class Method Dispay");
	}
	
}


class DerivedClass extends BaseClass{
	public void derivedClass(){
		System.out.println("Derived Class Method");
	}
	public void display(){
		System.out.println("Derived Class Method Dispay");
	}
}


public class Main{
	public static void main(String[] gcs){
		BaseClass baseRef;
		DerivedClass derivedRef;
		
		BaseClass baseObject=new BaseClass();
		baseObject.display();
		
		DerivedClass derivedObject=new DerivedClass();
		derivedObject.display();
		
		baseRef=derivedObject;
		//derivedRef=baseObject;
		baseRef.display();		
	    //baseRef.derivedClass();
		
		
	}
}