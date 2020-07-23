import javax.swing.table.AbstractTableModel;


public class ResultsTableModel extends AbstractTableModel{
	
	double[] lifts;
	double[] MDCGS;
	
	String[] columnNames;
	
	public ResultsTableModel(double[] lifts,double[] MDCGS,String[] columnNames){
		this.lifts=lifts;
		this.MDCGS=MDCGS;
		this.columnNames=columnNames;
	}
	
	 @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		
		return lifts.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		if(arg1==0){
			return arg0+1;
		}
		
		else if(arg1==1){
			//return 10;
			return Math.log10(arg0+1)/Math.log10(2);
		}
		
		else if(arg1==2){
			return "R"+(arg0+1);
		}
		
		else if(arg1==3){
			return lifts[arg0];
		}
		
		else if(arg1==4){
			//return 20;
			if(arg0==0)
				return lifts[arg0];
			else
				return (lifts[arg0])/(Math.log10(arg0+1)/Math.log10(2));
				
		}
		
		else if(arg1==5){
			return MDCGS[arg0];
		}

		
		
		return null;
	}
	
}
