import java.util.Scanner;

public class inverse
{
public static void main(String gcs[])
{
Scanner input=new Scanner(System.in);

System.out.println("enter row");
int row=input.nextInt();
float[][] a=new float[row][row];
System.out.println("enter the elements");
for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
a[i][j]=input.nextFloat();
}
}

determinant pp=new determinant();
float p=pp.det(a,row,row);

conjugate cgate=new conjugate(a,row,row);
float g[][]=cgate.conjuga();




transpose tpose=new transpose(g,row,row);
float h[][]=tpose.trans();

float[][] ff=new float[row][row];

for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
ff[i][j]=(float)h[i][j];
}
}
System.out.println();
System.out.println(p);
System.out.println();

for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
System.out.print(ff[i][j]+"/"+p+"  ");
}
System.out.println();
}



matrixmul mulll=new matrixmul(a,ff,row,row,row,row);
float gggg[][]=mulll.mul();


System.out.println("\n");




for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
System.out.print(gggg[i][j]+"   ");
}
System.out.println();
}


}
}