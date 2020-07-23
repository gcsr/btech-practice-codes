package gui;



import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class planettable extends JFrame
{
	private Object[][] cells={
	{"mercury",2440.0,0,false,Color.yellow},
	{"mercury",2440.0,0,false,Color.yellow},
	{"mercury",2440.0,0,false,Color.yellow},
	{"mercury",2440.0,0,false,Color.yellow},
	{"mercury",2440.0,0,false,Color.yellow},
	{"mercury",2440.0,0,false,Color.yellow}

	};
	private String[] columnnames={"planet","radius","moons","gaseous","color"};

	public planettable()
	{
		setTitle("simple table");
        setLayout(new BorderLayout());
		JTable table =new JTable(cells,columnnames);
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
}