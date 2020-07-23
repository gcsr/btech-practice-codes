import java.util.List;

import javax.swing.AbstractListModel;


public class BookListModel  extends AbstractListModel{

	List<Book> books;
	
	public BookListModel(List<Book> books){
		this.books=books;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return books.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return books.get(index);
	}

}
