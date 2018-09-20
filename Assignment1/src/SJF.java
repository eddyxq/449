import java.util.ArrayList;
import java.util.Collections;

public class SJF extends CPUSchedulingAlgorithm
{
	ArrayList<Integer> SJF_input = new fileReader().readInput("inputSJF.txt");

	@Override
	public void calculateWaitTime()
	{
		Collections.sort(SJF_input);
		for(int i = 0; i < SJF_input.size(); i++)
		{
			System.out.println(runningTime);
			totalTime += runningTime;
			runningTime += SJF_input.get(i);
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