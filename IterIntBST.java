//October 9th
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class IterIntBST implements Iterable<Integer> {
	
	static class Node {
		int value = 0;
		int depth = 0;
		Node left = null;
		Node right = null;
		
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	
	public class BSTIterator implements Iterator<Integer>{
		Stack<Node> stack;
		IterIntBST bst;
		
		public BSTIterator(IterIntBST bst) {
			this.bst = bst;
			this.stack = new Stack<Node>();
			this.getLefts(bst.root);
		}

		@Override
		public boolean hasNext() {
			if (this.stack.isEmpty()) {
				return false;
			}
			return true;
		}

		@Override
		public Integer next() throws NoSuchElementException{			
			if (!this.hasNext() || stack.size() < 1) {
				throw new NoSuchElementException("No more elements");
			}
			Node node = stack.pop();
			getLefts(node.right);
			return node.value;
		}
		
		private void getLefts(Node node) {
			if (node != null) {
				stack.push(node);
				this.getLefts(node.left);

			}
		}
		
	}
	
	Node root = null;
	private int height = 0;
	
	
	public IterIntBST(Node root){
		this.root = root;
	}
	
	
	
	@Override
	public BSTIterator iterator() {
		return new BSTIterator(this);
	}
	
	public int height() {
		return this.height;
	}
	
	public void add(Node newNode) {
		Node currNode = this.root;
		boolean located = false;
		while (!located) {
			if (newNode.value > currNode.value){
				if (currNode.right == null) {
					located = true;
					currNode.right = newNode;
				}
				else {
					currNode = currNode.right;
				}
			}
			else {
				if (currNode.left == null) {
					located = true;
					currNode.left = newNode;
				}
				else {
					currNode = currNode.left;
				}
			}
		}
		newNode.depth = currNode.depth + 1;
		if (newNode.depth > this.height){
			this.height = newNode.depth;
		}
		
	}
	 
	

	
	public static void main(String[] args) {
		
		IterIntBST.Node root = new IterIntBST.Node(5);
		IterIntBST bst = new IterIntBST(root);
		bst.add(new IterIntBST.Node(3));
		bst.add(new IterIntBST.Node(2));
		bst.add(new IterIntBST.Node(1));
		bst.add(new IterIntBST.Node(4));
		bst.add(new IterIntBST.Node(8));
		bst.add(new IterIntBST.Node(7));
		bst.add(new IterIntBST.Node(6));
		bst.add(new IterIntBST.Node(9));
		
		
		IterIntBST.BSTIterator iter = bst.iterator();
		
		if (iter.next() != 1) {
			throw new RuntimeException("next() Test Failed");
		}
		
		if (iter.next() != 2) {
			throw new RuntimeException("next() Test Failed");
		}
		
		if (iter.next() != 3) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 4) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 5) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 6) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 7) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 8) {
			throw new RuntimeException("next() Test Failed");
		}
		if (iter.next() != 9) {
			throw new RuntimeException("next() Test Failed");
		}
		
		
		try {
			iter.next();
			throw new RuntimeException("next() Test Failed");
		}
		catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				//pass
		}
			else {
				throw e;
			}
		
		
	}
		
		/*IterIntBST.Node root = new IterIntBST.Node(5);
		IterIntBST bst = new IterIntBST(root);
		bst.add(new IterIntBST.Node(3));
		bst.add(new IterIntBST.Node(2));
		bst.add(new IterIntBST.Node(1));
		bst.add(new IterIntBST.Node(4));
		bst.add(new IterIntBST.Node(8));
		bst.add(new IterIntBST.Node(7));
		bst.add(new IterIntBST.Node(6));
		bst.add(new IterIntBST.Node(9));
		
		
		IterIntBST.BSTIterator iter = bst.iterator();
		Stack<IterIntBST.Node> stack = iter.stack;
		
		if (stack.pop().value != 1) {
			throw new RuntimeException("getLefts() Test Failed");
		}
		if (stack.pop().value != 2) {
			throw new RuntimeException("getLefts() Test Failed");
		}
		if (stack.pop().value != 3) {
			throw new RuntimeException("getLefts() Test Failed");
		}
		if (stack.pop() != root) {
			throw new RuntimeException("getLefts() Test Failed");
		}
		
		/*IterIntBST bst = new IterIntBST(new Node(1));
		bst.add(new Node(2));
		bst.add(new Node(3));
		bst.add(new Node (4));
		
		Iterator<Integer> iter = bst.iterator();
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		System.out.println(iter.next());
		
		IterIntBST bst2 = new IterIntBST(new Node(4));
		bst2.add(new Node (3));
		bst2.add(new Node (2));
		bst2.add(new Node (1));
		
		
		Iterator<Integer> iter2 = bst2.iterator();
		System.out.println(iter2.next());
		System.out.println(iter2.next());
		System.out.println(iter2.next());
		System.out.println(iter2.next());*/
		
	}
	
	
	
}
