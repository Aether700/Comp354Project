package TestCodeFiles.RuntimeSystem.Test1;

import java.util.ArrayList;
import java.util.List;
//In runTime test1 we assume all the input commands have correct syntax
public class Test2Driver {
	public static void main(String[] args) {
		
		List<String> inputs=new ArrayList<String>();  
		
		inputs.add("statement1\n"+"statement2\n"+"statement3\n"+"                      \n"+"                      \n"); //input 1
		inputs.add("                                statement1\n"+" statement2        \n"+"      \n"+"     \n"+"      statement3 \n"); //input 2
		inputs.add("       statement1\n"+"       statement2\n"+"         "+"\n"+"      statement3\n     "); //input 3
		inputs.add("                          "); //input 4
		inputs.add(""); //input 5
		
		int i=1;
		
		for (String input:inputs) {
			
		     System.out.println("output of input "+i++ +":\n");
	             RuntimeSystem.runCode(input);
		     System.out.println();
		}
	}
}
