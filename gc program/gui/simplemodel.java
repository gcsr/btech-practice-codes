package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.tree.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.event.*;





	

public class simplemodel extends JFrame
{
	private JTree tree;
	ArrayList<DefaultMutableTreeNode> fields=new ArrayList<DefaultMutableTreeNode>();
	
	public simplemodel()
	{
		setTitle("objectinspector");
		
		DefaultMutableTreeNode root=makesampletree();
		objecttreemodel model=new objecttreemodel();
		model.setroot(root);
		tree=new JTree(model);
		JScrollPane scrollpane=new JScrollPane(tree);
		add(scrollpane,BorderLayout.CENTER);
		
		
	}
	public DefaultMutableTreeNode makesampletree()
	{
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("world");
		fields.add(root);
		DefaultMutableTreeNode country=new DefaultMutableTreeNode("India");
		fields.add(country);
		DefaultMutableTreeNode state=new DefaultMutableTreeNode("AP");
		fields.add(state);
		country.add(state);
		
		DefaultMutableTreeNode city=new DefaultMutableTreeNode("Rayachoty");
		fields.add(city);
	    state.add(city);
	    city=new DefaultMutableTreeNode("Kadapa");
	    fields.add(city);
	    state.add(city);
		city=new DefaultMutableTreeNode("good");
		fields.add(city);
	    state.add(city);
		
		state=new DefaultMutableTreeNode("UP");
		fields.add(state);
		country.add(state);
		root.add(country);
		country=new DefaultMutableTreeNode("Pakistan");
		fields.add(country);
	    root.add(country);
	   
	    
		return root;
	}
	
	class objecttreemodel implements TreeModel
	{

	    DefaultMutableTreeNode root;
		
		private EventListenerList listenerList=new EventListenerList();
		
		public objecttreemodel()
		{
			root=null;
		}
		
		public void setroot(DefaultMutableTreeNode v)
		{
			System.out.println("setroot");
			DefaultMutableTreeNode oldroot=v;
			root=v;
			System.out.println("root is "+root);
			fireTreeStructureChanged(oldroot);
		}
		
		public void addTreeModelListener(TreeModelListener l)
		{ 
            System.out.println("addtreemodelistener");	
            listenerList.add(TreeModelListener.class,l);
            
        }
		
		public void removeTreeModelListener(TreeModelListener l)
		{
			System.out.println("removetreemodelistenerlistener");
			listenerList.remove(TreeModelListener.class,l);
			
		}
		
		public int getIndexOfChild(Object parent,Object child)
		{
			System.out.println("getindexofchild");
			int n=getChildCount(parent);
			
			for(int i=0;i<n;i++)
			if(getChild(parent,i).equals(child))
			{
				System.out.println("index is "+i);
			return i;
			}
			return -1;
		}
		
		public void valueForPathChanged(TreePath path,Object newvalue)
		{
			
			System.out.println("valueforpathchanged");
		}
		
		public boolean isLeaf(Object parent)
		{
			System.out.println("isleaf");
			return getChildCount(parent)==0;
		}
		
		public int getChildCount(Object parent)
		{
			System.out.println("getchildcount");
			System.out.println("child count is "+fields.size());
			return fields.size();
		}
		
		public Object getChild(Object parent,int index)
		{
			//System.out.println("getchild");
						
			 DefaultMutableTreeNode f=(DefaultMutableTreeNode)fields.get(index);
			
			
			return f;
		}
		
		public Object getRoot()
		{
			//System.out.println("getroot");
			return root;
		}
		
		protected void fireTreeStructureChanged(Object oldroot)
		{
			System.out.println("firetreestructurechanged");
			TreeModelEvent event=new TreeModelEvent(this,new Object[]{oldroot});
			System.out.println("event is "+event);
			
			EventListener[] listeners=listenerList.getListeners(TreeModelListener.class);
			
			System.out.println("number of listeners is  "+listeners.length);
			
			for(int i=0;i<listeners.length;i++)
			{
				((TreeModelListener)listeners[i]).treeStructureChanged(event);
			}
    
        }
    }    
	
	
}