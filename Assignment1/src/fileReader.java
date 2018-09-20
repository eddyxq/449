import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileReader 
{
	public ArrayList<Integer> readInput(String fileName)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
				
		File file = new File(fileName);
		
		Scanner sc;
		try 
		{
			sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				int i = sc.nextInt();
				list.add(i);
			}		
			sc.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
		return list;
	}
}
