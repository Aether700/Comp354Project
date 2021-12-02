package NAR.Constructs;

import java.util.List;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class LoopConstruct extends HighLevelFunc {
	
	private static String VarRegex = "[a-z]+[0-9a-z]*";
	private String funcStatement;
	private int loopAmount;
	
	public LoopConstruct() {
		super("Loop");
	}
	
	public boolean isCorrectSyntax(String statement) 
	{
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^loop -?[0-9]+ times and do .+$")
        		|| lowerCase.matches("^loop -?[0-9]+.[0-9]+ times and do .+$")
        		|| lowerCase.matches("^loop " + VarRegex + " times and do .+$")) 
        {
        	String[] components = splitIntoComponents(statement);
        	
        	if (!isLoopCounterValid(components[0]))
        	{
        		return false;
        	}
        	else if (!internalStatementHasCorrectSyntax(components[1])) 
        	{
        		return false;
        	}
        	return true;
        }
        Editor.printToConsole("Invalid syntax for the loop construct");
        return false;
    }

	public void setArgs(String statement) 
	{
		String[] components = splitIntoComponents(statement);
		
		loopAmount = (int)getValue(components[0]);
		funcStatement = components[1];
	}

	public void execute() {
		HighLevelFunc func = findHighLevelFunc(funcStatement);
		for(int i = 0; i < loopAmount; i++) 
		{
			func.setArgs(funcStatement);
			func.execute();
		}
	}

	public String getHelpInformation() {
		return "Loop:\n"
                + "   Syntax: loop <number> times and do <construct to execute>\n"
                + "	  Executes the construct <number> amount of times \n"
                + "   Example: loop 4 times and do add 2 to 3\n\n";
	}
	
	private HighLevelFunc findHighLevelFunc(String statement) 
	{
		List<HighLevelFunc> constructs = Parser.getFuncList();
		
		for (HighLevelFunc func : constructs) 
		{
			if (func.isCalled(statement)) 
			{
				return func;
			}
		}
		return null;
	}
	
	private boolean internalStatementHasCorrectSyntax(String statement) 
	{
		List<HighLevelFunc> constructs = Parser.getFuncList();
		
		for (HighLevelFunc func : constructs) 
		{
			if (func.isCalled(statement)) 
			{
				if (func.isCorrectSyntax(statement)) 
				{
					return true;
				}
				return false;
			}
		}
		
		Editor.printToConsole("Unknown construct \'" + statement + "\'");
		return false;
	}
	
	private String[] splitIntoComponents(String statement) 
	{
		String[] components = new String[2];
		
		String lowerCase = statement.toLowerCase();
		int endOfLoop = lowerCase.indexOf("loop ") + 5;
		int startOfTimes = lowerCase.indexOf("times ");
		components[0] = statement.substring(endOfLoop, startOfTimes);
		int startOfDo = lowerCase.indexOf("do ");
    	components[1] = lowerCase.substring(startOfDo + 3);
    	
    	return components;
	}
	
	private double getValue(String statement) 
	{
		if (Parser.isVarDefined(statement)) 
		{
			return Parser.getVarValue(statement);
		}
		
		try 
		{
			return Double.parseDouble(statement);
		}
		catch (NumberFormatException num) 
		{
			System.out.println("Invalid number/variable \'" + statement + "\'");
			System.exit(1);
		}
		return 0.0f; //required by compiler
	}
	
	private boolean isLoopCounterValid(String statement) 
	{
		double realValue = getValue(statement);
		
		if (realValue < 0) 
		{
			Editor.printToConsole("The loop count for the loop construct needs to be positive");
			return false;
		}
		
		return true;
	}
}
