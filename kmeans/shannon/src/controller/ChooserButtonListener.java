package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.Logics;

public class ChooserButtonListener implements ActionListener {

	JTextField rateField;
	JTextField fileField;
	
	public ChooserButtonListener(JTextField fileField,JTextField rateField){
		
		this.fileField=fileField;
		this.rateField=rateField;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

			fileField.setText(Logics.getFilePath());
			rateField.setEditable(true);
			Logics.processAudioFile(fileField.getText());
			double maxFrequency=Logics.getMaxFrequency(fileField.getText());
			rateField.setText(""+Math.round(maxFrequency));
			//rateField.setText(""+maxFrequency);
			rateField.setEditable(false);
		
	}

}
