public class mazeproblem
{
public static void main(String gcs[])
{
//matrix mm=new matrix();
//int maze[][]=mm.problem();
int[][] ss={
{0,0,0,0,1,1,1,1},
{1,0,1,1,0,1,1,1},
{1,1,0,1,0,1,1,1},
{0,1,1,0,1,1,1,1},
{0,1,1,1,0,1,1,1},
{0,1,1,1,0,0,1,1},
{0,1,1,1,0,1,0,1},
{0,1,1,1,1,1,1,0}};

solution ans=new solution(ss);
ans.sol(0,0);
}
}