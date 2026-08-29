package LinkedList;


//Kth Node From end
//Middle Node
//Reverse LinkedList
//Detect Cycle // fast and slow

import LinkedList.Questions.RemoveDuplicateII;

import java.util.*;

public class LinkedMain {
    public static void main(String[] args) {
        ListImplementation list = new ListImplementation();

        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        list.printList();

        Node head = list.head;

        //Remove Duplicate II
        RemoveDuplicateII removeDuplicate = new RemoveDuplicateII();
        Node ans = removeDuplicate.deleteDuplicates(head);

        while(ans!=null){
            System.out.print(ans.data+" -> ");
            ans = ans.next;
        }
        System.out.println("null");



        
    }
}