package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.Logics;

public class RecordButtonListener implements ActionListener {

	JTextField rateField;
	JTextField fileField;
	
	public RecordButtonListener(JTextField fileField,JTextField rateField){
		
		this.fileField=fileField;
		this.rateField=rateField;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Logics.recordAudio();
		Logics.processAudioFile(fileField.getText());
		rateField.setEditable(true);
		double maxFrequency=Logics.getMaxFrequency(fileField.getText());
		rateField.setText(""+Math.round(maxFrequency));
		rateField.setEditable(false);
		
		
	}

}
