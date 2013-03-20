package persist;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSaver
{
	private FileSaver()
	{
	}
	
	/**
	 * Save the passed String to the file at the passed path
	 * @param path	the file to save the string to
	 * @param text	the string to save
	 */
	public static void save(final String path, final String text)
	{
		try
		{
			FileWriter fWriter = new FileWriter(path);
			PrintWriter pWriter = new PrintWriter(fWriter);
			
			pWriter.write(text);
			pWriter.close();
			fWriter.close();
		}
		catch(IOException e)
		{
			System.err.println("Error writing: " + e.getMessage());
		}
	}
}
