import java.util.Scanner;

public class conjugatematrix
{
public static void main(String gcs[])
{
Scanner input=new Scanner(System.in);

System.out.println("enter row");
int row=input.nextInt();
int[][] a=new int[row][row];
System.out.println("enter the elements");
for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
a[i][j]=input.nextInt();
}
}

//determinant pp=new determinant();
//int p=pp.det(a,row,row);

conjugate cgate=new conjugate(a,row,row);
int g[][]=cgate.conjuga();


for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
System.out.print(g[i][j]+"        ");
}
System.out.println();
}


}
}