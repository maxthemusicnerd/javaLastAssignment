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
		return getHeightHelper(root);
	}
	
	private int getHeightHelper(BSTreeNode<E> node) {
	    if (node == null) {
	        return -1; 
	    }

	    int leftHeight = getHeightHelper(node.getLeft());
	    int rightHeight = getHeightHelper(node.getRight());

	    return 1 + Math.max(leftHeight, rightHeight);
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
	public boolean contains(E entry) throws NullPointerException {
		
		if (entry == null) throw new NullPointerException("Entry cannot be null.");
		
		BSTreeNode<E> searchResult = searcherHelper(root, entry);
		if (searchResult == entry) {
			return true;
		} 
		return false;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		
		if (entry == null) throw new NullPointerException("Entry cannot be null.");
		
		BSTreeNode<E> searchResult = searcherHelper(root, entry);
		return searchResult;
	}
	
	
	private BSTreeNode<E> searcherHelper(BSTreeNode<E> node, E entry) {
	    if (node == null) {
	        return null;
	    }

	    int cmp = entry.compareTo(node.getValue());
	    
	    if (cmp < 0) {
	        return searcherHelper(node.getLeft(), entry);
	    } else if (cmp > 0) {
	        return searcherHelper(node.getRight(), entry);
	    } else {
	        return node;
	    }
	}
	
	
	

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		
		if (newEntry == null) throw new NullPointerException("Entry cannot be null.");
		
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
					size++;
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
	public BSTreeNode<E> removeMin() {
		
		boolean minNotFound = true;
		
		BSTreeNode<E> currentNode = root;
		
		while (minNotFound) {
			BSTreeNode<E> left = currentNode.getLeft();
			if (left != null) {
				currentNode = left;
			} else {
				minNotFound = false;
			}
		}
		
		return currentNode;
	}

	@Override
	public BSTreeNode removeMax() {
		
		boolean maxNotFound = true;
		
		BSTreeNode<E> currentNode = root;
		
		while (maxNotFound) {
			BSTreeNode<E> right = currentNode.getRight();
			if (right != null) {
				currentNode = right;
			} else {
				maxNotFound = false;
			}
		}
		
		return currentNode;
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
