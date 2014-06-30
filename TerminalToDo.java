import java.io.*;
import java.util.Scanner;

class TerminalToDo {
  File file = new File("/home/bigb/Programming/Java/TerminalToDo/list.txt");
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
      	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
      	out.println(item);
        out.close();
      }
      catch(IOException e){
	e.printStackTrace();
      }
    }
  }

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
    TerminalToDo term = new TerminalToDo();
    Scanner scn = new Scanner(System.in);
    
    System.out.println("TerminalToDo: Commandline based to-do application(v 0.0.1)");
    System.out.println("Type 'help' to list all commands");

    while(true){
      System.out.print("> ");
      if(scn.nextLine().equals("add")){
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

