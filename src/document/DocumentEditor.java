package document;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DocumentEditor
{
	private static StyledDocument 		current;	//the current style
	private static JTextPane 			textPane;	//the text container
	private static SimpleAttributeSet 	sas 	= new SimpleAttributeSet();
	
	private DocumentEditor()
	{
	}
	
	/**
	 * Set the StyledDocument to manipulate.
	 * @param styledDoc		the StyledDocument to manipulate
	 * @param newTextPane	the text pane the document is attached to
	 */
	public static void register(final StyledDocument styledDoc, 
			final JTextPane newTextPane)
	{
		current = styledDoc;
		textPane = newTextPane;
	}
	/**
	 * Choose whether to enable or disable the bold style.
	 * @param bold	true to enable bold, false otherwise.
	 */
	public static void setBold(final boolean bold)
	{
		StyleConstants.setBold(sas, bold);
		applyAttributes();
	}
	
	/**
	 *  Choose whether to enable or disable the underline style.
	 * @param under	true to enable underlines, false otherwise.
	 */
	public static void setUnder(final boolean under)
	{
		StyleConstants.setUnderline(sas, under);
		applyAttributes();
	}
	
	/**
	 *  Choose whether to enable or disable the italicized style.
	 * @param italic	true to enable italics, false otherwise.
	 */
	public static void setItalic(final boolean italic)
	{
		StyleConstants.setItalic(sas, italic);
		applyAttributes();
	}
	
	/**
	 *  Choose the size of the text.
	 * @param size the int size to display the text
	 */
	public static void setSize(final int size)
	{
		StyleConstants.setFontSize(sas, size);
		applyAttributes();
	}
	
	/**
	 * Choose the font family used to display the text.
	 * @param font	the String name of the font family to use
	 */
	public static void setFont(final String font)
	{
		StyleConstants.setFontFamily(sas, font);
		applyAttributes();
	}
	
	/**
	 * Apply the current attributes to the document.
	 */
	private static void applyAttributes()
	{
		//current.setCharacterAttributes(0, current.getLength(), sas, false);
		current.setParagraphAttributes(0, current.getLength(), sas, false);
	}
	
	/**
	 * Set the text of the textpane.
	 * @param text	the String text to set it to
	 */
	public static void setText(final String text)
	{
		textPane.setText(text);
	}
	
	/**
	 * Get the String text of the textpane.
	 * @return
	 */
	public static String getText()
	{
		return textPane.getText();
	}

}
