package NAR.Constructs;

import NAR.Editor;
import NAR.HighLevelFunc;
import NAR.Parser;

public class DivideConstruct extends HighLevelFunc {
    private double op1, op2, result;

    public DivideConstruct() {
        super("divide");
    }

    @Override
    public boolean isCorrectSyntax(String statement) {
        String lowerCase = statement.toLowerCase();
        if (lowerCase.matches("^divide (-?\\d+|\\w+|\\d+.\\d+) by (-?\\d+|\\w+|\\d+.\\d+)$")) {
            return true;

        }

        return false;
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
            if(op2==0){
                System.out.println("Divide by Zero Error: Right operand was 0.");
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
        if(op2==0){
            Editor.printToConsole("Undefined: Div by 0");
        }
        else{
            Editor.printToConsole(op1 / op2);
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
    public String getHelpInformation() {
        return "Divide:\n"
                + "   Syntax: Divide <optional \"-\"><variable/number> by <optional \"-\"><variable/number> "

                + "\n   Divides operand 1(left operand) by operand 2(right operand) and prints the result "
                +"\nNote: Dividing by zero will give an error so operand 2 cannot be 0"
                + "\n   Example: Divide 3.5 by 0.5\n"
                + " Divide x by y\n";

    }

    private String[] splitIntoComponents(String str) {
        String[] components = new String[2];


        int first = str.indexOf("divide ");
        int sec = str.indexOf("by ");
        components[0] = str.substring(first + 7, sec).trim();
        int third = str.length();
        components[1] = str.substring(sec + 3, third).trim();
        int fourth = str.length();



        return components;
    }

    public double getResult() {
        return result;
    }


}
