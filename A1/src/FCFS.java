import java.util.ArrayList;

public class FCFS 
{
	
	int runningTime = 0;
	
	ArrayList<Integer> FSFS_input = new fileReader().read_FCFS_input();
	
	public void solve()
	{
		calculateWaitTime();
	}
	
	public void calculateWaitTime()
	{
		int count = 0;
		double totalTime = 0;

		for(int i = 0; i < FSFS_input.size(); i++) 
		{
			int waitTime = runningTime;
			totalTime += waitTime;
			System.out.println(waitTime); 
			runningTime += FSFS_input.get(i);
			count += 1;
		}
		double averageWaitTime = totalTime/count;
		System.out.println(averageWaitTime);
	}
}
