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
import java.awt.*;
import java.awt.event.*;


/*
	Reference Docs
		 	https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
			https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog/6555051
*/

// Main Class To Handle All Small Dialog Box Related Operations
public class SmallDialogBoxes{

	// Collect Dialog box input values from these variables
	public String firstinput="";	
	public String secondinput = "";

	// check if last request was success or fail
	public boolean lastrequest = false;

	// Parent Object
	private JFrame parent;

	// Constructor
	public SmallDialogBoxes(JFrame obj){
		parent = obj;
	}

	// ask to replace
	public void askreplace(){
		System.out.println("[+] Ask Replace Popup");

		// Components to show
		JTextField obj1 = new JTextField();
		JTextField obj2 = new JTextField();
		Object[] container = {
			"Replace ", obj1,
			"With ", obj2,
		};

		// show box
		int option = JOptionPane.showConfirmDialog(parent, 
			container,"Replace Single ..", JOptionPane.OK_CANCEL_OPTION);

		if (option==JOptionPane.OK_OPTION) {
			firstinput =  obj1.getText();
			secondinput = obj2.getText();
			lastrequest = true;
		}else{
			firstinput = "";
			secondinput = "";
			lastrequest = false;
		}
		System.out.println(option);

	}

	// Ask To Replace All
	public void askreplaceall(){
		System.out.println("[+] Ask ReplaceALL Popup");

		// Components to show
		JTextField obj1 = new JTextField();
		JTextField obj2 = new JTextField();
		Object[] container = {
			"Replace this ", obj1,
			"With this ", obj2,
		};

		// show box
		int option = JOptionPane.showConfirmDialog(parent, 
			container,"Replace All..", JOptionPane.OK_CANCEL_OPTION);

		if (option==JOptionPane.OK_OPTION) {
			firstinput =  obj1.getText();
			secondinput = obj2.getText();
			lastrequest = true;
		}else{
			firstinput = "";
			secondinput = "";
			lastrequest = false;
		}
		System.out.println(option);
	}

	// Find Dialog 
	public void askfind(){
		System.out.println("[+] Ask Replace Popup");
		firstinput = JOptionPane.showInputDialog(parent, "Find", "Enter the word to find", JOptionPane.QUESTION_MESSAGE);
		
		if (firstinput==null){
			firstinput = "";
			lastrequest = true;
		}
		else{
			lastrequest = false;
		}



	}
	
	// Find All Dialog
	public void askfindall(){
		System.out.println("[+] Ask FindAll Popup");
		firstinput = JOptionPane.showInputDialog(parent, "Find All", "Enter the word to find", JOptionPane.QUESTION_MESSAGE);
		
		if (firstinput==null){
			firstinput = "";
			lastrequest = true;
		}
		else{
			lastrequest = false;
		}


	}

	// main function
	public static void main(String args[]){
		JFrame root = new JFrame("YUP");
		
		SmallDialogBoxes obj =  new SmallDialogBoxes(root);

		JButton button1 = new JButton("Replace");
		JButton button2 = new JButton("Replace All");
		JButton button3 = new JButton("Find..");
		JButton button4 = new JButton("Find All");


		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj.askreplace();
			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj.askreplaceall();
			}
		});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj.askfind();
			}
		});
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				obj.askfindall();
			}
		});
		root.add(button1, BorderLayout.NORTH);
		root.add(button2, BorderLayout.SOUTH);
		root.add(button3, BorderLayout.EAST);
		root.add(button4, BorderLayout.WEST);
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setFocusable(true);
		root.pack();
		root.setVisible(true);
	}
}
