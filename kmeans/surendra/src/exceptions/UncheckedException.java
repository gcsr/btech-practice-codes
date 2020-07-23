package exceptions;


class Class1{
	public void display(){
		System.out.println("hi");
	}
}

public class UncheckedException {
	public static void main(String[] gcs){
		
		Class1 ref2=null;
		int i=10;
		
		if(i==10)
			ref2=new Class1();
		ref2.display();
		
		Class1 ref=null;
		
		if(i==11)
			ref=new Class1();
		ref.display();
	}
}
