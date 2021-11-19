package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class MultiplyConstruct extends HighLevelFunc {
    private int op1, op2,result;
    public MultiplyConstruct() {
        super("mult");
    }

    @Override
    public boolean isCorrectSyntax(String statement)
    {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^mult -?\\d+ with -?\\d+$") )

        {
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
        op1=Integer.parseInt(components[0]);

        op2=Integer.parseInt(components[1]);

    }

    @Override
    public void execute() {

        Editor.printToConsole((op2*op1));
    }




    public static void main(String args[])
    {
        String statement="mult -2 with -9";
        Editor.run();
        Parser.checkUserInput(statement);
    }


    @Override
    public String getHelpInformation() {
        return null;
    }
    private String[] splitIntoComponents(String str)
    {
        String[] components = new String[2];




        int first = str.indexOf("mult ");
        int sec = str.indexOf("with ");
        components[0] = str.substring(first+5, sec).trim(); //retrieve condition
        int third  = str.length();
        components[1] = str.substring(sec+5, third).trim();

        int fourth = str.length();







        return components;
    }

    public int getResult() {
        return result;
    }


}
