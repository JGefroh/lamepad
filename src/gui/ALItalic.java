package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import document.DocumentEditor;

public class ALItalic implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		JToggleButton button = (JToggleButton)event.getSource();
		if(button.isSelected())
		{
			DocumentEditor.setItalic(true);
		}
		else
		{
			DocumentEditor.setItalic(false);
		}
	}
}
