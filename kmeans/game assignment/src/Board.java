import java.util.Random;

public class Board implements IBoard {
	draftFrame inst;
	private static int board[][] = new int[8][8];
	public static int ORANGE = 0, BLUE = 1,EMPTY = 2; 
	private static boolean gameInProgress = false; 
	private static int player = BLUE;  
	private static int total_orange_pieces = 12;
	private static int total_blue_pieces = 12;
    private boolean playerPosChange = false;
		
	public Board(){
		inst = new draftFrame(this);
		createBoard();
	}

    public void setPlayerPosChange(boolean iPlayerPosChange){
        playerPosChange = iPlayerPosChange;
    }

    public boolean getPlayerPosChange(){
        return playerPosChange;
    }

	public void setBoardPiece(int x, int y, int val)
	{
		board[y][x] = val;
	}

	public int getBoardPiece(int x, int y)
	{
		return board[y][x];
	}
	
	public void setGameProgress(boolean running)
	{
		gameInProgress = running;
	}

	public boolean getGameProgress()
	{
		return gameInProgress;
	}
	public void createBoard()
	{
		putPieces();
	}

	public void putPieces()
	{
		int lastSum=0, j;
        for (int i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if ( i % 2 == j % 2 ) {
                    if (i < 3){
            			board[i][j] = ORANGE;
                    }else if (i > 4){
            			board[i][j] = BLUE;
                    }else{
            			board[i][j] = EMPTY;
                    }
                }else {
        			board[i][j] = EMPTY;
                 }
            }
            lastSum+=7;
        }
	}

	public static void main(String[] args)
	{
		//Board chessBoard = new Board();
		new Board();
	}

	public void setPlayer(int iplayer) {
		if(iplayer>1)
			player = 1; //default
		else
			player = iplayer;
		
	}

	public int getPlayer() {
		return player;
	}

	public void setTotal_blue_pieces(int total_blue_pieces) {
		if(total_blue_pieces>=0)
			Board.total_blue_pieces = total_blue_pieces;
	}

	public int getTotal_blue_pieces() {
		return total_blue_pieces;
	}

	public void setTotal_orange_pieces(int total_orange_pieces) {
		if(total_orange_pieces>=0)
			Board.total_orange_pieces = total_orange_pieces;
	}

	public int getTotal_orange_pieces() {
		return total_orange_pieces;
	}


    public boolean startGame()
	{
		inst.newGame();
        return true;
	}

    public boolean makeMove(int x1, int y1, int x2, int y2)
	{
    	System.out.println("In make move b4 make move: ");
       /* for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		System.out.println(" i:"+i+" j:"+j+" val:"+getBoardPiece(i, j));
        	}
    	}*/

    	inst.makeMove(x1, y1,x2, y2);
		
    	System.out.println("In make move after make move: ");
        /*for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		System.out.println(" i:"+i+" j:"+j+" val:"+getBoardPiece(i, j));
        	}
    	}*/

		return true;
	}

	public String generateMove()
	{
        int cells[][] = new int[8][8];
        String moves[][] = new String[8][8];
        String output;
        int randint;
        int fromx=0, fromy=0, tox=0, toy=0;

        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                output = inst.getLegalMoveSquares(i, j);
                if(output.length()>3 && output.startsWith("jjj")){
                    randint = r.nextInt(10)+50;
                    moves[i][j] = output.substring(3);
                }else if(output.length()>2 && output.startsWith("mm")){
                    randint = r.nextInt(10)+50;
                    moves[i][j] = output.substring(2);
                }else{
                    randint = 0;
                    moves[i][j] = "";
                }
                cells[i][j] = randint;
            }
        }
        
		System.out.println(" cells [ ");
        for (int i = 0; i < 8 ; i++) {
    		System.out.print(" [ ");
        	for (int j = 0; j < 8; j++) {
        		System.out.print(" "+cells[i][j]+" ");
        	}
    		System.out.print(" ], ");
		}
		System.out.println(" ] ");

		System.out.println(" moves [ ");
        for (int i = 0; i < 8 ; i++) {
    		System.out.print(" [ ");
        	for (int j = 0; j < 8; j++) {
        		System.out.print(" "+moves[i][j]+" ");
        	}
    		System.out.print(" ], ");
		}
		System.out.println(" ] ");

        int max = cells[0][0];
        int maxi = 0, maxj = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cells[i][j] > max){
                    max = cells[i][j];
                    maxi = i;
                    maxj = j;
                }
            }
        }
        System.out.println("maxi: "+maxi);
        System.out.println("maxj: "+maxj);


        fromx = (maxi*50)+25;
        fromy = (maxj*50)+25;


        String temp[] = moves[maxi][maxj].split("\\s");
        if(temp.length!=2){
        	System.exit(0);
        }
        
        System.out.println("Integer.parseInt(temp[0]): "+Integer.parseInt(temp[0]));
        System.out.println("Integer.parseInt(temp[1]): "+Integer.parseInt(temp[1]));
        tox = (Integer.parseInt(temp[0])*50) + 25;
        toy = (Integer.parseInt(temp[1])*50) + 25;
        

        makeMove(maxi, maxj, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));

		String retData = "move "+fromx+" "+fromy+" "+tox+" "+toy;
        System.out.println("moved in server: "+retData);
        setPlayerPosChange(false);

        return retData;
	}

	public static int getMaxValue(int[] numbers){
	    int maxValue = numbers[0];
	    for(int i=1;i < numbers.length;i++){
	        if(numbers[i] > maxValue){
	            maxValue = numbers[i];
	        }
	    }
	    return maxValue;
	}



}
