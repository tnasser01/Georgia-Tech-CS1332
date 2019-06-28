package DataStructures.linear.LinkedList;

public class InsertIntoSortedLinkedList {

    static Node sortedInsert(Node head, int data) {

        Node newNode = new Node();
        newNode.data = data;

        //add data to empty list
        if(head == null){
            head = newNode;
            return newNode;
        }

        //list : 1 -> 2 -> 4 -> null
        //data: 3

        //if head not empty, cycle through list
        Node curr = head;
        while(curr.next != null && curr.next.data < data ){
                curr = curr.next;
        }

        //add to front
        if(curr == head && curr.data > data){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        //add to back
        else if(curr.next == null &&  curr.data < data){
            curr.next = newNode;
            newNode.prev = curr;
            return head;
        } else {
            //add in middle
            newNode.next = curr.next;
            curr.next.prev = newNode;
            curr.next = newNode;
            newNode.prev = curr;

        }
        return head;

    }

    public static void main(String[] args){
        Node head = null;
        head = sortedInsert(head, 2 );
        head = sortedInsert(head, 1);
        head = sortedInsert(head, 4 );
        head = sortedInsert(head, 3);
        Node curr = head;
        while(curr !=null){
            System.out.println(curr.data);
            curr = curr.next;
        }

    }

    private static class Node {
        int data;
        Node next;
        Node prev;

        public Node(){}
        public Node(int data){
            this.data = data;
        }

        public Node createLinkedList(){
            Node two = new Node(2);
            Node one = new Node(1);
            Node four = new Node(4);
            Node three = new Node(3);

            Node head = two;
            two.next = one;
            one.next = four;
            four.next = three;
            return head;
        }

    }

}
