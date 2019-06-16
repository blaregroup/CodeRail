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

/* Status bar */
public class StatusBar extends JPanel{
	
	//variables to store some useful data
	private Editor editor;
	private JLabel TabSize;
	private JLabel TotalLine;
	private JLabel CursorPosition;

	// Constructor
	public StatusBar(Editor e){
		
		// Initialize editor  object
		editor = e;

		// Configurations
		setVisible(true);
		setFocusable(true);
		setBackground(new Color(226, 230, 231));
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// Calling  methods
		TabSize();
		TotalLine();
		CursorPosition();
		UpdateStatus();
	}

	
	//method to display tab size
	public void TabSize()
	{	
		TabSize   = new JLabel();
		add(TabSize);
	}

	//method to display total number of line
	public void TotalLine()
	{
		TotalLine = new JLabel();
		add(TotalLine);
	}


	public void UpdateStatus(){
		TotalLine.setText(" Total Lines : "+editor.getLineCount());
		TabSize.setText(" Tab Size : "+editor.getTabSize());
	}

	public void LineStatusUpdate(int line, int column){
		CursorPosition.setText(String.format("  Line : %d | Column : %d ", line, column));
	}

	public void CursorPosition()
	{	

		CursorPosition = new JLabel();
		CursorPosition.setText(String.format("  Line : %d | Column : %d", 0,0));
		add(CursorPosition);

	}

	public void setcomponentcolor(int r,int g,int b)
	{
		TabSize.setForeground(new Color(r,g,b));
		TotalLine.setForeground(new Color(r,g,b));
	}
}


