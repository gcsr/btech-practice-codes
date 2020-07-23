import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class TableDisplayModel extends AbstractTableModel{
	List<Job> jobs=new LinkedList<Job>();
	long startTime=0;
    private String[] columnNames = {"Job Number", "Job Time", "Completion Time","Waiting Time"};

    public TableDisplayModel(List jobs) {
        this.jobs=jobs;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return jobs.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	//if(rowIndex==0)
    		//startTime=jobs.get(rowIndex).jobTime;
    	
       if(columnIndex==0)
    	   return jobs.get(rowIndex).jobNumber;
       if(columnIndex==1)
    	   return jobs.get(rowIndex).jobTime;  
       
       if(columnIndex==2)
    	   return jobs.get(rowIndex).serviceTime-jobs.get(rowIndex).arrivalTime;
       if(columnIndex==3)
    	   return (jobs.get(rowIndex).serviceTime-jobs.get(rowIndex).arrivalTime)-jobs.get(rowIndex).jobTime;
       return null;
    }
}
