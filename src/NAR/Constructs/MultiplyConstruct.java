package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class MultiplyConstruct extends HighLevelFunc 
{
	private static String VarRegex = "[a-z]+[0-9a-z]*";
    private double op1, op2;

    public MultiplyConstruct() {
        super("multiply");
    }

    @Override
    public boolean isCorrectSyntax(String statement) 
    {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^multiply -?[0-9]+ by -?[0-9]+$")
        		|| lowerCase.matches("^multiply -?[0-9]+ by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^multiply -?[0-9]+.[0-9]+ by -?[0-9]+$")
        		|| lowerCase.matches("^multiply -?[0-9]+.[0-9]+ by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^multiply " + VarRegex + " by -?[0-9]+$")
        		|| lowerCase.matches("^multiply -?[0-9]+ by " + VarRegex + "$")
        		|| lowerCase.matches("^multiply " + VarRegex + " by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^multiply -?[0-9]+.[0-9]+ by " + VarRegex + "$")
        		|| lowerCase.matches("^multiply " + VarRegex + " by " + VarRegex + "$"))
        {
        	String[] components = splitIntoComponents(lowerCase);
        	
        	if (!isValidValue(components[0])) 
        	{
        		Editor.printToConsole("Unknown variable \'" + components[0] + "\'");
        		return false;
        	}
        	
        	if (!isValidValue(components[1])) 
        	{
        		Editor.printToConsole("Unknown variable \'" + components[1] + "\'");
        		return false;
        	}
        	
        	return true;
        }

        Editor.printToConsole("Incorrect syntax for the multiply construct");
        return false;
    }
    
    @Override
    public void setArgs(String statement) {
        String[] components = splitIntoComponents(statement.toLowerCase());

        op1 = getValue(components[0]);
        op2 = getValue(components[1]);
    }


    @Override
    public void execute() 
    {
        Editor.printToConsole(op2 * op1);
    }

    @Override
    public String getHelpInformation() 
    {
        return "Multiply:\n"
                + "   Syntax: multiply <variable/number> with <variable/number> "

                + "\n   Multiplies the left operand with the right operand and prints the result "

                + "\n   Example: multiply 3 by 5"
                + "\n   multiply 33 by y\n\n";

    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];

        int first = str.indexOf("multiply ");
        int sec = str.indexOf("by ");
        components[0] = str.substring(first + 9, sec).trim();
        int third = str.length();
        components[1] = str.substring(sec + 3, third).trim();

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
    	catch(NumberFormatException num)
    	{
    		System.out.println("Unknown variable/number \'" + statement + "\'");
    		System.exit(1);
    	}
    	
    	return 0.0f; //required by compiler
    }
    
    private boolean isValidValue(String statement) 
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
    	catch(NumberFormatException num)
    	{
    		return false;
    	}
    }
}
