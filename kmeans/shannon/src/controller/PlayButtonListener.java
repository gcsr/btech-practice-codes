package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.Logics;

public class PlayButtonListener implements ActionListener {


	
	public PlayButtonListener(){
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {

			Logics.playSong();	
		
	}

}
