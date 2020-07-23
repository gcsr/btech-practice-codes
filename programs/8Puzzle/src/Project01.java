//import java.util.Date;

class Project01
{
//---------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        Solver puzzle = new Solver(25);
        puzzle.solve();
        puzzle.print();
    }
//---------------------------------------------------------------------------------
} // end class Project01
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//For complexity Computation

/*class Computation
{
    private int[] counter;                   // Array to track each depth count
    private int[] timer;
    private int[] cost;
    private int max;                          // max depth
    private int min = 0;                    // min depth
    private int num = 10;                 // # of each solution to get
    private int size = 75;                 // Base size to randomize by
//---------------------------------------------------------------------------------
    public Computation(int max)
    {
        this.max = max;
        counter = new int[max];
        timer = new int[max];
        cost = new int[max];
    }
//---------------------------------------------------------------------------------
    public Computation(int min, int max)
    {
        this.max = max;
        this.min = min;
        counter = new int[max];
        timer = new int[max];
        cost = new int[max];
    }
//---------------------------------------------------------------------------------
    // Runs enough puzzles to meet requirements
    public void run()
    {
        int i;
        do
        {
            Solver p = new Solver(size);
            Timer t = new Timer();
            t.start();
            p.solve();
            long time = t.end();
            Puzzle solution = p.getPuzzleSolution();

            if (solution.getDepth() <= max && (solution.getDepth()-min) > 0)
            {
                if (counter[(solution.getDepth()-1)-min] < num)
                {
                    counter[(solution.getDepth()-1)-min]++;
                    timer[(solution.getDepth()-1)-min] += time;
                    cost[(solution.getDepth()-1)-min] += p.getCost();
                }
            }
        } while (!checkCounter());
        System.out.println("Depth\tCost\tTime to Execute");
        for (i=0; i<(max-min); i++)
            System.out.println((i+1)+min+"\t"+(cost[i]/num)+"\t"+timer[i]/num);
    }
//---------------------------------------------------------------------------------
    // Returns true if Data is complete
    public boolean checkCounter()
    {
        int count = 0;
        for (int i=0; i<(max-min); i++)
        {
            count += counter[i];
        }
        return (count == ((max-min)*num));
    }
} // end class Computation
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Timer
{
    Date now;
    long start;
//---------------------------------------------------------------------------------
    public Timer() {}
//---------------------------------------------------------------------------------
    // Start timer
    public void start()
    {
        now = new Date();
        start = now.getTime();
    }
//---------------------------------------------------------------------------------
// End Timer
    public long end()
    {
        now = new Date();
        long end = now.getTime();
        return ((end-start));
    }
} // end class Timer
///////////////////////////////////////////////////////////////////////////////////////////////////////////////*/