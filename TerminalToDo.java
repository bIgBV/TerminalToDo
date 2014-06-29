import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

class TerminalToDo {
  private static final String FILE_NAME = "TODO_LIST.txt";
  String item; 

  TerminalToDo(){
    this.item = "";
  }

  public void addItem(String item){
    if (item == ""){
      System.out.println("Please enter your list item");
    }
    else{
      try{
      	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)));
      	out.println(item);
        out.close();      
	System.out.println("New item added to the list.");
      }
      catch(IOException e){
	e.printStackTrace();
      }
    }
  }

  public void listItems(){
    try{
      Scanner scn = new Scanner(new FileReader(FILE_NAME));
      while(scn.hasNext()){
        System.out.println(scn.next());
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String args[]){
    TerminalToDo term = new TerminalToDo();
    Scanner scn = new Scanner(System.in);
    
    System.out.println("TerminalToDo: Commandline based to-do application(v 0.0.1)");
    System.out.println("Type 'help' to list all commands");

    while(true){
      System.out.print("> ");
      if(scn.nextLine() == "add"){
	System.out.println("New item: ");
	String text = scn.nextLine();
	term.addItem(text);		
      }
      else if(scn.nextLine() == "show"){
	System.out.println(">Listing all present items:");
	term.listItems();
      }
      else{
	break;
      }
    }    
  }
}

