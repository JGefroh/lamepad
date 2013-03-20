package persist;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileOpener
{
	private FileOpener()
	{
		
	}
	
	
	/**
	 * Read the contents of the file at the given path
	 * @param path	the path of the file to read
	 * @return	the string content sof the file
	 */
	public static String read(final String path)
	{
		String returnVal="";
		try
		{
			Scanner fReader = new Scanner(new File(path));
			while(fReader.hasNext())
			{
				returnVal += fReader.nextLine();
				if(fReader.hasNext())
				{
					 returnVal += "\n";
				}
			}
			fReader.close();
		}
		catch(IOException e)
		{
			System.err.println("Error reading: " + e.getMessage());
		}
		return returnVal;
	}
}
