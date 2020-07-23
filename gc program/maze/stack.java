public class stack
{
public node head;
public node first;
stack()
{
head=new node();
head=null;
}

public void push(int x,int y)
{
System.out.println("push");
node pp=new node();
pp.putx(x);
pp.puty(y);
pp.next=null;

if(head==null)
{
head=pp;
first=pp;
}
else
first.next=pp;
first=pp;
}


public void pop()
{
System.out.println("pop");
node rem=head;

while(rem.next.next!=null)
{

rem=rem.next;
}
rem.next=null;
first=rem;
}

public void display()
{
node dis=head;

while(dis!=null)
{
System.out.print(dis.getx()+"      ");
System.out.println(dis.gety());
dis=dis.next;

}
}
public int lastx()
{
return first.getx();
}
public int lasty()
{
return first.gety();
}
}