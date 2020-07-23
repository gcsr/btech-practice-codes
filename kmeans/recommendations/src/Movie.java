import java.util.ArrayList;
import java.util.List;


public class Movie implements Comparable{
	
	private int id;
	private String name;
	private double[] genreValues=new double[19];
	List usersList=new ArrayList<Integer>();
	int[] users;
	
	public Movie(){
		
	}
	
	public Movie(int id){
		this.id=id;
		this.name="";
		//this.genreValues=;
	}
	
	public Movie(int id, String name,double[] genreValues){
		this.id=id;
		this.name=name;
		this.genreValues=genreValues;
		/*System.out.println();
		for(double x:genreValues){
			System.out.print("\t"+x);
		}
		System.out.println();*/
	}
	
	public void addUser(int user){
		usersList.add(user);
	}
	public void filter(int[] users){
		int size=usersList.size();
		this.users=users;
		for(int i=0;i<size;i++){
			Integer s=(Integer)usersList.get(i);
			if(!ifExists(s.intValue())){
				usersList.remove(i);
				size=size-1;
				i-=1;
			}
		}
		
		
	}
	
	private boolean ifExists(int s){
		for(int x:users){
			if(x==s)
				return true;
		}
		return false;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double[] getGenreValues() {
		/*System.out.println();
		for(double x:genreValues){
			System.out.print("\t"+x);
		}
		System.out.println();*/
		return genreValues;
	}
	public void setGenreValues(double[] genreValues) {
		this.genreValues = genreValues;
	}
	
	public int getUserSize(){
		return usersList.size();
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Movie item=(Movie)arg0;
		
		return item.getUserSize()-this.getUserSize();
	}

}
