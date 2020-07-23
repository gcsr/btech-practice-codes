import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UsersTable extends JPanel{
	public UsersTable(List<User> users){			
		JTable usersTable=new JTable(new UserTableModel(users));
		JScrollPane scroll=new JScrollPane(usersTable);
		usersTable.setFillsViewportHeight(true);
		add(scroll);
		
	}
}