package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT
{

	@Override
	public BSTreeNode getRoot() throws NullPointerException {
		
		return null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
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
	public boolean add(Comparable newEntry) throws NullPointerException {
		// TODO Auto-generated method stub
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
