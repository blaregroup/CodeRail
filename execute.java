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



	// CodeRail Module Objects
	private AddMenuBar menu;		// Menu Bar Module
	private editor obj;				// Editor Module
	private UndoManager manager; 	// Undo Manager

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
		obj = new editor(editor_width, editor_height);

		// Undo manager to track change and undo changes
		manager = new UndoManager();
		obj.getDocument().addUndoableEditListener(manager);

		
		// Create Menu Bar Object [CodeRail.AddMenuBar]
		menu = new AddMenuBar();
		
		//setting backgound color of menu bar
		menu.setBackground(Color.blue);


		// Add Object
		add(menu);
		add(obj);
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
		menu.menu_font_font_size_medium.addActionListener(this);
		menu.menu_font_font_size_small.addActionListener(this);

		// menu help
		menu.menu_help_topic.addActionListener(this);
		menu.menu_help_about.addActionListener(this);
		
	}

	public void exit(){
		System.exit(0);
	}

	public void actionPerformed(ActionEvent e){
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
			System.out.println(e.getSource());
		}
		
		else if (e.getSource()==menu.menu_file_open){}
		
		else if (e.getSource()==menu.menu_file_save){}
		
		else if (e.getSource()==menu.menu_file_saveas){}
		
		else if (e.getSource()==menu.menu_file_print){}
		
		else if (e.getSource()==menu.menu_file_exit){
			exit();
		}


		// menu edit
		else if (e.getSource()==menu.menu_edit_undo){
			manager.undo();
		}
		
		else if (e.getSource()==menu.menu_edit_redo){
			manager.redo();
		}
		
		else if (e.getSource()==menu.menu_edit_cut){
			obj.cut();
		}
		
		else if (e.getSource()==menu.menu_edit_copy){
			obj.copy();
		}
		
		else if (e.getSource()==menu.menu_edit_paste){
			obj.paste();
		}
		
		else if (e.getSource()==menu.menu_edit_delete){
			obj.setText("");

		}

		else if (e.getSource()==menu.menu_edit_selectall){
			obj.selectAll();
		}
		
		else if (e.getSource()==menu.menu_edit_find){}
		
		else if (e.getSource()==menu.menu_edit_findnext){}
		
		else if (e.getSource()==menu.menu_edit_replace){}
		
		else if (e.getSource()==menu.menu_edit_replaceall){}
		
		else if (e.getSource()==menu.menu_edit_goto){}

		// menu view		
		else if (e.getSource()==menu.menu_view_foreground){}
		
		else if (e.getSource()==menu.menu_view_background){}
		
		else if (e.getSource()==menu.menu_view_statusbar){}


		// menu font
		else if (e.getSource()==menu.menu_font_wordwrap){}
		else if (e.getSource()==menu.menu_font_font){}
		else if (e.getSource()==menu.menu_font_font_size_large){
			
			obj.setFont(new Font("",Font.BOLD,30));   
		}
		else if (e.getSource()==menu.menu_font_font_size_medium){
			obj.setFont(new Font("",Font.BOLD,20));
		}
		else if (e.getSource()==menu.menu_font_font_size_small){
			obj.setFont(new Font("",Font.BOLD,13));
		}

		// menu help
		else if (e.getSource()==menu.menu_help_topic){}
		else if (e.getSource()==menu.menu_help_about){}
	

	}
}


// main class to run Coderail classes
class execute{


	public static void main(String[] args){
		CombinedControls obj = new CombinedControls();
		System.out.println("Starting CodeRail...");

	}

}
