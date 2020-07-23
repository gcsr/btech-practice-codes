import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;


public class FrquentItemsModel extends AbstractTableModel{
	
	
	String[] columnNames;
	
	
	List<Graph> subGraphs;
	
	public FrquentItemsModel(List<Graph> subGraphs,String[] columnNames){
		this.subGraphs=subGraphs;
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
		
		return subGraphs.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		if(arg1==0)
			return subGraphs.get(arg0).input;
		else
			return subGraphs.get(arg0).support;
		
	}
	
}
