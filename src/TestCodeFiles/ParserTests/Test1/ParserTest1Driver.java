package TestCodeFiles.ParserTests.Test1;

public class ParserTest1Driver {
	public static void main(String[] args) 
	{
		// Test test 1 with dummy Add and Test constructs
		Parser.Initialize();
		System.out.println("Test: Add 2 to 3");
		System.out.println(Parser.checkUserInput("Add 2 to 3"));
		System.out.println("\nTest: Add 2 by 3");
		System.out.println(Parser.checkUserInput("Add 2 by 3"));
		System.out.println("\nTest: Test");
		System.out.println(Parser.checkUserInput("Test"));
		System.out.println("\nTest: Some string");
		System.out.println(Parser.checkUserInput("Some string"));
		System.out.println("\nTest: ");
		System.out.println(Parser.checkUserInput(""));
	}
}
