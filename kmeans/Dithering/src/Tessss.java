import java.util.ArrayList;
import java.util.List;
import java.util.AbstractList;


public class Tessss {
	
	public static void main(String[] gcs){
		List<String> names = new ArrayList();

		names.add("James");
		names.add("June");
		names.add("John");
		
		String[] na=new String[4];
		names.toArray(na);

		for(String name: names) {
			names.remove("June");
		  if(name == "James") {
		    System.out.println("James is in the list.");
		    //names.set(0,"pu");
		    //names[0]="Pu";
		    names.set(0, "Pu");//remove("June");
		  }
		}
		
		for(String name: names) {
			 
			    System.out.println(name);
			 
			}
		
		//names.remove("James");
		
		
	}

}
