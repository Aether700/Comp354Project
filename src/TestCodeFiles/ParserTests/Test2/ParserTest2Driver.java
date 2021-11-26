package TestCodeFiles.ParserTests.Test2;

import NAR.Parser;

public class ParserTest2Driver {
	public static void main(String[] args) 
	{
		System.out.println("Tests:");
		Parser.defineVar("X", 20);
		try {
			System.out.println(Parser.getVarValue("Y"));
		}
		catch (NullPointerException e) {}
		System.out.println(Parser.isVarDefined("Y"));
		System.out.println(Parser.isVarDefined("X"));
		System.out.println(Parser.getVarValue("X"));
		Parser.defineVar("Y", 5);
		System.out.println(Parser.isVarDefined("Y"));
		System.out.println(Parser.getVarValue("Y"));
		Parser.defineVar("X", Parser.getVarValue("Y"));
		System.out.println(Parser.getVarValue("X"));
		
		// Check if test results are same as expected values
		System.out.println("\nExpected values:");
		System.out.println("Y is not defined");
		System.out.println(false);
		System.out.println(true);
		System.out.println(20);
		System.out.println(true);
		System.out.println(5);
		System.out.println(5);
	}
}
