import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class fontpicker extends Applet
{
private fontpanel fpanel=new fontpanel(this);
private metricpanel mpanel=new metricpanel();
private Label mlabel=new Label();

public void init()
{
System.out.println("init");
setLayout(new BorderLayout());
add(fpanel,"North");
add(mpanel,"Center");
add(mlabel,"South");
}
public void start()
{
System.out.println("start");
Font font=fpanel.getSelectedFont();
updatemetricpanelfont(font);
updatemetricsinfo(font);
}
public void update(Font font)
{
System.out.println("update");
updatemetricpanelfont(font);
updatemetricsinfo(font);
}
public void updatemetricpanelfont(Font font)
{
System.out.println("updatemetricpanelfont");
mpanel.settext(fullnameoffont(font));
mpanel.setFont(font);
mpanel.repaint();
}

public void updatemetricsinfo(Font font)
{
System.out.println("updatemetricsinfo");
FontMetrics fm=getFontMetrics(font);
mlabel.setText("Ascent= "+fm.getAscent()+","+
"descent= "+fm.getDescent()+"leading= "+fm.getLeading());
}


public String fullnameoffont(Font font)
{
System.out.println("fullnameoffont");
String family=font.getFamily();
String style=new String();
switch(font.getStyle())
{
case Font.PLAIN:style="Plain";break;
case Font.BOLD:style="Bold";break;
case Font.ITALIC:style="Italic";break;
case Font.BOLD+Font.ITALIC:style="BoldItalic";break;
}
return family+style+Integer.toString(font.getSize());
}
}

class metricpanel extends Panel
{
private String text;
public void settext(String txt)
{
System.out.println("settext");
text=txt;
}
public void paint(Graphics gc)
{
System.out.println("paint");
Dimension size=getSize();
FontMetrics fm=gc.getFontMetrics();
Point b1=new Point();
int sw=fm.stringWidth(text),
ascent=fm.getAscent(),
descent=fm.getDescent(),leading=fm.getLeading();
gc.drawString(text,20,20);
}
}
class fontpanel extends Panel
{
private fontpicker listtest;
private List flist=new List(),stllist=new List(),sizelist=new List()	;

public fontpanel(fontpicker ap)
{
System.out.println("font panel constructor");
Listener listener=new Listener();
listtest=ap;
add(flist);
add(stllist);
add(sizelist);
populatefonts();
populatestyles();
populatesizes();
flist.addItemListener(listener);
stllist.addItemListener(listener);
sizelist.addItemListener(listener);
stllist.select(0);
flist.select(0);
sizelist.select(0);
}
public class Listener implements ItemListener
{
public void itemStateChanged(ItemEvent e)
{
System.out.println("changed");
listtest.update(getSelectedFont());
}
}
public Font getSelectedFont()
{
return new Font(flist.getSelectedItem(),stllist.getSelectedIndex(),Integer.parseInt(sizelist.getSelectedItem()));
}
public void populatefonts()
{
String fnames[]=getToolkit().getFontList();

for(int i=0;i<fnames.length;++i)
flist.add(fnames[i]);
}
public void populatestyles()
{
stllist.add("Plain");
stllist.add("Bold");
stllist.add("Italic");
stllist.add("BoldItalic");
}
public void populatesizes()
{
String sizes[]={"12","14","16","18","24","36","48"};
for(int i=0;i<sizes.length;++i)
sizelist.add(sizes[i]);
}
}