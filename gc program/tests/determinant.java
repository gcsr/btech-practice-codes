import java.util.*;

public class determinant
{

int sp=0;
public float det(float[][] a,int row,int column)
{
if(row==1)
return a[0][0];
if(row==2)
{
//System.out.println("two two");
return a[0][0]*a[1][1]-a[0][1]*a[1][0];
}
else
{
for(int i=0;i<row;i++)
{
//System.out.println("for");
//System.out.println(row);
float b[][]=sub(a,i,row);
sp+=Math.pow(-1,i)*a[0][i]*det(b,row-1,column-1);
//System.out.println(sp);
}
}
return sp;
}

public float[][] sub(float[][] a,int r,int row)
{
//System.out.println("sub");
float g[][]=new float[row-1][row];
//System.out.println(row-1);
//System.out.println("matrix");
for(int i=0;i<(row-1);i++)
{
for(int j=0;j<row;j++)
{
g[i][j]=a[i+1][j];
//System.out.println("matrix cons ");
}
}
//System.out.println("sub completed");
float[][] f=new float[row-1][row-1];
try
{
f=col(g,r,row-1);
}
catch(Exception e)
{
//System.out.println("maddaga");
}
return f;
}


public float[][] col(float [][]a,int r,int row)
{
//System.out.println("col");
int po=0;
float s[][]=new float[row][row];
for(int i=0;i<row;i++)
{
for(int j=0;j<row;j++)
{
if(j==r)
po=1;
if(po==1)
s[i][j]=a[i][j+1];
else
s[i][j]=a[i][j];
}
po=0;
}
return s;
}

} 