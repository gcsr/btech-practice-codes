import java.util.List;

import javax.swing.AbstractListModel;


public class UserListModel  extends AbstractListModel{

	UserRating[] users;
	
	public UserListModel(UserRating[] users){
		this.users=users;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return users.length;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return users[index].getUserId();
	}

}
