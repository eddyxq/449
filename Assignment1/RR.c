/**
 * This class contains the round robin algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.1
 * @since October 14, 2018
 */
#include <stdio.h>
#include <stdlib.h>

int main()
{
	int s = 20;
	char *inputFileName = (char*)malloc(s*sizeof(char));
	char *outputFileName = (char*)malloc(s*sizeof(char));
	int num;
	int runningTime = 0;
	int count = 0;
	float totalTime = 0;
	int quantumTime;
	//prompt for number of processes
	printf("Enter the number of processes: \n");
	scanf("%d", &num);
	int *RR_input = (int*)malloc(num*sizeof(int));
	//prompt for input file name
	printf("Enter the input file name: \n");
	scanf("%s", inputFileName);
	//prompt for output file name
	printf("Enter the output file name: \n");
	scanf("%s", outputFileName);
	int *waitTime = (int*)malloc(num*sizeof(int));
	int *remainingTime = (int*)malloc(num*sizeof(int));
	//open file for reading
    FILE *input;
    input = fopen(inputFileName, "r");
    free(inputFileName);
    //read file contents into array
    //store first line as quantum time
    fscanf(input, "%d", &quantumTime);
    //store rest of numbers
    for (int i = 0; i < num; i++)
    {
        fscanf(input, "%d", &RR_input[i]);
        remainingTime[i] = RR_input[i];
    }

    int allProcessFinished = 0;
    //while processes are not finished, keep processing
    while(allProcessFinished != 1)
    {
    	int anotherCheckNeeded = 0;
    	//checks each process
    	for (int i = 0 ; i < num; i++)
    	{
    		//determine if there's time remaining in this process
    		if (remainingTime[i] > 0)
    		{
    			//if code enters here, we need to do another iteration to see if we are finished
    			anotherCheckNeeded = 1;
    			if (remainingTime[i] > quantumTime)
    			{
    				runningTime += quantumTime;
    				int temp = remainingTime[i] - quantumTime;
    				remainingTime[i] = temp;
    			}
    			else
    			{
    				runningTime += remainingTime[i];
    				waitTime[i] = runningTime - RR_input[i];
    				remainingTime[i] = 0;
    			}
    		}
    	}
    	if (anotherCheckNeeded == 0)
    	{
    		allProcessFinished = 1;
    	}
    }
    //open file for writing
    FILE *output = fopen(outputFileName, "w");
    free(outputFileName);
    for (int i = 0 ; i < num; i++)
    {
    	totalTime += waitTime[i];
    	count += 1;
    	//write wait times
    	fprintf(output, "%d\n", waitTime[i]);
    }
    free(waitTime);
    free(remainingTime);
    free(RR_input);
    float averageWaitTime = totalTime/count;
    //write average time
    fprintf(output, "%f\n", averageWaitTime);

	return 0;
}
