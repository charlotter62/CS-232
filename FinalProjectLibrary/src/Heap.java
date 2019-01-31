
public class Heap<E extends Comparable<E>> {
	HeapNode<E> root;
	HeapNode<E> lastAdded;
	
	Heap(){
		root= null;
		lastAdded = null;
	}
	
	Heap(E e, int p){
		root = new HeapNode<E>(e,p);
		lastAdded = root;
	}
	
	//Getters & Setters
	public HeapNode<E>getRoot(){return root;}
	public HeapNode<E>getLast(){return lastAdded;}
	public void setRoot(E e, int p) {root = new HeapNode<E>(e, p);}
	public void setLast(HeapNode<E> hn) {lastAdded= hn;}
	
	//AddElement
	public void addElement(E e, int p) { 
		HeapNode<E> elem = new HeapNode<E>(e, p);// creates the heapNode to be added 
		addElement(elem, lastAdded);
	}
	
	//Add Element recursive
	public void addElement(HeapNode<E> elem, HeapNode<E> point) {
		if (lastAdded== root) { //if we're adding to the root 
			point.setLeftChild(elem);
			elem.setParent(point);
			lastAdded = elem;
		}
		
		else if(lastAdded == rightMostChild(root)) { //if we're adding to a new layer
			HeapNode<E> rmc = leftMostChild(root);
			rmc.setLeftChild(elem);
			elem.setParent(rmc);
			lastAdded = elem;
		}
		
		else if(point.getParent().getLeftChild() == point) { //if left branch
			if(point.getParent().getRightChild()==null) { //if no right sibling, elem is added as right sibling
				point.getParent().setRightChild(elem);
				elem.setParent(point.getParent());
				lastAdded = elem;
			}
			else {
				HeapNode<E> temp = point.getParent().getRightChild(); //goes to right branch (sibling)
				HeapNode<E> lmc = leftMostChild(temp); //go to left most nodes
				lmc.setLeftChild(elem); //set the left most spot to be the next element
				elem.setParent(lmc);
				lastAdded = elem;
			}
		}
		else
			addElement(elem, point.getParent()); //finds the next parent to check if it is a left subtree
		
		bubbleUp(lastAdded, lastAdded.getParent());
	}
	
	
	
	
	//leftMostChild()	
	public HeapNode<E> leftMostChild(HeapNode<E> l){ //finds the left most child from a given node
		HeapNode<E> lm = null;
		while (lm ==null) {
			if(l.getLeftChild() == null) {
				lm= l;
			}
			else {
				l = l.getLeftChild();
			}
		}
		return(lm);
	}
	
	
	//rightMostChild()
	public HeapNode<E> rightMostChild(HeapNode<E> r){ //finds the right most child from a given node
		HeapNode<E> rm = null;
		while (rm ==null) {
			if(r.getRightChild() == null) {
				rm= r;
			}
			else {
				r = r.getRightChild();
			}
		}
		return(rm);
	}
		
	
	//"Bubble up the tree" when we add
	public void bubbleUp(HeapNode<E> child, HeapNode<E> parent) {
		if(child.compareTo(parent) < 0) { //if child is higher priority, swap
			
			//Saving all the values for parent and child
			E pEl = parent.getElement();
			int pP = parent.getPriority();
			int pAO = parent.getArrivalOrder();
			E cEl = child.getElement();
			int cP = child.getPriority();
			int cAO = child.getArrivalOrder();
			
			//Swapping all values of parent and child
			parent.setElement(cEl); 
			parent.setPriority(cP);
			parent.setArrivalOrder(cAO);
			child.setElement(pEl);
			child.setPriority(pP);
			child.setArrivalOrder(pAO);
			
			//Prepare to repeat with next parent
			child = parent;
			parent = parent.getParent();
			
			//Repeat with next parent
			if(parent != null) {
				bubbleUp(child, parent);
			}
		}
	}
	
	//Bubble down the tree, when we remove (check two children, swap with the smaller of the 2 (if it is larger than both)
	public void bubbleDown(HeapNode<E> n) {
		HeapNode<E> r = n.getRightChild();
		HeapNode<E> l =n.getLeftChild();
		
		if(r!=null) {
			if(n.compareTo(r) > 0) {  //right is higher priority than n 
				if(l == null) {
					swap(n,r);
					bubbleDown(r);
				}
				else if(l.compareTo(r) > 0) {
					swap(n,r);
					bubbleDown(r);
				}
            else{
               swap(n,l);
               bubbleDown(l);
            }
			}	
			else if(l!=null) {
				if(n.compareTo(l) > 0) {
					swap(n,l);
					bubbleDown(l);
				}
			}		
		}
		else if(l!= null) {
			if(n.compareTo(l) > 0) {
				swap(n,l);
				bubbleDown(l);
			}
		}
			
	}//bubbledown

		

	//removeMin, and replace it with the last added
	public HeapNode<E> removeMin(){
		HeapNode<E> min = new HeapNode<E>(root.getElement(), root.getPriority());
		if (lastAdded == root) {
			lastAdded =null;
         root= null;
		}
		else {
         HeapNode<E> secondLastAdded = secondLastAdded(lastAdded);
			//put last added into the root 
			root.setElement(lastAdded.getElement());
			root.setArrivalOrder(lastAdded.getArrivalOrder());
			root.setPriority(lastAdded.getPriority());
			
			//get rid of last added at the end
			if(lastAdded.getParent().getLeftChild()==lastAdded) {
				lastAdded.getParent().setLeftChild(null);
			}
			else
				lastAdded.getParent().setRightChild(null);
			
			//what is the new last added? 
			lastAdded = secondLastAdded;
         bubbleDown(root);
		}
		return(min);
	}
	
	//Second last Added
	public HeapNode<E> secondLastAdded(HeapNode<E> la){
		HeapNode<E> sla = null;
      if(la == root){
         sla = null;
      }
		else if(la == leftMostChild(root)) { //if it was the first of a new row, we go back to last of last row
			sla = rightMostChild(root);
		}
		else 
         while (sla == null){
         
            if(la.getParent().getRightChild()== la) { //if was a right branch
               sla = rightMostChild(la.getParent().getLeftChild());	//find right most child of left branch
			   }
            else{//if it was a left branch, go up a level
               la = la.getParent();
            }
         } 
		return(sla);
	}
   
	
	//swap values function
	public void swap(HeapNode<E> One, HeapNode<E> Two){
		int OneAO = One.getArrivalOrder();
		int OneP = One.getPriority();
		E OneElem = One.getElement();
		int TwoAO = Two.getArrivalOrder();
		int TwoP = Two.getPriority();
		E TwoElem = Two.getElement();
		
		One.setArrivalOrder(TwoAO);
		One.setPriority(TwoP);
		One.setElement(TwoElem);
		Two.setArrivalOrder(OneAO);
		Two.setPriority(OneP);
		Two.setElement(OneElem);	
	}
	
	
	//toString that prints out the heap
	public String toString() {
		String heapstring= "";
		while(root!= null) {
			heapstring = heapstring + "\n" + removeMin();
		}
		return(heapstring);
	}
	
	
}
//Each node is min of it's children, so the root is removed. 