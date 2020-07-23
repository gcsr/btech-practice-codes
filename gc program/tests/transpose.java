public class transpose
{
float[][] b;
int row,column;
transpose(float[][] a,int row,int column)
{
b=a;
this.row=row;
this.column=column;
}
float[][] trans()
{
float[][] pppp=new float[column][row];
for(int i=0;i<row;i++)
{
for(int j=0;j<column;j++)
{
pppp[i][j]=b[j][i];
}
}
return pppp;
}
}