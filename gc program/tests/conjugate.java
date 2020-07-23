public class conjugate
{
float b[][];
int row,column;
conjugate(float[][] a,int row,int column)
{
b=a;
this.row=row;
this.column=column;
}
float[][] conjuga()
{
float[][] co=new float[row][column];
for(int i=0;i<row;i++)
{
for(int j=0;j<column;j++)
{
co[i][j]=(float)Math.pow(-1,(i+j+2))*cofactor(i,j);
}
}
return co;
}

float cofactor(int s,int p)
{
float[][] sub=new float[row-1][column-1];
int y=0,q=0;
for(int i=0;i<row-1;i++)
{
if(i==s)
y=1;
for(int j=0;j<column-1;j++)
{
if(j==p)
q=1;
sub[i][j]=b[i+y][j+q];
}
q=0; 
}


determinant ssss=new determinant();
float f=ssss.det(sub,row-1,column-1);

return f;
}
}