package TestCodeFiles.RuntimeSystemTests.Test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* importing the exact RunTimeSystem class from NAR package to get implemented 
with dummy Parser class in TestCodeFiles.RuntimeSystemTests.Test2 */

public class RuntimeSystem {
	
	private static RuntimeSystem Instance = new RuntimeSystem();
	private RuntimeSystem() {}
	
	
	private static List<String> listOfLines(String str) {

		List<String> lines = new ArrayList<>();
		Scanner scanner = new Scanner(str);

		while (scanner.hasNextLine()) {

			lines.add(scanner.nextLine());
		}

		scanner.close();
		return lines;
	}

	private static String reduceSpace(String str) {

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
	
	public static void runCode(String str) {

		List<String> lines = listOfLines(str);

		for (String line : lines) {

			String statement = reduceSpace(line);
              
			if (isLengthGreaterThanZero(statement)) {
				if (! Parser.checkUserInput(statement)) {
					break;
				}
			}

		}
		
	}

}
