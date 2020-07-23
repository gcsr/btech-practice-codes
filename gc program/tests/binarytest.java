import java.util.Scanner;


class binary
{
int num,bn[];
binary(int s)
{
num=s;
bn=new int[10];
}
int[] convert()
{
int i=0;
while(num>0)
{
bn[i]=num%2;
num=num/2;
i++;
}
return bn;
}
}
public class binarytest
{
public static void main(String gcs[])
{
Scanner input=new Scanner(System.in);
System.out.println("enter number");
int b=input.nextInt();
binary po=new binary(b);
int x[]=po.convert();
for(int v=x.length-1;v>=0;v--)
System.out.print(x[v]);
}
}