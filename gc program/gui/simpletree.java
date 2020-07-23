
package gui;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import java.awt.Container;

public class simpletree extends JFrame
{
	public simpletree()
	{
		setTitle("simpletree");
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("world");
		DefaultMutableTreeNode country=new DefaultMutableTreeNode("India");
		DefaultMutableTreeNode state=new DefaultMutableTreeNode("AP");
		country.add(state);
		
		DefaultMutableTreeNode city=new DefaultMutableTreeNode("Rayachoty");
	    state.add(city);
	    city=new DefaultMutableTreeNode("Kadapa");
	    state.add(city);
		state=new DefaultMutableTreeNode("UP");
		country.add(state);
		
		root.add(country);
		country=new DefaultMutableTreeNode("Pakistan");
	    root.add(country);
	    JTree tree=new JTree(root);
	    //tree.putClientProperty("JTree.lineStyle","Vertical");
	    
	    Container contentpane=getContentPane();
	    contentpane.add(new JScrollPane(tree));
	    tree.setRootVisible(false);
		System.out.println(tree.getSelectionPath());
		
	}
}