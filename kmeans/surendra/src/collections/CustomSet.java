package collections;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;


//java CustomSet
//CustomSet.main();

public class CustomSet {
	
	int s=10;
	
	public static void main(String[] gcs){
		Set set;
		
		Emp emp=new Emp();
		emp.var=13;

		//s=13;
		
		Emp emp1=new Emp("Surendra","20000");
		Emp emp2=new Emp("Surendra","20000");
		
		
		Set<Emp> hashSet=new TreeSet<Emp>();		
		hashSet.add(emp1);
		hashSet.add(emp2);
		//hashSet.add(emp2);
		//hashSet
		
		
		//HashSet<Object> hs=new HashSet<Object>();
		//hs.remove(arg0);
		
		//hs.addAll(hashSet);
		
		
		Iterator<Emp> iterator=hashSet.iterator();
		
		Emp tmp;
		while(iterator.hasNext()){
			tmp=iterator.next();
			System.out.println(tmp.getName());
			System.out.println(tmp.getSalary());
			
		}
		
		
		LinkedHashSet<String> ddd=new LinkedHashSet<String>();
		ddd.add("Surendra");
		ddd.add("Gc");
		ddd.add("Gc");		
		ddd.add("Pavan");
		
		Iterator<String> itr=ddd.iterator();
		
		
		while(itr.hasNext()){
			
			System.out.println(itr.next());
			
			
		}
		
	}
	
	
	
	
	
}


class Emp implements Comparable{
	private String name;
	private String salary;
	public int var;
	
	public Emp(){
		
	}
	
	public Emp(String name,String salary){
		this.salary=salary;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	
	//positive obj1 bigger than obj2
	//negativee reversed
	// 0
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("called while loading");
		Emp ne=(Emp)arg0;
		
		if(this.name.equals(ne.getName()) && this.salary.equals(ne.getSalary()))			
			return 0;
		else
			return -9;
	}
	
	
	
	
	
}


// 1	2	3	4
   				