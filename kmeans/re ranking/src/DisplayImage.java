import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class DisplayImage extends JFrame{
       public DisplayImage(String url){
         // setSize(1000, 750);  <---- do not do it
         // setResizable(false); <----- do not do it either, unless any good reason

                 

         ImageIcon image = new ImageIcon(url);
         JLabel label = new JLabel(image);
         JScrollPane scrollPane = new JScrollPane(label);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         add(scrollPane, BorderLayout.CENTER);
         setSize(800,600);
         setVisible(true);
         //pack();
      }

	
}