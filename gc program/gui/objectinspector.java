package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.tree.*;
import java.util.*;
import java.lang.reflect.*;
import javax.swing.event.*;


public class objectinspector extends JFrame
{
	private JTree tree;

	public objectinspector()
	{
		setTitle("objectinspector");

		Variable v=new Variable(getClass(),"this",this);

		objecttreemodel model=new objecttreemodel();
		model.setroot(v);
		tree=new JTree(model);
		add(new JScrollPane(tree),BorderLayout.CENTER);
	}
	class objecttreemodel implements TreeModel
	{
		private Variable root;
		private EventListenerList listenerList=new EventListenerList();
		public objecttreemodel()
		{
			root=null;
		}
		public void setroot(Variable v)
		{
			System.out.println("setroot");
			Variable oldroot=v;
			root=v;
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
			return i;

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
			System.out.println("child count is "+((Variable)parent).getFields().size());
			return ((Variable)parent).getFields().size();
		}
		public Object getChild(Object parent,int index)
		{
			System.out.println("getchild");
			ArrayList<Field> fields=((Variable)parent).getFields();

			Field f=(Field)fields.get(index);

			Object parentvalue=((Variable)parent).getValue();
			try
			{
				return new Variable(f.getType(),f.getName(),f.get(parentvalue));
			}
			catch(IllegalAccessException e)
			{
				return null;
			}
		}
		public Object getRoot()
		{
			System.out.println("getroot");
			return root;
		}

		protected void fireTreeStructureChanged(Object oldroot)
		{
			System.out.println("firetreestructurechanged");
			TreeModelEvent event=new TreeModelEvent(this,new Object[]{oldroot});
			EventListener[] listeners=listenerList.getListeners(TreeModelListener.class);
			for(int i=0;i<listeners.length;i++)
			{
				((TreeModelListener)listeners[i]).treeStructureChanged(event);
			}

        }
    }

	class Variable
	{
		Class type;
		String name;
		Object value;
		ArrayList<Field> fields;

		public Variable(Class atype,String aname,Object avalue)
		{
			System.out.println("variable constructor");
			type=atype;
			name=aname;
			value=avalue;
			fields=new ArrayList<Field>();

			if(!type.isPrimitive()&&!type.isArray()&&!type.equals(String.class)&&value!=null)
			{
				for(Class c=value.getClass();c!=null;c=c.getSuperclass())
				{

				Field[] fs=c.getDeclaredFields();
				AccessibleObject.setAccessible(fs,true);

				for(Field f:fs)
				{

				if((f.getModifiers()&Modifier.STATIC)==0)
				fields.add(f);
				}
		 		}
		 	}
		}
		public Object getValue()
		{
			//System.out.println("value is "+value);
			return value;
		}
		public ArrayList<Field> getFields()
		{
			//System.out.println("getfields");
			return fields;
		}
		public String toString()
		{
			//System.out.println("toString");
			String r=type+""+name;

			if(type.isPrimitive())
			r += "="+value;

			else if(type.equals(String.class))
			r=r+"="+value;

			else if(value==null)
			r += "=null";
			return r;
		}
	}

}