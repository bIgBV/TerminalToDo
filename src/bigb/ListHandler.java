package bigb;

import java.io.*;
import java.util.LinkedList;



public class ListHandler {




    LinkedList listLoader(String FILE_NAME){
        System.out.println(">Loading your list items...");

        LinkedList<TerminalToDo> list = new LinkedList<TerminalToDo>();
        try{
            BufferedReader read = new BufferedReader( new FileReader(FILE_NAME));
            String line;
            while((line = read.readLine()) != null){
                TerminalToDo node = new TerminalToDo(line);
                list.add(node);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Adds a new item to the existing to-do list, uses the <code>println()</code>
     * method of the {@link java.io.PrintWriter} class. Uses a {@link BufferedReader} to maintain
     * a buffer for the input from the user.
     *
     * @param item a String containing the user input.
     * @param list the list to which the item needs to be added.
     */
    public void addItem(String item, LinkedList<TerminalToDo> list){
        TerminalToDo node = new TerminalToDo(item);
        list.add(node);
    }

    /**
     * Adds a new item to the beginning of a list if urgency is supplied. Will add functionality for
     * ordering items by urgency.
     *
     * @param item a String containing the user input
     * @param urgency integer which holds the urgency value
     * @param list the list to which the item needs to be added.
     */
    public void addItem(String item, int urgency,LinkedList<TerminalToDo> list){
        TerminalToDo node = new TerminalToDo(item, urgency);
        list.addFirst(node);
    }

    /**
     * Lists all the items present in the list. Takes in the input file and lists all the
     * items present in it by reading each line.
     */
    public void listItems(LinkedList<TerminalToDo> list){

        // This uses a generic collection to avoid a ClassCastException when extracting list
        // items.
        int itemNumber = 1;
        for(TerminalToDo item : list){
            System.out.format("%5d%s%s%s",itemNumber , ": " , item.item,"\n");
            itemNumber++;
        }
    }

    /**
     * Deletes an item from the list and updates the file. It deletes the item from the list
     * using the <code>remove(int index)</code> method of {@link LinkedList} and then
     * iterates over the list and overwrites the file with the new list.
     *
     * @param index the index of the list item to be removed.
     */
    public String deleteItem(int index, LinkedList<TerminalToDo> list){
        TerminalToDo node = list.remove(index);
        String removedItem = node.item;
        return removedItem;
    }

    public void exitProgram(String FILE_NAME, LinkedList<TerminalToDo> list) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)));
            for (TerminalToDo o : list) {
                out.println(o.item);
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
