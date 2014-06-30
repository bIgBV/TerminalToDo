import java.io.*;
import java.util.Scanner;
/**
 * Main class for the application. 
 *
 * @author bIgBV
 * @version 0.1.1
 */
public class TerminalToDo {
  File file = new File("/home/bigb/Programming/Java/TerminalToDo/list.txt");
  String item; 

  TerminalToDo(){
    this.item = "";
  }

  /**
   * Adds a new item to the exsisting to-do list, uses the <code>println()</code>
   * method of the {@link PrintWriter} class. Uses a {@link BufferedReader} to maintain 
   * a buffer for the input from the user.
   *
   * @author bIgBV 
   * @param item a String containing the user input.
   * @throws IOException if an inpor or an output exception occured
   */
  public void addItem(String item){
    if (item == ""){
      System.out.println("Please enter your list item");
    }
    else{
      try{
      	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      	out.println(item);
        out.close();
      }
      catch(IOException e){
	e.printStackTrace();
      }
    }
  }

  /**
   * Lists all the items present in the list. Takes in the input file and lists all the
   * items present in it by reading each line.
   *
   * @author bIgBV 
   * @throws IOException if an inpor or an output exception occured
   */ 
  public void listItems(){
    try{
     BufferedReader read = new BufferedReader( new FileReader("/home/bigb/Programming/Java/TerminalToDo/list.txt"));
     String line=null;
     while((line = read.readLine()) != null){
       System.out.println(line);
     }
   }
    catch(IOException e){
     e.printStackTrace();
    }
  }

  public static void main(String args[]){
    /*
     * Creating the TerminalToDo objects and the Scanner objects. These objects 
     * are used for all opperations.
     */
    TerminalToDo term = new TerminalToDo();
    Scanner scn = new Scanner(System.in);
    
    System.out.println("TerminalToDo: Commandline based to-do application(v 0.1.1)");
    System.out.println("Type 'help' to list all commands");
     
    /*
     * Creates an infinite loop for the user to input commands. Uses the 'scn' object to 
     * scan user input and check for the right commands. If the correct command is found
     * the appropriate method is called. Otherwise the loop continues.
     */
    while(true){
      System.out.print("> ");
      String input = scn.nextLine();
      if(input.equals("help")){
	System.out.println("Welcome to TerminalToDo, the command line to-do list.\nYou have three options:\n");
	System.out.format("%10s%s","add:", "This command let's you add a new item to the list.\n");
	System.out.format("%10s%s","show:"," This command shows you all the items present in your list.\n");
	System.out.format("%10s%s","exit:"," This command exits the application.\n");
      }
      else if(input.equals("add")){
	     System.out.println("New item: ");
	     String text = scn.nextLine();
	     term.addItem(text);		
      }
      else if(input.equals("show")){
	     System.out.println(">Listing all present items:");
	     term.listItems();
      }
      else if(input.equals("exit")){
	     break;
      }
      else{
	System.out.println("Please enter the right command.");
      }
    }    
  }
}

