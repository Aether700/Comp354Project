package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SubtractConstruct extends HighLevelFunc {
    private Double op1, op2, result;

    public SubtractConstruct() {
        super("sub");
    }

    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^sub (-?\\d+|\\w+|\\d+.\\d+) from (-?\\d+|\\w+|\\d+.\\d+)$")) {
            return true;
            //check syntax of the construct to execute when the condition is true
        }
        //^\s+add\s+\d+\s+to\s+\d+\s*$
        //^add \d+ to \d+$
        return false;
    }
    public Double[] getVariables(String arg1,String arg2)
    {

        Double vals[]=new Double[2];

        if(Parser.isVarDefined(arg1))
        {
            vals[0]=Parser.getVarValue(arg1);
        }
        else
        {
            vals[0]=null;
        }
        if (Parser.isVarDefined(arg2)) {
            vals[1]=Parser.getVarValue(arg2);
        }
        else {
            vals[1]=null;
        }
        return vals;
    }
    @Override
    public void setArgs(String statement) {
        String[] components = splitIntoComponents(statement.toLowerCase());
        //TODO make sure components are not variables or parseInt will crash.
        //Double[] vars=getVariables(components[0].substring(1),components[1].substring(1));
        String v1,v2;
        try {
            if (components[0].charAt(0) == '-') {
                v1 = components[0].substring(1);
            } else {
                v1 = components[0];
            }
            if (components[1].charAt(0) == '-') {
                v2 = components[1].substring(1);
            } else {
                v2 = components[1];
            }
            Double[] vars = getVariables(v1, v2);

            if (vars[0] != null) {
                op1 = vars[0];
                if (components[0].charAt(0) == '-') {
                    op1 = -1 * op1;
                }
            } else {
                if (components[0].charAt(0) == '-') {
                    op1 = -1 * Double.parseDouble(components[0].substring(1));
                } else {
                    op1 = Double.parseDouble(components[0]);
                }
            }
            if (vars[1] != null) {
                op2 = vars[1];
                if (components[1].charAt(0) == '-') {
                    op2 = -1 * op2;
                }
            } else {
                if (components[1].charAt(0) == '-') {
                    op2 = -1 * Double.parseDouble(components[1].substring(1));
                } else {
                    op2 = Double.parseDouble(components[1]);
                }
            }
        }
        catch (NumberFormatException n)
        {
            Editor.printToConsole("Error in operands (Undefined variable or incorrect format).");
        }

    }

    @Override
    public void execute() {

        Editor.printToConsole((op2 - op1));
    }


    public static void main(String args[]) {
        String statement = "subtract 10000 from -9999";
        Editor.run();
        Parser.checkUserInput(statement);
    }


    @Override
    public String getHelpInformation() {
        return null;
    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];


        int first = str.indexOf("subtract ");
        int sec = str.indexOf("from ");
        components[0] = str.substring(first + 9, sec).trim(); //retrieve condition
        int third = str.length();
        components[1] = str.substring(sec + 5, third).trim();

        int fourth = str.length();


        return components;
    }

    public double getResult() {
        return result;
    }


}
