package implementations;

import java.io.Serializable;

public class BSTreeNode<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private BSTreeNode<E> parent;
	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	private E value;
	
	//constructor 
	public BSTreeNode(E value) {
	    this.value = value;
	}
	
	
	
	//getters
	public E getElement() {
		return value; 
	}
	
	public BSTreeNode<E> getLeft() {
		return left;
	}
	
	public BSTreeNode<E> getRight() {
		return right;
	}
	
	public BSTreeNode<E> getParent() {
		return parent;
	}
	
	
	//setters
	public void setLeft(BSTreeNode<E> left) {
	    this.left = left;
	}

	public void setRight(BSTreeNode<E> right) {
	    this.right = right;
	}

	public void setParent(BSTreeNode<E> parent) {
	    this.parent = parent;
	}
}
