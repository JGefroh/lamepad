package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import persist.FileSaver;
import document.DocumentEditor;

public class ALSave implements ActionListener
{
	JFrame frame;
	JFileChooser chooser;
	FileNameExtensionFilter filter;
	
	/**
	 * Create the ActionListener
	 * @param nFrame
	 */
	public ALSave(final JFrame nFrame)
	{
		frame = nFrame;
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Text Files", "txt");
		chooser.setFileFilter(filter);
	}
	
	/**
	 * Open the dialog if necessary (at first save) or if requested (save as).
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e==null || e.getActionCommand().equals("SAVEAS"))
		{//If request routed through or if specifically requested as save as
			int returnVal = chooser.showSaveDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{//Save file to given location
				FileSaver.save(chooser.getSelectedFile().getAbsolutePath(), 
						DocumentEditor.getText());
			}
		}
		else
		{//If requested to skip dialog
			if(chooser.getSelectedFile()!=null)
			{//If the file location is valid
				FileSaver.save(chooser.getSelectedFile().getAbsolutePath(), 
						DocumentEditor.getText());
			}
			else
			{//Route back to open dialog
				actionPerformed(null);
			}
		}

	}
}
