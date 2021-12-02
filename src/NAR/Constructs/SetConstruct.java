package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SetConstruct extends HighLevelFunc 
{
	private static String VarRegex = "[a-z]+[0-9a-z]*";
	
	private String varName;
	private double value;
	
	public SetConstruct() 
	{
		super("Set");
	}
	
	public boolean isCorrectSyntax(String statement) 
	{
		String lowerCase = statement.toLowerCase();
		if (lowerCase.matches("set -?[0-9]+.[0-9]+ to -?[0-9]+.[0-9]+")
				|| lowerCase.matches("set -?[0-9]+ to -?[0-9]+.[0-9]+")
				|| lowerCase.matches("set -?[0-9]+.[0-9]+ to -?[0-9]+")
				|| lowerCase.matches("set -?[0-9]+.[0-9]+ to " + VarRegex)
				|| lowerCase.matches("set -?[0-9]+ to " + VarRegex)) 
		{
			Editor.printToConsole("Set is used to define a variable, please provide a valid variable name.");
		}
		else if (!(lowerCase.matches("set " + VarRegex + " to " + VarRegex)
				|| lowerCase.matches("set " + VarRegex + " to -?[0-9]+")
				|| lowerCase.matches("set " + VarRegex + " to -?[0-9]+.[0-9]+")))
		{
			Editor.printToConsole("Incorrect syntax for the set construct.");
			return false;
		}
		
		String[] components = splitIntoComponents(lowerCase);
		return isValidVarNumber(components[1]);
	}

	public void setArgs(String statement) 
	{
		String[] components = splitIntoComponents(statement.toLowerCase());
		
		varName = components[0];
		value = getValue(components[1]);
	}

	public void execute() 
	{
		Parser.defineVar(varName, value);
		Editor.printToConsole(varName + " was set to " + value);
	}

	public String getHelpInformation() 
	{
		return "Set:\n"
				+ "   Syntax: set <variable name> to <variable/number>\n"
				+ "   Creates or changes variable with the provided name and gives it the provided value.\n"
				+ "   A valid variable name is any sequence of character and numbers including at the very\n"
				+ "   least one character (the variable name does not need to contain numbers).\n"
				+ "   Example: set X to 4\n"
				+ "   set X to Y\n"
				+ "\n";
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
			System.out.println("unknown variable/number \'" + statement + "\' reached");
			num.printStackTrace();
			System.exit(1);
		}
		return 0.0f; //required by compiler
	}
	
	private boolean isValidVarNumber(String statement) 
	{
		if (Parser.isVarDefined(statement)) 
		{
			return true;
		}
		
		try 
		{
			Double.parseDouble(statement);
			return true;
		}
		catch (NumberFormatException num) 
		{
			Editor.printToConsole("Undefined variable \'" + statement + "\' found");
		}	
		return false;
	}
	
	private String[] splitIntoComponents(String statement) 
	{
		String[] components = new String[2];
		
		components[0] = statement.substring(statement.indexOf("set ") + 4, statement.indexOf(" to "));
		components[1] = statement.substring(statement.indexOf(" to ") + 4, statement.length());
		return components;
	}
}
