package NAR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RuntimeSystem {
	
	private static RuntimeSystem Instance = new RuntimeSystem();
	RuntimeSystem() {}
	
	
	private List listOfLines(String str) {

		List<String> lines = new ArrayList<>();
		Scanner scanner = new Scanner(str);

		while (scanner.hasNextLine()) {

			lines.add(scanner.nextLine());
		}

		scanner.close();
		return lines;
	}

	private String reduceSpace(String str) {

		String line = str.replaceAll("\\s +", " ");

		if (isLengthGreaterThanZero(line)) {

			if (isFirstCharWhiteSpace(line)) {

				line = line.replaceFirst("\\ +", "");
			}
		}

		return line;
	}

	private static boolean isFirstCharWhiteSpace(String str) {

		char fChar = str.charAt(0);
		return Character.isWhitespace(fChar);
	}

	private static boolean isLengthGreaterThanZero(String str) {

		return str != null && str.length() > 0;
	}


	public void runCode(String str) {

		List<String> lines = Instance.listOfLines(str);

		for (String line : lines) {

			String statement = Instance.reduceSpace(line);

			if (Instance.isLengthGreaterThanZero(statement)) {

				System.out.print(statement);
                //check if the syntax is correct
				if (!Parser.checkUserInput(statement)) {
					break;
				}
			}

		}
	}

}
