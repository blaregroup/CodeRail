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
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*



class RowHighlighter implements CaretListener {
    private Editor txtarea;
    private Highlighter brush;
    private Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(206, 214, 224));

    RowHighlighter(Editor editor) {
        this.txtarea = editor;
        this.brush = editor.getHighlighter();
    }

    private void highlight_cursor(CaretEvent caretEvent) {
    }

    @Override
    public void caretUpdate(CaretEvent caretEvent) {
        this.highlight_cursor(caretEvent);
    }
}




*/

// status bar
public class StatusBar extends JPanel{
	
	//variables to store some useful data
	private Editor editor;
	private JLabel TabSize   = new JLabel();
	private JLabel TotalLine = new JLabel();
	//private JLabel Cursor    = new JLabel();
	private JLabel CursorPosition = new JLabel();
	private	Caret caret;
	private Point p;
	private double line;
	private double column;
	private int tabsize;
	private int totalline;
	
		
		

	// Constructor
	public StatusBar(Editor e){
		
		// Initialize editor  object
		editor = e;

		// Configurations
		setVisible(true);
		setFocusable(true);
		setBackground(new Color(226, 230, 231));
		setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		caret=editor.getCaret();
		
		

		
		/*
			not working 
			ChangeListener listener = new ChangeListener() {
		  public void stateChanged(ChangeEvent caretEvent) {
		   	    
		   	   // caret=editor.getCaret();
		   	    p=caret.getMagicCaretPosition();
		    try{
		    	line=p.getX();
		    	column=p.getY();
		    	System.out.println("a="+line+"b="+column);
		    	CursorPosition();
				}
				catch(Exception m){}
		  }
		};
		caret.addChangeListener( listener);*/

		//Calling  methods
		TabSize();
		TotalLine();
		CursorPosition();
	}

	
	//method to display tab size
	public void TabSize()
	{	
		tabsize=editor.getTabSize();
		TabSize.setText("Tab Size: "+tabsize);
		TabSize.setBounds(2,10,10,30);
		add(TabSize);

	}

	//method to display total number of line
	public void TotalLine()
	{

		totalline=editor.getLineCount();
		TotalLine.setText("Total Lines: "+totalline);
		add(TotalLine);
	}

	public void CursorPosition()
	{	
		p=caret.getMagicCaretPosition();
		try{
		line=p.getX();
		column=p.getY();
		
		}
		catch(Exception k)
		{}
		
		System.out.println("x="+line+"y="+column);
		CursorPosition.setText("line:"+(int)line+", column:"+(int)column);
		add(CursorPosition);

	}

	public void setcomponentcolor(int r,int g,int b)
	{
		TabSize.setForeground(new Color(r,g,b));
		TotalLine.setForeground(new Color(r,g,b));
	}
}


