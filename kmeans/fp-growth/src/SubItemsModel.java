import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;


public class SubItemsModel extends AbstractTableModel{
	
	
	String[] columnNames;	
	Graph[][] subSubGraphs;
	
	public SubItemsModel(Graph[][] subSubGraphs,String[] columnNames){
		this.subSubGraphs=subSubGraphs;
		this.columnNames=columnNames;
	}
	
	 @Override
	public String getColumnName(int column) {
	        return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		
		return subSubGraphs.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		if(arg1==0)
			return subSubGraphs[arg0][0];
		else
			return subSubGraphs[arg0][1];
		
	}
	
}
