import java.util.*;


class test3
{
Formatter output;
test3()
{
try
{
output=new Formatter("clients.txt");
output.write(234);
}
catch(Exception e)
{
System.out.println("ss");
}
}
}
public class mp
{
public static void main(String gcs[])
{
test3 pp=new test3();
}
}