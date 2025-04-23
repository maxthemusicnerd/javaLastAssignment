package implementations;

public class BSTreeNode<E>
{	
	private BSTreeNode<E> parent;
	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	private E value;
	
	//constructor 
	public BSTreeNode(E value) {
	    this.value = value;
	}
	
	
	
	//getters
	public E getValue() {
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
