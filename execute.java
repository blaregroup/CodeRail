// import modules
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager;
import CodeRail.*;


/*
================================================================================
						Project CodeRail
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			himanshu sharma

================================================================================
								README
================================================================================
We have decided to follow, Divide and Conquer strategy. so, in this project, 
CodeRail folder is our source of module and class. we are going to implement
various types of functionality and features in seperate class files. so, that
Contributors of Project don't have to mess with complete project code to understand
any specific functions.
 
*/

/*
This Combined Controls class play the role of connector or main control. basically,
this create object of all CodeRail Subdirectory class and then, connect them.

*/

/*
	USEFUL Documentation:
		https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html


*/
class CombinedControls extends JFrame implements ActionListener {
	/*

		JFrame : Class To Create Main Window Object

		ActionLIstener: Implementation

	*/

	/*
				Configurations Settings

	*/
	private static final String window_name = "CodeRail Text Editor";
	private static final int window_width = 1000;
	private static final int window_height = 700;
	private static final int editor_width = 900;
	private static final int editor_height = 700;
	private static final boolean debug = true;



	// CodeRail Module Objects
	private AddMenuBar menu;		// Menu Bar Module
	private Editor obj;				// Editor Module
	private UndoManager manager; 	// Undo Manager
	private FileManager FileObj;	// FileManager Module Object
	private JScrollPane scrolltext;
	private SmallPopWindows PopUpDialog;

	// constructor
	CombinedControls(){

		// [https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html]
		super(window_name);

		// Window Configuration 
		setSize(window_width, window_height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setFocusable(true);

		

		// Create TextArea Object [CodeRail.editor]
		obj = new Editor(editor_width, editor_height);
		JScrollPane scrolltext = new JScrollPane(obj); 

		// Undo manager to track change and undo changes
		manager = new UndoManager();
		obj.getDocument().addUndoableEditListener(manager);

		
		// Create Menu Bar Object [CodeRail.AddMenuBar]
		menu = new AddMenuBar();
		
		//setting backgound color of menu bar
		menu.setBackground(Color.blue);

		// File Manager Module Object
		FileObj = new FileManager(obj);


		// Add Object
		add(menu);
		add(scrolltext);
		setJMenuBar(menu);  
		setVisible(true);
		//PopUpDialog =  new SmallDialogBoxes(this);

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
		menu.menu_edit_findnext.addActionListener(this);
		menu.menu_edit_replace.addActionListener(this);
		menu.menu_edit_replaceall.addActionListener(this);
		menu.menu_edit_goto.addActionListener(this);

		// menu view
		menu.menu_view_foreground.addActionListener(this);
		menu.menu_view_background.addActionListener(this);
		menu.menu_view_statusbar.addActionListener(this);

		// menu font
		menu.menu_font_wordwrap.addActionListener(this);
		menu.menu_font_font.addActionListener(this);
		menu.menu_font_font_size_large.addActionListener(this);
		menu.menu_font_font_size_small.addActionListener(this);

		// menu help
		menu.menu_help_topic.addActionListener(this);
		menu.menu_help_about.addActionListener(this);
		
	}

	private void exit(){
		System.exit(0);
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
			PopUpDialog = new SmallPopWindows(this, 0);
		}
		
		else if (e.getSource()==menu.menu_edit_findnext){
			if (debug) {
				System.out.println("[-] findnext");

			}
			PopUpDialog = new SmallPopWindows(this, 0);
		}
		
		else if (e.getSource()==menu.menu_edit_replace){
			if (debug) {
				System.out.println("[-] replace");

			}
			PopUpDialog = new SmallPopWindows(this, 1);
		}
		
		else if (e.getSource()==menu.menu_edit_replaceall){
			if (debug) {
				System.out.println("[-] replaceall");

			}
			PopUpDialog = new SmallPopWindows(this, 1);
		}
		
		else if (e.getSource()==menu.menu_edit_goto){
			if (debug) {
				System.out.println("[-] goto");

			}
		}

		// menu view		
		else if (e.getSource()==menu.menu_view_foreground){
			if (debug) {
				System.out.println("[-] view foreground");

			}
		}
		
		else if (e.getSource()==menu.menu_view_background){
			if (debug) {
				System.out.println("[-] view background");

			}
		}
		
		else if (e.getSource()==menu.menu_view_statusbar){
			if (debug) {
				System.out.println("[-] view statusbar");

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
				obj.increasefont();

			}
			//obj.setFont(new Font("",Font.BOLD,30));   
		}
		else if (e.getSource()==menu.menu_font_font_size_small){
			if (debug) {
				System.out.println("[-] font size small");

			}
			obj.decreasefont();
			//obj.setFont(new Font("",Font.BOLD,13));
		}

		// menu help
		else if (e.getSource()==menu.menu_help_topic){
			if (debug) {
				System.out.println("[-] help topic");

			}
		}
		else if (e.getSource()==menu.menu_help_about){
			if (debug) {
				System.out.println("[-] about");

			}
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
