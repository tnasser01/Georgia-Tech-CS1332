package DataStructures.tree.bst;

public class PostOrderTraversalTwoStacks<T> {

//    private Node<T> head;
//
//    public PostOrderTraversalTwoStacks(T[] data) {
//        this.root = constructBST(data);
//    }
//
//    public Node constructBST(T[] dataArr){
//
//        //construct DataStructures.tree.bst.BST
//
//        Node one = new Node(dataArr[0]);
//        head = one;
//        for(int i = 1; i < dataArr.length; i++) {
//            Node newNode = new Node(dataArr[1]);
//            Node curr = head;
//            while(curr !=null) {
//                if (newNode.data.compareTo(curr.data) < 0) {
//                    curr = curr.left;
//                } else {
//                    curr = curr.right;
//                }
//            }
//            curr =  newNode;
//
//        }
//
//        return one;
//    }
//
//    public void postOrderTraversalTwoStacks(){
//
//        Stack<Node> aStack = new Stack<Node>();
//        Stack<Node> bStack = new Stack<Node>();
//
//        aStack.push(one);
//        while(!aStack.isEmpty()){
//            Node curr = aStack.pop();
//            if (curr.left!=null){
//                aStack.push(curr.left);
//            }
//            if (curr.right!=null){
//                aStack.push(curr.right);
//            }
//            bStack.push(curr);
//        }
//
//        while(!bStack.isEmpty()){
//            System.out.print(bStack.pop().data);
//        }
//    }
//
//    private class Node<T>{
//
//        T data;
//        Node left;
//        Node right;
//
//        public Node(T data){
//            this(data, null, null);
//        }
//
//        public Node(T data, Node left, Node right){
//            this.data = data;
//            this.left = left;
//            this.right = right;
//        }
//    }
}

