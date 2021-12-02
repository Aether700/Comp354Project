package TestCodeFiles.IntegrationTests.RuntimeParser;

import NAR.Editor;
import NAR.RuntimeSystem;

public class RuntimeParserTestDriver 
{
	public static void main(String[] args) 
	{
		String[] testCode = {
				"Add 1 to X",
				"Add 1 to 5\nDivide 50 by 0\nAdd 1 to 0\n", 
				"Add 1 to 5\nAdd 1 to 0\nDivide 50 by 0\n", 
				"TEST INPUT\nADD 100\n", 
				"Add 1 to 5\nTEST INPUT\nADD 100\nAdd 1 to 0\n", 
				"Add 1 to 5\nAdd 1 to 0\nTEST INPUT\nADD 100\n", 
				"SET x = 30\nAdd x to y\n", 
				"\n \n",
				"Add      1 to       5\n\n\n"
		};
		
		Editor.run();
		
		for (int i = 0; i < testCode.length; i++) 
		{
			RuntimeSystem.runCode(testCode[i]);
			Editor.printToConsole("====== Next Test starts now======");
		}		
	}
}
