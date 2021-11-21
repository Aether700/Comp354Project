public class Parser {

	// Dummy singleton parser class used to test the RunTime
	private static Parser Instance = new Parser();

	private Parser() {
	}

	public static boolean checkUserInput(String statement) {
		System.out.println(statement);
		return true;

	}

}
