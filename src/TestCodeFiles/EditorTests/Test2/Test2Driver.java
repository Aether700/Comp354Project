package TestCodeFiles.EditorTests.Test2;

import NAR.Editor;

public class Test2Driver 
{
	public static void main(String[] args) 
	{
		Parser.Initialize(); //comment and uncomment this line to test with or without the HighLevelFunction objects in the parser
		Editor.run();
	}
}
