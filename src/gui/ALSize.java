package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import document.DocumentEditor;

public class ALSize implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		JComboBox combo = (JComboBox)event.getSource();
		Object selected = combo.getSelectedItem();
		if(selected!=null)
		{
			try
			{//Get the selected size and set the size.
				int size = Integer.parseInt((String)selected);
				DocumentEditor.setSize(size);
			}
			catch(NumberFormatException e)
			{
				
			}
		}
	}
}
