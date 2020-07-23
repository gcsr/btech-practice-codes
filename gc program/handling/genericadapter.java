import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;


public class genericadapter implements ActionListener
{
public Object listener;
public String mname;
public Method method;
public Object arg=new Object();
public Class[] ctype={ActionEvent.class};
public genericadapter(Object listener,String mname)
{
this.listener=listener;
this.mname=mname;
try
{
method=listener.getClass().getMethod(mname,ctype);
}
catch(NoSuchMethodException e)
{
System.out.println("method not found");
}
catch(SecurityException e)
{
System.out.println("security exception");
}
}
public void actionPerformed(ActionEvent event)
{
arg=event;
try
{
method.invoke(listener,arg);
}
catch(NullPointerException e)
{
System.out.println("null object  null method");
}
catch(IllegalAccessException e)
{
System.out.println("methos cannot be legally accessed");
}
catch(IllegalArgumentException e)
{
System.out.println("bad arguments exception");
}
catch(InvocationTargetException e)
{
System.out.println("exception thrown by method");
}
}
}