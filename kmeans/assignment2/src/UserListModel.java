import java.util.List;

import javax.swing.AbstractListModel;


public class UserListModel  extends AbstractListModel{

	List<User> users;
	
	public UserListModel(List<User> users){
		this.users=users;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return users.get(index);
	}

}
