import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Employee {

	String ID;
	String Name;
	String Gender;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
	public void print(List<String> values)
	{
		List<Integer> counts=new LinkedList<Integer>();
		List<String> temp=new LinkedList<String>();
		Iterator<String> itr=values.iterator();
		String one;
		while(itr.hasNext()){
			one=itr.next();
			if(temp.contains(one))
				counts.set(temp.indexOf(one),counts.get(temp.indexOf(one))+1 );
			else{
				temp.add(one);
				counts.add(1);
			}
					
		}
		
		itr=temp.iterator();
		Iterator<Integer> counter=counts.iterator();
		while(itr.hasNext()){
			
			System.out.println(itr.next()+"\t"+counter.next());
			
		}
	}


	
}
