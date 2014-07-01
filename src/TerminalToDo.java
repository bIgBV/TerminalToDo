import java.io.*;
import java.util.*;

/**
 * Main class for the application. 
 *
 * @version 0.1.1
 */
public class TerminalToDo {

  // This variable stores the location for the list file.
  public static final String FILE_NAME = "../res/list.txt"; 

  LinkedList<String> todoList = new LinkedList<String>();
  String item; 

  /**
   * Default constructor initializes an empty item and adds the items of 
   * the specified list to a {@link LinkedList } to perform operations on.
   */
  TerminalToDo(){
    this.item = "";
    System.out.println(">Loading your list items...");

    File file = new File(TerminalToDo.FILE_NAME);
    LinkedList<String> list = new LinkedList<String>();
    try{
      BufferedReader read = new BufferedReader( new FileReader(TerminalToDo.FILE_NAME));

      String line = null;
      while((line = read.readLine()) != null){
        list.add(line);
      }

      this.todoList = list;
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  /**
   * Adds a new item to the exsisting to-do list, uses the <code>println()</code>
   * method of the {@link PrintWriter} class. Uses a {@link BufferedReader} to maintain 
   * a buffer for the input from the user.
   *
   * @param item a String containing the user input.
   * @param file a file object containing the file to be written to.
   * @throws IOException if an inpor or an output exception occured
   */
  public void addItem(String item, File file, TerminalToDo term){
    if (item == ""){
      System.out.println("Please enter your list item");
    }
    else{
      try{
      	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      	out.println(item);
        out.close();
	term.todoList.add(item);
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
   * @param file a file object containing the file to read from.
   * @throws IOException if an input or an output exception occured
   */ 
  public void listItems(TerminalToDo term){

    // This uses a generic collection to avoid a ClassCastException when extracting list 
    // items.
    Iterator<String> listIterator = term.todoList.iterator();
    int itemNumer = 1;
    while(listIterator.hasNext()){
      System.out.format("%5d%s%s%s",itemNumer , ": " , listIterator.next(),"\n");
      itemNumer++;
    }   
  }
  
  /**
   * Deletes an item from the list and updates the file. It deletes the item from the list
   * using the <code>remove(int index)</code> method of {@link LinkedList} and then 
   * iterates over the list and overwrites the file with the new list.
   *
   * @param term the class object containing the list.
   * @param index the index of the list item to be removed.
   * @throws IOException if an input or output exception occurs.
   */
  public String deleteItem(TerminalToDo term, int index){
    String removedItem = "";
    if (index < 0){
      removedItem = "Please provide the proper list item number.";
    }
    else{
      try{
         removedItem = term.todoList.remove(index);
	 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(term.FILE_NAME)));
	 Iterator<String> listIterator = term.todoList.iterator();
	 while(listIterator.hasNext()){
           out.println(listIterator.next());
        }
	out.close();
      }
      catch(IOException e){
	e.printStackTrace();
      }
    }
    return removedItem;
  }

  public static void main(String args[]){

    System.out.println("TerminalToDo: Commandline based to-do application(v 0.1.1)");
    System.out.println("Type 'help' to list all commands");

    /*
     * Creating the TerminalToDo objects and the Scanner objects. These objects 
     * are used for all opperations.
     */
    TerminalToDo term = new TerminalToDo();
    Scanner scn = new Scanner(System.in);
    File file = new File(term.FILE_NAME);
         
    /*
     * Creates an infinite loop for the user to input commands. Uses the 'scn' object to 
     * scan user input and check for the right commands. If the correct command is found
     * the appropriate method is called. Otherwise the loop continues.
     */
    while(true){
      System.out.print("> ");

      /*
       * Storing the command entered by the user in a variable 'input'. Before it is assigned
       * to it, all the whitespaces are stripped from the input using regex replaceAll() 
       * method.
       */
      String input = scn.nextLine().replaceAll("\\s+","");

      // This part of the main method cheks for user input.
      if(input.equals("help")){
	System.out.println("Welcome to TerminalToDo, the command line to-do list.\nYou have three options:\n");
	System.out.format("%10s%s","add:", " This command let's you add a new item to the list.\n");
	System.out.format("%10s%s","show:"," This command shows you all the items present in your list.\n");
	System.out.format("%10s%s","delete:"," This command asks for an index and deletes the particular item at that index.\n");
	System.out.format("%10s%s","exit:"," This command exits the application.\n");
      }
      else if(input.equals("add")){
	System.out.print("New item: ");
	String text = scn.nextLine();
	term.addItem(text, file, term);
	System.out.println("New item has been added...");
	term.listItems(term);	
      }

      else if(input.equals("show")){
	System.out.println("Listing all present items:");
	term.listItems(term);
      }
      else if(input.equals("delete")){
	System.out.println("Listing present items in list...");
	term.listItems(term);
	System.out.println("Please specify which item you want to remove: ");
	int index = Integer.parseInt(scn.next()) - 1;
	String removedItem = term.deleteItem(term, index);
	System.out.println("The removed item is: ");
	System.out.println(removedItem);
	System.out.println("The new list items are: ");
	term.listItems(term);
      }
      else if(input.equals("exit")){
	System.out.println("Exiting...")
	break;
      }
      else{
	System.out.println("> Please enter a command.");
      }
    }    
  }
}
