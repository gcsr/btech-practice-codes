import java.util.List;

import javax.swing.table.AbstractTableModel;


public class DisplayImprovedTableModel extends AbstractTableModel{
	List<ImprovedApprioryTransaction> list;
	
    private String[] columnNames = {"TransactionItems","support","Search List"};

    public DisplayImprovedTableModel(List<ImprovedApprioryTransaction> list) {
        this.list=list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return list.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
       
        if(columnIndex==0){
            return list.get(rowIndex).tr.toString();
        }
        
        else if(columnIndex==1)
            return list.get(rowIndex).count;    
        else
        	return list.get(rowIndex).displaySearch();
    }


}
