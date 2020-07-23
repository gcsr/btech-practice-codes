package gui;


import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Enumeration;
import javax.swing.tree.TreePath;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Component;
import java.lang.reflect.Modifier;
import javax.swing.ImageIcon;

public class classtree extends JFrame
{
	JTree tree;
	DefaultTreeModel model;
	JTextField tfield;
	DefaultMutableTreeNode root;
	
	public classtree()
	{
		setTitle("classtree");
		
		root=new DefaultMutableTreeNode(java.lang.Object.class);
		model=new DefaultTreeModel(root);
		tree=new JTree(model);
		addclass(getClass());
		
		classnametreecellrenderer renderer=new classnametreecellrenderer();
		
		renderer.setClosedIcon(new ImageIcon("dd.jpeg"));
		renderer.setLeafIcon(new ImageIcon("dd.jpeg"));
		renderer.setOpenIcon(new ImageIcon("dd.jpeg"));
		tree.setCellRenderer(renderer);
		
		add(new JScrollPane(tree),BorderLayout.CENTER);
		addtextfield();
	}
	
	public void addtextfield()
	{
		JPanel panel=new JPanel();
		
		ActionListener addlistener=new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String text=tfield.getText();
					addclass(Class.forName(text));
					tfield.setText("");
				}
				catch(ClassNotFoundException ex)
				{
					JOptionPane.showMessageDialog(null,"class not found");
				}
			}
		};
		
		tfield=new JTextField(20);
		tfield.addActionListener(addlistener);
		panel.add(tfield);
		
		JButton button=new JButton("add");
		button.addActionListener(addlistener);
		panel.add(button);
		
		
		add(panel,BorderLayout.SOUTH);
		
	}
	public  DefaultMutableTreeNode addclass(Class c)
	{
		System.out.println("c is "+c);
		if(c.isInterface()||c.isPrimitive())
		return null;
		
		DefaultMutableTreeNode node=finduserobject(c);
		if(node!=null)
		{
		System.out.println("node not null");	
		return node;
		}
		Class s=c.getSuperclass();
		System.out.println("s is "+s);
		
		DefaultMutableTreeNode parent;
		
		if(s==null)
		{
			System.out.println("null");
		parent=root;
		}
		else
		{
			System.out.println("not null");
		parent=addclass(s);
		}
		DefaultMutableTreeNode nnode=new DefaultMutableTreeNode(c);
		model.insertNodeInto(nnode,parent,parent.getChildCount());
		
		TreePath tpath=new TreePath(model.getPathToRoot(nnode));
		
		tree.makeVisible(tpath);
		return nnode;
		
	}
	 
	public DefaultMutableTreeNode finduserobject(Object obj)
	{
		Enumeration e=root.breadthFirstEnumeration();
		while(e.hasMoreElements())
		{
			DefaultMutableTreeNode node=(DefaultMutableTreeNode)e.nextElement();
			if(node.getUserObject().equals(obj))
			return node;
		}
		return null;
	}
}

class classnametreecellrenderer extends DefaultTreeCellRenderer
{
	public Component getTreeCellRendererComponent(JTree tree,Object value,
	boolean selected,boolean expanded,boolean leaf,int row,boolean hasfocus)
	{
		super.getTreeCellRendererComponent(tree,value,selected,
		expanded,leaf,row,hasfocus);
		System.out.println("renderung");
		
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
		
		Class c=(Class)node.getUserObject();
		
		
		if(plainFont==null)
		{
			plainFont=getFont();
			if(plainFont!=null)
			italicFont=plainFont.deriveFont(Font.ITALIC);
			
		}
		
		if((c.getModifiers()&Modifier.ABSTRACT)==0)
		setFont(plainFont);
		else
		setFont(italicFont);
		
		return this;
		
	}
	
	Font plainFont=null;
	Font italicFont=null;
	
}