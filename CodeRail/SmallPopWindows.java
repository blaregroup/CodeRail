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
//import javax.swing.SwingConstants;


/*
	Reference Docs
		 	https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
			https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog/6555051
			https://docs.oracle.com/javase/7/docs/api/javax/swing/JDialog.html
			https://tips4java.wordpress.com/2008/10/29/line-painter/
			https://www.daniweb.com/programming/software-development/threads/417646/find-a-word-in-jtextarea-and-higlight-it

*/



public class SmallPopWindows extends JDialog implements ActionListener{

	// Get in in reverse
	public int dialogtype; 


	// Input Field
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();

	private JButton button1;
	private JButton button2;
	private JTextArea textobj;


	public SmallPopWindows(JFrame parent, JTextArea texto, int dialog_type_code){
		super(parent);
		textobj = texto; // JTextArea object
		dialogtype = dialog_type_code;

		GenerateDialog();
	}

	// Constructor
	public SmallPopWindows(JTextArea texto, int dialog_type_code){
		textobj = texto; // JTextArea object
		dialogtype = dialog_type_code;
		GenerateDialog();
	}

	private void GenerateDialog(){
		/*
			Use dialog_type_code:
				0 = Find Dialog [Default]
				1 = Replace dialog
		*/

		// Replace Dialog Code
		if (dialogtype==1) {
			
			InitGUIReplace();

		// Find Dialog Codes
		}else{
			
			InitGUIFind();
		}
		InitGUI();
	}

	private void perform_find_routine(){
		String allinput = textobj.getText();
		String input = input1.getText();
		System.out.println("request to perform find routine "+input);

	}
	private void perform_findall_routine(){
		String allinput = textobj.getText();
		String input = input2.getText();
		System.out.println("request to perform findall routine "+input);
	}
	private void perform_replace_routine(){
		String allinput = textobj.getText();
		String inp_1 = input1.getText();
		String inp_2 = input2.getText();
		System.out.println("request to perform replace "+inp_1+ " with " + inp_2);
	}
	private void perform_replaceall_routine(){
		String allinput = textobj.getText();
		String inp_1 = input1.getText();
		String inp_2 = input2.getText();
		System.out.println("request to perform replace all "+inp_1+ " with " + inp_2);
	}

	public void actionPerformed(ActionEvent e){

		if (e.getSource()==button1) {
			// Find Window Find button clicked
			if(dialogtype==0){
				perform_find_routine();
			// Replace Window Replace Button Clicked
			}else{
				perform_replace_routine();
			}
		}
		else{
			// Find Window Findall Button Clicked
			if(dialogtype==0){
				perform_findall_routine();
			}
			// Replace Window ReplaceAll Button Clicked
			else{
				perform_replaceall_routine();
			}
		}

	}

	// Initialise GUI
	private void InitGUI(){	
		setFocusable(true);
		setVisible(true);
		setSize(600, 100);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	private void InitGUIReplace(){

		setTitle("Replace ...");

		JLabel label_one = new JLabel("Replace This", SwingConstants.CENTER);
		JLabel label_two = new JLabel("With This", SwingConstants.CENTER);

		button1 = new JButton("Replace ");
		button2 = new JButton("Replace All");

		button1.addActionListener(this);
		button2.addActionListener(this);

		// Using Grid Layout

		add(label_one);
		add(input1);
		add(button1);
		add(label_two);
		add(input2);
		add(button2);
		
		// Panel Attach To main root
		setLayout(new GridLayout(2,3));

	}


	// various types of GUI
	private void InitGUIFind(){
		// Panel Created
		//JPanel left_pane = new JPanel();
		//JPanel center_pane = new JPanel();
		//JPanel right_pane = new JPanel();
		setTitle("Find ...");

		JLabel label_one = new JLabel("Find", SwingConstants.CENTER);
		JLabel label_two = new JLabel("Find All", SwingConstants.CENTER);

		button1 = new JButton("Find");
		button2 = new JButton("Find All");
		button1.addActionListener(this);
		button2.addActionListener(this);

		/*
				BorderLayout

		left_pane.add(label_one, BorderLayout.NORTH);
		left_pane.add(label_two, BorderLayout.SOUTH);

		center_pane.add(input1, BorderLayout.NORTH);
		center_pane.add(input2, BorderLayout.SOUTH);

		right_pane.add(button1, BorderLayout.NORTH);
		right_pane.add(button2, BorderLayout.SOUTH);

		add(left_pane, BorderLayout.EAST);
		add(center_pane, BorderLayout.CENTER);
		add(right_pane, BorderLayout.WEST);
		*/

		// Using Grid Layout

		add(label_one);
		add(input1);
		add(button1);
		add(label_two);
		add(input2);
		add(button2);
		
		// Panel Attach To main root
		setLayout(new GridLayout(2,3));

	}

		public static void main(String args[]){
		JFrame root = new JFrame("YUP");
		JTextArea textarea = new JTextArea("Yup! Working A B A B Abc");
		
		JButton button1 = new JButton("Replace");
		JButton button3 = new JButton("Find..");


		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SmallPopWindows d = new SmallPopWindows(root,textarea, 1);
			}
		});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SmallPopWindows d = new SmallPopWindows(root,textarea, 0);
			}
		});
		root.add(button1, BorderLayout.EAST);
		root.add(button3, BorderLayout.WEST);
		root.add(textarea,BorderLayout.CENTER);
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		root.setFocusable(true);
		root.setSize(400, 400);
		root.setVisible(true);
	}
}