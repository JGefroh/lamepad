package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowAbout
{
	private static 	WindowAbout instance;	//The singleton instance.
	private 		JFrame 		frame;
	private 		JPanel 		cPanel;

	///////////////////////////////////////////////////////////////////////////
	/**
	 * Constructor is private to ensure singleton.
	 */
	private WindowAbout()
	{
		initFrame();
		initComponents();
		finalizeFrame();
	}

	/**
	 * Retrieve the instance of the MainWindow class.
	 * If it does not already exist, create and return one.
	 * @return	the instance of the MainWindow class
	 */
	public static synchronized WindowAbout getInstance()
	{
		if(instance==null)
		{
			instance = new WindowAbout(); 
		}
		return instance;
	}
	

	///////////////////////////////////////////////////////////////////////////
	/**
	 * Create the JFrame object.
	 */
	private void initFrame()
	{
		frame = new JFrame(Constants.WINDOW_TITLE);
		frame.setSize(Constants.defaultWindowHeight, 
				Constants.defaultWindowWidth);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	
	/**
	 * Apply the final settings to the frame.
	 */
	private void finalizeFrame()
	{
		frame.pack();
		frame.validate();
		frame.setMinimumSize(frame.getSize());
		frame.setResizable(false);
		frame.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////
	/**
	 * Create the GUI components of the frame.
	 */
	private void initComponents()
	{
		initContentPanel();
		initImage();
		initButton();
	}
	
	
	/**
	 * Create the main content panel.
	 */
	private void initContentPanel()
	{
		cPanel = new JPanel(new BorderLayout());
		frame.add(cPanel);
	}
	
	
	/**
	 * Create the image with ABOUT info.
	 */
	private void initImage()
	{
		try
		{
			ImageIcon image = new ImageIcon(getClass()
					.getResource(ResourceManifest.ABOUT));
			JLabel labelImage = new JLabel(image);
			cPanel.add(labelImage, BorderLayout.CENTER);
		}
		catch(NullPointerException e)
		{
		}
	}
	
	
	/**
	 * Create the close button.
	 */
	private void initButton()
	{
		JButton btnClose = new JButton("Close");
		JPanel subPanel = new JPanel();
		
		btnClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				frame.setVisible(false);
			}
		});
		
		btnClose.setMaximumSize(new Dimension(100, 50));
		subPanel.add(btnClose);
		cPanel.add(subPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Make the window visible/invisible.
	 * @param visible	true to make visible, false to hide.
	 */
	public void setVisible(final boolean visible)
	{
		frame.setVisible(visible);
	}
}
