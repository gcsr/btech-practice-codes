import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;


public class adjustmentexample extends Applet
{
private ScrollPane spane=new ScrollPane();

private Scrollbar sbar=new Scrollbar(Scrollbar.HORIZONTAL);

public void init()
{
setLayout(new BorderLayout());
sbar.setValues(0,10,0,400);
sbar.setUnitIncrement(10);
sbar.setBlockIncrement(20);
add(sbar,"North");
spane.add(new pp(),"Center");
add(spane,"Center");
sbar.addAdjustmentListener(new debugadjustmentlistener());
spane.getHAdjustable().addAdjustmentListener(new debugadjustmentlistener());
spane.getVAdjustable().addAdjustmentListener(new debugadjustmentlistener());
}
class pp extends Panel
{
public pp()
{
for(int i=0;i<25;++i)
add(new Button("Button"+i));
}
}
class debugadjustmentlistener implements AdjustmentListener
{
public void adjustmentValueChanged(AdjustmentEvent e)
{
Object obj=e.getSource();
System.out.println(obj.toString());
}
}
}