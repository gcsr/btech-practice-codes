import java.util.Random;

class Puzzle
{
    private int[] pieces = new int[9];
    private boolean manhattan = true;       //Type of heuristic function
    private int depth;                                 //Number of moves made so far
    private int parentID=-1;                      //Parent ID for node tracking
//---------------------------------------------------------------------------------
    public Puzzle(int move) {generate(move);}
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan)
    {
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan, int depth)
    {
        this.depth = depth;
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(int[] squares, boolean isManhattan, int depth, int parentID)
    {
        this.depth = depth;
        this.parentID = parentID;
        for (int i=0; i<9; i++)
        {
            pieces[i] = squares[i];
            setHeuristic(isManhattan);
        }
    }
//---------------------------------------------------------------------------------
    public Puzzle(Puzzle puzzle)
    {
        pieces = puzzle.copyArray();
        manhattan = puzzle.isManhattan();
        depth = puzzle.getDepth();
        parentID = puzzle.getParentID();
    }
//---------------------------------------------------------------------------------
    // Generates a fresh set of squares by moving the blank x number of times
    public void generate(int move)
    {
        // create a correct puzzle
        for (int i=0; i<9; i++)
            pieces[i] = i;
        // randomly move the puzzle (25 times for this project)
        for (int j=0; j<move; j++)
        {
            // pick a random action
            Random rand = new Random();
            int choice = rand.nextInt(4)+1;
            switch (choice)
            {
                case 1:
                    pieces = up(pieces);
                    break;
                case 2:
                    pieces = down(pieces);
                    break;
                case 3:
                    pieces = left(pieces);
                    break;
                case 4:
                    pieces = right(pieces);
                    break;
            }
        }
    }
//---------------------------------------------------------------------------------
    private int[] up(int[] puzzleArray)
    {
        int i;
        int[] temp = new int[puzzleArray.length];
        int holder;
        for (i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (i=0; i<temp.length; i++)
        {
            if (temp[i] == 0)
                square = i;
        }
        // invalid option check
        if (square < 3)
            return temp;
        holder = temp[square];
        temp[square] = temp[square-3];
        temp[square-3] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] down(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square > 5)
            return temp;
        holder = temp[square];
        temp[square] = temp[square+3];
        temp[square+3] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] left(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square%3 == 0)
            return temp;
        holder = temp[square];
        temp[square] = temp[square-1];
        temp[square-1] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    private int[] right(int[] puzzleArray)
    {
        int holder;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int square= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                square = i;
        }
        // invalid option check
        if (square%3 == 2)
            return temp;
        holder = temp[square];
        temp[square] = temp[square+1];
        temp[square+1] = holder;
        return temp;
    }
//---------------------------------------------------------------------------------
    public String toString()
    {
        String out = new String("===============\n");
        for (int i=1; i<=9; i++) {
            if ((i%3) == 1)
                out += " | ";
            if (pieces[i-1] == 0)
                out += "  | ";
            else
                out += pieces[i-1]+" | ";
            if ((i%3) == 0)
                out += "\n===============\n";
        }
        return out;
    }
//---------------------------------------------------------------------------------
    public boolean equals(Puzzle p)
    {
		return (p.toString().equals(toString()));
	}
//---------------------------------------------------------------------------------
    public boolean isManhattan()  {return manhattan;}
//---------------------------------------------------------------------------------
    public int getDepth() {return depth;}
//---------------------------------------------------------------------------------
    public int getParentID() {return parentID;}
//---------------------------------------------------------------------------------
    public void setDepth(int depth) {this.depth = depth;}
//---------------------------------------------------------------------------------
    // Set Heuristic type
    public void setHeuristic(boolean isManhattan)
    {
        manhattan = isManhattan;
    }
//---------------------------------------------------------------------------------
    // Get Heuristic based on type
    public int getH()
    {
        //if (manhattan)
           return getmanhattanH();
        //else
          // return getOtherH();
    }
//---------------------------------------------------------------------------------
    // Set Non-manhattan Heuristic
    private int getOtherH()
    {
        // count the number of squares in the wrong place
        int wrong = 0;
        for (int i=0; i<9; i++)
            if (pieces[i] != i)
                wrong++;
        return wrong;
    }
//---------------------------------------------------------------------------------
    // Set manhattan Heuristic
    private int getmanhattanH()
    {
        int h = 0;
        int xs, xg, ys, yg;
        for (int i=0; i<9; i++)
        {
            // breaks the game locations into a grid formation
            // measures the distance using grid length.
            xg = i%3;
            yg = i/3;
            xs = pieces[i]%3;
            ys = pieces[i]/3;
            h += Math.abs(xs-xg) + Math.abs(ys-yg);
        }
        return h;
    }
    public int[] getPieces()
    {
    	return pieces;
    }
//---------------------------------------------------------------------------------
    // Add to the cost
    public void addMove() { depth++;}
//---------------------------------------------------------------------------------
    public int[] copyArray()
    {
        int[] temp = new int[9];
        for (int i=0; i<9; i++)
            temp[i] = pieces[i];
        return temp;
    }
//---------------------------------------------------------------------------------
    public int[] up() { return up(pieces);}
//---------------------------------------------------------------------------------
    public int[] down() { return down(pieces);}
//---------------------------------------------------------------------------------
    public int[] left() { return left(pieces);}
//---------------------------------------------------------------------------------
    public int[] right() {return right(pieces);}
//---------------------------------------------------------------------------------
} // end class Pazzle
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
