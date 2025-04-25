package implementations;

import utilities.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class PostOrderIterator<E> implements Iterator<E> {
	private Stack<BSTreeNode<E>> stack = new Stack<>();
    private BSTreeNode<E> lastVisited = null;

    public PostOrderIterator(BSTreeNode<E> root) {
        if (root != null) stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        while (!stack.isEmpty()) {
            BSTreeNode<E> node = stack.peek();

            //move down the left side of the tree
            if (node.getLeft() != null && lastVisited != node.getLeft() && lastVisited != node.getRight()) {
                stack.push(node.getLeft());
            }
            //move down the right side
            else if (node.getRight() != null && lastVisited != node.getRight()) {
                stack.push(node.getRight());
            } else {
                stack.pop();
                lastVisited = node;
                E result = node.getElement();
        		if (result != null) {
                	return result; 
                } else {
                	throw new NoSuchElementException("No such element to view");
                }
            }
        }
        //this case shouldn't be reached
        return null;  
    }
}
