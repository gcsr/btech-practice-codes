import java.util.Scanner;

class modulo
{
int p;
modulo(int x)
{
p=x;
}
int sum(int x,int y)
{
return (x+y)%p;
}
int diff(int x,int y)
{
return (x-y)%p;
}
int mul(int x,int y)
{
return (x*y)%p;
}
int div(int x,int y)
{
return (x/y)%p;
}
}
public class modulotest
{
public static void main(String gcs[])
{
Scanner input=new Scanner(System.in);
System.out.println("enter modulo number");
modulo po=new modulo(input.nextInt());
System.out.println("enter integers");
int x=input.nextInt();
int y=input.nextInt();
System.out.print(po.sum(x,y)+"  "+po.diff(x,y)+"  "+po.mul(x,y)+"  "+po.div(x,y)+"  ");
}
}

