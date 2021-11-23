package TestCodeFiles.RuntimeSystemTests.Test1;

// Dummy singleton parser class used to test the RunTime
public class Parser {

	private static Parser Instance = new Parser();

	private Parser() {
	}

	public static boolean checkUserInput(String statement) {
		System.out.println(statement);
		return true;

	}

}
