package inheritance;

class Relationship {

}


class DerivedRelationship extends Relationship{
	
}


public class TestClass extends DerivedRelationship{
	public static void main(String[] gcs){
		DerivedRelationship  obj=new DerivedRelationship();
		
		if(obj instanceof Relationship){
			System.out.println("Is A relationship");
		}
		
		if(obj instanceof TestClass){
			System.out.println("Is A relationship");
		}
	}
}