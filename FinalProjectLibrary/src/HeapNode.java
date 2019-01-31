
public class HeapNode<E extends Comparable<E>> {
	private E element;
	private HeapNode<E> leftChild;
	private HeapNode<E> rightChild;
	private HeapNode<E> parent;
	private int priority;
	private int arrivalOrder;
	private static int nextOrder= 0;
	
	HeapNode(E e, int p){
		element = e;
		leftChild = null;
		rightChild = null;
		parent = null;
		priority = p;
		arrivalOrder = nextOrder;
		nextOrder++;
	}
	
	//Getters and Setters
	public HeapNode<E>getLeftChild(){return leftChild;}
	public HeapNode<E>getRightChild(){return rightChild;}
	public HeapNode<E>getParent(){return parent;}
	public E getElement() {return element;}
	public int getPriority() {return priority;}
	public int getArrivalOrder() {return arrivalOrder;}
	
	void setLeftChild(HeapNode<E> b) { leftChild = b;}
	void setRightChild(HeapNode<E> b) { rightChild = b;}
	void setParent(HeapNode<E> b) { parent = b;}
	void setElement(E e) {element = e;}
	void setPriority(int p) {priority = p;}
	void setArrivalOrder(int a) {arrivalOrder = a;}
	
	//toString method
	public String toString() {
		String s = element.toString();
		return s;
	}
	
	//compareTo (pg 339 in the textbook)
	public int compareTo(HeapNode<E> other) {
		int result;
		if(priority > other.getPriority()) {
			result = 1;
		}
		else if (priority < other.getPriority()) {
			result= -1;
		}
		else if (arrivalOrder > other.getArrivalOrder()) {
			result = 1;
		}
		else
			result = -1;
		return(result);
	}

}
