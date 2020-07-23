package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
    	
    	Map<String,ArrayList<Employee>>staff=new HashMap<String,ArrayList<Employee>>();
    	
    	ArrayList<Employee> managerBucket=new ArrayList<Employee>();
    	
    	Employee emp=new Employee("AmyLee");
    	Employee emp2=new Employee("Surendra");
    	//100000 employees
    	
    	
    	managerBucket.add(emp);
    	managerBucket.add(emp2);
    	
    	staff.put("Manager",managerBucket);
    	
    	
    	//seearching for employee
    	ArrayList<Employee> requiredBuccket=staff.get("Manager");    	
    	requiredBuccket.indexOf("Surendra");
    	
    	
    	
    	// iterating hashmap
    	for(Map.Entry<String,ArrayList<Employee>> entry:staff.entrySet())
    	{
    		//String key=entry.getKey();
    		ArrayList<Employee> value=entry.getValue();
    		
    		Iterator<Employee> itr=value.iterator();
    		
    		while(itr.hasNext()){
    			Employee temp=itr.next();
    			System.out.println(temp);
    			//System.out.println(temp.toString());
    		}
    		
    		
    		
    		//System.out.println("key="+key+"value="+value);
    	}
    	
    	
    }
}

class Employee
{
	String name;
	private double salary;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee(String n)
	{
		name=n;
		salary=0;	
	}
	
	@Override
	public String toString()
	{
		return "[name="+name+",salary="+salary+"]";	
	}
	
}
