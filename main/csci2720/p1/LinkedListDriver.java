package csci2720.p1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The main driver class to test SortedLinkedList class.
 */
public class LinkedListDriver {

    /*
     * Constant commands
     */
    static final char INSERT = 'i';
    static final char DELETE = 'd';
    static final char SEARCH = 's';
    static final char GET_NEXT = 'n';
    static final char RESET_LIST = 'r';
    static final char DEL_ALT = 'a';
    static final char MERGE = 'm';
    static final char INTERSECTION = 't';
    static final char PRINT_ALL = 'p';
    static final char LENGTH = 'l';
    static final char QUIT = 'q';

    /**
     * The main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Commands:");
        System.out.println("(i) - Insert value");
        System.out.println("(d) - Delete value");
        System.out.println("(s) - Search value");
        System.out.println("(n) - Print next iterator value");
        System.out.println("(r) - Reset iterator");
        System.out.println("(a) - Delete alternate nodes");
        System.out.println("(m) - Merge lists");
        System.out.println("(t) - Find intersection");
        System.out.println("(p) - Print list");
        System.out.println("(l) - Print length");
        System.out.println("(q) - Quit program");
        SortedLinkedList list = new SortedLinkedList();
        SortedLinkedList tempList = new SortedLinkedList();
        Scanner stdIn = new Scanner(System.in);
        try {
            File file = new File(args[0]);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                NodeType node = new NodeType();
                node.info = new ItemType(Integer.parseInt(fileReader.next()));
                try {
                    list.insertItem(node.info);
                } catch (Exception e) {} // catch
            } // while
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
            System.exit(1);
        } // catch
        System.out.print("Enter a command: ");
        String command = stdIn.nextLine();
        ItemType itemtype = new ItemType(0);
        if (command.equals("" + QUIT)) System.out.println("Exiting the program...");
        while (!command.equals("" + QUIT)) {
            boolean valid = true;
            switch (command) {
            case "" + PRINT_ALL: // print list
                System.out.print("The list is: ");
                list.printList();
                break;
            case "" + INSERT: // insert item
                System.out.print("Enter a number to insert: ");
                int item = Integer.parseInt(stdIn.nextLine());
                itemtype = new ItemType(item);
                System.out.print("Original list: ");
                list.printList();
                try {
                    list.insertItem(itemtype);
                } catch (Exception e) {
                    System.out.println("Sorry. You cannot insert the duplicate item");
                } // catch
                System.out.print("New list: ");
                list.printList();
                break;
            case "" + DELETE: // delete item
                System.out.print("Enter a number to delete: ");
                itemtype = new ItemType(Integer.parseInt(stdIn.nextLine()));
                if (list.getLength() == 0) {
                    System.out.println("You cannot delete from an empty list");
                } else {
                    System.out.print("Original list: ");
                    list.printList();
                    list.deleteItem(itemtype);
                    System.out.print("New list: ");
                    list.printList();
                }
                break;
            case "" + LENGTH: // print length of list
                System.out.print("The length of the list is " + list.getLength() + "\n");
                break;
            case "" + SEARCH: // search item
                System.out.print("Enter a number to search: ");
                itemtype = new ItemType(Integer.parseInt(stdIn.nextLine()));
                System.out.print("Original list: ");
                list.printList();
                if (list.getLength() == 0) {
                    System.out.println("The list is empty");
                } else if (list.searchItem(itemtype) == -1) {
                    System.out.println("Item is not in the list");
                } else {
                    System.out.println("The item is present at index " + list.searchItem(itemtype));
                } // else
                break;
            case "" + GET_NEXT: // get next item
                list.getNextItem();
                break;
            case "" + RESET_LIST: // reset iterator
                list.resetList();
                System.out.println("Iterator is reset");
                break;
            case "" + DEL_ALT: // delete alternate nodes
                System.out.print("Original list: ");
                list.printList();
                tempList = list.deleteAltNodes(list);
                System.out.print("Modified list: ");
                tempList.printList();
                break;
            case "" + MERGE: // merge list
                System.out.print("Enter the length of the new list: ");
                int length = stdIn.nextInt();
                System.out.print("Enter the numbers: ");
                SortedLinkedList list2 = new SortedLinkedList();
                for (int i = 0; i < length; i++) {
                    try {
                        list2.insertItem(new ItemType(stdIn.nextInt()));
                    } catch (Exception e ) {}
                } // for
                System.out.print("The list 1: ");
                list.printList();
                System.out.print("The list 2: ");
                list2.printList();
                list2 = list.mergeList(list, list2);
                System.out.print("Merged list: ");
                list2.printList();
                stdIn.nextLine();
                break;
            case "" + INTERSECTION: // intersection
                System.out.print("Enter the length of the new list: ");
                int listLength = stdIn.nextInt();
                System.out.print("Enter the numbers: ");
                tempList = new SortedLinkedList();
                for (int i = 0; i < listLength; i++) {
                    try {
                        tempList.insertItem(new ItemType(stdIn.nextInt()));
                    } catch (Exception e ) {}
                } // for
                System.out.print("The list 1: ");
                list.printList();
                System.out.print("The list 2: ");
                tempList.printList();
                System.out.print("Intersection of lists: ");
                SortedLinkedList intersectedList = list.intersection(list, tempList);
                intersectedList.printList();
                stdIn.nextLine();
                break;
            default: // invalid command
                valid = false;
                break;
            } //switch
            if (valid) System.out.print("Enter a command: ");
            else System.out.print("Invalid command try again: ");
            command = stdIn.nextLine();
            if (command.equals("" + QUIT)) System.out.println("Exiting the program...");
        } // while
    } // main
} // class
