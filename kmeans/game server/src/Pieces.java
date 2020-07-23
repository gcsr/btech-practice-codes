
import java.util.ArrayList;


   class Pieces {

      private int[][] board;  
      public static final int NUMBER_COLUMNS = 8, NUMBER_ROWS = 8;
      
      Pieces() {
         board = new int[8][8];
         setUpGame();
      }

      public void setUpGame() {
		for (int i = 0; i < NUMBER_ROWS; i++) {
			for (int j = 0; j < NUMBER_COLUMNS; j++) {
				if ( i % 2 == j % 2 ) {
					if (i < 3)
						board[i][j] = 1;
					else if (i > 4)
						board[i][j] = 0;
					else
						board[i][j] = 2;
				}
				else {
					board[i][j] = 2;
				}
			}
		}
      }
      
      public int getPiece(int i, int j) {
          return board[i][j];
      }

      public void MakeMove(int row1, int col1, int row2, int col2) {
          board[row2][col2] = board[row1][col1];
          board[row1][col1] = 2;
       }

      public void MakeTake(int row1, int col1, int row2, int col2) {
          board[row2][col2] = board[row1][col1];
          board[row1][col1] = 2;
          board[(row2+row1)/2][(col2+col1) / 2] = 2;
       }
      
      private boolean MovedCorrectly(int player, int row1, int col1, int row2, int col2) {
          if (row2 < 0 || row2 >= 8 || col2 < 0 || col2 >= 8)
             return false;

          if (board[row2][col2] != 2)
             return false;

          if (player == 0) {
             if (board[row1][col1] == 0 && row2 > row1)
                 return false;
              return true;
          }
          else {
             if (board[row1][col1] == 1 && row2 < row1)
                 return false;
              return true;
          }

       }

      private boolean TakenCorrectly(int player, int row1, int col1, int row2, int col2, int row3, int col3) {
    	  
          if (row3 < 0 || row3 >= 8 || col3 < 0 || col3 >= 8)
             return false;

          if (board[row3][col3] != 2)
             return false;

          if (player == 0) {
             if (board[row1][col1] == 0 && row3 > row1)
                return false;
             if (board[row2][col2] != 1)
                return false;
             return true;
          }
          else {
             if (board[row1][col1] == 1 && row3 < row1)
                return false;
             if (board[row2][col2] != 0)
                return false;
             return true;
          }

       }


      public ArrayList<int[]> getAllMoves(int player, int fromRow, int fromCol, boolean onlyJump)
      {
          if (player>1)
              return null;

          ArrayList<int[]> move = new ArrayList<int[]>();
          int tmpArray[] = new int[4]; 
          
          int strow, stcol, maxrow, maxcol;
          
          if(onlyJump){
        	  maxrow = fromRow;
        	  maxcol = fromCol;
        	  strow = fromRow;
        	  stcol = fromCol;
          }else{
        	  maxrow = NUMBER_ROWS;
        	  maxcol = NUMBER_COLUMNS;
        	  strow = fromRow;
        	  stcol = fromCol;
          }
        	  
          
          for (int row = strow; row < maxrow; row++) {
              for (int col = stcol; col < maxcol; col++) {
                 if (board[row][col] == player) {
                     if (TakenCorrectly(player, row, col, row+1, col+1, row+2, col+2)){
                     	tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row+2; tmpArray[3] = col+2;
                        System.out.println(java.util.Arrays.toString(tmpArray));
                     	move.add(tmpArray.clone());
                     }
                     if (TakenCorrectly(player, row, col, row-1, col+1, row-2, col+2)){
                     	tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row-2; tmpArray[3] = col+2;
                        System.out.println(java.util.Arrays.toString(tmpArray));
                     	move.add(tmpArray.clone());
                     }
                     if (TakenCorrectly(player, row, col, row+1, col-1, row+2, col-2)){
                     	tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row+2; tmpArray[3] = col-2;
                        System.out.println(java.util.Arrays.toString(tmpArray));
                     	move.add(tmpArray.clone());
                     }
                     if (TakenCorrectly(player, row, col, row-1, col-1, row-2, col-2)){
                     	tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row-2; tmpArray[3] = col-2;
                        System.out.println(java.util.Arrays.toString(tmpArray));
                     	move.add(tmpArray.clone());
                     }
                 }
              }
          }
          
          if (move.size() == 0) {
              for (int row = 0; row < 8; row++) {
                 for (int col = 0; col < 8; col++) {
                    if (board[row][col] == player) {
                       if (MovedCorrectly(player,row,col,row+1,col+1)){
                          tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row+1; tmpArray[3] = col+1;
                          System.out.println(java.util.Arrays.toString(tmpArray));
                          move.add(tmpArray.clone());
                       }
                       if (MovedCorrectly(player,row,col,row-1,col+1)){
                          tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row-1; tmpArray[3] = col+1;
                          System.out.println(java.util.Arrays.toString(tmpArray));
                          move.add(tmpArray.clone());
                       }
                       if (MovedCorrectly(player,row,col,row+1,col-1)){
                          tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row+1; tmpArray[3] = col-1;
                          System.out.println(java.util.Arrays.toString(tmpArray));
                          move.add(tmpArray.clone());
                       }
                       if (MovedCorrectly(player,row,col,row-1,col-1)){
                          tmpArray[0] = row; tmpArray[1] = col; tmpArray[2] = row-1; tmpArray[3] = col-1;
                          System.out.println(java.util.Arrays.toString(tmpArray));
                          move.add(tmpArray.clone());
                       }
                    }
                 }
              }
          }
	          
        	  /*int index =0; 
	          for (int row = strow; row < maxrow; row++) {
	              for (int col = stcol; col < maxcol; col++) {
	            	  index=row+col;
	            	  System.out.println("index: "+index+" values:\n\t"+move.get(index)[0]+"\t"+ 
	            			  move.get(index)[1]+"\t"+move.get(index)[1]+"\t"+move.get(index)[2]+"\n");
	              }
            	  index+=7;
	          }*/
	          	
	          if(move.size()>0)
	        	  return move;
	          else
	        	  return null;	
           }

   }

