package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class AddConstruct extends HighLevelFunc {
    private double op1, op2, result;

    public AddConstruct() {
        super("add");
    }

    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^add (-?\\d+|\\w+|\\d+.\\d+) to (-?\\d+|\\w+|\\d+.\\d+)$")) {
            return true;
            //check syntax of the construct to execute when the condition is true
        }

        return false;
    }

    @Override
    public void setArgs(String statement) {
        String[] components = splitIntoComponents(statement.toLowerCase());


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
            System.out.println("Error in operands (Undefined variable or incorrect format).");
            System.exit(1);
        }

    }
    private Double[] getVariables(String arg1,String arg2)
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
    public void execute() {

        Editor.printToConsole(op1 + op2);
    }





    @Override
    public String getHelpInformation() {
        return "Add:\n"
                + "   Syntax: add <optional \"-\"><variable/number> to <optional \"-\"><variable/number> "

                + "\n   Adds both the operands provided and prints the result "

                + "\n   Example: add 3 to 5\n"
                + "add 5 to x\n";

    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];


        int first = str.indexOf("add ");
        int sec = str.indexOf("to ");
        components[0] = str.substring(first + 4, sec).trim(); //retrieve condition
        int third = str.length();
        components[1] = str.substring(sec + 3, third).trim();

        int fourth = str.length();


        return components;
    }

    public double getResult() {
        return result;
    }


}
