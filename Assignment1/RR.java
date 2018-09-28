/**
 * This class contains the round robin scheduling algorithm
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

public class RR 
{
	PrintWriter writer;
	ArrayList<Integer> RR_input;
	int runningTime = 0;
	int count = 0;
	double totalTime = 0;
	
	public RR(String inputFileName, String outputFileName) throws IOException
	{
		RR_input = new fileReader().readInput(inputFileName);
		writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName, false)));
	}
	
	public void solve() 
	{
		//first line in text file is the quantum time
		int quantumTime = RR_input.get(0);
		//remove first line so we only have burst time
		RR_input.remove(0);
		ArrayList<Integer> remainingTime = new ArrayList<Integer>(RR_input);
		int[] waitTime = new int[RR_input.size()];
		
		boolean allProcessFinished = false;
		//while processes are not finished, keep processing
		while(!allProcessFinished)
		{
			boolean anotherCheckNeeded = false;
			//checks each process
			for (int i = 0 ; i < RR_input.size(); i++)
			{
				//determine if there's time remaining in this process
				if (remainingTime.get(i) > 0) 
				{
					//if code enters here, we need to do another iteration to see if we are finished
					anotherCheckNeeded = true;
					if (remainingTime.get(i) > quantumTime)
					{
						runningTime += quantumTime;
						remainingTime.set(i, remainingTime.get(i)-quantumTime);
					}
					else
					{
						runningTime += remainingTime.get(i);
						waitTime[i] = runningTime - RR_input.get(i);
						remainingTime.set(i, 0);
					}
				}
			}
				allProcessFinished = !anotherCheckNeeded;
		}
		
		for (int i = 0 ; i < waitTime.length; i++)
		{
			totalTime += waitTime[i];
			count += 1;
			writer.println(waitTime[i]);
		}
		double averageWaitTime = totalTime/count;
		writer.println(averageWaitTime);
		writer.close();
	}
	
	public static void main(String[] args) throws IOException 
	{
		String inputFileName = args[0];
		String outputFileName = args[1];
		RR ques4 = new RR(inputFileName, outputFileName);
		ques4.solve();
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