import NAR.Editor;


public class Test1Driver {
	public static void main(String[] args) 
	{
		String st ="add 78 to 9\n"
				+ " add  99   to  5      \n"
				+ "                      \n"
				+ "           \n"
				+ " add 7 to 4                         \n" + "                               \n"+ "\n"
				+ "\n"
				+ "addd hh j\n"
				+ "         \n";
		
		RuntimeSysObj.runCode(st); //comment and uncomment this line to test with or without the HighLevelFunction objects in the parser
		//Editor.run();

	}
}
