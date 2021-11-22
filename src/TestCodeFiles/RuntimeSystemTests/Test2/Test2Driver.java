package TestCodeFiles.RuntimeSystem.Test2;

import java.util.ArrayList;
import java.util.List;

public class Test2Driver {
	public static void main(String[] args) {
		
		List<String> inputs=new ArrayList<String>();  
		
		inputs.add("statement\n"+"statement\n"+""+"wrong\n"+"statement\n"); //input 1
		inputs.add("wrong\n"+"statement\n"+"statement\n"+"statement\n"+"statement\n"); //input 2
		inputs.add("statement\n"+"statement\n"+"wrong\n"+"wrong\n"+"statement\n"+"statement\n"); //input 3
		inputs.add(""); //input 4
		inputs.add("                                  \n"+"                 \n"); //input 5
		
		int i=1;
		
		for (String input:inputs) {
			
		System.out.println("output of input "+i++ +":\n");
		RuntimeSystem.runCode(input);
		System.out.println();
		
		}
	}
}
