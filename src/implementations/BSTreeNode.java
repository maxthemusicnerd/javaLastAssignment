package implementations;

public class BSTreeNode<E>
{	
	private BSTreeNode<E> parent;
	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	private E value;
	
	public BSTreeNode(E value) {
	    this.value = value;
	}
	
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
}
