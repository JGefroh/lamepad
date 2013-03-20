package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALAbout implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		WindowAbout wa = WindowAbout.getInstance();
		wa.setVisible(true);
	}
}
