import java.util.List;
import javax.swing.table.AbstractTableModel;


public class UserTableModel extends AbstractTableModel{	

    private List<User> users;
    private String[] columnNames = {"User Name", "Book1","Book2","Book3"};

    public UserTableModel(List<User> users) {
        this.users=users;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return users.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            return users.get(rowIndex).getUserName();
        }
        else if(columnIndex>0){
        	Book book=users.get(rowIndex).getBook(columnIndex-1);
            if(book!=null)
            	return book.getAuthorName()+book.getTitleName();
            else
            	return "";
            		
            
        }
        return null;
    }


}

