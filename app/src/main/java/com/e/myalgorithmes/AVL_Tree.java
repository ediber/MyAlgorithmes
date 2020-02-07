package com.e.myalgorithmes;

public class AVL_Tree {
  //  private Node root;

    public Node insert(Node root, int num){
        Node node = new Node(num);
        if(root == null){
            root = node;
        } else {
            if(num < root.num){
                root.left = insert(root.left, num);
            }
            else if (num > root.num){
                root.right = insert(root.right, num);
            }


            int heightDelta = getHeightDelta(root);


            if(heightDelta > 1 && num < root.left.num){ // left left case
                root = rotateRight(root);
            } else if(heightDelta > 1 && num > root.left.num){ // left right case
                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            } else if(heightDelta < -1 && num < root.right.num){ // right left case
                root.right = rotateRight(root.right);
                root = rotateLeft(root);
            } else if(heightDelta < -1 && num > root.right.num){ // right right case
                root = rotateLeft(root);
            }

            root.height = calculateHeight(root);
        }



        return root;
    }

    private int calculateHeight(Node root) {
        int leftHeight;
        int rightHeight;
        if(root.left == null){
            leftHeight = 0;
        } else {
            leftHeight = root.left.height;
        }

        if(root.right == null){
            rightHeight = 0;
        } else {
            rightHeight = root.right.height;
        }
        return  1 + Math.max(leftHeight, rightHeight);
    }

    private int getHeightDelta(Node root) {
        int leftHeight;
        int rightHeight;
        if(root.left == null){
             leftHeight = 0;
        } else {
             leftHeight = root.left.height;
        }

        if(root.right == null){
             rightHeight = 0;
        } else {
             rightHeight = root.right.height;
        }
        return leftHeight - rightHeight;
    }

    private Node rotateLeft(Node root) {
        Node t1 = root.right.left;
        Node right = root.right;
        root.right.left = root;
        root.right = t1;

        // update heights
        right.height = calculateHeight(right);
        root.height = calculateHeight(root);

        return right;
    }

    private Node rotateRight(Node root) {
        Node left = root.left;
        Node t1 = root.left.right;
        root.left.right = root;
        root.left = t1;

        // update heights
        left.height = calculateHeight(left);
        root.height = calculateHeight(root);

        return left;
    }

    public String toString(Node root){
        String ans = "";
        if (root == null){
            return "";
        }
        ans =  "(" + toString(root.left) +")" + root.num + "("  + toString(root.right) + ")";

        return ans;
    }


    public class Node{
        public int num;
        public int height;
        public Node left;
        public Node right;

        public Node(int num) {
            this.num = num;
            this.height = 1;
        }


    }
}


