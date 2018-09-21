/**
 * This class contains the methods for a CPU scheduling algorithms
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.2
 * @since September 20, 2018
 */
public abstract class CPUSchedulingAlgorithm 
{
	int runningTime = 0;
	int count = 0;
	double totalTime = 0;
	
	public abstract void calculateWaitTime();
	
	public void solve()
	{
		calculateWaitTime();
		calculateAvgTime();
	}
	
	public void calculateAvgTime()
	{
		double averageWaitTime = totalTime/count;
		System.out.println(averageWaitTime);
	}
}