package TestCodeFiles.RuntimeSystem.Test2;



public class Test2Driver {
	public static void main(String[] args) {
		
		String st = "add 78 to 9 \n" + "         \n" + "                      \n"
				+ " add  99   to  5                \n" + " add               7 to 4                         \n"
				+ "                       wrong\n" + "\n" + "\n"
				+ "add   7 to                                        78j\n" + "         \n";

		RuntimeSystem.runCode(st);

	}
}
