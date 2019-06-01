package CodeRail;

/*
================================================================================
						Project CodeRail
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			himanshu sharma
*/


// import modules
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

// https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html

// editor class name
public class Editor extends JTextArea implements DocumentListener {

	// Constructor
	public Editor(int w, int h){

		super(w, h);
		
		// Configurations
		setVisible(true);
		setFocusable(true);
	
		//setBackground(Color.blue);
		//setForeground(Color.white);
		//setFont(new Font("",Font.BOLD,25));
		
		getDocument().addDocumentListener(this);
	}

	// Overrided Event Listeners
	public void changedUpdate(DocumentEvent doc){

	}

	//
	public void removeUpdate(DocumentEvent doc){
	
	
	}
	
	//
	public void insertUpdate(DocumentEvent doc){
	}

	/*

	USEFUL Methods

	copy()
	cut()
	getSelectedText()
	getSelectionEnd
	getSelectionStart
	getText
	getText <-- Specific Portion
	paste
	replaceSelection
	select
	selectAll
	setText
	updateUI

	*/


	// main function trigger 
	public static void main(String[] args){
		
		// Create Window Frame
		JFrame obj1 = new JFrame("CodeRail Text");

		// Window Configuration
		//obj1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		obj1.setSize(300, 400);
		obj1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//obj1.setVisible(true);
		//obj1.setFocusable(true);
		
		// Create Text Area Object
		Editor obj = new Editor(200, 300);
		obj1.add(obj);
		
	}

}


