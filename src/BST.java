import java.util.Iterator;
import java.util.Stack;

/***********************************************************************************************************************
 * @file: BST.java
 * @description: This program implements a BST class to manage the structure of a BST.
 * @author: Olivia Sturges
 * @date: September 12, 2024
 **********************************************************************************************************************/

public class BST<E extends Comparable<E>> {
    private Node<E> root; // Root of the BST
    private int nodecount; // Number of nodes in the BST

    // Implement the constructor
    BST() {
        root = null;
        nodecount = 0;
    }


    // Implement the clear method
    // Reinitialize the tree
    public void clear() {
        root = null;
        nodecount = 0;
    }


    // Implement the size method
    // returns the number of nodes in the tree
    public int size() {
        return nodecount;
    }

    // Implement the insert method
    // Inserts a comparable element into the tree
    // e is the element to insert
    public void insert(E e) {
        root = insertHelp(root, e);
        nodecount++;
    }

    // Private method that is used to help insert a node into the tree
    // Returns the inserted node
    private Node<E> insertHelp(Node<E> rt, E e) {
        if (rt == null) {
            return new Node<E>(e);
        }
        if (rt.getElement().compareTo(e) >= 0) {
            rt.setLeft(insertHelp(rt.getLeft(), e));
        } else {
            rt.setRight(insertHelp(rt.getRight(), e));
        }
        return rt;
    }


    // Implement the remove method
    // Removes an element from the tree
    // key is the value to be removed
    // returns the removed element, null if the key is not in the tree
    public E remove(E key) {
        E temp = findHelp(root, key); // First find the value
        if (temp != null) {
            root = removeHelp(root, key); // Now remove the node
            nodecount--;
        }
        return temp;
    }

    // Private method that is used to help find an element in the tree
    // Returns the desired element if found, returns null if element is not in tree
    private E findHelp(Node<E> rt, E key) {
        if (rt == null) {
            return null;
        }
        if (rt.getElement().compareTo(key) > 0) {
            return findHelp(rt.getLeft(), key);
        } else if (rt.getElement().compareTo(key) == 0) {
            return rt.getElement();
        } else {
            return findHelp(rt.getRight(), key);
        }
    }

    // Private method that is used to help remove a node from the tree
    // Returns null if the desired node is not found, else returns the removed node
    private Node<E> removeHelp(Node<E> rt, E key) {
        if (rt == null) {
            return null;
        }
        if (rt.getElement().compareTo(key) > 0) {
            rt.setLeft(removeHelp(rt.getLeft(), key));
        } else if (rt.getElement().compareTo(key) < 0) {
            rt.setRight(removeHelp(rt.getRight(), key));
        } else { // Found the node
            // Has one child
            if (rt.getLeft() == null) {
                return rt.getRight();
            } else if (rt.getRight() == null) {
                return rt.getLeft();
            }
            // Has two children
            else {
                Node<E> temp = getMax(rt.getLeft());
                rt.setElement(temp.getElement());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }
        return rt;
    }

    // Private method that is used to get the node with the largest valued element in a subtree
    // Returns the root if the subtree is empty, else returns node with the largest valued element
    private Node<E> getMax(Node<E> rt) {
        if (rt.getRight() == null) {
            return rt;
        }
        return getMax(rt.getRight());
    }

    // Private method that is used to delete the node with the largest valued element in a subtree
    // Returns the deleted node
    private Node<E> deleteMax(Node<E> rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
    }

    // Implement the search method
    // Searches for a desired element in the tree
    // key is the value of the element to be found
    // Returns the found element, null if the key is not in the tree
    public E search(E key) {
        return findHelp(root, key);
    }

    // Implement the iterator method
    // Method that calls the iterator class
    public Iterator iterator(){
        return new BSTIterator();
    }

    // Implement the BSTIterator class
    private class BSTIterator implements Iterator<E> {
        private Stack<Node<E>> stack; // stack used to iterate through tree
        private Node<E> current; // current node

        // Constructor that does inorder iteration for BST
        public BSTIterator() {
            if (root != null) {
                stack = new Stack<Node<E>>();
                goLeftFrom(root);
            }
        }
        @Override
        // Method to determine whether the stack is empty
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Method that goes to next node of stack in BST inorder iteration
        @Override
        public E next() {
            if (!stack.isEmpty()) {
                current = stack.peek();
                stack.pop();

                if (current.getRight() != null) {
                    goLeftFrom(current.getRight());
                }
            }
            return (current.getElement());
        }

        // adds nodes in the left subtree to the stack
        private void goLeftFrom(Node<E> t) {
            while (t != null) {
                stack.push(t);
                t = t.getLeft();
            }
        }

    }

}