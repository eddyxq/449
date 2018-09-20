import java.util.ArrayList;

public class FCFS extends CPUSchedulingAlgorithm
{	
	ArrayList<Integer> FCFS_input = new fileReader().readInput("inputFCFS.txt");
	
	@Override
	public void calculateWaitTime()
	{
		for(int i = 0; i < FCFS_input.size(); i++) 
		{
			totalTime += runningTime;
			System.out.println(runningTime); 
			runningTime += FCFS_input.get(i);
			count += 1;
		}
	}

	@Override
	public void calculateAvgTime() 
	{
		double averageWaitTime = totalTime/count;
		System.out.println(averageWaitTime);
	}
}