package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import document.DocumentEditor;

public class ALFont implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		JComboBox combo = (JComboBox)event.getSource();
		Object selected = combo.getSelectedItem();
		if(selected!=null)
		{
			try
			{
				String font = (String)selected;
				String[] parts = font.split("\"");
				DocumentEditor.setFont(parts[1]);
			}
			catch(NumberFormatException e)
			{
				
			}
		}
	}
}
