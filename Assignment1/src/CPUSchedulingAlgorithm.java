public abstract class CPUSchedulingAlgorithm 
{
	int runningTime = 0;
	int count = 0;
	double totalTime = 0;
	
	public void solve()
	{
		calculateWaitTime();
		calculateAvgTime();
	}
	
	public abstract void calculateWaitTime();
	
	public abstract void calculateAvgTime();

}