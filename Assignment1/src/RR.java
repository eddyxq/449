/**
 * This class contains the round robin scheduling algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.2
 * @since September 20, 2018
 */
import java.util.ArrayList;

public class RR extends CPUSchedulingAlgorithm
{
	ArrayList<Integer> RR_input = new fileReader().readInput("inputRR.txt");

	@Override
	public void calculateWaitTime() 
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
			//checks if we are done
			if (anotherCheckNeeded == false)
			{
				allProcessFinished = true;
			}
		}
		
		for (int i = 0 ; i < waitTime.length; i++)
		{
			totalTime += waitTime[i];
			count += 1;
			System.out.println(waitTime[i]);
		}
	}
}