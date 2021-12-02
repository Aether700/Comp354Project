package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

import java.util.List;

public class IfElseConstruct extends HighLevelFunc
{
	private String ifFuncStatement;
	private String elseFuncStatement;
	private Condition cond;
	
	public IfElseConstruct() 
	{
		super("If");	
	}
	
	public boolean isCorrectSyntax(String statement) 
	{
		String lowerCase = statement.toLowerCase();
		if (lowerCase.matches("^if .+ do .+$") || lowerCase.matches("^if .+ do .+ else do .+$")) 
		{
			String[] components = splitIntoComponents(lowerCase);
			
			cond = Condition.getCondition(components[0]);
			if (cond == null) 
			{
				return false;
			}
			
			//check syntax of the construct to execute when the condition is true
			if (!internalStatementHasCorrectSyntax(components[1])) 
			{
				return false;
			}
			
			//check syntax of the construct to execute when the condition is false if applicable
			if (components.length > 2 && !internalStatementHasCorrectSyntax(components[2])) 
			{
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void setArgs(String statement) 
	{
		String lowerCase = statement.toLowerCase();
		String[] components = splitIntoComponents(lowerCase);
		
		cond.setArgs(components[0]);
		
		//handle the ifFunc
		ifFuncStatement = components[1];
		
		if (components.length > 2) 
		{
			elseFuncStatement = components[2];
		}
		else 
		{
			elseFuncStatement = null;
		}
	}
	
	public void execute() 
	{
		if (cond.isTrue()) 
		{
			HighLevelFunc func = findHighLevelFunc(ifFuncStatement);
			func.setArgs(ifFuncStatement);
			func.execute();
		}
		else if (elseFuncStatement != null) 
		{
			HighLevelFunc func = findHighLevelFunc(elseFuncStatement);
			func.setArgs(elseFuncStatement);
			func.execute();
		}
	}
	
	public String getHelpInformation() 
	{
		return "If/If Else:\n"
				+ "   Syntax: if <variable/number> is <optional \"not\"> <condition check> "
				+ "<variable/number> do <construct to execute>\n"
				+ "   or\n"
				+ "   if <variable/number> is <optional \"not\"> <condition check> <variable/number> "
				+ "do <construct to execute> else do <construct to execute>\n"
				+ "\n   Executes the first constructs provided if the condition is true "
				+ "otherwise executes the second "
				+ "construct provided if any was provided\n"
				+ "\n   Example: if 4 is less than 2 do add 3 to 5\n"
				+ "   if X is greater than or equal to 2 do subtract 5 from X else do add 10 to X\n"
				+ "\n";
	}

	//splits the string provided into the the condition, the construct to execute if the condition is true 
	//and the construct to execute if the condition is false if applicable
	private String[] splitIntoComponents(String str) 
	{
		String[] components = new String[2];
		
		boolean hasElse = str.contains("else do ");
		
		if (hasElse) 
		{
			components = new String[3];
		}
		
		
		int startOfDo = str.indexOf("do ");
		components[0] = str.substring(3, startOfDo).trim(); //retrieve condition
		
		int endOfIfFunc = str.length();
		
		if (hasElse) 
		{
			endOfIfFunc = str.indexOf("else do ");
		}
		
		components[1] = str.substring(startOfDo + 3, endOfIfFunc).trim();
		
		if (hasElse) 
		{
			components[2] = str.substring(endOfIfFunc + 8).trim();
		}
		
		return components;
	}
	
	//Retrieves the HighLevelFunc object corresponding to the statement provided. Does not verify that the syntax is correct. 
	//Returns null if no corresponding HighLevelFunc object was found
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
	
	// private helper class which stores the condition to test
	private static class Condition
	{
		private double lhs;
		private double rhs;
		private boolean isNot; //checks if the condition is marked as "not" <rest of condition>
		private ConditionState state;
		
		private Condition(ConditionState state, boolean isNot) 
		{
			this.state = state;
			this.isNot = isNot;
		}
		
		//verifies if the condition is true or not
		public boolean isTrue() 
		{
			boolean result = true; //default to true, value will be changed
			switch(state) 
			{
			case Equal:
				result = lhs == rhs;
				break;
				
			case Greater:
				result = lhs > rhs;
				break;
				
			case GreaterOrEqual:
				result = lhs >= rhs;
				break;
				
			case Less:
				result = lhs < rhs;
				break;
				
			case LessOrEqual:
				result = lhs <= rhs;
				break;
			}
			
			if (isNot) 
			{
				return !result;
			}
			
			return result;
		}
		
		
		//is responsible for retrieving the value of the number(s)/variable(s) in the conditional statement
		public void setArgs(String statement) 
		{
			String[] components = splitIntoComponents(statement.toLowerCase());
			lhs = parseIntoNumber(components[0]);
			rhs = parseIntoNumber(components[2]);
		}

		//returns a condition statement based on the statement provided. This function will only set the 
		//state and the isNot boolean of the Condition object. if the syntax of the provided statement 
		//is incorrect this function returns null.
		//
		//Note that this function is the only way to get a Condition object as the constructor is marked as 
		//private to force the users of this class to use this function
		public static Condition getCondition(String statement) 
		{
			if (!hasProperStructure(statement)) 
			{
				return null;
			}
			
			String lowerCase = statement.toLowerCase().trim();
			boolean isNot = lowerCase.contains("is not ");
			String[] components = splitIntoComponents(lowerCase);
			
			ConditionState state = ConditionState.Equal; // set to equal by default will be changed
			
			if (components[1].equalsIgnoreCase("equal to")) 
			{
				state = ConditionState.Equal;
			}
			else if (components[1].equalsIgnoreCase("greater than")) 
			{
				state = ConditionState.Greater;
			}
			else if (components[1].equalsIgnoreCase("greater than or equal to")) 
			{
				state = ConditionState.GreaterOrEqual;
			}
			else if (components[1].equalsIgnoreCase("less than")) 
			{
				state = ConditionState.Less;
			}
			else if (components[1].equalsIgnoreCase("less than or equal to")) 
			{
				state = ConditionState.LessOrEqual;
			}
			else 
			{
				Editor.printToConsole("Unknown condition \'" + components[1] + "\'");
				return null;
			}
			
			return new Condition(state, isNot);
		}
		
		//makes sure the structure of the statement is the following: 
		// <var/num> is not(optional) <state> <var/num>
		//
		//ex: 5 is greater than X
		//Y is not smaller than 3
		private static boolean hasProperStructure(String statement) 
		{
			final String regexVar = "[a-z]+[0-9a-z]*";
			String lowerCase = statement.toLowerCase().trim();
			
			
			//check both numbers
			//\u0020 checks for the space character using it's Unicode in hexadecimal
			if (lowerCase.matches("^-?[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$") 
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$")
					|| lowerCase.matches("^-?[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$")
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$")
					|| lowerCase.matches("^-?[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$") 
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+\\.[0-9]+$")
					|| lowerCase.matches("^-?[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+\\.[0-9]$")
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+\\.[0-9]+$")) 
			{
				return true;
			}
			else if (lowerCase.matches("^" + regexVar + " is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$") // check for var then number
					|| lowerCase.matches("^" + regexVar + " is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+\\.[0-9]+$")
					|| lowerCase.matches("^" + regexVar + " is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$")
					|| lowerCase.matches("^" + regexVar 
							+ " is not [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+\\.[0-9]+$"))
			{
				String[] components = splitIntoComponents(lowerCase);
				if (Parser.isVarDefined(components[0])) 
				{
					return true;
				}
				else 
				{
					Editor.printToConsole("Unknown token \'" + components[0] + "\'");
					return false;
				}
			}
			else if  (lowerCase.matches("^-?[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$") // check for number then var
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$")
					|| lowerCase.matches("^-?[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$")
					|| lowerCase.matches("^-?[0-9]+\\.[0-9]+ is not [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$"))
			{
				String[] components = splitIntoComponents(lowerCase);
				if (Parser.isVarDefined(components[2])) 
				{
					return true;
				}
				else 
				{
					Editor.printToConsole("Unknown token \'" + components[2] + "\'");
					return false;
				}
			}
			else if  (lowerCase.matches("^" + regexVar + " is [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$")  //check for both var
					|| lowerCase.matches("^" + regexVar + " is not [a-z]+[a-z\\u0020]+[a-z] " + regexVar + "$"))
			{
				String[] components = splitIntoComponents(lowerCase);
				if (!Parser.isVarDefined(components[0])) 
				{
					Editor.printToConsole("Unknown token \'" + components[0] + "\'");
					return false;
				}
				
				if (Parser.isVarDefined(components[2])) 
				{
					return true;
				}
				else 
				{
					Editor.printToConsole("Unknown token \'" + components[2] + "\'");
					return false;
				} 
			}
			
			
			Editor.printToConsole("Unknown conditional statement: \'" + statement + "\'");
			return false;
		}
		
		//splits the provided statement into an array of string containing the first number/variable, the 
		//condition state and the second number/variable in that order
		private static String[] splitIntoComponents(String statement) 
		{
			//^-?[0-9]+ is [a-z]+[a-z\\u0020]+[a-z] -?[0-9]+$
			String[] components = new String[3];
			
			//get first number/var
			int startOfIs = statement.indexOf("is ");
			components[0] = statement.substring(0, startOfIs).trim();
			
			//get state
			boolean hasNot = statement.contains("is not ");
			int startOfState = hasNot ? startOfIs + 7 : startOfIs + 3;
			int endOfState = statement.lastIndexOf(" ");
			components[1] = statement.substring(startOfState, endOfState).trim();
			
			//get second number/var
			components[2] = statement.substring(endOfState).trim();
			return components;
		}
		
		//parses the provided statement into a number
		private static double parseIntoNumber(String statement) 
		{
			if (Parser.isVarDefined(statement))
			{
				return Parser.getVarValue(statement);
			}
			
			try 
			{
				double val = Double.parseDouble(statement);
				return val;
			}
			catch(NumberFormatException num) 
			{
				//this case should never be reached since the statement should have 
				//been parsed and verified before this function is called
				System.out.println("Incorrect number: \'" + statement + "\'");
				num.printStackTrace();
				System.exit(1);// quit application
			}
			return 0.0; //required by compiler
		}
		
 		private static enum ConditionState
		{
			Equal,
			Greater,
			GreaterOrEqual,
			Less,
			LessOrEqual
		}
	}
}
