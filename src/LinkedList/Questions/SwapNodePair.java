package LinkedList.Questions;

import LinkedList.Node;

public class SwapNodePair {
        public Node swapPairs(Node head) {
            Node dummy = new Node(0, head);
            Node prv = dummy;

            //   prev -> 1 -> 2 -> 3 -> 4 -> null
            //     p          p
            //   prev -> 2 -> 1 -> 4 -> 3 -> null
            //           f    s    f    s

            while(prv.next != null && prv.next.next != null){
                Node first = prv.next;
                Node second = prv.next.next;

                prv.next = second;
                first.next = second.next;
                second.next = first;

                prv = first;
            }

            return dummy.next;
        }

        //Using Recursion
        public Node swapUsingRecursion(Node head) {
            if (head == null || head.next == null) return head;

            Node first = head;
            Node second = head.next;

            // Recursively swap the rest of the list
            first.next = swapPairs(second.next);
            second.next = first;

            // second is now the head of this swapped pair
            return second;

        }
}
