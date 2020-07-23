import java.util.Scanner;


class hexa
{
int num;
char bn[];
hexa(int s)
{
num=s;
bn=new char[10];
}
char[] convert()
{
int i=0;
while(num>0)
{
int x=num%16;
if(x<=9)
bn[i]=(char)(48+x);
else
bn[i]=(char)(x+55);
num=num/16;
i++;
}
return bn;
}
}
public class hexatest
{
public static void main(String gcs[])
{
Scanner input=new Scanner(System.in);
System.out.println("enter number");
int b=input.nextInt();
hexa po=new hexa(b);
char x[]=po.convert();
for(int v=x.length-1;v>=0;v--)
{
if((int)x[v]!=0)
System.out.print(x[v]);
}
}
}