package gui;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
public class investmenttable extends JFrame
{
	public investmenttable()
	{
		setTitle("investmenttable");
		TableModel model=new  investmenttablemodel(30,5,10);
		JTable table=new JTable(model);
		add(new JScrollPane(table));
	}
	class investmenttablemodel extends AbstractTableModel
	{
		private int years,minrate,maxrate;
		public investmenttablemodel(int y,int r1,int r2)
		{
			years=y;
			minrate=r1;
			maxrate=r2;
		}
		public int getRowCount(){return years;
		}
		public int getColumnCount()
		{
			return (maxrate-minrate+1);
		}
		public Object getValueAt(int r,int c)
		{
			return r*c;
		}
		public String ColumnName()
		{
			return "gc";
		}
	}
}