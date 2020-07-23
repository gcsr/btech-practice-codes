package p1;

import javax.swing.JFrame;

public class DesktopFrametest
{
public static void main(String gcs[])
{
DesktopFrame app=new DesktopFrame();
app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
app.setSize(400,400);
app.setVisible(true);
}
}