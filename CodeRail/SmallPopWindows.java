package CodeRail;


/*
================================================================================
						Project CodeRail
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			himanshu sharma


	Reference Docs
			https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
			https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog/6555051
			https://docs.oracle.com/javase/7/docs/api/javax/swing/JDialog.html
			https://tips4java.wordpress.com/2008/10/29/line-painter/
			https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html
			https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
			https://www.daniweb.com/programming/software-development/threads/417646/find-a-word-in-jtextarea-and-higlight-it

*/

// import modules
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.net.URI;



// Class To Handle Many Pop Window Functionalities
public class SmallPopWindows extends JDialog implements ActionListener{

	// Get in in reverse
	public int dialogtype; 			// Dialog Type
	private Pattern pattern;		// Pattern Object 
	private Matcher matcher;		// Matcher Object
	private String remindinput="";	// Remind Previous Request Input
	private Highlighter brush;		// JTextArea Highlighter
	Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 190, 118));


	// Global Input Field
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();

	private JButton button1;
	private JButton button2;
	private JTextArea textobj;

	private JButton github_link;
	private JButton blaregroup_link;
	private JButton license_link;


	public SmallPopWindows(JFrame parent, JTextArea texto, int dialog_type_code){
		super(parent);
		textobj = texto; // JTextArea object
		dialogtype = dialog_type_code;
		brush = texto.getHighlighter();
		GenerateDialog();
	}

	// Constructor
	public SmallPopWindows(JTextArea texto, int dialog_type_code){
		textobj = texto; // JTextArea object
		dialogtype = dialog_type_code;
		brush = texto.getHighlighter();
		GenerateDialog();

	}

	private void GenerateDialog(){
		/*
			Use dialog_type_code:
				0 = Find Dialog [Default]
				1 = Replace dialog
				2 = About Dialog
		*/

		addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent e)
				{
					brush.removeAllHighlights();
					e.getWindow().dispose();
				}
			});
		

		// Replace Dialog Code
		if (dialogtype==1) {
			InitGUIReplace();

		
		}
		else if(dialogtype==2){
			//AboutDialogCode
			//System.out.println("YUP");
			InitGUIAbout();

		}else{
		// Find Dialog Codes
			InitGUIFind();
		}

		InitGUI();
	}

	// find routine 
	private void perform_find_routine(){
		System.out.println("[+] Request to perform find routine ");
		find_engine(false);
	}

	private void perform_findall_routine(){
		System.out.println("[+] Request to perform findall routine ");
		find_engine(true);
	}
	private void highlight_text(int start, int end){
		try{
			brush.addHighlight(start, end, painter);
		}catch (Exception error){
			System.out.println(error);
		}

	}
	private void find_engine(boolean findall){
		/*
		Method That Can Handle All Find Routine Related Internal Processing Functionality.

		if findall is true:
			Then Perform Findall Routine
		Else Perform Find Routine
		
		*/
		String input;

		// Retrive Data
		if (findall){	
			input = input2.getText();	// Get Findall Input 
		}
		else{
			input = input1.getText();	// Get Find Input
		}
		String allinput = textobj.getText(); 	// Get Text

		// Check if Input is Provided
		if (input.length()!=0) {
			
			// Check If Input is Changed From Previous Requested Routine
			if (remindinput.compareTo(input)==0) {
				System.out.println("[-] Input is Same");
			}
			else{
				// if New Input is Provided
				System.out.println("[-] Input Changed");
				remindinput = input;		

				// Compile Regular Expression
				pattern = Pattern.compile(input);
				matcher = pattern.matcher(allinput);
			}			

			// Print Information
			System.out.println("[+] Input Length "+input.length());
			
			// Run A Loop
			while(true){

				if (!findall) {
					brush.removeAllHighlights();
					
				}
				// if Any Match Found
				if (matcher.find()) {
					// Get Start and End
					int start = matcher.start();
					int end  = matcher.end();
					highlight_text(start, end);

					System.out.println("[+] Word Find : "+ start);
					System.out.println("[-] Here is Your Work "+allinput.substring(start, end));					
				}else{
					// if Match Not Found
					System.out.println("[-] Nothing Matched");
					matcher.reset();
					break;

				}

				// if its not find all then Quit in Single Run
				if (!findall) {
						break;
						
					}				
			}
		} 
		// input word not provided
		else{
			System.out.println("[-] Search Input is Empty");
		}
	}


	private void replace_engine(boolean replaceall){

		/*
		Replace Engine
		If ReplaceAll is True:
			Then Perform Replace All Functionality
		Else:
			Single Replace

		*/
		String input;
		String replacer;

		// Retrive Data

		replacer = input2.getText();	// Get Replae with 
		input = input1.getText();		// Get Input
		String allinput = textobj.getText(); 	// Get Text


		// Check if Input is Provided
		if (input.length()!=0) {
			
			// Check If Input is Changed From Previous Requested Routine
			if (remindinput.compareTo(input)==0) {
				System.out.println("[-] Input is Same");
				
			}
			else{
				// if New Input is Provided
				System.out.println("[-] Input Changed");
				remindinput = input;		

				// Compile Regular Expression
				pattern = Pattern.compile(input);
				matcher = pattern.matcher(allinput);
			}			


			// Print Information
			System.out.println("[+] Input Length "+input.length());
			
			// Run A Loop
			while(true){

				if (!replaceall) {
					brush.removeAllHighlights();
					
				}
				// if Any Match Found
				if (matcher.find()) {
					// Get Start and End
					int start = matcher.start();
					int end  = matcher.end();
					
					System.out.println("[+] Word Find : "+ start);
					System.out.println("[-] Here is Your Work "+allinput.substring(start, end));
					textobj.replaceRange(replacer , start, end);
					highlight_text(start, start+replacer.length());					
				}else{
					// if Match Not Found
					System.out.println("[-] Nothing Matched");
					matcher.reset();
					break;

				}

				// if its not find all then Quit in Single Run
				if (!replaceall) {
						break;
						
					}				
			}
		} 
		// input word not provided
		else{
			System.out.println("[-] Search Input is Empty");
		}


	}


	private void perform_replace_routine(){
		System.out.println("[+] Request to perform replace ");
		replace_engine(false);
	}
	private void perform_replaceall_routine(){
		System.out.println("[+] Request to perform replace all ");
		replace_engine(true);
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
		else if(e.getSource()==github_link)
		{	//github_link
			if (Desktop.isDesktopSupported()) 
					{
				      try {
				        	Desktop.getDesktop().browse(new URI("http://github.com/blareGroup/CodeRail"));
				      }
				  	  catch (Exception m) {}
				    } 
		}
		else if(e.getSource()==blaregroup_link)
		{
			//blaregroup_line
			if (Desktop.isDesktopSupported()) 
					{
				      try {
				        	Desktop.getDesktop().browse(new URI("http://blareGroup.com"));
				      }
				  	  catch (Exception m) {}
				    }

		}
		else if(e.getSource()==license_link)
		{
			//license_link
			if (Desktop.isDesktopSupported()) 
					{
				      try {
				        	Desktop.getDesktop().browse(new URI("https://github.com/blaregroup/CodeRail/blob/master/LICENSE"));
				      }
				  	  catch (Exception m) {}
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
		if(dialogtype==2)
		{
			setSize(700,480);
		}

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

	private void InitGUIAbout(){

		setTitle("About CodeRail ");
		setLayout(null);
		

		//creating layout 
		JPanel mainpanel=new JPanel();
		JPanel leftpanel=new JPanel();
		JPanel rightpanel=new JPanel();
		JPanel bottompanel=new JPanel();
		
		
		//adding useful component
		JLabel head=new JLabel("<html><b>CodeRail</b> Text Editor</html>");
		JTextArea about=new JTextArea();
		JLabel copyright= new JLabel("© 2019 BLARE GROUP(www.blaregroup.com)");
		Icon img = new ImageIcon(getClass().getResource("logo.jpg")); 
		JLabel logo = new JLabel(img);
		
		//adding button
		github_link= new JButton("Github");
		blaregroup_link = new JButton("visit Website");
		license_link = new JButton("License");
		
		
		
		

		//head label editing
		head.setFont(new Font("",Font.PLAIN,30));
		head.setForeground(Color.WHITE);



		//about label editing
		about.setEditable(false);
		about.setLineWrap(true);
		about.setMargin(new Insets(10,10,0,5));
		about.setWrapStyleWord(true);
		about.setBackground(new Color(31, 35, 64));
		about.setForeground(Color.WHITE);
		about.setFont (new Font("",Font.ITALIC,17));
		about.setText("CodeRail is a Open Source Text Editor. It is a Platform Independent Text Editor.");
		about.append("\n\nCodeRail is created by BlareGroup, a Developers Group who create softwares and programs.");
		about.append("\n\nCredit :-\n\nSuraj Singh Bisht (surajsinghbisht054@gmail.com)");
		about.append("\n\nHimanshu Sharma (himanshusharma2972@gmail.com)");				
		

		//github 
		github_link.addActionListener(this);
		
		//blaregroup
		blaregroup_link.addActionListener(this);

		//license
		license_link.addActionListener(this);
		
		//copyright
		copyright.setFont(new Font("",Font.ITALIC,12));
		copyright.setForeground(Color.WHITE);

	



		//setting position of components
		head.setBounds(10,5,440,50);
		about.setBounds(0,60,440,320);
		license_link.setBounds(100,10,130,20);
		github_link.setBounds(290,10,130,20);
		blaregroup_link.setBounds(470,10,130,20);
		copyright.setBounds(200,40,500,50);
		logo.setBounds(20,70,200,200);
		
		//config main panel
		mainpanel.setBackground(new Color(31, 35, 64));
		mainpanel.setBounds(0,0,700,400);
		mainpanel.setLayout(null);

		//config left panel
		leftpanel.setBackground(new Color(31, 35, 64));
		leftpanel.setForeground(Color.WHITE);
		leftpanel.setLayout(null);
	 	leftpanel.setBounds(0,0,250,398);

		//config right panel
		rightpanel.setBackground(new Color(31, 35, 64));
		rightpanel.setLayout(null);
		rightpanel.setBounds(252,1,450,398);
			
		//config  bottom panel			
		bottompanel.setBackground(new Color(21, 25, 54));
		bottompanel.setBounds(0,400,700,80);
		bottompanel.setLayout(null);

		//adding panels to window
		add(mainpanel);
		add(bottompanel);

		//adding component on main panel
		mainpanel.add(leftpanel);
		mainpanel.add(rightpanel);

		//adding  component on left panel
		leftpanel.add(logo);

		//adding component on right panel
		rightpanel.add(head);
		rightpanel.add(about);

		//adding component on bottom panel
		bottompanel.add(license_link);
		bottompanel.add(blaregroup_link);
		bottompanel.add(github_link);
		bottompanel.add(copyright);
		
		

	}

		public static void main(String args[]){
		JFrame root = new JFrame("YUP");
		JTextArea textarea = new JTextArea("Testing Purpose ABCABCABCABCABCABCABCABCABCABC");
		
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