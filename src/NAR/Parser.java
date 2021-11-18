package NAR;
import java.util.ArrayList;
import java.util.List;

import NAR.Constructs.*;

public class Parser 
{
	private static Parser Instance = new Parser();
	
	private List<HighLevelFunc> constructs;
	
	private Parser() 
	{
		constructs = new ArrayList<HighLevelFunc>();
		
		//initialize and add HighLevelFunc objects here 
		constructs.add(new IfElseConstruct());
	}
	
	public static boolean checkUserInput(String statement) 
	{
		List<HighLevelFunc> constructs = Instance.constructs;
		
		for (HighLevelFunc func : constructs) 
		{
			if (func.isCalled(statement)) 
			{
				if (func.isCorrectSyntax(statement)) 
				{
					func.setArgs(statement);
					func.execute();
					return true;
				}
				//if the statement is being called and we don't have a 
				//correct syntax stop looking for constructs and return false
				return false;
			}
		}
		
		//if no valid construct found print an error message and return false
		String calledConstructName = statement.split(" ")[0];
		Editor.printToConsole("Unknown construct '" + calledConstructName + "' found.");
		return false;
	}
	
	public static List<HighLevelFunc> getFuncList() { return Instance.constructs; }
}
