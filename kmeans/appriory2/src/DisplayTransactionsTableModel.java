import java.awt.event.WindowListener;

import javax.swing.table.AbstractTableModel;




public class DisplayTransactionsTableModel extends AbstractTableModel{
	private Transaction[]  transactions;
	
    private String[] columnNames = {"Transaction Id", "TransactionItems"};

    public DisplayTransactionsTableModel(Transaction[] transactions) {
        this.transactions=transactions;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return transactions.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex==0){
            return "T"+rowIndex;
        }
        else if(columnIndex==1){
            return transactions[rowIndex].toString();
        }     
        
        return null;
       
    }



}
