package gui;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.JColorChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class tablecellrenderer extends JFrame
{
	public tablecellrenderer()
	{
		setTitle("tablecellrenderer");
		TableModel model=new planettablemodel();

		JTable table=new JTable(model);
	//	table.setDefaultRenderer(Color.class,new colortablecellrenderer());
	//	table.setDefaultEditor(Color.class,new colortablecelleditor());

		table.setRowSelectionAllowed(false);

		JComboBox mooncombo=new JComboBox();

		for(int i=0;i<=20;i++)
			mooncombo.addItem(i);

	    TableColumnModel columnmodel=table.getColumnModel();
	    TableColumn mooncolumn=columnmodel.getColumn(2);
	    mooncolumn.setCellEditor(new DefaultCellEditor(mooncombo));
	    mooncolumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
	    mooncolumn.setHeaderValue(new ImageIcon("cc.jpg"));

        table.setRowHeight(100);
        add(new JScrollPane(table),BorderLayout.CENTER);
	}
}
class planettablemodel extends AbstractTableModel
{
	private String[] columnnames={"planet","radius","moons","gaseous","Color","images"};
	public Object[][] cells={{"mercury",2440.0,0,false,Color.yellow,new ImageIcon("cc.jpg")},
		{"venus",6052.0,0,false,Color.yellow,new ImageIcon("cc.jpg")},
		{"Earth",7000.0,0,true,Color.red,new ImageIcon("cc.jpg")
		},
		{"Mars",33397.0,3,true,Color.red,new ImageIcon("cc.jpg")
		}
		};
		public String getColumnName(int c)
		{
		//	System.out.println("getcolumnname");
			return columnnames[c];
		}
		public Object getValueAt(int r,int c)
		{
		//	System.out.println("getvalueat");
			return cells[r][c];
		}
		public int getColumnCount()
		{
		//	System.out.println("getcolumncount");
			return cells[0].length;
		}
		public int getRowCount()
		{
		//	System.out.println("getrowcount");
			return cells.length;
		}

		public void setValueAt(Object obj,int r,int c)
		{
		//	System.out.println("setvalue at");
			cells[r][c]=obj;
		}
		public Class getColumnClass(int c)
		{
		//	System.out.println("getcolumnclass");
			return cells[0][c].getClass();
		}
		public boolean isCellEditable(int r,int c)
		{
		//	System.out.println("iscelleditable");
			return c==0||c==2||c==3||c==4;
		}

}
class colortablecellrenderer extends JPanel implements TableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isselected,boolean hasfocus,int row,int col)
	{
		//System.out.println("renderer");
		setBackground((Color)value);
		if(hasfocus)
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			else
				setBorder(null);
				return this;
	}

}

class colortablecelleditor extends AbstractCellEditor implements TableCellEditor
{
	JPanel panel;
	JColorChooser colorchooser;
	JDialog colordialog;
	public colortablecelleditor()
	{
		System.out.println("editorcons");
		panel=new JPanel();
		colorchooser=new JColorChooser();
		colordialog=JColorChooser.createDialog(null,"planetcolor",false,colorchooser,
		new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("yes");
				stopCellEditing();
			}
		},
		new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("no");
				cancelCellEditing();
			}
		});
	}
	public Component getTableCellEditorComponent(JTable table,
	Object value,boolean isselected,int row,int column)
	{
		System.out.println("editor");
		colorchooser.setColor((Color)value);
		return panel;
	}
	public Object getCellEditorValue()
	{
		System.out.println("getcelleditorvalue");
		return colorchooser.getColor();
	}
	public boolean shouldSelectCell(EventObject event)
	{
		System.out.println("shouldselectvalue");
		colordialog.setVisible(true);
		return true;
	}
	public void cancelCellEditing()
	{
		System.out.println("cancelediting");
		colordialog.setVisible(false);
		super.cancelCellEditing();
	}
	public boolean stopCellEditing()
	{
		System.out.println("stopediting");
		colordialog.setVisible(false);
		super.stopCellEditing();
		return true;
	}



}