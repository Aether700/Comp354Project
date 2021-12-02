package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SubtractConstruct extends HighLevelFunc 
{
	private static String VarRegex = "[a-z]+[0-9a-z]*";
	private Double op1, op2;

    public SubtractConstruct() {
        super("subtract");
    }
    
    @Override
    public boolean isCorrectSyntax(String statement) 
    {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^subtract -?[0-9]+ from -?[0-9]+$")
        		|| lowerCase.matches("^subtract -?[0-9]+ from -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^subtract -?[0-9]+.[0-9]+ from -?[0-9]+$")
        		|| lowerCase.matches("^subtract -?[0-9]+.[0-9]+ from -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^subtract " + VarRegex + " from -?[0-9]+$")
        		|| lowerCase.matches("^subtract -?[0-9]+ from " + VarRegex + "$")
        		|| lowerCase.matches("^subtract " + VarRegex + " from -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^subtract -?[0-9]+.[0-9]+ from " + VarRegex + "$")
        		|| lowerCase.matches("^subtract " + VarRegex + " from " + VarRegex + "$"))
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

        Editor.printToConsole("Incorrect syntax for the subtract construct");
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
        Editor.printToConsole(op2 - op1);
    }

    @Override
    public String getHelpInformation() {
    	return "Subtract:"
    			+ "\n   Syntax: subtract <variable/number> from <variable/number> "
    			
                + "\n   Subtracts the left operand from the right operand and prints the result"
                + "\n   Example: subtract 3 from 5"
                + "\n   subtract x from y\n\n";
    	
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

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];

        int first = str.indexOf("subtract ");
        int sec = str.indexOf("from ");
        components[0] = str.substring(first + 9, sec).trim();
        int third = str.length();
        components[1] = str.substring(sec + 5, third).trim();

        return components;
    }
}
