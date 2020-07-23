import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class DisplayTable extends JFrame{
	public DisplayTable(double[][] data,String[] columnNames){
		super("statistics");
		setSize(500,400);
		setLocation(300,200);
		
		Object[][] datad = new Object[data.length][data[0].length];
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				datad[i][j]=(int)data[i][j];
			}
		}
		
		JTable table = new JTable(datad, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		setVisible(true);
	}
}
