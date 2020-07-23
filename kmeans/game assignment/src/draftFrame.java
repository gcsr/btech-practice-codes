public class draftFrame{

	private static final long serialVersionUID = 1L;

	IBoard board;

    boolean playerPositionChanged = false;
    
    public draftFrame(IBoard iboard) {
		super();
		board = iboard;
        playerPositionChanged = false;
	}
	

    
    public void quit()
    {
        board.setGameProgress(false);
    }
    
    public void newGame()
    {
    	board.setPlayer(1);
    	board.putPieces();
        board.setGameProgress(true);
    }

    public void newGameServer()
    {
    	board.setPlayer(0);
    	board.putPieces();
        board.setGameProgress(true);
    }

    private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

    	
	   if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
	      return false;
	
	   if (board.getBoardPiece(r3, c3) != 2)
	      return false;
	
	   if (player == 0) {
	      if (board.getBoardPiece(r1, c1) == 0 && c3 > c1 && board.getBoardPiece(r2, c2) == 1) 
	         return true;
	      return false;
	   }
	   else {
	      if (board.getBoardPiece(r1, c1) == 1 && c3 < c1 && board.getBoardPiece(r2, c2) == 0)
	         return true;
	      return false;
	   }
    }

    public boolean canMove(int player,int r1,int c1,int r2,int c2)
    {
    	 System.out.println("inside canMove squares r1:"+r1+" c1:"+c1+" r2:"+r2+" c2:"+c2);
         if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
            return false;

         if (board.getBoardPiece(r2, c2) != 2)
            return false;

         if (board.getPlayer() == 0) {
            if (board.getBoardPiece(r1, c1) == 0 && c2 > c1)
                return true;
             return false;
         }
         else {
            if (board.getBoardPiece(r1, c1) == 1 && c2 < c1) 
                return true;
             return false;
         }

    }
    
    public String getLegalMoveSquares(int row,int col){
        String output = "x";
        int x=0, y=0;
        
    	if (canJump(board.getPlayer(),row, col, row+1, col+1, row+2, col+2)){
            x=row+2; y=col+2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row+1,col+1)){
            x=row+1; y=col+1;
            output = "mm"+x+" "+y;
		}

		if (canJump(board.getPlayer(), row, col, row-1, col+1, row-2, col+2)){
            x=row-2; y=col+2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row-1,col+1)){
            x=row-1; y=col+1;
            output = "mm"+x+" "+y;
        }

		if (canJump(board.getPlayer(), row, col, row+1, col-1, row+2, col-2)){
            x=row+2; y=col-2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row+1,col-1)){
            x=row+1; y=col-1;
            output = "mm"+x+" "+y;
		}

		if (canJump(board.getPlayer(), row, col, row-1, col-1, row-2, col-2)){
            x=row-2; y=col-2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row-1,col-1)){
            x=row-1; y=col-1;
            output = "mm"+x+" "+y;
		}
        
        return output;
	}

    public boolean makeMove(int row1, int col1, int row2, int col2)
	{
		int inROW = 100;
		int inCOL = 100;
		boolean isJumpValid = false;

		if((row2==row1+2 && col2==col1+2)
		&& (canJump(board.getPlayer(), row1, col1, row1+1, col1+1, row2, col2 )))
		{
			inROW = row1+1;
			inCOL = col1+1;
			isJumpValid = true;
		} else if((row2==row1-2 && col2==col1+2)
		&& (canJump(board.getPlayer(), row1, col1, row1-1, col1+1, row2, col2 )))
		{
			inROW = row1-1;
			inCOL = col1+1;
			isJumpValid = true;
		} else if((row2==row1+2 && col2==col1-2)
		&& (canJump(board.getPlayer(), row1, col1, row1+1, col1-1, row2, col2 )))
		{
			inROW = row1+1;
			inCOL = col1-1;
			isJumpValid = true;
		}

		else if((row2==row1-2 && col2==col1-2)
		&& (canJump(board.getPlayer(), row1, col1, row1-1, col1-1, row2, col2 )))
		{
			inROW = row1-1;
			inCOL = col1-1;
			isJumpValid = true;
		}


		if(row1<64 && col1<64){
			if(isJumpValid)
				{

		            if((row1!=row2 || col1!=col2)){
			            board.setBoardPiece(row1, col1, 2);
			            board.setBoardPiece(row2, col2, board.getPlayer());
			            board.setBoardPiece(inROW, inCOL, 2);

			            if(board.getPlayer()>0){
							board.setPlayer(0);
							board.setTotal_orange_pieces(board.getTotal_orange_pieces()-1);
			            } else{
							board.setPlayer(1);
							board.setTotal_blue_pieces(board.getTotal_blue_pieces()-1);
			            }

			            if(board.getTotal_blue_pieces()==0){
			    			newGame();
			    		} else if(board.getTotal_orange_pieces()==0){
			    			newGame();
			    		}
                        playerPositionChanged = true;
					}

			        isJumpValid = false;

		        	row1 = 100;
		        	col1 = 100;
		        	inROW = 100;
		        	inCOL = 100;

		            /*for (int i = 0; i < 8; i++) {
		                for (int j = 0; j < 8; j++) {
				            System.out.println(" i:"+i+" j:"+j+" val:"+board.getBoardPiece(i, j));

		                }
		            }*/
		        	
				}else if(((row2==row1+1 && col2==col1+1)
					|| (row2==row1-1 && col2==col1+1)
					|| (row2==row1+1 && col2==col1-1)
					|| (row2==row1-1 && col2==col1-1)
					) && canMove(board.getPlayer(), row1, col1, row2, col2))
				{
		            if((row1!=row2 || col1!=col2)){
			            board.setBoardPiece(row1, col1, 2);
			            board.setBoardPiece(row2, col2, board.getPlayer());

			            if(board.getPlayer()>0)
							board.setPlayer(0);
						else
							board.setPlayer(1);
                        
                        playerPositionChanged = true;
					}
			}else{
	        	row1 = 100;
	        	col1 = 100;
			}
		}
		return true;
	}
}
