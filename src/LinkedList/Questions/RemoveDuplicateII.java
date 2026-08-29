package LinkedList.Questions;

import LinkedList.Node;

public class RemoveDuplicateII {

    public Node deleteDuplicates(Node head) {
       Node dumb = new Node(0, head);
       Node pre = dumb;
       Node cur = head;

       while(cur != null){
           if(cur.next != null && cur.data == cur.next.data){
               while(cur.next != null && cur.data == cur.next.data){
                   cur = cur.next;
               }
               pre.next = cur.next;

           }
           else{
               pre = pre.next;
           }
           cur = cur.next;
       }

       return dumb.next;
    }
}