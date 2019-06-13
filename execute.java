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
			himanshusharma2972@gmail.com
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

		Font font_family = new Font("Courier", Font.BOLD,16);
		
		//set font for JTextArea Object
		obj.setFont(font_family);
		

		// Undo manager to track change and undo changes
		manager = new UndoManager();
		obj.getDocument().addUndoableEditListener(manager);

		
		// Create Menu Bar Object [CodeRail.AddMenuBar]
		menu = new AddMenuBar();
		
		//setting backgound color of menu bar
		menu.setBackground(new Color(142, 68, 173));

		// File Manager Module Object
		FileObj = new FileManager(obj);

		
		
                        



		// Add Object
		add(menu);
		add(scrolltext);
		setJMenuBar(menu);  
		setVisible(true);

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
		menu.menu_view_statusbar.addActionListener(this);
		menu.menu_view_cursorline.addActionListener(this);
		menu.menu_view_linenumber.addActionListener(this);

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
