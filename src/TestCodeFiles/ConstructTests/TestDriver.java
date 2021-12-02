package TestCodeFiles.ConstructTests;

import NAR.Editor;
import NAR.Parser;
import NAR.RuntimeSystem;

public class TestDriver 
{
	private static void AddTest() 
	{
		String statement = "add y1 to x";
		Editor.run();
		Parser.defineVar("x",500.0);
		Parser.defineVar("y1",-69);
		Parser.checkUserInput(statement);		
	}

	private static void DivideTest() 
	{
		String statement = "divide y1 by x";
        Editor.run();
        Parser.defineVar("x",20.20);
        Parser.defineVar("y1",200.20);
        Parser.checkUserInput(statement);
	}
	
	private static void MultiplyTest() 
	{
		String statement = "multiply -2 by -9";
        Editor.run();
        Parser.checkUserInput(statement);
	}
	
	private static void SquareRootTest() 
	{
		String statement = "square root of 9";
        Editor.run();
        Parser.checkUserInput(statement);
	}
	
	private static void SubtractTest() 
	{
		String statement = "subtract 10000 from -9999";
        Editor.run();
        Parser.checkUserInput(statement);
	}
	
	private static void SetTest() 
	{
		String code = "Set x to 10\nAdd x to 0";
        Editor.run();
        RuntimeSystem.runCode(code);
	}
	
	private static void LoopTest() 
	{
		String code = "loop 10.9 times and do Add 2 to 1.5";
        Editor.run();
        RuntimeSystem.runCode(code);
	}
	
	private static void IfElseTest() 
	{
		String code = "if 3 is less than 5 do subtract 2 from 5\n"
				+ "if 3 is greater than 5 do subtract 2 from 5 else do add 2 to 5\n"
				+ "if 3 is less than 5 do subtract 2 from 5 else do add 2 to 5\n";
		Editor.run();
        RuntimeSystem.runCode(code);
	}
	
    public static void main(String args[])
    {
        int testIndex = 7; //change to select which test to run
    	
        switch(testIndex) 
        {
        case 0:
        	AddTest();
        	break;
        	
        case 1:
        	DivideTest();
        	break;
        
        case 2:
        	MultiplyTest();
        	break;
        	
        case 3:
        	SquareRootTest();
        	break;
        
        case 4:
        	SubtractTest();
        	break;
        
        case 5:
        	SetTest();
        	break;
        
        case 6:
        	LoopTest();
        	break;
        
        case 7:
        	IfElseTest();
        	break;
        	
        default:
        	System.out.println("Incorrect test please select a test index between 0 and 7 inclusively");
        }
    	
    }
}
