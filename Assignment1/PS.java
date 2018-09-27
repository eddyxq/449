/**
 * This class contains the priority scheduling algorithm
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class PS
{
	PrintWriter writer;
	ArrayList<Integer> PS_input;
	ArrayList<Integer> burstTime = new ArrayList<Integer>();
	ArrayList<Integer> priority = new ArrayList<Integer>();
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();	
	int runningTime = 0;
	int count = 0;
	double totalTime = 0;

	public PS(String inputFileName, String outputFileName) throws IOException
	{
		PS_input = new fileReader().readInput(inputFileName);
		writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, false)));
	}
	
	public void solve() 
	{
		for(int i = 0; i < PS_input.size(); i++)
		{
			burstTime.add(PS_input.get(i));
			i++;
			priority.add(PS_input.get(i));
		}
		
		for(int i = 0; i < burstTime.size(); i++)
		{
			map.put(priority.get(i), burstTime.get(i));	
		}
		Collections.sort(priority);
		
		for(int i = 0; i < priority.size(); i++)
		{
			writer.println(runningTime);
			totalTime += runningTime;
			runningTime += map.get(priority.get(i));
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
		PS ques3 = new PS(inputFileName, outputFileName);
		ques3.solve();
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
			catch (FileNotFoundException e) 
			{
				System.out.println("File not found");
			}
			return input;
		}
	}
}