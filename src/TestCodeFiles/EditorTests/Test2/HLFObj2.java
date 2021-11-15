package TestCodeFiles.EditorTests.Test2;

import NAR.HighLevelFunc;

//Dummy HighLevelFunc object used when testing the editor
public class HLFObj2 extends HighLevelFunc 
{
	public HLFObj2() { super("HLF1Obj2"); }
	
	public boolean isCorrectSyntax(String statement) { return true; }
	public void setArgs(String statement) { }
	public void execute() { }
	
	public String getHelpInformation() 
	{
		return "Help information for HLFObj2";
	}

}
