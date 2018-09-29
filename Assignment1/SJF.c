/**
 * This class contains the shortest job first algorithm
 * @author Eddy Qiang - CPSC 449 - Tutorial T03
 * @version 1.0
 * @since September 26, 2018
 */
#include <stdio.h>
#include <stdlib.h>

int main()
{
	char inputFileName[100];
	char outputFileName[100];
	int num;
	int SJF_input[1000];
	int SJF_output[1000];
	int arr[1000];
	int runningTime = 0;
	int count = 0;
	float totalTime = 0;
	int n;
	int temp;

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
    //read file contents into array
    for (int i = 0; i < num; i++)
    {
        fscanf(input, "%d", &SJF_input[i]);
    }
    //sort input into accending order
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
    //calculate wait times
    for(int i = 0; i < num; i++)
	{
		totalTime += runningTime;
		SJF_output[i] = runningTime;
		runningTime += SJF_input[i];
		count += 1;
	}
    //open file for writing
	FILE *output = fopen(outputFileName, "w");
	//write wait times
	for (int i = 0; i < num; i++)
	{
		fprintf(output, "%d\n", SJF_output[i]);
	}
	float averageWaitTime = totalTime/count;
	//write average time
	fprintf(output, "%f\n", averageWaitTime);

	return 0;
}
