

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;


class Board extends JFrame implements MouseListener, BoardInterfaceImpl {

	private static final long serialVersionUID = 1L;

	Pieces piece;
	boolean running;
	int player;
	int fromRow, fromCol;
	int toRow, toCol;
	DraftFrame chessBoard;
	
	Array unitMove[];
	ArrayList<int[]> allMoves =  new ArrayList<int[]>(); 
	
	public Board() {
		super();
		chessBoard = new DraftFrame(this);
		
	    JFrame frame = new JFrame();
	    frame.setTitle("Chess Board");
	    frame.setSize(420, 420);
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    chessBoard.addMouseListener(this);
	    Container contentPane = frame.getContentPane();
	    contentPane.add(chessBoard);

	    frame.setVisible(true);

		piece = new Pieces();
		newGame();
		chessBoard.repaint();
	}

	
	  public Pieces getPieceData()
	  {
		  return piece;
	  }
	  
      public Dimension getPreferredSize()
      {
    	  return new Dimension(400, 400);
      }


      public Dimension getMinimumSize()
      {
          return new Dimension(400, 400);
      }


      public Dimension getMaximumSize()
      {
          return new Dimension(400, 400);
      }


      public boolean getGameProgress()
      {
		  return running;
      }
	  
      public ArrayList<int[]> getAllMoves()
      {
		  return allMoves;
      }

      public int RowNumber()
      {
		  return fromRow;
      }
	  
      public int ColumnNumber()
      {
		  return fromCol;
      }
	  

      void newGame() {
         piece.setUpGame();
         player = 0;
         allMoves = piece.getAllMoves(0, 0, 0, false);
         fromRow = -1;
         running = true;
         chessBoard.repaint();
      }

      void selectionsInsideBoard(int row, int col) {
          for (int i = 0; i < allMoves.size(); i++){
        	  if(allMoves.get(i)[0] == row && allMoves.get(i)[1] == col){
                  fromRow = row;
                  fromCol = col;
                  chessBoard.repaint();
                  return;
        	  }
          }
          
          if (fromCol <0 || fromRow < 0) {
              return;
          }
    	  
          for (int i = 0; i < allMoves.size(); i++){
              if (allMoves.get(i)[0] == fromRow && allMoves.get(i)[1] == fromCol && 
            		  allMoves.get(i)[2] == row && allMoves.get(i)[3] == col){
            	 toRow = row;
            	 toCol = col;
                 if ((fromRow == toRow+2 && fromCol == toCol+2)
                       	|| (fromRow == toRow-2 && fromCol == toCol+2)
                     	|| (fromRow == toRow+2 && fromCol == toCol-2)
                     	|| (fromRow == toRow-2 && fromCol == toCol-2)){
             		   piece.MakeTake(fromRow, fromCol, toRow, toCol);
             		   fromRow = toRow;
             		   fromCol = toCol;
                       //fromRow = allMoves.get(fromRow+fromCol*8)[2];  // have to check here -- some errors 
                       //fromCol = allMoves.get(fromRow+fromCol*8)[0];  // have to check here too 
                       //chessBoard.repaint();
                  }
             	  else{
             		  piece.MakeMove(fromRow, fromCol, toRow, toCol);
             	  }
                   
                   if (player == 0) {
                      player = 1;
                      allMoves = piece.getAllMoves(1, 0, 0, false);
                   }
                   else {
                      player = 0;
                      allMoves = piece.getAllMoves(0, 0, 0, false);
                   }
                   chessBoard.repaint();
            	 return;
              }
          }
      }

      public void mousePressed(MouseEvent evt) {
    	 System.out.println("mouse pressed "+running);
         if (running == true){
            int col = (evt.getX()) / 40;
            int row = (evt.getY()) / 40;
            if (col >= 0 && col < 8 && row >= 0 && row < 8)
            	selectionsInsideBoard(row,col);
         }
      }


      public void mouseReleased(MouseEvent evt) { }
      public void mouseClicked(MouseEvent evt) { }
      public void mouseEntered(MouseEvent evt) { }
      public void mouseExited(MouseEvent evt) { }

      public static void main(String args[])
      {
	      new Board();
	      //Board board = new Board();
      }

   }

