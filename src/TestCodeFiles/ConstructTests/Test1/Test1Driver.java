package TestCodeFiles.ConstructTests.Test1;

import NAR.Editor;
import NAR.Parser;

public class Test1Driver {
    public static void main(String args[])
    {
        String statement = "add 1y to x";
        Editor.run();
        Parser.defineVar("x",500.0);
        Parser.defineVar("1y",-69);
        Parser.checkUserInput(statement);
    }
}
