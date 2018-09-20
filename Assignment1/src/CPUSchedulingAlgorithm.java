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