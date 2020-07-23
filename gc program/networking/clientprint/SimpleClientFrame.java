



import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;


public class SimpleClientFrame extends JFrame {
    private JTextArea _messageBox;
    private JButton _chooseFileButton;
    private JButton _printFileButton;
    private JFileChooser _fileChooser;

    public SimpleClientFrame() {
        buildGUI();
       
    }

    private void buildGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        _messageBox = new JTextArea();
        mainPanel.add(new JScrollPane(_messageBox), BorderLayout.CENTER);
        createButtons();
        JPanel buttonHolder = new JPanel(new GridLayout(1, 2));

        buttonHolder.add(_chooseFileButton);
        buttonHolder.add(_printFileButton);
        mainPanel.add(buttonHolder, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
    }

    private void createButtons() {
        _chooseFileButton = new JButton("Choose File");
        _chooseFileButton.addActionListener(new FindFile());
        _printFileButton = new JButton("Print File");
        _printFileButton.addActionListener(new PrintFile());
    }

   


    private class FindFile implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	if(_fileChooser==null)
			{
				_fileChooser=new JFileChooser();
				if(JFileChooser.APPROVE_OPTION==_fileChooser.showOpenDialog(SimpleClientFrame.this))
					_messageBox.setText(_fileChooser.getSelectedFile().getAbsolutePath());
			}
         
        }
    }


    private class PrintFile implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                FileInputStream documentStream = new FileInputStream(_fileChooser.getSelectedFile());
                DocumentDescription documentDescription = new
                    DocumentDescription(documentStream);

                /*
                 New network code here
                 
                 */
                 
                
                
		
				Object pter=Naming.lookup("rmi://localhost/sprinter");
				
				Printer printer=(Printer)pter;
                printer.printDocument(documentDescription);

            } catch (PrinterException printerException) {
                String errorMessage = "Print failed after " + printerException.getNumberOfPagesPrinted() + " pages.";

                JOptionPane.showMessageDialog(SimpleClientFrame.this,
                    errorMessage,
                    "Error in printing", JOptionPane.INFORMATION_MESSAGE);
                _messageBox.setText("Exception attempting to print " + (_fileChooser.getSelectedFile()).getAbsolutePath() +
                    "\n\t Error was: " + printerException.getHumanReadableErrorDescription());
            } catch (Exception exception) {
                _messageBox.setText("Exception attempting to print " + (_fileChooser.getSelectedFile()).getAbsolutePath() +
                    "\n\t Error was: " + exception.toString());
                exception.printStackTrace();
            }
        }
    }
}
