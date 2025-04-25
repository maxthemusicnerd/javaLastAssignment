package implementations;

import utilities.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;


public class PreOrderIterator<E> implements Iterator<E> {
	private Stack<BSTreeNode<E>> stack = new Stack<>();
    
    public PreOrderIterator(BSTreeNode<E> root) {
        if (root != null) {
            stack.push(root); 
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        BSTreeNode<E> node = stack.pop();
        E result = node.getElement();
        
        if (node.getRight() != null) stack.push(node.getRight());
        if (node.getLeft() != null) stack.push(node.getLeft());
        
        if (result != null) {
        	return result; 
        } else {
        	throw new NoSuchElementException("No such element to view");
        }
        
    }

}
