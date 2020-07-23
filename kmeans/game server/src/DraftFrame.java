
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DraftFrame extends JPanel {
	
	private static final long serialVersionUID = 6537428562757540620L;
	BoardInterfaceImpl board;
	
	DraftFrame(BoardInterfaceImpl iboard)
	{
		board = iboard;
	}
	
    public void paintComponent(Graphics g) {
    	
    super.paintComponent(g);
    
    g.setColor(Color.white);
    g.drawRect(0,0,getSize().width-1,getSize().height-1);
    g.drawRect(1,1,getSize().width-3,getSize().height-3);

    for (int row = 0; row < 8; row++) {
       for (int col = 0; col < 8; col++) {
           if ( row % 2 == col % 2 )
              g.setColor(Color.white);
           else
              g.setColor(Color.black);
           g.fillRect(col*40,row*40, 40, 40);

         switch (board.getPieceData().getPiece(row,col)) {
            case 0:
               g.setColor(Color.red);
               g.fillOval(col*40+2, row*40+2, 36, 36);
               break;
            case 1:
               g.setColor(Color.green);
               g.fillOval(col*40+2, row*40+2, 36, 36);
               break;
         	}
         }
      }
    
    if (board.getGameProgress()) {
	     g.setColor(Color.cyan);
	     for (int i = 0; i < board.getAllMoves().size(); i++) {
	        g.drawRect(board.getAllMoves().get(i)[1]*40, board.getAllMoves().get(i)[0]*40, 40, 40);
	     }
	     if (board.RowNumber() >= 0) {
	        g.setColor(Color.white);
	        g.drawRect(2+board.ColumnNumber()*40,2+board.RowNumber()*40, 39, 39);
	        g.setColor(Color.green);
	        for (int i = 0; i < board.getAllMoves().size(); i++) {
	           if (board.getAllMoves().get(i)[1] == board.ColumnNumber() && board.getAllMoves().get(i)[0] == board.RowNumber())
	              g.drawRect(board.getAllMoves().get(i)[3]*40, board.getAllMoves().get(i)[2]*40, 40, 40);
	        }
	     }
  }

    
    
   }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Chess Board");
    frame.setSize(420, 420);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new DraftFrame(null));

    frame.setVisible(true);
  }
}