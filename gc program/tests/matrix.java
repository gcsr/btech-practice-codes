import java.util.Scanner;


public matrix
{
int [][] problem()
{

int maze[][];
System.out.println("enter the no of rows");
Scanner input=new Scanner(System.in);
int row=input.nextInt();
System.out.println("enter the no of columns");
int column=input.nextInt();
maze=new int[row][column];

System.out.println("fill the maze ");
for(int i=0;i<row;i++)
{
for)int j=0;j<column;j++)
{
maze[i][j]=input.nextInt();
}
}

return maze;
}
}