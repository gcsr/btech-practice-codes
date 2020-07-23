package gui;


import javax.swing.JFrame;
import javax.swing.tree.TreeNode;
import javax.swing.tree.*;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;


public class treeedit extends JFrame
{
	DefaultTreeModel model;
	private JTree tree;
	public treeedit()
	{
		setTitle("treeedit frame");
		
		
		
		TreeNode root=makesampletree();
		model=new DefaultTreeModel(root);
		tree=new JTree(model);
		tree.setEditable(true);
		JScrollPane scrollpane=new JScrollPane(tree);
		add(scrollpane,BorderLayout.CENTER);
		makebuttons();
		
	}
	public TreeNode makesampletree()
	{
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("world");
		DefaultMutableTreeNode country=new DefaultMutableTreeNode("India");
		DefaultMutableTreeNode state=new DefaultMutableTreeNode("AP");
		country.add(state);
		
		DefaultMutableTreeNode city=new DefaultMutableTreeNode("Rayachoty");
	    state.add(city);
	    city=new DefaultMutableTreeNode("Kadapa");
	    state.add(city);
		city=new DefaultMutableTreeNode("good");
	    state.add(city);
		
		state=new DefaultMutableTreeNode("UP");
		country.add(state);
		root.add(country);
		country=new DefaultMutableTreeNode("Pakistan");
	    root.add(country);
	   
	    
		return root;
	}
	public void makebuttons()
	{
		JPanel panel=new JPanel();
		JButton addsiblingbutton=new JButton("addsibling");
		addsiblingbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				//System.out.println(selectednode);
				if(selectednode==null)return;
				DefaultMutableTreeNode parent=(DefaultMutableTreeNode)selectednode.getParent();
				//System.out.println(parent);
				DefaultMutableTreeNode newnode=new DefaultMutableTreeNode("new");
				
				int selectedindex=parent.getIndex(selectednode);
				//System.out.println(selectedindex);
				
				model.insertNodeInto(newnode,parent,selectedindex+1);
				
				TreeNode[] nodes=model.getPathToRoot(newnode);
				
				//for(TreeNode s:nodes)
				//System.out.println(s);
				
				TreePath path=new TreePath(nodes);
				//System.out.println(path);
				tree.scrollPathToVisible(path);
				
				
				
				
				
			}
		});
		panel.add(addsiblingbutton);
		JButton addchildbutton=new JButton("addchild");
		panel.add(addchildbutton);
		addchildbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				//System.out.println(selectednode);
				if(selectednode==null)return;
				
				DefaultMutableTreeNode newnode=new DefaultMutableTreeNode("new");
				
				
				//System.out.println(selectedindex);
				
				model.insertNodeInto(newnode,selectednode,selectednode.getChildCount());
				
				TreeNode[] nodes=model.getPathToRoot(newnode);
				
				
				
				//for(TreeNode s:nodes)
				//System.out.println(s);
				
				TreePath path=new TreePath(nodes);
				//System.out.println(path);
				tree.scrollPathToVisible(path);
				
				
				
				
				
			}
		});
		
		
		
		JButton deletebutton=new JButton("delete");
		panel.add(deletebutton);
		deletebutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				
				if((selectednode!=null)&&selectednode.getParent()!=null)
				model.removeNodeFromParent(selectednode);
				
				
						
				
			}
		});
		
		
		add(panel,BorderLayout.SOUTH);
		
		
		
		
	}
}