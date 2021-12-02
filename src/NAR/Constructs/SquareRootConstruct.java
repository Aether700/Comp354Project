package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SquareRootConstruct extends HighLevelFunc {
	private static String VarRegex = "[a-z]+[0-9a-z]*";
    private double numberValue;

    public SquareRootConstruct() {
        super("square root");
    }
    
    public boolean isCalled(String statement) 
    {
    	String[] words = statement.split(" ");
    	if (words.length < 2) 
    	{
    		return false;
    	}
    	return words[0].equalsIgnoreCase("square") && words[1].equalsIgnoreCase("root"); 
    }
    
    @Override
    public boolean isCorrectSyntax(String statement) 
    {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^square root of -?[0-9]+$")
        		|| lowerCase.matches("^square root of -?[0-9]+.[0-9]+$")
        		|| lowerCase.matches("^square root of " + VarRegex)) 
        {
        	String numberVarStr = getNumberVarStr(lowerCase);
        	if (!isValidValue(numberVarStr)) 
        	{
        		Editor.printToConsole("Unknown variable \'" + numberVarStr + "\'");
        		return false;
        	}
            return true;
        }
        
        Editor.printToConsole("Incorrect syntax for the square root construct");
        return false;
    }

    @Override
    public void setArgs(String statement) 
    {
    	numberValue = getValue(getNumberVarStr(statement.toLowerCase()));
    }

    @Override
    public void execute() 
    {
        Editor.printToConsole(Math.sqrt(numberValue));
    }

    @Override
    public String getHelpInformation() {
        return "Square Root:"
                + "\n   Syntax: square root of <variable/number> "

                + "\n   Prints the square root of the provided number/variable"
                + "\n   Note: cannot be negative"
                + "\n   Example: square root of 9"
                + "\n   square root of x\n\n";
    }

    private String getNumberVarStr(String str) 
    {
        int first = str.indexOf("square root of ");
        return str.substring(first + 15).trim();
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
