package TestCodeFiles.ParserTests.Test1;

import NAR.HighLevelFunc;

// Dummy Test HighLevelFunc object used when testing the parser
public class Test extends HighLevelFunc 
{
	public Test() { super("Test"); }
	
	public boolean isCorrectSyntax(String statement) { 
		return isCalled(statement);
	}
	
	public void setArgs(String statement) { 
		return;
	}
	
	public void execute() { 
		System.out.println("Test construct is called");
	}
	
	public String getHelpInformation() 
	{
		return "Help information for Test";
	}
}
