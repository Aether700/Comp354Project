package TestCodeFiles.ParserTests.Test1;

import NAR.HighLevelFunc;

// Dummy Add HighLevelFunc object used when testing the parser
public class Add extends HighLevelFunc 
{
	private int var1;
	private int var2;
	
	public Add() { super("Add"); }
	
	public boolean isCorrectSyntax(String statement) { 
		if(statement.matches("Add \\d to \\d")) {
			return true;
		} else {
			System.out.println("Incorrect add syntax");
			return false;
		}
	}
	
	public void setArgs(String statement) { 
		String s = statement.replaceAll("[^0-9]", "");
		var1 = Character.getNumericValue(s.charAt(0));
		var2 = Character.getNumericValue(s.charAt(1));
	}
	
	
	public void execute() { 
		System.out.println(var1 + var2);
	}
	
	public String getHelpInformation() 
	{
		return "Help information for Add";
	}
}
