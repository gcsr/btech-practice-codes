

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board implements IBoard {
	draftFrame inst;
	private static int board[][] = new int[8][8];
	public static int ORANGE = 0, BLUE = 1,EMPTY = 2; 
	private static boolean gameInProgress = false; 
	private static int player = BLUE;  
	private static int total_orange_pieces = 12;
	private static int total_blue_pieces = 12;
	private int fromPositionX = 0;
	private int fromPositionY = 0;
	private int toPositionX =  0;
	private int toPositionY =  0;
    private boolean playerPosChange = false;
		
	public Board(){
		inst = new draftFrame(this);
		createBoard();
		//setBoard();
		//pullPieces();
	}

   public void setFromPosition(int x, int y){
        fromPositionX = x;
        fromPositionY = y;
   }

   public void setToPosition(int x, int y){
        toPositionX =  x;
        toPositionY =  y;
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
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	System.out.println("i:"+i+" j:"+j+" : "+board[i][j]);
            }
        }*/
    	System.out.println("board inside get"+board[x][y]);
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
		JFrame.setDefaultLookAndFeelDecorated(true);
		inst.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		putPieces();
		inst.pack();
		inst.setResizable( false );
		inst.setLocationRelativeTo( null );
		inst.setVisible(true);
	}

	public void recreateBoard()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		inst.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		reputPieces();
		inst.pack();
		inst.setResizable( false );
		inst.setLocationRelativeTo( null );
		inst.setVisible(true);
	}

	//@SuppressWarnings("deprecation")
	public void putPieces()
	{
		JLabel iPiece;	
		Piece piece = new Piece();
		int lastSum=0, j;
        for (int i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if ( i % 2 == j % 2 ) {
                    if (i < 3){
                    	iPiece = piece.getImageIcon(0, i+j+lastSum);
            			board[i][j] = ORANGE;
                    }else if (i > 4){
                    	iPiece = piece.getImageIcon(1, i+j+lastSum);
            			board[i][j] = BLUE;
                    }else{
            			board[i][j] = EMPTY;
            			iPiece = null;
                    }
                }else {
        			board[i][j] = EMPTY;
        			iPiece = null;
                 }
            	
    			JPanel panel = (JPanel) inst.getBackgroundBoard().getComponent(i+j+lastSum);
    			panel.removeAll();
    			if(iPiece!=null)
    				panel.add(iPiece);
            }
            lastSum+=7;
        }
	}

	public void reputPieces()
	{
		JLabel iPiece;	
		Piece piece = new Piece();
		int lastSum=0, j;
        for (int i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
	        	iPiece = piece.getImageIcon(board[i][j], i+j+lastSum);
				JPanel panel = (JPanel) inst.getBackgroundBoard().getComponent(i+j+lastSum);
				panel.removeAll();
				if(iPiece!=null)
					panel.add(iPiece);
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
        for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		System.out.println(" i:"+i+" j:"+j+" val:"+getBoardPiece(i, j));
        	}
    	}

		inst.allowedDestination(x1, y1);
		if(inst.makeMove(x2, y2)){
			
	    	System.out.println("In make move after make move: ");
	        for (int i = 0; i < 8; i++) {
	        	for (int j = 0; j < 8; j++) {
	        		System.out.println(" i:"+i+" j:"+j+" val:"+getBoardPiece(i, j));
	        	}
	    	}
	        
			return true;
			
		}else
			return false;
	}

    public String getMove() throws InterruptedException
	{
        while(true){
            if(getPlayerPosChange()){
                String data = "move "+fromPositionX+" "+fromPositionY+" "+toPositionX+" "+toPositionY;
                setPlayerPosChange(false);
                return data;
            }
            Thread.sleep(500);
        }
	}


}
