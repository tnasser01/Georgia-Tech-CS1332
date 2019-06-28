package Graphs;

import java.util.*;

class Solution {

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    //Queue of Nodes:  Most Recently Used a->b->c->d Least Recently Used
    public Solution(int capacity) {
        map = new HashMap<Integer, Node>();
        head = null;
        tail = null;
        this.capacity = capacity;
    }

    public int get(int key) {

        Node getNode = map.get(key);
        if(getNode == null) {
            return -1;
        }

        //Entry Exists
        //Entry is at front - no need to move
        if(map.size() == 1 || getNode.prev == null){
            return getNode.val;
        }

        //Entry is at end
        if(getNode.next == null) {
            tail = getNode.prev;
            getNode.prev.next = null;
        } else {
            //Entry is in middle
            getNode.prev.next = getNode.next;
            getNode.next.prev = getNode.prev;
        }

        getNode.next = head;
        getNode.prev = null;
        head.prev = getNode;
        head = getNode;

        return getNode.val;

    }

    public void put(int key, int value) {

        //If LRU cache is empty
        if(map.size() == 0) {
            Node putNode = new Node(key, value);
            map.put(key, putNode);
            head = putNode;
            tail = putNode;
            //If Entry is Not in LRU Cache
        } else if(map.get(key)==null) {
            //Evict Least Recently Used Node
            if(map.size() == capacity) {
                map.remove(tail.key);

                if(head == tail) {
                    head = null;
                    tail = null;
                } else {
                    tail = tail.prev;
                    tail.next = null;
                }
            }
            Node putNode = new Node(key, value);
            map.put(key, putNode);
            putNode.next = head;
            if(head!= null)
                head.prev = putNode;
            head = putNode;
            if(map.size() == 1) {
                tail = head;
            }
            //Entry is Already in LRU Cache
        } else {
            //Node is only entry or at head
            if(map.size()==1 || head.key == key) {
                Node node = map.get(key);
                node.val = value;
                //Node is at tail
            } else if(tail.key == key) {
                Node node = map.get(key);
                tail = tail.prev;
                node.prev.next = node.next;
                node.val = value;
                node.next = head;
                if(head!= null)
                    head.prev = node;
                node.prev = null;
                head = node;
                //Node is in middle
            } else {
                Node node = map.get(key);
                node.prev.next= node.next;
                node.next.prev = node.prev;
                node.val = value;
                node.next = head;
                if(head!= null)
                    head.prev = node;
                node.prev = null;
                head = node;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution(2);
        sol.put(2, 1);
        sol.put(1,1);
        sol.put(2,3);
        sol.put(4,1);
        System.out.println(sol.get(1));
        System.out.println(sol.get(2));

    }
}

class Node {
    Node next;
    Node prev;
    Integer key;
    Integer val;

    public Node(int key, int val) {
        this.next = null;
        this.prev = null;
        this.key = key;
        this.val = val;
    }

}







