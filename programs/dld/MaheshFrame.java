import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class MaheshFrame extends JFrame{

	JList list;
	/**
	 * @param args
	 */
	MaheshFrame()
	{
		String[] selection={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",};
		list =new JList(selection);
		list.setVisibleRowCount(5);
		list.setSelectedIndex(4);
		setLayout(new BorderLayout());
		add(list,BorderLayout.NORTH);
		setSize(500,500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MaheshFrame();

	}

}
