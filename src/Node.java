/*******************************************************************************************************************
 * @file: Node.java
 * @description: This program implements a Node class with a comparable interface to represent the nodes of the BST.
 * @author: Olivia Sturges
 * @date: September 11, 2024
 *********************************************************************************************************************/

public class Node<E extends Comparable<? super E >> {
    private E element; // element for this node
    private Node<E> left; // pointer to left child
    private Node<E> right; // pointer to right child

    // Implement the constructor
    // default
    public Node() {
        element = null;
        left = null;
        right = null;
    }
    // constructing element with no children
    public Node (E element) {
        this.element = element;
        left = null;
        right = null;
    }
    // constructing element with children
    public Node (E element, Node<E> left, Node<E> right ) {
        this.element = element;
        this.left = left;
        this.right = right;
    }


    // Implement the setElement method
    // sets value to the node
    public void setElement(E element) {
        this.element = element;
    }


    // Implement the setLeft method
    // sets value to left child
    public void setLeft(Node<E> left) {
        this.left = left;
    }


    // Implement the setRight method
    // sets value to right child
    public void setRight(Node<E> right) {
        this.right = right;
    }


    // Implement the getLeft method
    // get's left child
    public Node<E> getLeft() {
        return left;
    }


    // Implement the getRight method
    // get's right child
    public Node<E> getRight() {
        return right;
    }


    // Implement the getElement method
    // get's value of node
    public E getElement() {
        return element;
    }


    // Implement the isLeaf method
    // returns true or false depending on whether node is a leaf
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

}
