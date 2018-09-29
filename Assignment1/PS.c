/**
 * This class contains the first priority scheduling algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.0
 * @since September 26, 2018
 */
#include <stdio.h>
#include <stdlib.h>

int main()
{
	char text1[100];
	char text2[100];
	char inputFileName[100];
	char outputFileName[100];
	char char_b[100];
	char char_p[100];
	int burstTime[100];
	int priority[100];
	int arr[100];
	int PS_output[100];
	int n;
	int temp;
	int num;
	float totalTime = 0;
	float averageWaitTime = 0;
	//prompt for number of processes
	printf("Enter the number of processes: \n");
	scanf("%d", &num);
	//prompt for input file name
	printf("Enter the input file name: \n");
	scanf("%s", inputFileName);
	//prompt for output file name
	printf("Enter the output file name: \n");
	scanf("%s", outputFileName);
	//open file for reading
    FILE *input;
    input = fopen(inputFileName, "r");

    fscanf(input, "%s", text1);
    fscanf(input, "%s", text2);

    for(int x = 0; x < num; x++)
	{
    	 fscanf(input, "%s", char_b);
    	 fscanf(input, "%s", char_p);

    	 int btime = atoi(char_b);
    	 int prior = atoi(char_p);

    	 burstTime[x] = btime;
    	 priority[x] = prior;
   	}
    //sort the processes by priority
	for(int i=0;i<num;i++)
	{
		n = i;
		for(int j=i+1;j<num;j++)
		{
			if (priority[j] < priority[n])
			{
				n = j;
			}
		}
	   temp = priority[i]; priority[i] = priority[n]; priority[n] = temp;
	   temp = burstTime[i]; burstTime[i] = burstTime[n]; burstTime[n] = temp;
	   temp = arr[i];  arr[i] = arr[n]; arr[n] = temp;
	}
	PS_output[0] = 0;
	//calculate waiting time
	for(int i=1;i<num;i++)
	{
		PS_output[i] = 0;
		for(int j=0;j<i;j++)
		{
			PS_output[i] += burstTime[j];
		}
		totalTime += PS_output[i];
	}
	FILE *output = fopen(outputFileName, "w");
	//write wait times
	for(int i=0;i<num;i++)
	{
		fprintf(output, "%d\n", PS_output[i]);
	}
	averageWaitTime = totalTime/num;
	//write average time
	fprintf(output, "%f\n", averageWaitTime);

	return 0;
}
