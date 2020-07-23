

class Human{
	String name;
	int age;
	String qualification;
	String sex;
	
	public Human(String name,int age,String qualification,String sex){
		this.name=name;
		this.age=age;
		this.qualification=qualification;
		this.sex=sex;
	}
	
	public void introduce(){
		System.out.println("Hi I'm "+name+" with age "+age);
	}
}
public class SuperHero extends Human{
	
	String egoName;
	public SuperHero(String name,int age,String qualification,String sex,String egoName){
		super(name,age,qualification,sex);
		this.egoName=egoName;
	}
	
	public void introduce(){
		System.out.println("Hi I'm "+name+" with age "+age);
	}
}
