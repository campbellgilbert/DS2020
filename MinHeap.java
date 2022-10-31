//October 12th
import java.util.Arrays;

public class MinHeap {

    /**
     * A min heap node that stores an element and its priority.
     */
    class Node {
        HeapElement value;
        int priority;

        public Node(HeapElement value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    private Node[] array;
    private int size;

    /** 
     * Initialize the min heap.
     */
    public MinHeap() {
        // start with space for ten strings
        this.array = new Node[10];
        this.size = 0;
    }

    // UTILITY METHODS

    /**
     * Double the size of the Node array.
     */
    private void resize() {
        Node[] newArray = new Node[2 * this.array.length];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    /**
     * Calculate the index of the parent node.
     *
     * This method assumes the child index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the child node.
     */
    private int parent(int index) {
        return (int)Math.floor((index-1)/2); 
    }

    /**
     * Calculate the index of the left child.
     *
     * This method assumes the parent index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the parent node.
     * @return The index of the left child node.
     */
    private int leftChild(int index) {
        return (2*index) + 1; 
    }

    /**
     * Calculate the index of the right child.
     *
     * This method assumes the parent index is valid,
     * and does not need to perform error checking.
     *
     * @param index The index of the parent node.
     * @return The index of the right child node.
     */
    private int rightChild(int index) {
        return (2*index) + 2; 
    }

    /**
     * Swap the array contents of the given indices.
     *
     * @param index1 The first index.
     * @param index2 The second index.
     */
    private void swap(int index1, int index2) {
    	if (this.array[index1].priority > this.array[index2].priority) {
    		//if first element is bigger than the second
    		Node hold = this.array[index1];
    		this.array[index1] = this.array[index2];
    		this.array[index2] = hold;
    	} else {
    		Node hold = this.array[index2];
    		this.array[index2] = this.array[index1];
    		this.array[index1] = hold;
    	}
    }

    // ADD

    /**
     * Add an element to the min heap.
     *
     * @param element The element to add.
     * @param priority The priority of the element.
     */
    public void add(HeapElement element, int priority) {
        // resize if needed
    	if (this.size + 1 > this.array.length) {
    		resize();
    	}
        // put the new node in the array
    	int currIndex = 0;
    	while (this.array[currIndex] != null) {
    		currIndex++;
    	}
    	Node n = new Node(element, priority);
    	this.array[currIndex] = n;
    	int nIndex = currIndex;
        // percolate up (Challenge Q2)
    	while (currIndex >= 0 && n.priority < this.array[parent(nIndex)].priority) {
    		swap(nIndex, parent(nIndex));
    		nIndex = parent(nIndex);
    		currIndex--;
    	}
        // increment size
    	this.size++;
    }

    // POLL

    /**
     * Remove and return the element with the lowest priority number.
     *
     * @return The HeapElement with the lowest priority number.
     */
    public HeapElement poll() {
        // save the return value to a temporary variable
    	Node hold = this.array[0];
    	Node node = null;
    	int i = 0;
    	while (this.array[i] != null && i < this.size()) {
    		node = this.array[i]; 		
    		
    		i++;
    	}
        // replace the root with the last element
    	this.array[0] = null;
    	this.array[0] = node;
        // decrement the size
    	this.size--;
        // percolate down
        int currNodeIndex = 0; 
        while (true) {
            // break if there is no children (Challenge Q3a)
        	int leftIndex = leftChild(currNodeIndex);
        	int rightIndex = rightChild(currNodeIndex);
        	
        	if (leftIndex >= this.size() && rightIndex >= this.size()) {
        		break;
        	} else if (this.array[leftIndex] == null && this.array[rightIndex] == null) {
        		break;
        	}

            int childNodeIndex = -1; // initialize with dummy value

            // find the appropriate child to compare with (Challenge Q3b)

            if (this.array[leftIndex] == null || leftIndex > this.array.length) {
            	//if there is no left child
            	childNodeIndex = rightIndex;
 
            } else if (this.array[rightIndex] == null || rightIndex > this.array.length) {
            	//if there is no right child
            	childNodeIndex = leftIndex;

            } else { //if both children exist
            	
            	if (leftIndex == currNodeIndex || rightIndex == currNodeIndex) {
            		break;
            	}
            	
            	if (this.array[leftIndex].priority < node.priority 
            			&& this.array[leftIndex].priority < this.array[rightIndex].priority) { 
            		//if left child is higher priority
            		childNodeIndex = leftIndex;
            		
            	} else if (this.array[rightIndex].priority < node.priority
            			&& this.array[rightIndex].priority < this.array[leftIndex].priority) { 
            		//if right child is higher priority
            		childNodeIndex = rightIndex;
            		
            	} else { 
                    // break if both children are lower priority (Challenge Q3c)
            		break;
            	}
        		
            }
    		swap(childNodeIndex, currNodeIndex);
            currNodeIndex = childNodeIndex;
           
        }
        // return the stored value
        return hold.value; // FIXME
    }

    // OTHER HEAP METHODS

    /**
     * Get the size of the min heap.
     *
     * @return The size of the min heap.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return (but not remove) the element with the lowest priority number.
     *
     * @return The HeapElement with the lowest priority number.
     */
    public HeapElement peek() {
        return this.array[0].value;
    }

    // DEBUG METHODS

    /**
     * Print the array of the min heap, as is.
     */
    public void printArray() {
        if (this.size == 0) {
            System.out.println("{}");
        }
        String result = "{" + this.array[0].value;
        for (int i = 1; i < this.size; i++) {
            result += ", " + this.array[i].value;
        }
        System.out.println(result + "}");

    }

    /**
     * Print the min heap as a binary tree.
     */
    public void printTree() {
        this.printTree(0, "");
    }

    private void printTree(int index, String indent) {
        if (index >= this.size) {
            return;
        }
        this.printTree(this.rightChild(index), indent + "    ");
        System.out.println(indent + this.array[index].value);
        this.printTree(this.leftChild(index), indent + "    ");
    }

}
