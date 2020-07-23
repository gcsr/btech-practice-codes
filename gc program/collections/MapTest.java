import java.util.Map;
import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
    	Map<String,Employee>staff=new HashMap<String,Employee>();
    	staff.put("144_25_5464",new Employee("Amey Lee"));
    	staff.put("144_25_5764",new Employee("Amey Lee"));
    	staff.put("144_25_5464",new Employee("Amey Lee"));
    	staff.put("144_25_5464",new Employee("Amey Lee"));
    	
    	for(Map.Entry<String,Employee> entry:staff.entrySet())
    	{
    		String key=entry.getKey();
    		Employee value=entry.getValue();
    		System.out.println("key="+key+"value="+value);
    	}
    	
    	
    }
}

class Employee
{
	String name;
	private double salary;
	
	public Employee(String n)
	{
		name=n;
		salary=0;	
	}
	public String toString()
	{
		return "[name="+name+",salary="+salary+"]";	
	}
	
}
