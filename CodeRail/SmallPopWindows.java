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

*/



public class SmallPopWindows extends JDialog implements ActionListener{

	// Get in in reverse
	public String getinput_one;
	public String getinput_second;

	// Input Field
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();
	private JButton button1;
	private JButton button2;

	public SmallPopWindows(JFrame parent, int dialog_type_code){
		super(parent);
		GenerateDialog(dialog_type_code);
	}

	// Constructor
	public SmallPopWindows(int dialog_type_code){
		GenerateDialog(dialog_type_code);
	}

	private void GenerateDialog(int dialog_type_code){
		/*
			Use dialog_type_code:
				0 = Find Dialog [Default]
				1 = Replace dialog
		*/

		// Replace Dialog Code
		if (dialog_type_code==1) {
			
			InitGUIReplace();

		// Find Dialog Codes
		}else{
			
			InitGUIFind();
		}
		InitGUI();
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource()==button1) {
			getinput_one = input1.getText();
			getinput_second = "";
		}
		else{
			getinput_one = "";
			getinput_second = input2.getText();
		}

		System.out.println(getinput_one);
		System.out.println(getinput_second);
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
		
		JButton button1 = new JButton("Replace");
		JButton button2 = new JButton("Replace All");
		JButton button3 = new JButton("Find..");
		JButton button4 = new JButton("Find All");


		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SmallPopWindows d = new SmallPopWindows(root, 1);
			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SmallPopWindows d = new SmallPopWindows(root, 1);
			}
		});
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			SmallPopWindows d = new SmallPopWindows(root, 0);
			}
		});
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Find all Trigger
				SmallPopWindows d = new SmallPopWindows(root, 0);
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