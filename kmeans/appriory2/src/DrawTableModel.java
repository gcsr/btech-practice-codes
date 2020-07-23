import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;




public class DrawTableModel extends AbstractTableModel{
		
    private String[] columnNames;
    private int columnCount;
    Results rs1,rs2;
    int counter=0;

    public DrawTableModel(Results rs1,Results rs2) {
        counter=0;
        this.rs1=rs1;
        this.rs2=rs2;
        columnCount=rs1.instCount*2+1;
        columnNames=new String[columnCount];
        columnNames[0]="parameter";
        for(int i=0;i<rs1.instCount;i++){
        	columnNames[1+2*i]="Appriory "+i;
        	columnNames[1+2*i+1]="Improved Appriory "+i;
        }    
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	if(rowIndex==0){
    		 if(columnIndex==0){
    			 counter=0;
    	            return "Times(ms)";
    	            
    	     }
    		 else{
    			 if(columnIndex%2==0){
    				 counter++;
    				 return rs2.times[counter-1];    				 
    			 }
    			 else{
    				 return rs1.times[counter];
    			 }
    		 }
    	       
    	}
       
    	else{
    		 if(columnIndex==0){
    			 counter=0;
 	            return "Statements";
     		 }
    		 
    		 else{
    				
    			 if(columnIndex%2==0){
    					 counter++;
        				 return rs2.instructions[counter-1];    				 
        		 }
        		 else{
        			 return rs1.instructions[counter];
        		 }
    			
    		 }
    	}        
       
    }
}
