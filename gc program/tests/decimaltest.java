import java.util.Scanner;
import java.lang.*;;
class decimal
{
int dec(char[] s)
{
int p=0;
int po=0;
for(int i=s.length-1;i>=0;i--)
{
int x=(int)s[i];
if(x<71&&x>64)
p+=(Math.pow(16,po)*(x-55));
else
p+=Math.pow(16,po)*(x-48);
po++;
}
return p;
}
}
public class decimaltest
{
public static void main(String gcs[])
{
Scanner sc=new Scanner(System.in);
System.out.println("enter the hexacode");
String h=sc.nextLine();
char[] a=h.toCharArray();
decimal pp=new decimal();
int k=pp.dec(a);
System.out.println(k);
}
}