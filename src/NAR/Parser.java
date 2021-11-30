package NAR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import NAR.Constructs.IfElseConstruct;
import NAR.Constructs.SetConstruct;

public class Parser 
{
	private static Parser Instance = new Parser();
	
	private List<HighLevelFunc> constructs;
	private HashMap<String, Double> variables;
	
	private Parser() 
	{
		constructs = new ArrayList<HighLevelFunc>();
		variables = new HashMap<String, Double>();
		
		//initialize and add HighLevelFunc objects here 
		constructs.add(new IfElseConstruct());
		constructs.add(new SetConstruct());
	}
	
	/**
	 * This method gets some input string as its parameters. 
	 * This method checks for valid construct and syntax to execute the relevant HighLevelFunc object. 
	 * If no valid HighLevelFunc object is found or if the syntax for said object is incorrect, 
	 * the method returns false, otherwise true.  
	 * @param statement
	 * @return
	 */
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
	
	/**
	 * This method takes a variable name as input.
	 * Returns true if the variable has been defined false otherwise.
	 * @param variable
	 * @return
	 */
	public static boolean isVarDefined(String variable) {
		return Instance.variables.containsKey(variable);
	}
	
	/**
	 * This method takes two parameters, a variable name, and it's associated number value. 
	 * It stores the variable name and number together in internal memory. 
	 * It also can be used to store a new value for an already defined variable.
	 * @param variable
	 * @param value
	 */
	public static void defineVar(String variable, double value) {
		Instance.variables.put(variable, value);
	}
	
	/**
	 * This method takes a variable name as input and 
	 * returns the numeric value associated with it.
	 * @param variable
	 * @return value of variable
	 */
	public static double getVarValue(String variable) {
		return Instance.variables.get(variable);
	}
	
	/**
	 * Returns the list of HighLevelFunc objects supported by the parser/application. 
	 * This function is used by the editor when displaying syntax information for the user in the output console.
	 * @return
	 */
	public static List<HighLevelFunc> getFuncList(){
		return Instance.constructs;
	}
}
