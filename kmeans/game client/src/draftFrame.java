
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class draftFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	{
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel backgroundBoard;
	JPanel square;
	//private Square square1;
	Square squarePanel;
	JLayeredPane jlayeredPane; 
	JButton newGameButton;
    JButton resignButton; 
    JTextArea message;
    JLabel chPiece;
    
    IBoard board;

    boolean playerPositionChanged = false;
    
    int xPos;
    int yPos;

    int oldROW = 100;
    int oldCOL = 100;
    
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);

				draftFrame inst = new draftFrame();
				inst.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

				//Display the window
				inst.pack();
				inst.setResizable( false );
				inst.setLocationRelativeTo( null );
				inst.setVisible(true);


				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	public draftFrame(IBoard iboard) {
		super();
		initGUI();
		board = iboard;
        playerPositionChanged = false;
	}
	
	private void initGUI() {
		jlayeredPane = new JLayeredPane();
		
		
		setLayout(new BorderLayout());
		//getContentPane().add(jlayeredPane);
		jlayeredPane.setPreferredSize(new Dimension(400, 400));
		jlayeredPane.addMouseListener( this );
		jlayeredPane.addMouseMotionListener( this );
		
		newGameButton = new JButton("New Game");
        message = new JTextArea();
        add(newGameButton,BorderLayout.SOUTH);
        add(message,BorderLayout.WEST);
        //addMouseListener(this);
        //addMouseMotionListener(this);

		backgroundBoard = new JPanel();
		
		jlayeredPane.add( backgroundBoard, JLayeredPane.DEFAULT_LAYER);
		backgroundBoard.setLayout( new GridLayout(8, 8) );
		backgroundBoard.setPreferredSize( new Dimension(400, 400) );
		backgroundBoard.setBounds(0, 0, 400, 400);
		
		for (int i = 0; i < 64; i++)
		{
			squarePanel = new Square(i);
			backgroundBoard.add(squarePanel, BorderLayout.CENTER);
			squarePanel.setBackground(squarePanel.getColor(100));
		}

		//add(backgroundBoard,BorderLayout.CENTER);
		add(jlayeredPane,BorderLayout.CENTER);
		//jlayeredPane.add(newGameButton);
		//jlayeredPane.add(message);
		
		//newGameButton.setBounds(420, 100, 120, 30);
        newGameButton.addActionListener(this);
		
		message.setEditable(false);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		//message.setBounds(410, 150, 150, 150);
        message.setFont(new  Font("Serif", Font.BOLD, 14));
        //message.setBackground();
        message.setForeground(Color.red);
        message.setText("There is no game in progress!");

	}

    public void paintSquareComponent() {
    	for(int i=0;i<64;i++){
    		JPanel panel = (JPanel) getBackgroundBoard().getComponent(i);
			panel.setBackground(Color.GREEN);
    		panel.setBackground(squarePanel.getColor(i));
    	}
    	
    }
    
    public void actionPerformed(ActionEvent evt) {
	     Object src = evt.getSource();
	     if (src == newGameButton){
	    		 if(board.getGameProgress()==false)
	    			 newGame();
	    		 else
	    			 quit();
	     }
    }
    
    public void quit()
    {
        board.setGameProgress(false);
        newGameButton.setText("New Game");
        message.setText("Player 2 won.");
        paintSquareComponent();
    }
    
    public void newGame()
    {
        newGameButton.setText("Quit");
		//initGUI();
    	board.setPlayer(1);
    	board.putPieces();
        board.setGameProgress(true);
        message.setText("Blue: Make your move.");
        repaint();
    }

    public void newGameServer()
    {
        newGameButton.setText("Quit");
		//initGUI();
    	board.setPlayer(0);
    	board.putPieces();
        board.setGameProgress(true);
        message.setText("Orange: Make your move.");
        repaint();
    }

    private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

    	System.out.println("inside canJump squares r1:"+r1+" c1:"+c1+" r2:"+r2+" c2:"+c2+" r3:"+r3+" c3:"+c3);
    	
	   if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
	      return false;
	
	   if (board.getBoardPiece(r3, c3) != 2)
	      return false;
	
	   if (player == 0) {
	      if (board.getBoardPiece(r1, c1) == 0 && c3 > c1 && board.getBoardPiece(r2, c2) == 1) 
	         return true;
	      //if (board.getBoardPiece(r2, c2) != 1)
	      //   return false;
	      return false;
	   }
	   else {
	      if (board.getBoardPiece(r1, c1) == 1 && c3 < c1 && board.getBoardPiece(r2, c2) == 0)
	         return true;
	      //if (board.getBoardPiece(r2, c2) != 0)
	      //   return false;
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
    
    public void highlightSquare(int x, int y)
    {
		JPanel panel = (JPanel) getBackgroundBoard().getComponent(x+y*8);
		panel.setBackground(Color.GREEN);
		int val = x+y*7;
    	System.out.println("inside highlight squares x:"+x+" y:"+y+"(x)*7)+y:"+val);
    }

    public String getLegalMoveSquares(int row,int col){
        String output = "x";
        int x=0, y=0;
        
    	if (canJump(board.getPlayer(),row, col, row+1, col+1, row+2, col+2)){
			highlightSquare( row+2, col+2);
            x=row+2; y=col+2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row+1,col+1)){
			highlightSquare( row+1,col+1);
            x=row+1; y=col+1;
            output = "mm"+x+" "+y;
		}

		if (canJump(board.getPlayer(), row, col, row-1, col+1, row-2, col+2)){
			highlightSquare(row-2, col+2);
            x=row-2; y=col+2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row-1,col+1)){
            highlightSquare(row-1,col+1);
            x=row-1; y=col+1;
            output = "mm"+x+" "+y;
        }

		if (canJump(board.getPlayer(), row, col, row+1, col-1, row+2, col-2)){
			highlightSquare(row+2,col-2);
            x=row+2; y=col-2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row+1,col-1)){
            highlightSquare(row+1,col-1);
            x=row+1; y=col-1;
            output = "mm"+x+" "+y;
		}

		if (canJump(board.getPlayer(), row, col, row-1, col-1, row-2, col-2)){
			highlightSquare(row-2, col-2);
            x=row-2; y=col-2;
            output = "jjj"+x+" "+y;
		}else if (canMove(board.getPlayer(),row,col,row-1,col-1)){
			highlightSquare(row-1,col-1);
            x=row-1; y=col-1;
            output = "mm"+x+" "+y;
		}
        
        return output;
	}


    public void showLegalMoveSquares(int row,int col){
    	message.setText("inside showLegalMoveSquares");
 
    	if (canJump(board.getPlayer(),row, col, row+1, col+1, row+2, col+2)){
			highlightSquare( row+2, col+2);
		}else if (canMove(board.getPlayer(),row,col,row+1,col+1)){
			highlightSquare( row+1,col+1);
		}

		if (canJump(board.getPlayer(), row, col, row-1, col+1, row-2, col+2)){
			highlightSquare(row-2, col+2);
		}else if (canMove(board.getPlayer(),row,col,row-1,col+1)){
				highlightSquare(row-1,col+1);
		}
			
		if (canJump(board.getPlayer(), row, col, row+1, col-1, row+2, col-2)){
			highlightSquare(row+2,col-2);
		}else if (canMove(board.getPlayer(),row,col,row+1,col-1)){
				highlightSquare(row+1,col-1);
		}
			
		if (canJump(board.getPlayer(), row, col, row-1, col-1, row-2, col-2)){
			highlightSquare(row-2, col-2);
		}else if (canMove(board.getPlayer(),row,col,row-1,col-1)){
			highlightSquare(row-1,col-1);
		}
	}
    
	public JPanel getBackgroundBoard(){
		return backgroundBoard;
	}

	//@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("in mouse clicked");
        //message.setText("mouse clicked");
	}

	//@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("in mouse entered");
        //message.setText("mouse entered");
	}

	//@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("in mouse exied");
        //message.setText("mouse exited");
	}

	//@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("in mouse pressed");
		if(board.getPlayer()==0){
			System.out.println("returning");
			return;
		}	
        board.setPlayerPosChange(false);
		allowedDestination(e.getX(), e.getY());
        //board.setFromPosition(e.getX(), e.getY());
	}

	//@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("in mouse released");
		if(board.getPlayer()==0)
			return;
		makeMove(e.getX(),e.getY());
        //board.setToPosition(e.getX(), e.getY());
        if(playerPositionChanged){
            board.setPlayerPosChange(true);
            playerPositionChanged = false;
        }
	}

	//@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("in mouse dragged");
		if(board.getPlayer()==0)
			return;
		if (chPiece == null) return;
			chPiece.setLocation(e.getX() + xPos, e.getY() + yPos);
	}

	//@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("in mouse moved");
        //message.setText("mouse moved");
	}

    public boolean makeMove(int posX, int posY)
	{
    	System.out.println("in make move");
    	boolean isLegalMove = false;
    	
		paintSquareComponent();
		Component c = backgroundBoard.findComponentAt(posX, posY);
		int x = (int)c.getBounds().getX();
		int y = (int)c.getBounds().getY();
		System.out.println(c.getLocation());
		System.out.println(x + " "+ y);
		



		//c.setBackground(currentColor);
		if (chPiece == null)
			return false;

		int newx = posX;
    	int newy = posY;
        int row = (newx - 2) / 50;
        int col = (newy - 2) / 50;
        
        board.setToPosition( row, col);
        
		int inROW = 100;
		int inCOL = 100;
		
		boolean isJumpValid = false;

		if((row==oldROW+2 && col==oldCOL+2)
		&& (canJump(board.getPlayer(), oldROW, oldCOL, oldROW+1, oldCOL+1, row, col )))
		{
			inROW = oldROW+1;
			inCOL = oldCOL+1;
			isJumpValid = true;
		} else if((row==oldROW-2 && col==oldCOL+2)
		&& (canJump(board.getPlayer(), oldROW, oldCOL, oldROW-1, oldCOL+1, row, col )))
		{
			inROW = oldROW-1;
			inCOL = oldCOL+1;
			isJumpValid = true;
		} else if((row==oldROW+2 && col==oldCOL-2)
		&& (canJump(board.getPlayer(), oldROW, oldCOL, oldROW+1, oldCOL-1, row, col )))
		{
			inROW = oldROW+1;
			inCOL = oldCOL-1;
			isJumpValid = true;
		}

		else if((row==oldROW-2 && col==oldCOL-2)
		&& (canJump(board.getPlayer(), oldROW, oldCOL, oldROW-1, oldCOL-1, row, col )))
		{
			inROW = oldROW-1;
			inCOL = oldCOL-1;
			isJumpValid = true;
		}


		if(oldROW<64 && oldCOL<64){
			if(isJumpValid)
				{
					chPiece.setVisible(false);

					if (c instanceof JLabel)
					{
						Container parent = c.getParent();
						//remove the piece that is capture
						parent.remove(0);
						parent.add( chPiece );
					}
					else
					{
						Container parent = (Container)c;
						parent.add( chPiece );
					}

		            if(oldROW<64 && oldCOL<64 && (oldROW!=row || oldCOL!=col)){
			            board.setBoardPiece(oldROW, oldCOL, 2);
			            board.setBoardPiece(row, col, board.getPlayer());
			            board.setBoardPiece(inROW, inCOL, 2);

			            JPanel panel = (JPanel) getBackgroundBoard().getComponent(inROW+inCOL*8);
		    			panel.removeAll();

		    			paintSquareComponent();


			            if(board.getPlayer()>0){
							board.setPlayer(0);
							board.setTotal_orange_pieces(board.getTotal_orange_pieces()-1);
			            } else{
							board.setPlayer(1);
							board.setTotal_blue_pieces(board.getTotal_blue_pieces()-1);
			            }

			            if(board.getTotal_blue_pieces()==0){
			    			newGame();
			    			message.setText("Server wins game.");
			    		} else if(board.getTotal_orange_pieces()==0){
			    			newGame();
			    			message.setText("Blue Player wins game.");
			    		}
                        playerPositionChanged = true;
                        isLegalMove = true;
					}

					chPiece.setVisible(true);
			        chPiece = null;
			        isJumpValid = false;

		        	oldROW = 100;
		        	oldCOL = 100;
		        	inROW = 100;
		        	inCOL = 100;

		            for (int i = 0; i < 8; i++) {
		                for (int j = 0; j < 8; j++) {
				            System.out.println(" i:"+i+" j:"+j+" val:"+board.getBoardPiece(i, j));

		                }
		            }
				}else if(((row==oldROW+1 && col==oldCOL+1)
				|| (row==oldROW-1 && col==oldCOL+1)
				|| (row==oldROW+1 && col==oldCOL-1)
				|| (row==oldROW-1 && col==oldCOL-1)
				) && canMove(board.getPlayer(), oldROW, oldCOL, row, col))
				{
					chPiece.setVisible(false);

					if (c instanceof JLabel)
					{
						Container parent = c.getParent();
						//remove the piece that is capture
						parent.remove(0);
						parent.add( chPiece );
					}
					else
					{
						Container parent = (Container)c;
						parent.add( chPiece );
					}

		            if(oldROW<64 && oldCOL<64 && (oldROW!=row || oldCOL!=col)){
			            board.setBoardPiece(oldROW, oldCOL, 2);
			            board.setBoardPiece(row, col, board.getPlayer());

			            if(board.getPlayer()>0)
							board.setPlayer(0);
						else
							board.setPlayer(1);
                        
                        playerPositionChanged = true;
                        isLegalMove = true;
					}

					chPiece.setVisible(true);
			        chPiece = null;
			        
			}else{
    			JPanel panel = (JPanel) getBackgroundBoard().getComponent(oldROW+oldCOL*8);
    			panel.removeAll();
				panel.add(chPiece);

	        	chPiece = null;
	        	oldROW = 100;
	        	oldCOL = 100;
	        	isLegalMove = false;
			}
		}
        
		board.recreateBoard();
		paintSquareComponent();
		return isLegalMove;
	}

	public void allowedDestination(int posX, int posY){
		if(board.getTotal_blue_pieces()==0){
			newGame();
			message.setText("Server wins game.");
		} else if(board.getTotal_orange_pieces()==0){
			newGame();
			message.setText("Blue Player wins game.");
		}

        chPiece = null;
        oldROW = 100;
        oldCOL = 100;
	    if (board.getGameProgress() == false)
	        message.setText("Game not in Progress.");
	    else{
	    	int x = posX;
	    	int y = posY;
            int row = (x - 2) / 50;
            int col = (y - 2) / 50;

            if(board.getPlayer() != board.getBoardPiece(row, col)){
            	if(board.getPlayer()==1)
            		message.setText("Its your turn. "+board.getPlayer());
            	else
            		message.setText("Its server's turn. "+board.getPlayer());
            }else{
                oldROW = row;
                oldCOL = col;
	            chPiece = null;
	            board.setFromPosition(row, col);
	            
				Component c = backgroundBoard.findComponentAt(x, y);

				//c.setBackground(Color.green);
				message.setText("mousePressed row:"+row+" col:"+col);

				if (c instanceof JPanel)
					return;

				Point pLoc = c.getParent().getLocation();
				xPos = pLoc.x - posX;
				yPos = pLoc.y - posY;
				chPiece = (JLabel)c;
				chPiece.setLocation(posX + xPos, posY + yPos);
				chPiece.setSize(chPiece.getWidth(), chPiece.getHeight());
				//jlayeredPane.add(chPiece, JLayeredPane.DRAG_LAYER);

	            if (col >= 0 && col < 8 && row >= 0 && row < 8){
	               showLegalMoveSquares(row,col);
	            }
            }
	    }

	}


}