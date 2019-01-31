import java.util.Arrays;

public class ArrayStack <T> implements StackADT<T> {
	
	/*protected*/ T [] stack; //instance variable of our array stack
	/*private*/ int top;//keeping track of the last in, first out 
	
	//The constructor 
	@SuppressWarnings("unchecked")
	ArrayStack(){ 
		stack = (T[])(new Object [10]);
		top = 0;
	}
	
	//Copy Constructor
	public ArrayStack(ArrayStack<T> another){
		    this.stack = another.stack;
		    this.top = another.top;
	}
	
	
	public void push(T element) {
		if (size() == stack.length) {
			expandCapacity();
		}
		
		stack[top] = element;
		top++;
	}
	
	public T pop() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("stack");
		}
		
		top--;
		T result = stack[top];
		stack[top]=null;
		return result;
	}
	
	public T peek() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("stack");
		}
		
		return stack[top-1];
	}
	
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return (top==0);
	}
	
	private void expandCapacity(){
		stack = Arrays.copyOf(stack, stack.length * 2);
	}
	
	
	public String toString() {
		String s = "";
		for (int i = top-1; i>=0; i--) {
			s += stack[i] + "\n";
		}
		return(s);
	}

}
