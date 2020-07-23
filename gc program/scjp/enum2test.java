enum enum2
{
big(8),huge(10),overwhelming(16);
enum2(int ounces)
{
this.ounces=ounces;
}
private int ounces;

public int getounces()
{
return ounces;
}
}
class enum2test
{
public static void main(String gcs[])
{

for(enum2 p:enum2.values())
System.out.println(p.getounces());
}
}