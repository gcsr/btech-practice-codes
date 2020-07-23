import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;




public class DisplayApprioryTableModel extends AbstractTableModel{
	List<ApprioryTransaction> list;
	
    private String[] columnNames = {"TransactionItems","support"};

    public DisplayApprioryTableModel(List<ApprioryTransaction> list) {
        this.list=list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return list.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
       
        if(columnIndex==0){
            return list.get(rowIndex).tr.toString();
        }
        
        else
            return list.get(rowIndex).count;    
       
    }



}
