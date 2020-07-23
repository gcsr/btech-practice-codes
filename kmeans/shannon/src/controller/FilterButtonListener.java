package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import biz.source_code.dsp.filter.FilterPassType;
import model.Logics;

public class FilterButtonListener implements ActionListener {

	JTextField filterOrderField;
	JTextField fcf1Field;
	
	public FilterButtonListener(JTextField filterOrderField,JTextField fcf1Field){
		this.filterOrderField=filterOrderField;
		this.fcf1Field=fcf1Field;
	}
	
	public void actionPerformed(ActionEvent arg0) {

		try{
				Logics.filterWavFile("output.au",FilterPassType.lowpass, Integer.parseInt(filterOrderField.getText()), Float.parseFloat(fcf1Field.getText()), .8,"output.au");
			}catch(Exception ex){
				ex.printStackTrace();
			}
			  		
		
	}

}
