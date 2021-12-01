package NAR.Constructs;

import java.util.List;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class LoopConstruct extends HighLevelFunc {
	
	private String funcStatement;
	private int loopAmount;
	
	public LoopConstruct() {
		super("Loop");
	}
	
	public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^loop (\\d+) times and do .+$")) {
        	int startOfDo = lowerCase.indexOf("do ");
        	String function = lowerCase.substring(startOfDo + 3);
        	if(internalStatementHasCorrectSyntax(function)) {
        		return true;
        	}
        }
        return false;
    }

	public void setArgs(String statement) {
		String lowerCase = statement.toLowerCase();
		loopAmount = Integer.parseInt(lowerCase.split(" ")[1]);
		int startOfDo = lowerCase.indexOf("do ");
    	funcStatement = lowerCase.substring(startOfDo + 3);
	}

	public void execute() {
		for(int i = 0; i < loopAmount; i++) {
			HighLevelFunc func = findHighLevelFunc(funcStatement);
			func.setArgs(funcStatement);
			func.execute();
		}
	}

	public String getHelpInformation() {
		return "Loop:\n"
                + "   Syntax: loop <number> times and do <construct to execute>\n"
                + "	  Executes the construct <number> amount of times \n"
                + "   Example: loop 4 times and do add 2 to 3\n";
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
}
