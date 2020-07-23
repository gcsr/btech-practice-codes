import java.util.ArrayList;


public class alist
{
public static void main(String gcs[])
{
ArrayList<Integer> list=new ArrayList<Integer>();
list.add(90);
list.add(100);
list.add(110);
list.add(120);
list.remove(3);
for(Integer s:list)
System.out.println(s);
}
}