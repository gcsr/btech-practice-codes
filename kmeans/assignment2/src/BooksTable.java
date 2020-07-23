import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BooksTable extends JPanel{
	public BooksTable(List<Book> books){		
		JTable usersTable=new JTable(new BookTableModel(books));
		JScrollPane scroll=new JScrollPane(usersTable);
		usersTable.setFillsViewportHeight(true);
		add(scroll);		
	}
}