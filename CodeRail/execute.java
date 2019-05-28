// import modules
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.UIManager;
import CodeRail.*;
import java.io.*;


/*
================================================================================
						Project CodeRail
================================================================================

	Author:
			Suraj Singh Bisht
			surajsinghbisht054@gmail.com


			Himanshu Sharma
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
class CombinedControls extends JFrame implements ActionListener {
	/*

		JFrame : Class To Create Main Window Object

		ActionLIstener: Implementation

	*/

	/*
				Configurations Settings

	*/
		
	private static final String window_name = "CodeRail Text Editor";
	private static final int window_width = 900;
	private static final int window_height = 500;
	private static final int editor_width = 0;
	private static final int editor_height = 0;



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
		setExtendedState(JFrame.MAXIMIZED_BOTH);   //for opening window on full screen

		// Create TextArea Object [CodeRail.editor]
		obj = new editor(editor_width, editor_height);
		
		//Adding scroll bar to textarea 
		//In this we pass obj(object of editor class) as argument so we have to add scroll instead of scroll
		JScrollPane scroll=new JScrollPane(obj, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//config scroll bar		
		scroll.getVerticalScrollBar().setBackground(new Color(0,27,51));
		scroll.getHorizontalScrollBar().setBackground(new Color(0,27,51)); 
		scroll.getVerticalScrollBar().setForeground(Color.white);
		scroll.getHorizontalScrollBar().setForeground(Color.white); 

		// Undo manager to track change and undo changes
		manager = new UndoManager();
		obj.getDocument().addUndoableEditListener(manager);

		
		// Create Menu Bar Object [CodeRail.AddMenuBar]
		menu = new AddMenuBar();
		
		//setting backgound color of menu bar
		menu.setBackground(Color.blue);


		// Add Object
		add(menu);
		add(scroll);   //here we add scroll because scroll included obj 
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
		menu.menu_view_theme.addActionListener(this);
		menu.menu_view_theme_black.addActionListener(this);
		menu.menu_view_theme_blue.addActionListener(this);
		menu.menu_view_theme_white.addActionListener(this);

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
		
		else if (e.getSource()==menu.menu_file_open){

			try{
					//JFileChoose create a dialog box 
					JFileChooser open = new JFileChooser();
					
					open.showOpenDialog(null);
					
						try
						{
							
							FileReader reader = new FileReader(open.getSelectedFile().getPath());  //get the path of file
		                    BufferedReader br = new BufferedReader(reader);						   //creates a reader to get data form selected file 
		                    obj.read( br, null );	//write data to textarea
		                    br.close();				//closing file	
		                    obj.requestFocus();		
						}			
						catch(Exception ae)
						{} 
		             
				}
			catch(Exception se){}	   

		}
		
		else if (e.getSource()==menu.menu_file_save){}
		
		else if (e.getSource()==menu.menu_file_saveas){

			try{
					//JFileChoose create a dialog box 
					JFileChooser saveas = new JFileChooser();
					
					saveas.showSaveDialog(null);
					//create file 
					File file = new File(saveas.getSelectedFile().getPath());
					
						try
						{
							String source = obj.getText();    				//getting data from textarea
							char buffer[] = source.toCharArray();			//converting data from string to char
							FileWriter f=new FileWriter(file,false);		//creating file writer

							//writing data to file	
							for(int i=0;i<buffer.length;i++)
							{
								f.write(buffer[i]);		
							}
							
							f.close(); 		//closing file

						}
						catch(Exception ae)
						{} 
		             
				}
			catch(Exception se){}	            
      
		}
		
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
		else if (e.getSource()==menu.menu_view_foreground){

		}
		else if (e.getSource()==menu.menu_view_background){}
		
		else if (e.getSource()==menu.menu_view_statusbar){}

		else if (e.getSource()==menu.menu_view_theme){}

		else if (e.getSource()==menu.menu_view_theme_black){
			obj.setBackground(new Color(40,41,35));
			obj.setForeground(Color.white);
			obj.setCaretColor(Color.white);
		}

		else if (e.getSource()==menu.menu_view_theme_blue){

			obj.setBackground(new Color(0,37,51));
			obj.setForeground(Color.white);
			obj.setCaretColor(Color.white);
		}

		else if (e.getSource()==menu.menu_view_theme_white){

			obj.setBackground(Color.white);
			obj.setForeground(Color.black);
			obj.setCaretColor(Color.black);
		}


		// menu font
		else if (e.getSource()==menu.menu_font_wordwrap){}
		else if (e.getSource()==menu.menu_font_font){}
		else if (e.getSource()==menu.menu_font_font_size_large){
			
			obj.setFont(new Font("",Font.PLAIN,30));   
		}
		else if (e.getSource()==menu.menu_font_font_size_medium){
			obj.setFont(new Font("",Font.PLAIN,20));
		}
		else if (e.getSource()==menu.menu_font_font_size_small){
			obj.setFont(new Font("",Font.PLAIN,13));
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
