// import modules
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager;
import CodeRail.*;
import java.net.URI;


/*
================================================================================
						Project CodeRail
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			himanshu sharma
			himanshusharma2972@gmail.com

	================================================================================
									README
	================================================================================
	We have decided to follow, Divide and Conquer strategy. so, in this project, 
	CodeRail folder is our source of module and class. we are going to implement
	various types of functionality and features in seperate class files. so, that
	Contributors of Project don't have to mess with complete project code to understand
	any specific functions.
	 
	This Combined Controls class play the role of connector or main control. basically,
	this create object of all CodeRail Subdirectory class and then, connect them.

	USEFUL Documentation:
		https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html


*/
class CombinedControls extends JFrame implements ActionListener,ItemListener, DocumentListener, CaretListener {
	/*

		JFrame : Class To Create Main Window Object

		ActionLIstener: Implementation

				Configurations Settings

	*/
	private static final String window_name = "CodeRail Text Editor";
	private static final int window_width = 1000;
	private static final int window_height = 700;
	private static final int editor_width = 0;
	private static final int editor_height = 0;
	private static final boolean debug = false;
	private static boolean editing = false;
		


	// CodeRail Module Objects
	private AddMenuBar menu;        // Menu Bar Module
	private Editor obj;             // Editor Module
	private UndoManager manager;    // Undo Manager
	private FileManager FileObj;    // FileManager Module Object
	private JScrollPane scrolltext; // Scrollbar Pane
	private SmallPopWindows PopUpDialog; // pop up Dialog Box Windows
	private StatusBar statusbarObj; // Status Bar
	private LineNumberColumn clm;   // Line Numbers
	private JPanel panel;

	// constructor
	CombinedControls(){

		/* 
		This Class Specially Created To Handle Listener Implementation and Generate Calls To Required
		Methods.

			[https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html] 

		*/
		super(window_name);


		/* 

		Create TextArea Object [CodeRail.editor] 
			1. Create Editor Object
			2. Create Line Number Object
			3. Create ScrollPane Object and Add Editor Object
			4. Add Line Number Object in Scroll Pane
			5. Connect Event Listeners to This Class
			6. Initialise Configurations
		*/
		obj = new Editor(editor_width, editor_height);
		clm = new LineNumberColumn(obj);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(clm, BorderLayout.WEST);
		panel.add(obj, BorderLayout.CENTER);
		panel.setVisible(true);
		JScrollPane scrolltext = new JScrollPane(panel); 
		obj.getDocument().addDocumentListener(this);
		


		
		/* set font for JTextArea Object */
		Font font_family = new Font("Courier", Font.BOLD,16);
		obj.setFont(font_family);
		

		/* Undo manager to track change and undo changes */
		manager = new UndoManager();
		obj.getDocument().addUndoableEditListener(manager);

		/* Create Menu Bar Object [CodeRail.AddMenuBar] */
		menu = new AddMenuBar();
		menu.setBackground(new Color(142, 68, 173));

		/* File Manager Module Object */
		FileObj = new FileManager(obj);

		/* Status bar */
		statusbarObj = new StatusBar(obj);
		


		/* Window Configuration  */  
		setSize(window_width, window_height); // Set Size
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Close Operation
		add(menu);                              // Menu
		add(scrolltext);                        // Scroll Text
		add(statusbarObj,BorderLayout.SOUTH);   // Status Bar
		setJMenuBar(menu);                      // Menu
		setVisible(true);
		setFocusable(true);
		clm.update_line_number_configurations();


		/*
		Here, First we implemented ActionListener into self class.
		and then, register this self listener classes with menu bar
		object. Whenever user generate any type of event through menu
		bar, this actionListener Automatically get call.

		Then, actionPerfomed Method get called. we use if else condition
		to filter specific menu bar calls.

		*/
		registerListener();

	}
	@Override
	public void caretUpdate(CaretEvent caretEvent) {
		try{
			int offset = caretEvent.getMark();
			int line = obj.getLineOfOffset(offset);
			statusbarObj.LineStatusUpdate(line+1, offset - obj.getLineStartOffset(line));
		}catch(Exception error){
			System.out.println(error);

		}
		
	}


