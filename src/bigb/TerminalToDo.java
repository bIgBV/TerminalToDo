package bigb;

import java.util.*;


/**
 * Main class for the application.
 *
 * @version 0.1.1
 */
public class TerminalToDo {

    // This variable stores the location for the list file.
    public static final String FILE_NAME = "src/bigb/list.txt";

    String item;
    int urgency;

    TerminalToDo(String item){
        this.item = item;
    }

    TerminalToDo(String item, int urgency){
        this.item = item;
        this.urgency = urgency;
    }

    public static void main(String args[]){

        System.out.println("TerminalToDo: Commandline based to-do application(v 0.2.1)");
        System.out.println("Type 'help' to list all commands");

        ListHandler handler = new ListHandler();


        // Loading list into memory
        LinkedList<TerminalToDo> LIST = handler.listLoader(FILE_NAME);

        // Setting up scanner to take user input.
        Scanner scn = new Scanner(System.in);

        while(true){
            System.out.print("> ");
            String input = scn.nextLine();
            //String input = scn.nextLine().replaceAll("\\s+","");

            // This part of the main method checks for user input.
            if(input.equals("help")){
                System.out.println("Welcome to bigb.TerminalToDo.bigb.TerminalToDo, the command line to-do list.\nYou have three options:\n");
                System.out.format("%10s%s","add:", " This command let's you add a new item to the list.\n");
                System.out.format("%10s%s","show:"," This command shows you all the items present in your list.\n");
                System.out.format("%10s%s", "delete:", " This command asks for an index and deletes the particular item at that index.\n");
                System.out.format("%10s%s", "exit:", " This command exits the application.\n");
            }
            else if(input.equals("add")){
                System.out.print("New item: ");
                String text = scn.nextLine();
                if(text.equals("")){
                    System.out.print("> Please enter a new list item");
                }
                else {
                     if(text.contains(":")){
                        int urgencyIndex = text.indexOf(":");
                        int urgency = Integer.parseInt(text.substring(urgencyIndex, text.length()).replaceAll("\\s+", ""));
                        handler.addItem(text, urgency, LIST);
                    }
                    else {
                         handler.addItem(text, LIST);
                         System.out.println("New item has been added...");
                         handler.listItems(LIST);
                     }
                }
            }
            else if(input.equals("show")){
                System.out.println("Listing all present items:");
                handler.listItems(LIST);
            }
            else if(input.equals("delete")){
                System.out.println("Listing present items in list...");
                handler.listItems(LIST);
                System.out.println("Please specify which item you want to remove: ");
                int index = Integer.parseInt(scn.next()) - 1;
                if(index > LIST.size() || index <= 0){
                    System.out.println("Please provide the proper list item number.");
                }
                else {
                    String removedItem = handler.deleteItem(index, LIST);
                    System.out.println("The removed item is: ");
                    System.out.println(removedItem);
                    System.out.println("The new list items are: ");
                    handler.listItems(LIST);
                }
            }
            else if(input.equals("exit")){
                System.out.println("Exiting...");
                handler.exitProgram(FILE_NAME, LIST);
                break;
            }
            else{
                System.out.println("> Please enter a command.");
            }
        }
    }
}
