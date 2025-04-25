package implementations;

import utilities.BSTreeADT;

import utilities.Iterator;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>
{
	

	private BSTreeNode<E> root;
	private int size = 0;
	
	public BSTree() {
        this.root = null;
        this.size = 0;
    }
	
	public BSTree(E rootNode) {
		BSTreeNode<E> newNode = new BSTreeNode<E>(rootNode);
		this.size = 1;
		this.root = newNode;
	}
	
	@Override
	public BSTreeNode<E> getRoot() throws NullPointerException {
		if (root == null) {
			throw new NullPointerException("Tree root is null");
		}
		return root;
	}

	@Override
	public int getHeight() {
		return getHeightHelper(root);
	}
	
	private int getHeightHelper(BSTreeNode<E> node) {
	    if (node == null) {
	        return 0; 
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
		if (root == null) {
			return true;
		}
		
		return false;
	}	

	@Override
	public void clear() {
		size = 0;
		root = null;
		
	}

	@Override
	public boolean contains(E entry) throws NullPointerException {
		
		if (entry == null) throw new NullPointerException("Entry cannot be null.");
		
		return searcherHelper(root, entry) != null;
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

	    int cmp = entry.compareTo(node.getElement());
	    
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
			int cmp = newEntry.compareTo(currentNode.getElement());
			if (cmp < 0) {
				BSTreeNode<E> left = currentNode.getLeft();
				if (left == null) {
					BSTreeNode<E> newNode = new BSTreeNode<E>(newEntry);
					currentNode.setLeft(newNode);
					newNode.setParent(currentNode);
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
					newNode.setParent(currentNode);
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
	    if (root == null) {
	        return null;
	    }

	    BSTreeNode<E> current = root;

	    if (current.getLeft() == null) {
	        root = current.getRight();
	        if (root != null) {
	            root.setParent(null);
	        }
	        size--;
	        return current;
	    }

	    while (current.getLeft() != null) {
	        current = current.getLeft();
	    }

	    BSTreeNode<E> parent = current.getParent();
	    BSTreeNode<E> rightChild = current.getRight();

	    if (parent != null) {
	        parent.setLeft(rightChild);
	        if (rightChild != null) {
	            rightChild.setParent(parent);
	        }
	    }

	    size--;
	    return current;
	}

	
	//Like why do u wanna remove max tho, max is so cool 

	@Override
	public BSTreeNode<E> removeMax() {
	    if (root == null) {
	        return null;
	    }

	    BSTreeNode<E> currentNode = root;

	    while (currentNode.getRight() != null) {
	        currentNode = currentNode.getRight();
	    }

	    if (currentNode.getLeft() != null) {
	        if (currentNode.getParent() != null) {
	            currentNode.getParent().setRight(currentNode.getLeft());
	            currentNode.getLeft().setParent(currentNode.getParent());
	        } else {
	            root = currentNode.getLeft();
	            root.setParent(null);
	        }
	    } else {
	        if (currentNode.getParent() != null) {
	            currentNode.getParent().setRight(null);
	        } else {
	            root = null;
	        }
	    }

	    size--;
	    return currentNode;
	}
	
	
	//Hello!!! For all of these I'm assuming when you want to grab something, you want to grab the value, not the node itself!! If you actually want the NODE
	//and not the VALUE please change the code in the three classes PostOrderIterator, PreOrderIterator, and InOrderIterator. Thanks!
	
	// I'm just forseeing some bugs lol 
	@Override
	public Iterator<E> inorderIterator() {
		
		return new InOrderIterator<>(root);
	}

	@Override
	public Iterator<E> preorderIterator() {
		return new PreOrderIterator<>(root);
	}

	@Override
	public Iterator<E> postorderIterator() {
		return new PostOrderIterator<>(root);
	}
	
}
