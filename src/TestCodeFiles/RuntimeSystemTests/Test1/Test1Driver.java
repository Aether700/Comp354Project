package TestCodeFiles.RuntimeSystem.Test1;

//In runTime test1 we assume all the input commands have correct syntax

public class Test2Driver {
	public static void main(String[] args) {
		
		String st = "add 78 to 9 \n" + "                      \n" + "                      \n"
				+ " add  99   to  5                \n" + " add               7 to 4                         \n"
				+ "                               \n" + "\n" + "\n"
				+ "add   7 to                                        78j\n" + "         \n";

		RuntimeSystem.runCode(st);

	}
}
