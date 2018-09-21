/**
 * This class reads input from text files
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.2
 * @since September 20, 2018
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileReader 
{
	public ArrayList<Integer> readInput(String fileName)
	{
		ArrayList<Integer> input = new ArrayList<Integer>();
		File file = new File(fileName);
		Scanner sc = null;

		try 
		{
			if (fileName == "inputPrioritySch.txt")
			{
				sc = new Scanner(file);
				//skips the first line of text
				sc.nextLine();
				while(sc.hasNextLine())
				{
					String str = sc.nextLine();
					String[] line = str.split(" ");
					input.add(Integer.parseInt(line[0]));
					input.add(Integer.parseInt(line[1]));
				}		
			}
			else
			{
				sc = new Scanner(file);
				while(sc.hasNextLine())
				{
					int i = sc.nextInt();
					input.add(i);
				}		
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
	
		return input;
	}
}
