/**
 * This class contains the first come first serve algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.2
 * @since September 20, 2018
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FCFS
{	
	PrintWriter writer;
	ArrayList<Integer> FCFS_input;
	int runningTime = 0;
	int count = 0;
	double totalTime = 0;
	
	public FCFS(String inputFileName, String outputFileName) throws IOException
	{
		FCFS_input = new fileReader().readInput(inputFileName);
		writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, false)));
	}
	
	public void solve() 
	{
		for(int i = 0; i < FCFS_input.size(); i++) 
		{
			totalTime += runningTime;
			writer.println(runningTime);
			runningTime += FCFS_input.get(i);
			count += 1;
		}
		double averageWaitTime = totalTime/count;
		writer.println(averageWaitTime);
		writer.close();
	}
	
	public static void main(String[] args) throws IOException  
	{
		String inputFileName = args[0];
		String outputFileName = args[1];
		FCFS ques1 = new FCFS(inputFileName, outputFileName);
		ques1.solve();
	}
	
	private class fileReader 
	{
		public ArrayList<Integer> readInput(String fileName)
		{
			ArrayList<Integer> input = new ArrayList<Integer>();
			File file = new File(fileName);
			Scanner sc = null;
			try 
			{
				sc = new Scanner(file);
				while(sc.hasNextLine())
				{
					int i = sc.nextInt();
					input.add(i);
				}		
			}
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found");
			}
			return input;
		}
	}
}