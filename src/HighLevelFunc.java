
// abstract class representing a high level construct
public abstract class HighLevelFunc 
{
	private String name;
	
	public HighLevelFunc(String name) 
	{
		if (name == null) 
		{
			throw new NullPointerException("HighLevelFunc name cannot be null");
		}
		this.name = name;
	}
	
	public boolean isCalled(String statement)
	{
		return name.equalsIgnoreCase(statement.split(" ")[0]);
	}
	
	public abstract boolean isCorrectSyntax(String statement);
	public abstract void setArgs(String statement);
	public abstract void execute();
	public abstract String getHelpInformation();
}
