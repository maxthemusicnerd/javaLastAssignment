package implementations;

import java.util.NoSuchElementException;

import java.util.Stack;

import utilities.Iterator;

public class InOrderIterator<E> implements Iterator<E> {

	private Stack<BSTreeNode<E>> stack = new Stack<>();
    
    public InOrderIterator(BSTreeNode<E> root) {
        pushLeft(root);  
    }


    private void pushLeft(BSTreeNode<E> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();  
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
        
    
        pushLeft(node.getRight());
        
        
        if (result != null) {
        	return result; 
        } else {
        	throw new NoSuchElementException("No such element to view");
        }
        
    }

}
