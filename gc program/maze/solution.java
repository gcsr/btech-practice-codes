public class solution
{
int a[][];
stack po;

public solution(int[][] a)
{
this.a=a;
po=new stack();
}
 
public void sol(int i,int j)
{
System.out.println(i+"    "+j);
if((i==7)&&(j==7))
return;
if((a[i][j+1]==0)&&(a[i][j]==0))
{
po.push(i,j);
sol(i,j+1);
}
else 
if((a[i][j]==0)&&(a[i+1][j]==0))
{
po.push(i,j);
sol(i+1,j);
}
if((a[i+1][j+1]==0)&&(a[i][j]==0))
{
po.push(i,j);
sol(i+1,j+1);
}
else
{
a[i][j]=1;
po.pop();
sol(po.lastx(),po.lasty());
}
}

}