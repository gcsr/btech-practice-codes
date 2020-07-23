
package gui;


import javax.swing.*;

import java.util.*;

import java.awt.*;

import java.awt.event.*;
import javax.swing.event.*;

import javax.swing.tree.*;

import java.lang.reflect.*;



public class classbrowser extends JFrame
{
	JTree tree;
	DefaultTreeModel model;
	JTextField tfield;
	DefaultMutableTreeNode root;
	JTextArea textarea;
	
	public classbrowser()
	{
		setTitle("classtree");
		
		root=new DefaultMutableTreeNode(java.lang.Object.class);
		model=new DefaultTreeModel(root);
		tree=new JTree(model);
		addclass(getClass());
		
		
		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent e)
			{
				TreePath path=tree.getSelectionPath();
				if(path==null)
				return;
				DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				Class c=(Class)selectednode.getUserObject();
				String des=getfiledescription(c);
				 textarea.setText(des);
				
				
			}
		});
		
		int mode=TreeSelectionModel.SINGLE_TREE_SELECTION;
		tree.getSelectionModel().setSelectionMode(mode);
		
		textarea=new JTextArea();
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JScrollPane(tree));
		panel.add(new JScrollPane(textarea));
		
		add(panel,BorderLayout.CENTER);
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
	
	public String getfiledescription(Class s)
	{
		StringBuilder r=new StringBuilder();
		Field[] fields=s.getDeclaredFields();
		
		for(Field fs:fields)
		{
			if((fs.getModifiers()&Modifier.STATIC)!=0)r.append("static");
			r.append(fs.getType().getName());
			r.append("   ");
			r.append(fs.getName());
			r.append("\n");
		}
		return r.toString();
	}
	
}

