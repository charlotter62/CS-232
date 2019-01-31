
public class heapdriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap<Integer> h = new Heap<Integer>(11,11);
        h.addElement(10,10);
        h.addElement(9,9);
        h.addElement(8,8);
        h.addElement(7,7);
        h.addElement(6,6);
        h.addElement(5,5);
        h.addElement(4,4);
        h.addElement(3,3);
        h.addElement(2,2);
        h.addElement(1,1);
        System.out.println(h.toString());
        /*System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());
        System.out.println(h.removeMin());*/
	}

}
