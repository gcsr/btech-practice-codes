package algorithms;

public class TestStaticObjects {
	
	static Dip dippppp;
	
	public static void main(String[] gcs){
		
		TestStaticObjects tsb=new TestStaticObjects();
		tsb.test();
		tsb.display();
		
		
		TestStaticObjects tsb1=new TestStaticObjects();
		tsb1.test();
		tsb1.display();
		
		System.out.println(tsb.dippppp);
		System.out.println(tsb.dippppp);
		System.out.println(tsb.dippppp.hashCode());
		System.out.println(tsb1.dippppp.hashCode());
		
		System.out.println(tsb.dippppp==tsb1.dippppp);
		
	}
	
	
	public void display(){
		System.out.println(dippppp.j);
		System.out.println(dippppp.s);
	}
	
	public void test(){
		dippppp=new Dip();
	}
}

class Dip{
	int s=10;
	int j=200;
}
