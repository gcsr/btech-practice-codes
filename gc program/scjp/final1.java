class final2
{
int p=90;
void method()
{
p=89;
}
}
public class final1
{
public static void main(String gcs[])
{
final final2 ss=new final2();
ss.p=99;
ss.method();
}
}