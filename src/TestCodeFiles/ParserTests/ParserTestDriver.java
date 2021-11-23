package TestCodeFiles.ParserTests;

import NAR.Parser;

public class ParserTestDriver {
	public static void main(String[] args) 
	{
		System.out.println("Tests:");
		Parser.defineVar("X", 20);
		try {
			System.out.println(Parser.getVarValue("Y"));
		}
		catch (NullPointerException e) { 
			System.out.println("Variable is not defined");
		}
		System.out.println(Parser.isVarDefined("Y"));
		System.out.println(Parser.isVarDefined("X"));
		System.out.println(Parser.getVarValue("X"));
		Parser.defineVar("Y", 5);
		System.out.println(Parser.isVarDefined("Y"));
		System.out.println(Parser.getVarValue("Y"));
		Parser.defineVar("X", Parser.getVarValue("Y"));
		System.out.println(Parser.getVarValue("X"));
		
		System.out.println("\nExpected values:");
		System.out.println("Variable is not defined");
		System.out.println(false);
		System.out.println(true);
		System.out.println(20);
		System.out.println(true);
		System.out.println(5);
		System.out.println(5);
	}
}
