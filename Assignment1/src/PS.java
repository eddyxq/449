import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PS extends CPUSchedulingAlgorithm
{
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();	
	ArrayList<Integer> PS_input = new fileReader().readInput("inputPrioritySch.txt");
	ArrayList<Integer> burstTime = new ArrayList<Integer>();
	ArrayList<Integer> priority = new ArrayList<Integer>();
	@Override
	public void calculateWaitTime() 
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
			System.out.println(runningTime);
			totalTime += runningTime;
			runningTime += map.get(priority.get(i));
			count += 1;
		}
	}
}