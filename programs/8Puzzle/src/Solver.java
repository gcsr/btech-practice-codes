import java.util.Vector;

class Solver
{
    private int cost = 0;
    private Vector usedList;
    private Vector fringeList;
    private Puzzle p;
	int count=0;
//---------------------------------------------------------------------------------
    public Solver(int depth)
    {
        usedList = new Vector();
        fringeList = new Vector();
        p = new Puzzle(depth);
        fringeList.add(p);
    }
    
    public Solver(int[] pieces)
    {
    	usedList = new Vector();
        fringeList = new Vector();
        p = new Puzzle(pieces,true);
        fringeList.add(p);
    }
//---------------------------------------------------------------------------------
    public Solver(Puzzle puzzle)
    {
        usedList = new Vector();
        fringeList = new Vector();
        p = new Puzzle(puzzle);
        fringeList.add(p);
    }
//---------------------------------------------------------------------------------
    public void solve()  {while (expandNodes(p)) {p=findBestNode();}}
//---------------------------------------------------------------------------------
    public int getCost() {return (usedList.size() + fringeList.size());}
//---------------------------------------------------------------------------------
    // Finds the best node in the list
    private void printEach(int[] pieces)
    {
    	for(int i=0;i<9;i++)
    	{
    		if(i%3==0)
    		{
    			System.out.println();
    			System.out.print(pieces[i]);
    		}
    		else
    		{	
    			System.out.print(pieces[i]);
    			System.out.print("\t");
    		}
    	}
    }
    private Puzzle findBestNode()
    {
        Puzzle best;
        Puzzle test;
        // If the fringe is empty, grab the only node
        if (fringeList.size()==0)
            best = (Puzzle)usedList.elementAt(0);
        // Otherwise grab the first fringe node, set it for comparison.
        else
            best = (Puzzle)fringeList.elementAt(0);
        for (int i=1; i<fringeList.size();i++)
        {
            test = (Puzzle)fringeList.get(i);
            // Get Heuristic Value for Node
            int bestH = best.getDepth() + best.getH();
            int testH = test.getDepth() + test.getH();
            // If it is lower, change "Best"
            if (testH < bestH)
                best = test;
        }
        //printEach(best.getPieces());
		count++;
	   System.out.print(count+" ");
        return best;
    }
//---------------------------------------------------------------------------------
    private boolean expandNodes(Puzzle p)
    {
        int[] test;
        Puzzle temp;
        // take Node off of fringe
        fringeList.remove(p);
        // add it to used list, for GRAPH-SEARCH
        int parentID = usedList.size();
        usedList.add(p);
        // if Heuristic is 0, done
        if (p.getH() == 0)
            return false;
        // else test all directions
        test = p.up();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.down();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.left();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        test = p.right();
        temp = new Puzzle(test, p.isManhattan(), p.getDepth(), parentID);
        addToFringe(temp);
        return true;
    }
//---------------------------------------------------------------------------------
    private void addToFringe(Puzzle p)
    {
        // no repeat nodes
        if (!nodeRepeated(p))
        {
            p.addMove();
            fringeList.add(p);
        }
    }
//---------------------------------------------------------------------------------
    private boolean nodeRepeated (Puzzle node)
    {
        for (int i=0; i<usedList.size(); i++)
        {
            if (node.equals((Puzzle)usedList.get(i)))
                return true;
        }
        return false;
    }
//---------------------------------------------------------------------------------
    public Vector getVector()
    {
    	Vector list = new Vector();
        // get solution
        Puzzle node = getPuzzleSolution();
        // add to answer list
        list.add(node);
        // find parent node
        int parentID = node.getParentID();
        while (parentID != -1)
        {
            node = (Puzzle)usedList.elementAt(parentID);
            list.add(node);
            parentID = node.getParentID();
        }
        return list;
    }
    public void print()
    {
        Vector list = new Vector();
        // get solution
        Puzzle node = getPuzzleSolution();
        // add to answer list
        list.add(node);
        // find parent node
        int parentID = node.getParentID();
        while (parentID != -1)
        {
            node = (Puzzle)usedList.elementAt(parentID);
            list.add(node);
            parentID = node.getParentID();
        }

        for (int i = 1; i <= list.size();  i++)
        {
			if (i ==1)
			{
				System.out.println("Original:");
				System.out.println((Puzzle)list.elementAt(list.size()-i));
			}
			else
			{
				System.out.println("Step " + ( i -1)+ ":");
				System.out.println((Puzzle)list.elementAt(list.size()-i));
			}
	    }
        System.out.println("\nSolution with "+getPuzzleSolution().getDepth()+" step(s).");
    }
//---------------------------------------------------------------------------------
    public Puzzle getPuzzleSolution()
    {
        return (Puzzle)usedList.lastElement();
    }
//---------------------------------------------------------------------------------
} // end class Solver
