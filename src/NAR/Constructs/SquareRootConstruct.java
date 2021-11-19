package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SquareRootConstruct extends HighLevelFunc {
    private int op1, result;

    public SquareRootConstruct() {
        super("square root");
    }
    
    public boolean isCalled(String statement) 
    {
    	String[] splitStrArr = statement.split(" ");
    	return splitStrArr[0].equalsIgnoreCase("square") 
    			&& splitStrArr[0].equalsIgnoreCase("root");
    }
    
    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^square root of \\d+$")) {
            return true;
            //check syntax of the construct to execute when the condition is true
        }
        //^\s+add\s+\d+\s+to\s+\d+\s*$
        //^add \d+ to \d+$
        return false;
    }

    @Override
    public void setArgs(String statement) {
        String[] components = splitIntoComponents(statement.toLowerCase());
        //TODO make sure components are not variables or parseInt will crash.
        op1 = Integer.parseInt(components[0]);


    }

    @Override
    public void execute() {

        Editor.printToConsole((Math.sqrt(op1)));
    }


    public static void main(String args[]) {
        String statement = "square root of 9";
        Editor.run();
        Parser.checkUserInput(statement);
    }


    @Override
    public String getHelpInformation() {
        return null;
    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];

        int first = str.indexOf("square root of ");
        components[0] = str.substring(first + 15).trim(); //retrieve condition

        return components;
    }

    public int getResult() {
        return result;
    }


}
