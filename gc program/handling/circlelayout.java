package gui;

import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;

public class circlelayout implements LayoutManager
{
public void layoutContainer(Container parent)
{
System.out.println("layoutcontainer");


int n=parent.getComponentCount();
System.out.println(n);	

	
System.out.println(parent.getSize());


for(int i=0;i<n;i++)
{
Component c=parent.getComponent(i);


if(c.isVisible())
{
Dimension d=c.getPreferredSize();
System.out.println(d);
//c.setBounds(0,0,d.width,d.height);
System.out.println(c.getBounds());
}
}





}


public Dimension minimumLayoutSize(Container parent)
{
System.out.println("minimumlayoutsize");		
return new Dimension(3,3);
              
}
public Dimension preferredLayoutSize(Container parent)
{
System.out.println("preferredlayoutsize");		
return new Dimension(3,3);
}
public void removeLayoutComponent(Component comp)
{
System.out.println("removelayoutcomponent");		
}
public void addLayoutComponent(String name,Component comp)
{
System.out.println("addlayoutcomponent");		
}
}