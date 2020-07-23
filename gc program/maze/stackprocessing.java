public class stackprocessing
{
public static void main(String gcs[])
{
stack io=new stack();

io.push(3,4);
io.push(23,45);
io.push(3,5);
io.push(34,45);
int x=io.lastx();
int y=io.lasty();
System.out.println("last is "+x+"   "+y);
io.pop();
//io.pop();
io.display();
x=io.lastx();
y=io.lasty();
System.out.println("last is "+x+"   "+y);
}
}