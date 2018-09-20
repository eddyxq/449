public class Main 
{
	public static void main(String[] args) 
	{
		System.out.println("Question1:");
		FCFS ques1 = new FCFS();
		ques1.solve();

		System.out.println("Question2:");
		SJF ques2 = new SJF();
		ques2.solve();
		
		System.out.println("Question3:");
		PS ques3 = new PS();
		ques3.solve();
	}
}