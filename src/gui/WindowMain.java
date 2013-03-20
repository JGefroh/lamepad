package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import document.DocumentEditor;

public class WindowMain
{
	private static 	WindowMain 	instance;	//The singleton instance
	private 		JFrame 		frame;		//The frame everything is on
	private 		JPanel 		cPanel;		//The panel everything is added to

	/**
	 * Easylaunch!
	 * @param args command-line args
	 */
	public static void main(String[] args)
	{
		WindowMain ws = WindowMain.getInstance();
	}
	///////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * No-arg constructor is private to ensure singleton.
	 */
	private WindowMain()
	{
		initFrame();
		initComponents();
		finalizeFrame();
	}
	
	/**
	 * Retrieve the instance of the WindowMain class.
	 * If it does not already exist, create and return one.
	 * @return	the instance of the MainWindow class
	 */
	public static synchronized WindowMain getInstance()
	{
		if(instance==null)
		{
			instance = new WindowMain(); 
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Apply the final settings to the frame.
	 */
	private void finalizeFrame()
	{
		frame.pack();
		frame.validate();
		frame.setMinimumSize(frame.getSize());
		frame.setVisible(true);
	}
	///////////////////////////////////////////////////////////////////////////
	/**
	 * Create the GUI components of the frame.
	 */
	private void initComponents()
	{
		initContentPanel();
		initMenuBar();
		initToolBar();
		initTextPane();
	}
	
	/**
	 * Create and place a JMenuBar and its components.
	 */
	private void initMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		menuBar.setName(Constants.NAME_MENUBAR);
		
		/////File Menu/////
		JMenu menuFile = new JMenu(Constants.MENUFILE);
		JMenuItem menuFile_Exit = new JMenuItem(Constants.MENUFILE_EXIT);
		JMenuItem menuFile_Save = new JMenuItem(Constants.MENUFILE_SAVE);
		JMenuItem menuFile_SaveAs = new JMenuItem(Constants.MENUFILE_SAVEAS);
		JMenuItem menuFile_Open = new JMenuItem(Constants.MENUFILE_OPEN);
		
		JMenu menuHelp = new JMenu(Constants.MENUHELP);
		JMenuItem menuHelp_About = new JMenuItem(Constants.MENUHELP_ABOUT);

		/////Config/////
		menuFile_Save.addActionListener(new ALSave(frame));
		menuFile_SaveAs.addActionListener(new ALSave(frame));
		menuFile_Open.addActionListener(new ALOpen(frame));
		menuFile_Exit.addActionListener(new ALExit());
		menuHelp_About.addActionListener(new ALAbout());
		
		menuFile_SaveAs.setActionCommand("SAVEAS");
		
		menuFile_Exit.setIcon(getIconAt(ResourceManifest.MENUFILE_EXIT));
		menuFile_Open.setIcon(getIconAt(ResourceManifest.MENUFILE_OPEN));
		menuFile_Save.setIcon(getIconAt(ResourceManifest.MENUFILE_SAVE));
		menuFile_SaveAs.setIcon(getIconAt(ResourceManifest.MENUFILE_SAVEAS));
		menuHelp_About.setIcon(getIconAt(ResourceManifest.MENUHELP_ABOUT));

		menuFile.setMnemonic(KeyEvent.VK_F);
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuFile_Save.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuFile_Open.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));	
		menuFile_Exit.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		/////Place/////
		menuFile.add(menuFile_Open);
		menuFile.add(menuFile_Save);
		menuFile.add(menuFile_SaveAs);
		menuFile.add(menuFile_Exit);
		menuHelp.add(menuHelp_About);
		
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		frame.setJMenuBar(menuBar);
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
	 * Create the toolbar and its components.
	 */
	private void initToolBar()
	{
		/////Init/////
		JToolBar toolBar = new JToolBar();
		JToggleButton btnBold = new JToggleButton();
		JToggleButton btnItalic = new JToggleButton();
		JToggleButton btnUnder = new JToggleButton();
		
		JLabel labelSize = new JLabel(Constants.LABEL_SIZE);
		JLabel labelFont = new JLabel(Constants.LABEL_FONT);
		
		labelSize.setBorder(new EmptyBorder(5,5,5,5));
		labelFont.setBorder(new EmptyBorder(5,5,5,5));
		
		JComboBox comboSize = new JComboBox();
		JComboBox comboFont = new JComboBox();
		
		/////Config/////
		toolBar.setFloatable(false);	//Disable drag and drop.
		
		btnBold.setIcon(this.getIconAt(ResourceManifest.BOLD_OFF));
		btnItalic.setIcon(this.getIconAt(ResourceManifest.ITALIC_OFF));
		btnUnder.setIcon(this.getIconAt(ResourceManifest.UNDER_OFF));
		
		btnBold.setSelectedIcon(this.getIconAt(ResourceManifest.BOLD_ON));
		btnItalic.setSelectedIcon(this.getIconAt(ResourceManifest.ITALIC_ON));
		btnUnder.setSelectedIcon(this.getIconAt(ResourceManifest.UNDER_ON));
		
		btnBold.setBorder(null);
		btnItalic.setBorder(null);
		btnUnder.setBorder(null);
		
		btnBold.addActionListener(new ALBold());
		btnItalic.addActionListener(new ALItalic());
		btnUnder.addActionListener(new ALUnder());
		
		populateComboSize(comboSize);
		populateComboFont(comboFont);
		
		comboSize.addActionListener(new ALSize());
		comboFont.addActionListener(new ALFont());
		comboSize.setMaximumSize(new Dimension(50, 50));
		
		/////Place/////
		toolBar.add(btnBold);
		toolBar.add(btnItalic);
		toolBar.add(btnUnder);
		
		toolBar.add(labelSize);
		toolBar.add(comboSize);
		
		toolBar.add(labelFont);
		toolBar.add(comboFont);
		cPanel.add(toolBar, BorderLayout.NORTH);
	}
	
	/**
	 * Create the text pane and its components.
	 */
	private void initTextPane()
	{
		//Create
		JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane();
		
		//Config
		scrollPane.setViewportView(textPane);
		scrollPane.setPreferredSize(new Dimension(600, 700));
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		DocumentEditor.register(textPane.getStyledDocument(), textPane);//EZ reference
		
		//Place
		cPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	/////Utility/////
	/**
	 * Retrieve the icon resource.
	 * @param iconPath	location of the icon
	 * @return	the icon
	 */
	private ImageIcon getIconAt(final String iconPath)
	{
		ImageIcon icon;
		try
		{
			icon = new ImageIcon(getClass().getResource(iconPath));
		}
		catch(NullPointerException e)
		{//Resource not found at path.
			return new ImageIcon();
		}
		return icon;
	}
	
	/**
	 * Fill the Size comobox with numeric values
	 * @param comboSize	the combobox to fill
	 */
	private void populateComboSize(final JComboBox comboSize)
	{
		String[] validValues = {"12", "16", "20", 
								"24", "32", "48", "64", "72"};
		comboSize.setModel(new DefaultComboBoxModel(validValues));
	}
	
	/**
	 * Fill the Font combobox with the list of system fonts.
	 * @param comboFont	the combobox to fill
	 */
	private void populateComboFont(final JComboBox comboFont)
	{
		GraphicsEnvironment ge = 
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		for(int index=0;index<fonts.length;index++)
		{
			//Remove HTML to make it 9001 times faster.
			fonts[index] = 
				"<html><font face=\"" + fonts[index] 
				+ "\">" + fonts[index] + "</font></html>";
		}
		comboFont.setModel(
				new DefaultComboBoxModel(fonts));
		
	}
}
