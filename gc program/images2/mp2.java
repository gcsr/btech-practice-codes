import java.io.*;

public class mp2
{
public static void main(String gcs[])
{
try
{
FileOutputStream fout=new FileOutputStream("myfile.txt");
fout.write(123);
fout.write(1245);

fout.close();
}
catch(Exception e)
{
}
}
}