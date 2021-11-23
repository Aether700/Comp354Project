package TestCodeFiles.EditorTests.Test2;

import TestCodeFiles.EditorTests.Editor;

public class Test2Driver 
{
	public static void main(String[] args) 
	{
		//comment and uncomment the line below to test with or without the 
		//HighLevelFunction objects in the parser
		Parser.Initialize(); 
		Editor.run();
	}
}
