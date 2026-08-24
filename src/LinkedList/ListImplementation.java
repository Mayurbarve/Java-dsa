package LinkedList;

public class ListImplementation {

    Node head;
    //static Node tail;

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }


    void addLast(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        Node ptr = head;
        while (ptr.next != null){
            ptr = ptr.next;
        }

        ptr.next = newNode;
    }

    void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    void printList(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }

        Node ptr = head;
        System.out.print(ptr.data + " -> ");

        while(ptr.next != null){
            ptr = ptr.next;
            System.out.print(ptr.data + " -> ");
        }

        System.out.println("null");
    }

    void removeFirst(){
        if(head == null){
            System.out.println("List is empty");
        }
        else{
            head = head.next;
        }

    }

    void removeLast(){
        if(head == null){
            System.out.println("List is empty");
        }

        Node ptr = head;
        while(ptr.next.next != null){
            ptr = ptr.next;
        }

        ptr.next = null;

    }
}
