package LinkedList;


//Kth Node From end
//Middle Node
//Reverse LinkedList
//Detect Cycle // fast and slow

import java.util.*;

public class LinkedMain {
    public static void main(String[] args) {
        ListImplementation list = new ListImplementation();
        LinkedList<Integer> ls = new LinkedList<>();


        list.addFirst(45);
        list.addLast(8);
        list.addFirst(7);
        list.addLast(9);
        list.addLast(10);

        list.printList();
    }
}