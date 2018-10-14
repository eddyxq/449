/**
 * This class contains the first priority scheduling algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.1
 * @since October 14, 2018
 */
#include <stdio.h>
#include <stdlib.h>

int main()
{
	int s = 20;
	char *text1 = (char*)malloc(s*sizeof(char));
	char *text2 = (char*)malloc(s*sizeof(char));
	char *inputFileName = (char*)malloc(s*sizeof(char));
	char *outputFileName = (char*)malloc(s*sizeof(char));
	char *char_b = (char*)malloc(s*sizeof(char));
	char *char_p = (char*)malloc(s*sizeof(char));
	int n;
	int temp;
	int num;
	float totalTime = 0;
	float averageWaitTime = 0;
	//prompt for number of processes
	printf("Enter the number of processes: \n");
	scanf("%d", &num);
	int *burstTime = (int*)malloc(num*sizeof(int));
	int *priority = (int*)malloc(num*sizeof(int));
	int *arr = (int*)malloc(num*sizeof(int));
	int *PS_output = (int*)malloc(num*sizeof(int));
	//prompt for input file name
	printf("Enter the input file name: \n");
	scanf("%s", inputFileName);
	//prompt for output file name
	printf("Enter the output file name: \n");
	scanf("%s", outputFileName);
	//open file for reading
    FILE *input;
    input = fopen(inputFileName, "r");
    free(inputFileName);
    fscanf(input, "%s", text1);
    fscanf(input, "%s", text2);
    free(text1);
    free(text2);

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
	free(outputFileName);
	//write wait times
	for(int i=0;i<num;i++)
	{
		fprintf(output, "%d\n", PS_output[i]);
	}
	averageWaitTime = totalTime/num;
	//write average time
	fprintf(output, "%f\n", averageWaitTime);
	free(char_b);
	free(char_p);
	free(burstTime);
	free(priority);
	free(arr);
	free(PS_output);
	return 0;
}
