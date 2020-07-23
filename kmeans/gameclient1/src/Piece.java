

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Piece {
	
	//private static JLabel pieces[][] = new JLabel[16][16];
	//private static JLabel whitePieces[] = new JLabel[16];
	
	Piece()	{}
	
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	
	public JLabel getImageIcon(int whichColor, int val){
        	if ( whichColor==0) {
    			ImageIcon icon1 = createImageIcon("orange.png","icon"+val);
            	return new JLabel(icon1);
            }else if ( whichColor==1) {
    			ImageIcon icon1 = createImageIcon("blue.png","icon"+val);
            	return new JLabel(icon1);
            }else
            	return null;
    }


	/*public JLabel getPiece(int i, int j){
		if(i>=0 && i<8 && j>=0 && j<8){
			return pieces[i][j];
		}else{
			return null;
		}
	}

	public JLabel setPiece(int i, int j){
		pieces[i][j];
	}*/
	
}