package TestCodeFiles.EditorTests.Test2;

import NAR.HighLevelFunc;
import java.util.ArrayList;

// Dummy singleton parser class used to test the editor
public class Parser 
{
	private static Parser Instance = new Parser();
	
	private ArrayList<HighLevelFunc> constructs;

	private Parser() 
	{
		constructs = new ArrayList<HighLevelFunc>();
	}
	
	public static void Initialize() 
	{
		Instance.constructs.add(new HLFObj1());
		Instance.constructs.add(new HLFObj2());
	}
	
	public static ArrayList<HighLevelFunc> getFuncList() { return Instance.constructs; }
}
