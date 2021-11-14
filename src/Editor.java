
public class Editor 
{
	private static Editor Instance = new Editor();
	
	//make constructor private so no one can create an object of this class
	private Editor() 
	{
		
	}
	
	public static void run() 
	{
		Instance.runImpl();
	}
	
	private void runImpl()//Impl stands for implementation 
	{
		// implement actual method here
	}
}
