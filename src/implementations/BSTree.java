package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>
{

	private BSTreeNode<E> root;
	private int size = 0;
	
	@Override
	public BSTreeNode getRoot() throws NullPointerException {
		return root;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (root != null) {
			return true;
		}
		
		return false;
	}	

	@Override
	public void clear() {
		root = null;
		
	}

	@Override
	public boolean contains(Comparable entry) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BSTreeNode search(Comparable entry) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		
		if (root == null) {
	        root = new BSTreeNode<>(newEntry);
	        size++;
	        return true;
	    }
		
		BSTreeNode<E> currentNode = root;
		
		boolean isAdded = false;
		
		while (isAdded == false) {
			int cmp = newEntry.compareTo(currentNode.getValue());
			if (cmp < 0) {
				BSTreeNode<E> left = currentNode.getLeft();
				if (left == null) {
					BSTreeNode<E> newNode = new BSTreeNode<E>(newEntry);
					currentNode.setLeft(newNode);
					size++
					return true;
				} else {
					currentNode = left;
				}
			} else if (cmp > 0) {
				BSTreeNode<E> right = currentNode.getRight();
				if (right == null) {
					BSTreeNode<E> newNode = new BSTreeNode<E>(newEntry);
					currentNode.setRight(newNode);
					size++;
					return true;
				} else {
					currentNode = right;
				}
			} else {
				//if the value is identical then we cannot add 
				return false;
			}
		}
		
		return false;
	}

	@Override
	public BSTreeNode removeMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSTreeNode removeMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator inorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator preorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator postorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
