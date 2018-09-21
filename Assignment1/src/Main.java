public class Main 
{
	public static void main(String[] args) 
	{
		System.out.println("Question 1:");
		FCFS ques1 = new FCFS();
		ques1.solve();

		System.out.println("Question 2:");
		SJF ques2 = new SJF();
		ques2.solve();
		
		System.out.println("Question 3:");
		PS ques3 = new PS();
		ques3.solve();
		
		System.out.println("Question 4:");
		RR ques4 = new RR();
		ques4.solve();
	}
}