public class matrixmul
{
float a[][],b[][];
int row1,column1,row2,column2;
matrixmul(float[][] a,float[][] b,int r1,int c1,int r2,int c2)
{
this.a=a;
this.b=b;
row1=r1;
row2=r2;
column1=c1;
column2=c2;
}
public float[][] mul()
{
float s[][]=new float[row1][column2];
for(int i=0;i<row1;i++)
{
for(int j=0;j<column2;j++)
{
s[i][j]=0;
for(int k=0;k<row2;k++)
{
s[i][j]+=a[i][k]*b[k][j];
}
}
}
return s;
}
}