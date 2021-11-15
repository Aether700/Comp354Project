package TestCodeFiles.EditorTests.Test2;

import NAR.HighLevelFunc;

// Dummy HighLevelFunc object used when testing the editor 
public class HLFObj1 extends HighLevelFunc 
{
	public HLFObj1() { super("HLF1Obj1"); }
	
	public boolean isCorrectSyntax(String statement) { return true; }
	public void setArgs(String statement) { }
	public void execute() { }
	public String getHelpInformation() 
	{
		return "Help information for HLFObj1\nmultiple line support working";
	}
}
