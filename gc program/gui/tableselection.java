
package gui;




import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.table.TableColumnModel;

public class tableselection extends JFrame
{
	DefaultTableModel model;
	JTable table;
	private ArrayList<TableColumn> removedcolumns;
	public tableselection()
	{
		setTitle("more about tables");
		model=new DefaultTableModel(10,10);
		table=new JTable(model);
		add(new JScrollPane(table),"Center");
		for(int i=0;i<model.getRowCount();i++)
			for(int j=0;j<model.getColumnCount();j++)
				model.setValueAt((i+1)*(j+1),i,j);

				removedcolumns=new ArrayList<TableColumn>();

				JMenuBar menubar=new JMenuBar();
				setJMenuBar(menubar);

				JMenu selectionmenu=new JMenu("selection");
				menubar.add(selectionmenu);
				final JCheckBoxMenuItem rowsitem=new JCheckBoxMenuItem("rows");
				final JCheckBoxMenuItem columnsitem=new JCheckBoxMenuItem("columns");
				final JCheckBoxMenuItem cellsitem=new JCheckBoxMenuItem("cells");

				rowsitem.setSelected(table.getRowSelectionAllowed());
				columnsitem.setSelected(table.getColumnSelectionAllowed());
				cellsitem.setSelected(table.getCellSelectionEnabled());
				selectionmenu.add(rowsitem);
				selectionmenu.add(columnsitem);
				selectionmenu.add(cellsitem);

				rowsitem.addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							table.clearSelection();
							table.setRowSelectionAllowed(rowsitem.isSelected());
							cellsitem.setSelected(table.getCellSelectionEnabled());
						}
					});

				columnsitem.addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							table.clearSelection();
							table.setColumnSelectionAllowed(columnsitem.isSelected());
							cellsitem.setSelected(table.getCellSelectionEnabled());
						}
					});

				cellsitem.addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							table.clearSelection();
							table.setCellSelectionEnabled(cellsitem.isSelected());
							rowsitem.setSelected(table.getRowSelectionAllowed());
							columnsitem.setSelected(table.getColumnSelectionAllowed());
						}
					});

			JMenu tablemenu=new JMenu("edit");
			menubar.add(tablemenu);

			JMenuItem hidecolumnsitem=new JMenuItem("hide columns");
			hidecolumnsitem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int[] selected=table.getSelectedColumns();
					TableColumnModel columnmodel=table.getColumnModel();

					for(int i:selected)
					{
						TableColumn column=columnmodel.getColumn(i);
						table.removeColumn(column);
						removedcolumns.add(column);

					}
				}
			});

          tablemenu.add(hidecolumnsitem);

          JMenuItem showcolumnsitem=new JMenuItem("show columns");

          showcolumnsitem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{

					for(TableColumn i:removedcolumns)
					{

						table.addColumn(i);


					}
					removedcolumns.clear();
				}
			});

			tablemenu.add(showcolumnsitem);

			JMenuItem addrowitem=new JMenuItem("add row");

          addrowitem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{


						Integer[] newcells=new Integer[model.getColumnCount()];
						for(int s=0;s<newcells.length;s++)
							newcells[s]=(s+1)*(model.getRowCount()+1);
							model.addRow(newcells);




				}
			});
			tablemenu.add(addrowitem);





	}

}