	public void changedUpdate(DocumentEvent documentEvent) {
		// System.out.println("Document Change Update");
		clm.update_line_number_configurations();
		editing = true;
		};

	public void removeUpdate(DocumentEvent documentEvent) {
		// System.out.println("remove Update");
		clm.UpdateLineNumbers();
		statusbarObj.UpdateStatus();
		editing = true;
		};

	public void insertUpdate(DocumentEvent documentEvent) {
		clm.UpdateLineNumbers();
		statusbarObj.UpdateStatus();
		editing = true;
		};

	public void registerListener(){
		/*
		Register Self Class ActionListener With Menu Bar Objects.
		So, Whenever User Generate Event this class actionPerform
		actomatically get call.

		*/
		
		// menu file items
		menu.menu_file_new.addActionListener(this);
		menu.menu_file_open.addActionListener(this);
		menu.menu_file_save.addActionListener(this);
		menu.menu_file_saveas.addActionListener(this);
		menu.menu_file_print.addActionListener(this);
		menu.menu_file_exit.addActionListener(this);


		// menu edit
		menu.menu_edit_undo.addActionListener(this);
		menu.menu_edit_redo.addActionListener(this);
		menu.menu_edit_cut.addActionListener(this);
		menu.menu_edit_copy.addActionListener(this);
		menu.menu_edit_paste.addActionListener(this);
		menu.menu_edit_delete.addActionListener(this);
		menu.menu_edit_selectall.addActionListener(this);
		menu.menu_edit_find.addActionListener(this);
		menu.menu_edit_replace.addActionListener(this);
		menu.menu_edit_goto.addActionListener(this);

		// menu view
		menu.theme_a.addActionListener(this);
		menu.theme_b.addActionListener(this);
		menu.theme_c.addActionListener(this);
		menu.theme_d.addActionListener(this);
		menu.theme_e.addActionListener(this);
		menu.theme_f.addActionListener(this);
		menu.theme_g.addActionListener(this);
		menu.theme_h.addActionListener(this);
		menu.theme_0.addActionListener(this);
		menu.language_c.addActionListener(this);
		menu.language_cplus.addActionListener(this);
		menu.language_csharp.addActionListener(this);
		menu.language_css.addActionListener(this);
		menu.language_html.addActionListener(this);
		menu.language_java.addActionListener(this);
		menu.language_javascript.addActionListener(this);
		menu.language_plain.addActionListener(this);
		menu.language_php.addActionListener(this);
		menu.language_python.addActionListener(this);
		menu.menu_view_statusbar.addItemListener(this);
		menu.menu_view_cursorline.addItemListener(this);
		menu.menu_view_linenumber.addItemListener(this);

		// menu font
		menu.menu_font_wordwrap.addActionListener(this);
		menu.menu_font_font.addActionListener(this);
		menu.menu_font_font_size_large.addActionListener(this);
		menu.menu_font_font_size_small.addActionListener(this);

		// menu help
		menu.menu_help_topic.addActionListener(this);
		menu.menu_help_about.addActionListener(this);

		// Override Close Operation
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				exit();
			}
		});

		obj.addCaretListener(this);
		
	}

	private void exit(){
		System.out.println("[-] Safe Protocol to Exit..");

		// if any changes made
		if(editing){

			int ask = JOptionPane.showConfirmDialog(null, 
					"Are you want to Save The Changes");

			// Yes, Save Changes and Close
			if (ask==JOptionPane.YES_OPTION) {
				FileObj.SaveFile();
				System.exit(0);
									
			// Don't Save Changes But Close 
			}else if (ask==JOptionPane.NO_OPTION) {
				System.exit(0);

			// Don't Do anything
			}else{

			}


		}else{
			System.exit(0);
		}
		
	}

	//adding action to checkbox menu like status bar,linenumber,cursor
	public void itemStateChanged(ItemEvent e) 
	{
		// for status bar state change
		if (e.getSource()==menu.menu_view_statusbar) {
			System.out.println("[+] Status Bar State Changed");

			if(e.getStateChange()==1)
			{
				statusbarObj.setVisible(true);
			}
			else{ 
				statusbarObj.setVisible(false);
			}
		}
		// for line number state change
		else if (e.getSource()==menu.menu_view_linenumber) {
			System.out.println("[+] Line Number State Changed");

			if(e.getStateChange()==1)
			{
				clm.setVisible(true);
			}
			else{ 
				clm.setVisible(false);
			}
		}

		// for cursor highlight
		else if (e.getSource()==menu.menu_view_cursorline){
			System.out.println("[+] Cursor Line State Changed");

			if(e.getStateChange()==1)
			{
				//statusbarObj.setVisible(true);
			}
			else{ 
				//statusbarObj.setVisible(false);
			}   
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (debug) {
			System.out.println(e.getSource());
							
		}
		try{
			CustomActionPerformed(e);

		}catch (Exception error){
			System.out.println("[-] Error Catch During CustomActionPerformed Function Call");
			error.printStackTrace();
			System.out.println(error);
		}
	}

	private void CustomActionPerformed(ActionEvent e) throws Exception{
		/*
		This method catch events.

			USEFUL JTextArea Supported Functions Methods

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
	

		// menu file items
		if (e.getSource()==menu.menu_file_new){
			if (debug) {
				System.out.println("[-] new file");

			}
			FileObj.SaveFile();
		}
		
		else if (e.getSource()==menu.menu_file_open){
			if (debug) {
				System.out.println("[-] open file");

			}
			FileObj.OpenFileChooser();
			clm.UpdateLineNumbers();
			statusbarObj.TotalLine();

		}
		
		else if (e.getSource()==menu.menu_file_save){
			if (debug) {
				System.out.println("[-] save file");

			}
			FileObj.SaveFile();

		}
		
		else if (e.getSource()==menu.menu_file_saveas){
			if (debug) {
				System.out.println("[-] save as file");

			}
			FileObj.SaveAsFile();
		}
		
		else if (e.getSource()==menu.menu_file_print){
			if (debug) {
				System.out.println("[-] print file");

			}
		}
		
		else if (e.getSource()==menu.menu_file_exit){
			if (debug) {
				System.out.println("[-] exit");

			}

			exit();
		}



		// menu edit
		else if (e.getSource()==menu.menu_edit_undo){
			if (debug) {
				System.out.println("[-] undo");

			}
			manager.undo();
		}
		
		else if (e.getSource()==menu.menu_edit_redo){
			if (debug) {
				System.out.println("[-] redo");

			}
			manager.redo();
		}
		
		else if (e.getSource()==menu.menu_edit_cut){
			if (debug) {
				System.out.println("[-] cut");

			}
			obj.cut();
		}
		
		else if (e.getSource()==menu.menu_edit_copy){
			if (debug) {
				System.out.println("[-] copy");

			}
			obj.copy();
		}
		
		else if (e.getSource()==menu.menu_edit_paste){
			if (debug) {
				System.out.println("[-] paste");

			}
			obj.paste();
		}
		
		else if (e.getSource()==menu.menu_edit_delete){
			if (debug) {
				System.out.println("[-] delete");

			}
			obj.setText("");

		}

		else if (e.getSource()==menu.menu_edit_selectall){
			if (debug) {
				System.out.println("[-] selectall");

			}
			obj.selectAll();
		}
		
		else if (e.getSource()==menu.menu_edit_find){
			if (debug) {
				System.out.println("[-] find");
				
			}
			PopUpDialog = new SmallPopWindows(this, obj, 0);
			
		}
		
		else if (e.getSource()==menu.menu_edit_replace){
			if (debug) {
				System.out.println("[-] replace");

			}
			PopUpDialog = new SmallPopWindows(this, obj, 1);
		}
		
		
		else if (e.getSource()==menu.menu_edit_goto){
			if (debug) {
				System.out.println("[-] goto");

			}
			try{
				int l = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Line Number"));
				System.out.println(l);
				obj.setCaretPosition(obj.getLineStartOffset(l)-1);
			}catch(Exception error){
				System.out.println(error);
			}
		}

		// Default
		else if (e.getSource()==menu.theme_0){
			if (debug) {
				System.out.println("[-] theme selected");

			}

			obj.setBackground(Color.WHITE);
			obj.setForeground(Color.BLACK);
			menu.setBackground(new Color(142, 68, 173));
			obj.setCaretColor(Color.BLACK);
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(34, 47, 62));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(34,47,62);
		}
		// Dark
		else if (e.getSource()==menu.theme_a){
			if (debug) {
				System.out.println("[-] theme selected");

			}

			obj.setBackground(new Color(61, 61, 61));
			obj.setForeground(new Color(236, 240, 241));
			menu.setBackground(new Color(142, 68, 173));
			obj.setCaretColor(new Color(236, 240, 241));
			clm.setBackground(new Color(75, 75, 75));
			clm.setForeground(new Color(236, 240, 241));
			statusbarObj.setBackground(new Color(75, 75, 75));
			statusbarObj.setcomponentcolor(236,240,241);
		}
		// Light
		else if (e.getSource()==menu.theme_b){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(236, 240, 241));
			obj.setForeground(new Color(34, 47, 62));
			menu.setBackground(new Color(211, 84, 0));
			obj.setCaretColor(new Color(34, 47, 62));
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(34, 47, 62));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(34,47,62);
		}
		// Cool
		else if (e.getSource()==menu.theme_c){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(9, 132, 227));
			obj.setForeground(new Color(236, 240, 241));
			menu.setBackground(new Color(44, 62, 80));
			obj.setCaretColor(new Color(236,240,241));
			clm.setBackground(new Color(1, 122, 217));
			clm.setForeground(new Color(236, 240, 241));
			statusbarObj.setBackground(new Color(1, 122, 217));
			statusbarObj.setcomponentcolor(236,240,241);
		}
		// Vim
		else if (e.getSource()==menu.theme_d){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(111, 30, 81));
			obj.setForeground(new Color(247, 241, 227));
			menu.setBackground(new Color(179, 55, 113));
			obj.setCaretColor(new Color(247, 241, 227));
			clm.setBackground(new Color(101, 20, 71));
			clm.setForeground(new Color(247, 241, 227));
			statusbarObj.setBackground(new Color(101, 20, 71));
			statusbarObj.setcomponentcolor(247, 241, 227);
		}
		// Ocean
		else if (e.getSource()==menu.theme_e){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(154, 236, 219));
			obj.setForeground(new Color(44, 58, 71));
			menu.setBackground(new Color(130, 88, 159));
			obj.setCaretColor(new Color(44, 58, 71));
			clm.setBackground(new Color(144, 226, 209));
			clm.setForeground(new Color(44, 58, 71));
			statusbarObj.setBackground(new Color(144, 226, 209));
			statusbarObj.setcomponentcolor(44, 58, 71);
		}
		// Arc
		else if (e.getSource()==menu.theme_f){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(72, 84, 96));
			obj.setForeground(new Color(248, 239, 186));
			menu.setBackground(new Color(44, 58, 71));
			obj.setCaretColor(new Color(248, 239, 186));
			clm.setBackground(new Color(62, 74, 86));
			clm.setForeground(new Color(248, 239, 186));
			statusbarObj.setBackground(new Color(62, 74, 86));
			statusbarObj.setcomponentcolor(248, 239, 186);

		}
		// Pro
		else if (e.getSource()==menu.theme_g){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(44, 58, 71));
			obj.setForeground(new Color(130, 88, 159));
			menu.setBackground(new Color(24, 44, 97));
			obj.setCaretColor(new Color(130, 88, 159));
			clm.setBackground(new Color(34, 48, 61));
			clm.setForeground(new Color(130, 88, 159));
			statusbarObj.setBackground(new Color(34, 48, 61));
			statusbarObj.setcomponentcolor(130, 88, 159);
		}
		// MAC
		else if (e.getSource()==menu.theme_h){
			if (debug) {
				System.out.println("[-] theme selected");

			}
			obj.setBackground(new Color(236, 240, 241));
			obj.setForeground(new Color(44, 62, 80));
			menu.setBackground(new Color(44, 58, 71));
			obj.setCaretColor(new Color(44, 62, 80));
			clm.setBackground(new Color(226, 230, 231));
			clm.setForeground(new Color(44, 62, 80));
			statusbarObj.setBackground(new Color(226, 230, 231));
			statusbarObj.setcomponentcolor(44, 62, 80);
		}

		else if (e.getSource()==menu.language_plain){
			if (debug) {
				System.out.println("[-] plain language selected");

			}
			

		}

		else if (e.getSource()==menu.language_c){
			if (debug) {
				System.out.println("[-] c language selected");

			}

		}

		else if (e.getSource()==menu.language_cplus){
			if (debug) {
				System.out.println("[-]  c++ language selected");

			}

		}

		else if (e.getSource()==menu.language_csharp){
			if (debug) {
				System.out.println("[-] csharp language selected");

			}

		}

		else if (e.getSource()==menu.language_css){
			if (debug) {
				System.out.println("[-] css language selected");

			}

		}

		else if (e.getSource()==menu.language_html){
			if (debug) {
				System.out.println("[-] html language selected");

			}

		}

		else if (e.getSource()==menu.language_java){
			if (debug) {
				System.out.println("[-] java language selected");

			}

		}

		else if (e.getSource()==menu.language_javascript){
			if (debug) {
				System.out.println("[-] javascript language selected");

			}

		}

		else if (e.getSource()==menu.language_php){
			if (debug) {
				System.out.println("[-] php language selected");

			}

		}

		else if (e.getSource()==menu.language_python){
			if (debug) {
				System.out.println("[-] python language selected");

			}

		}

		else if (e.getSource()==menu.menu_view_statusbar){
			if (debug) {
				System.out.println("[-] view statusbar");

			}

		}

		else if (e.getSource()==menu.menu_view_cursorline){
			if (debug) {
				System.out.println("[-] highlight cursor line");

			}
		}


		else if (e.getSource()==menu.menu_view_linenumber){
			if (debug) {
				System.out.println("[-] show line number");

			}
		}


		// menu font
		else if (e.getSource()==menu.menu_font_wordwrap){
			if (debug) {
				System.out.println("[-] view wordwrap");

			}
		}
		else if (e.getSource()==menu.menu_font_font){
			if (debug) {
				System.out.println("[-] font");

			}
		}
		else if (e.getSource()==menu.menu_font_font_size_large){
			if (debug) {
				System.out.println("[-] font size large");

			}
			obj.increasefont();
			clm.update_line_number_configurations();
		}
		else if (e.getSource()==menu.menu_font_font_size_small){
			if (debug) {
				System.out.println("[-] font size small");


			}
			obj.decreasefont();
			clm.update_line_number_configurations();
		}

		// menu help
		else if (e.getSource()==menu.menu_help_topic){
			if (debug) {
				System.out.println("[-] help topic");

			}
				if (Desktop.isDesktopSupported()) 
				{
				  try {
						Desktop.getDesktop().browse(new URI("http://www.blaregroup.com"));
				  }
				  catch (Exception m) {}
				} 
		}
		else if (e.getSource()==menu.menu_help_about){
			if (debug) {
				System.out.println("[-] About");
			}
			PopUpDialog = new SmallPopWindows(this, obj, 2);
		}
	

	}
}


// main class to run Coderail classes
class execute{


	public static void main(String[] args){
		CombinedControls obj = new CombinedControls();
		System.out.println("Starting CodeRail...");

	}

}
