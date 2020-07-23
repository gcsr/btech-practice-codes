package inheritance;

class Superclass{
	   
	   private int age;

	   Superclass(){
		   
	   }
	   Superclass(int age){
	      this.age = age; 		 
	   }

	   public void getAge(){
	      System.out.println("The value of the variable named age in super class is: " +age);
	   }
	   
	   
	   //fiuncationality

}

public class Subclass extends Superclass {
	
		int derivedAge;
	
	   Subclass(int age,int derivedAge){
		   //super();
	      super(age);
	      //this.age=age;
	      this.derivedAge=derivedAge;
	   }

	   public static void main(String argd[]){
	      Subclass s = new Subclass(24,45);
	      s.getAge();
	   }
	   
	   public void functionality(){
		   //super();
	   }

}