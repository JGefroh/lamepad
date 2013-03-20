package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import persist.FileOpener;
import document.DocumentEditor;

public class ALOpen implements ActionListener
{
	JFrame frame;
	public ALOpen(final JFrame frame)
	{
		this.frame = frame;
	}
	public void actionPerformed(ActionEvent e)
	{		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter("Text Files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			DocumentEditor.setText(FileOpener.read(
					chooser.getSelectedFile().getAbsolutePath()));
		}
	}
}
