package LinkedList;


//Kth Node From end
//Middle Node
//Reverse LinkedList
//Detect Cycle // fast and slow

import LinkedList.Questions.RemoveDuplicateII;
import LinkedList.Questions.SwapNodePair;

import java.util.*;

public class LinkedMain {
    public static void main(String[] args) {
        ListImplementation list = new ListImplementation();

        list.addFirst(7);
        list.addFirst(6);
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);


        Node head = list.head;

        System.out.println(head.data);

        //Remove Duplicate II
        /*
        RemoveDuplicateII removeDuplicate = new RemoveDuplicateII();
        Node ans = removeDuplicate.deleteDuplicates(head);
        list.printList(ans);
        */


        //Swap Node in pair
        SwapNodePair swapNodePair = new SwapNodePair();
        Node pairHead = swapNodePair.swapPairs(head);
        Node recursiveHead = swapNodePair.swapUsingRecursion(head);
        list.printList(pairHead);

        
    }
}