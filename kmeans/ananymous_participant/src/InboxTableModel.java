import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class InboxTableModel extends AbstractTableModel{	

    private ArrayList<Message> messages;
    private String[] columnNames = {"S. No","From", "To","Message"};

    public InboxTableModel(ArrayList<Message> messages) {
        this.messages=messages;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return messages.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	if(columnIndex==0){
            return ""+(rowIndex+1);
         }
        if(columnIndex==1){
           return messages.get(rowIndex).getFrom();
        }
        else if(columnIndex==2){
        	return messages.get(rowIndex).getTo();            
        }else if(columnIndex==3){
        	Message msg=messages.get(rowIndex);
        	
        	return msg.getMessage();
        }
        return null;
    }


}

