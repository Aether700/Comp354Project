package NAR;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

import java.util.List;

//this line includes the Parser used for test 2 and will be removed from the final build
import TestCodeFiles.EditorTests.Test2.Parser; 

public class Editor 
{
	private static Editor Instance = new Editor();
	
	private JFrame window;
	private JTextArea codingArea;
	private JTextArea outputConsole;
	
	//make constructor private so no one can create an object of this class
	private Editor() 
	{
		//font size for both the output console and the coding area
		int fontSize = 24;
		
		// Create and set up window object
		window = new JFrame("NAR Editor");
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH); // make the window maximized on launch
		
		//create the layout using the GridLayout manager
		JPanel layout = new JPanel(new GridLayout(1, 2));
		
		//setup left column
		JPanel leftPanel = new JPanel(new BorderLayout());
		
		JButton runButton = new JButton("Run");
		
		//hook up the run button to an anonymous class which will handle the button press
		runButton.addActionListener(new ActionListener() 
		{
			//exact implementation of this function will need to be changed
			public void actionPerformed(ActionEvent e) {
				Editor.clearConsole();
				String code = Editor.getCodeStr();
				Editor.printToConsole(code);
				System.out.println("Run button pressed: " + code);
			}
		});
		
		codingArea = new JTextArea();
		//set font size of the coding area
		codingArea.setFont(new Font(codingArea.getFont().getName(), Font.PLAIN, fontSize));
		
		//put coding area in a scroll pane
		JScrollPane codeScrollPane = new JScrollPane(codingArea);	
		
		//add coding area and run button to the left column/panel
		leftPanel.add(codeScrollPane, BorderLayout.CENTER);
		leftPanel.add(runButton, BorderLayout.SOUTH);
		
		//set up right column
		JPanel rightPanel = new JPanel(new BorderLayout());
		
		JButton helpButton = new JButton("Help");
		
		//hook up the help button to an anonymous class which will handle the button press
		helpButton.addActionListener(new ActionListener() 
		{
			//exact implementation of this function will need to be changed
			public void actionPerformed(ActionEvent e) {
				Editor.clearConsole();
				Editor.displayHelpInformation();
			}
		});
		
		outputConsole = new JTextArea();
		//change the font size of the output console
		outputConsole.setFont(new Font(outputConsole.getFont().getName(), Font.PLAIN, fontSize));
		// make the output console read only
		outputConsole.setEditable(false); 
	
		//put console into a scroll pane
		JScrollPane consoleScrollPane = new JScrollPane(outputConsole);	
		
		rightPanel.add(consoleScrollPane, BorderLayout.CENTER);
		rightPanel.add(helpButton, BorderLayout.SOUTH);
		
		//add both columns to the layout panel
		layout.add(leftPanel);
		layout.add(rightPanel);		
		
		//add the panel to the window
		window.add(layout);
	}
	
	public static void run() 
	{
		Instance.window.setVisible(true);
	}
	
	public static void printToConsole(Object o) 
	{
		String currText = Instance.outputConsole.getText();
		if (currText.equals("")) 
		{
			Instance.outputConsole.setText(o.toString());
		}
		else 
		{
			Instance.outputConsole.setText(Instance.outputConsole.getText() + "\n" + o.toString());
		}
	}
	
	private static void clearConsole() 
	{
		Instance.outputConsole.setText("");
	}
	
	private static void displayHelpInformation() 
	{
		List<HighLevelFunc> constructs = Parser.getFuncList();
		
		for (HighLevelFunc func : constructs) 
		{
			printToConsole(func.getHelpInformation());
		}
	}
	
	private static String getCodeStr() 
	{
		try 
		{
			return Instance.codingArea.getDocument().getText(0, Instance.codingArea.getDocument().getLength());
		}
		catch (BadLocationException badLocation) 
		{
			//should never happen since we always fetch the length of 
			//the document to retrieve it's content
			return ""; 
		}
	}
}
