import java.util.ArrayList;
import java.util.List;


public class Item implements Comparable{
	List usersList=new ArrayList<Integer>();
	int[] users;
	//int mo
	
	
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
	
	public int getUserSize(){
		return usersList.size();
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Item item=(Item)arg0;
		
		return this.getUserSize()-item.getUserSize();
	}
	
}
