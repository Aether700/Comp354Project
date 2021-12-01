package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class SquareRootConstruct extends HighLevelFunc {
    private double op1, op2, result;

    public SquareRootConstruct() {
        super("square root");
    }
    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^square root of (\\d+|\\w+|\\d+.\\d+)$")) {
            return true;

        }
        //^\s+add\s+\d+\s+to\s+\d+\s*$
        //^add \d+ to \d+$
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

            Double[] vars = getVariables(v1, "a");

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

        }
        catch (NumberFormatException n)
        {
            System.out.println("Error in operands (Undefined variable or incorrect format).");
            System.exit(1);
        }

    }

    @Override
    public void execute() {

        Editor.printToConsole(Math.sqrt(op1));
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
    public String getHelpInformation() {
        return "Square Root:\n"
                + "   Syntax: square root of <variable/number> "

                + "\nPrints the square root of operand 1"
                +"\nNote: cannot be negative"
                + "\n   Example: square root of 9 \n"
                + "square root of x \n";

    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];


        int first = str.indexOf("square root of ");

        components[0] = str.substring(first + 15).trim();


        return components;
    }

    public double getResult() {
        return result;
    }


}
