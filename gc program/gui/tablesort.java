package gui;

import javax.swing.JFrame;
import javax.swing.table.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;




public class tablesort extends JFrame
{
	public Object[][] cells={{"mercury",2440.0,0,false,Color.yellow},
		{"venus",6052.0,0,false,Color.yellow},
		{"sachin",7000.0,0,true,Color.red
		}
		};
		private String[] columnnames={"planet","radius","moons","gaseous","Color"};

	public tablesort()
	{


		setTitle("table sort");

		DefaultTableModel model=new DefaultTableModel(cells,columnnames);
		final sfiltermodel sorter=new sfiltermodel(model);

		final JTable table=new JTable(sorter);
		add(new JScrollPane(table),BorderLayout.CENTER);
		table.getTableHeader().addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount()<2)
					return;

					int tcolumn=table.columnAtPoint(e.getPoint());

						int mcolumn=table.convertColumnIndexToModel(tcolumn);
						sorter.sort(mcolumn);
			}
		});
	}
	class sfiltermodel extends AbstractTableModel
	{
		TableModel model;
		private int sortcolumn;
		private Row[] rows;

		public sfiltermodel(TableModel m)
		{
			model=m;
			rows=new Row[model.getRowCount()];

			for(int i=0;i<rows.length;i++)
			{
				rows[i]=new Row();
				rows[i].index=i;
			}

		}
		public void sort(int c)
		{
			sortcolumn=c;
			Arrays.sort(rows);
			fireTableDataChanged();
		}

		public Object getValueAt(int r,int c)
		{
			return model.getValueAt(rows[r].index,c);
		}
		public boolean isCellEditable(int r,int c)
		{
			return model.isCellEditable(rows[r].index,c);
		}
		public void setValueAt(Object avalue,int r,int c)
		{
			model.setValueAt(avalue,rows[r].index,c);
		}
		public int getRowCount()
		{return model.getRowCount();}
		public int getColumnCount()
		{
			return model.getColumnCount();
		}
		public String getColumnName(int c)
		{
			return model.getColumnName(c);
		}
		public Class getColumnClass(int c)
		{
			return model.getColumnClass(c);
		}

		private class Row implements Comparable<Row>
		{
			public int index;

			public int compareTo(Row other)
			{
				Object a=model.getValueAt(index,sortcolumn);
				Object b=model.getValueAt(other.index,sortcolumn);
				if(a instanceof Comparable)
					return ((Comparable)a).compareTo(b);
					else
						return a.toString().compareTo(b.toString());
			}
		}

	}
}