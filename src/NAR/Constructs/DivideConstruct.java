package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class DivideConstruct extends HighLevelFunc 
{
	private static String VarRegex = "[a-z]+[0-9a-z]*";
    private double op1, op2;

    public DivideConstruct() {
        super("divide");
    }

    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^divide -?[0-9]+ by -?[0-9]+$")
        		|| lowerCase.matches("^divide -?[0-9]+.[0-9]+ by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^divide -?[0-9]+ by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^divide -?[0-9]+.[0-9]+ by -?[0-9]+$")
        		|| lowerCase.matches("^divide " + VarRegex + " by -?[0-9]+$")
        		|| lowerCase.matches("^divide -?[0-9]+ by " + VarRegex + "$")
        		|| lowerCase.matches("^divide " + VarRegex + " by -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^divide -?[0-9]+.[0-9]+ by " + VarRegex + "$")
        		|| lowerCase.matches("^divide " + VarRegex + " by " + VarRegex + "$")) 
        {
        	String[] components = splitIntoComponents(lowerCase);
        	
        	if (!isValidValue(components[0])) 
        	{
        		Editor.printToConsole("Unknown variable \'" + components[0] + "\'");
        		return false;
        	}
        	
        	if (isValidValue(components[1])) 
        	{
        		double denominator = getValue(components[1]);
        		if (denominator == 0.0f) 
        		{
        			Editor.printToConsole("Division by zero is undefined and cannot be executed.");
        			return false;
        		}
        		return true;
        	}
        	else 
        	{
        		Editor.printToConsole("Unknown variable \'" + components[1] + "\'");
        		return false;
        	}
        }

        Editor.printToConsole("Incorrect syntax for the divide construct");
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
        Editor.printToConsole(op1 / op2);
    }

    @Override
    public String getHelpInformation() {
        return "Divide:\n"
                + "   Syntax: Divide <variable/number> by <variable/number>\n"

                + "\n   Divides the left operand by the right operand and prints the result "
                + "\n   Note: Dividing by zero will give an error so operand 2 cannot be 0"
                + "\n   Example: Divide 3.5 by 0.5"
                + "\n   Divide x by y\n\n";

    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];

        int first = str.indexOf("divide ");
        int sec = str.indexOf("by ");
        components[0] = str.substring(first + 7, sec).trim();
        int third = str.length();
        components[1] = str.substring(sec + 3, third).trim();

        return components;
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
    
}
