package TestCodeFiles.RuntimeSystemTests.Test2;

// Dummy singleton Parser class used to test the RunTime
public class Parser {
	
	private static Parser Instance = new Parser();

	private Parser() {
	}

	public static boolean checkUserInput(String str) {
		if (str.equals("wrong")) {
			System.out.println("wrong statement encountered");
			return false;
		} else {
			System.out.println("correct statement encountered");
			return true;
		}

	}
}
