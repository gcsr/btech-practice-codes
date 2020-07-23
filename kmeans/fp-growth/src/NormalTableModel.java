import java.util.List;

import javax.swing.table.AbstractTableModel;


public class NormalTableModel extends AbstractTableModel{
	
	
	String[] columnNames;
	
	List<Normal> normals;
	
	public NormalTableModel(List<Normal> normals,String[] columnNames){
		this.normals=normals;
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
		
		return normals.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		if(arg1==0)
			return normals.get(arg0).str;
		else
			return normals.get(arg0).value;
		
	}
	
}
