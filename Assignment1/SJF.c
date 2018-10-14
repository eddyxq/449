/**
 * This class contains the shortest job first algorithm
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
	int n;
	int temp;
	//prompt for number of processes
	printf("Enter the number of processes: \n");
	scanf("%d", &num);
	int *SJF_input = (int*)malloc(num*sizeof(int));
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
    //read file contents into array
    for (int i = 0; i < num; i++)
    {
        fscanf(input, "%d", &SJF_input[i]);
    }
    //sort input into ascending order
    int *arr = (int*)malloc(num*sizeof(int));
	for(int i=0;i<num;i++)
	{
		n = i;
		for(int j=i+1;j<num;j++)
		{
			if (SJF_input[j] < SJF_input[n])
			{
				n = j;
			}
		}
		temp = SJF_input[i]; SJF_input[i] = SJF_input[n]; SJF_input[n] = temp;
		temp = arr[i]; arr[i] = arr[n]; arr[n] = temp;
	}
	free(arr);
    //calculate wait times
	int *SJF_output = (int*)malloc(num*sizeof(int));
    for(int i = 0; i < num; i++)
	{
		totalTime += runningTime;
		SJF_output[i] = runningTime;
		runningTime += SJF_input[i];
		count += 1;
	}
    free(SJF_input);
    //open file for writing
	FILE *output = fopen(outputFileName, "w");
	free(outputFileName);
	//write wait times
	for (int i = 0; i < num; i++)
	{
		fprintf(output, "%d\n", SJF_output[i]);
	}
	free(SJF_output);
	float averageWaitTime = totalTime/count;
	//write average time
	fprintf(output, "%f\n", averageWaitTime);

	return 0;
}
