/**
 * This class contains the first come first serve algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.2
 * @since September 20, 2018
 */
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
}