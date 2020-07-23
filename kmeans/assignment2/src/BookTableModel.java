import java.util.List;

import javax.swing.table.AbstractTableModel;


public class BookTableModel extends AbstractTableModel{	

    private List<Book> books;
    private String[] columnNames = {"Author Name", "Title Name","Borrower"};

    public BookTableModel(List<Book> books) {
        this.books=books;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return books.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            return books.get(rowIndex).getAuthorName();
        }
        else if(columnIndex==1){
            return books.get(rowIndex).getTitleName();
        }
        
        else if(columnIndex==2){
        	User user=books.get(rowIndex).getBorrower();
            if(user!=null)
            	return user.getUserName();
            else
            	return "";
        }
        return null;
    }


}

