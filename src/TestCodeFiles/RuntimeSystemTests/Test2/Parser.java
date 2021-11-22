package TestCodeFiles.RuntimeSystem.Test2;

public class Parser {

	// Dummy singleton parser class used to test the RunTime
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
