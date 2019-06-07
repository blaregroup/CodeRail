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
import java.io.*;

/*
	Reference Docs 
			https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html
			https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
			https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html
			https://docs.oracle.com/javase/7/docs/api/javax/swing/text/JTextComponent.html
			https://docs.oracle.com/javase/7/docs/api/java/io/OutputStreamWriter.html
			https://docs.oracle.com/javase/7/docs/api/java/io/File.html
*/



// Class Object To handle File Related Operations
public class FileManager{

	// variables to store some useful objects
	public File infile; 				// open text document File Object
	public String Path = "."; 			// Path Of Directory
	public int FILE_PATH_AVAILABLE; 	// File Selected Or Not
	public int NEEDTOSAVE;				// Changes in Document
	private Editor editor;				// To Handle JTextArea Object

	// Constructor
	public FileManager(Editor e){
		// Initialize object
		editor = e;
	}

	// Load File Content
	private boolean LoadSelectedFile() throws Exception {
		// lines of files
		String lines;

		// File Reader is Ready to ready
		FileReader file_reader = new FileReader(infile);

		// Reader Buffer is Ready to Read
		BufferedReader input_pipe = new BufferedReader(file_reader);

		while( (lines=input_pipe.readLine())!=null){
			System.out.println("[+] Opening File");
			//System.out.print("Start-");
			//System.out.print(lines);
			//System.out.print("-End");
			editor.append(lines);
		}
		input_pipe.close();
		return true;
	}
	// if its old one then
	private void Save(){
		try{
			FileWriter output_pipe = new FileWriter(infile);
			output_pipe.write(editor.getText());
			output_pipe.flush();
			output_pipe.close();

		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}

	}
	// Save As New File Method
	public void SaveAsFile(){
		File temp = infile;
		infile = null;
		SaveFile();
		infile = temp;
	}

	// Check its New File Or Old One And Then Save It
	public void SaveFile(){
		// if File Object Avaialble
		if (infile!=null) {
			System.out.println("[+] File Saved");
			Save();
		
		// Ask if Not File Object Available	
		}else{

			JFileChooser chooser = new JFileChooser(Path);
			chooser.setDialogTitle("Select Path To Save File");
			chooser.setApproveButtonText("Save ");
			chooser.setApproveButtonToolTipText("Click Here To Save It");
			int ret = chooser.showSaveDialog(null);
			if (ret==JFileChooser.APPROVE_OPTION) {
				System.out.println("HERE, Its is The Name of File");
				File f = chooser.getSelectedFile();
				try{
					f.createNewFile();
				} catch (Exception e){
					System.out.println(e);
				}
				Path = f.getAbsolutePath();
				
				infile = f;
				FILE_PATH_AVAILABLE = 1;

				Save();
			}
			else{
				//
				System.out.println("[-] Noting To Save - File Dialog Box");
				System.out.println(ret);
			}
		}
	}

	// pop up file chooser dialog box
	public void OpenFileChooser(){
		// object
		JFileChooser chooser = new JFileChooser(Path);
		
		// receive return value
		int returnvalue = chooser.showOpenDialog(null);
		
		// if user select any file to open
		if (returnvalue == JFileChooser.APPROVE_OPTION){
			
			// get file object
			infile = chooser.getSelectedFile();

			// change the state of FILE_PATH_AVAILABLE variable
			FILE_PATH_AVAILABLE = 1;
			
			// set file path
			Path = infile.getPath();
			
			System.out.println("File Selected : "+Path);

			// Load File Content
			try{
				// Load Selected File
				LoadSelectedFile();
			} catch (Exception e){
				// Report Error
				e.printStackTrace();
				System.out.println("[-] Error Captured");
				System.out.println(e);
			}
		
		}
	}

	public static void main(String args[]){
		// Test Frame
		JFrame root = new JFrame("Test Filemanager");

		root.setSize(300, 400);
		root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Editor obj = new Editor(100, 200);
		JButton button1 = new JButton("Open File");
		JButton button2 = new JButton("Save File");
		JButton button3 = new JButton("Save As ..");

		FileManager fm = new FileManager(obj);
		
		button1.setSize(20, 30);
		button2.setSize(20, 30);

		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			fm.OpenFileChooser();	
			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fm.SaveFile();
			}
		});

		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fm.SaveAsFile();
			}
		});

		root.add(obj, BorderLayout.CENTER);
		root.add(button1, BorderLayout.SOUTH);
		root.add(button2, BorderLayout.NORTH);
		root.add(button3, BorderLayout.EAST);

		root.setFocusable(true);
		root.setVisible(true);

	}
}