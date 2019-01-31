//import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;

public class Library {
	private Book[] books;
	private int size;
	private Hashtable<String, ArrayList<Book>> hashbooks;
	private Heap<Book> heapbooks;
	
	Library(){
		books = new Book[10];
		size = 0;
		hashbooks = new Hashtable<>();
		heapbooks = new Heap<Book>();
	}
	
	Library(Book[] list){
		books = list;
	}
	
   public int getSize() {return(size);}
   public Book[] getBooks(){return(books);}
   public Hashtable<String, ArrayList<Book>> getHashBooks(){return(hashbooks);}
   public Heap<Book> getHeapBooks(){return(heapbooks);}
	
   public String toString() {
		String print = ">>>>>";
		for(int i=0; i<size; i++) {
			print = print + "\n" + (books[i].toString());
		}
		print= print + "\n>>>>>";
		return(print);
	}
	
	public void AddBook(Book b) throws FullLibraryException {
		//adds the book to the books[] array
		books[size] = b;
		size++;
		
		//sort books[] array
		this.bubbleSort();
		
		//adds the book to the hashtable for genre
		this.AddBookHash(b);
		
		//adds the book to the heap... because the list of books is sorted by priority, we can add it using it's index
		Scanner sc = new Scanner(System.in);
		System.out.println("Adding " + b.getTitle() + " to your library!");
		System.out.println("How much would you like to read this book on a scale of 1-10? \n(1 being I CANT WAIT to 10 being ID RATHER SLEEP!)");
		int priority = sc.nextInt();
		if(heapbooks.getRoot() == null) {
			heapbooks = new Heap<Book>(b, priority);
		}
		else {
			heapbooks.addElement(b, priority);
		}
	}
	
	
	public void AddBookHash(Book b) { //add the item to the genre specific arraylist of the hashtable
		// - a. getting or making the genre's list
		String genre = b.getGenre();
		
		ArrayList<Book> thisGenreList;//initializing the genreList
		if (hashbooks.containsKey(genre)){
			thisGenreList = hashbooks.get(genre);}
		else {
			thisGenreList= new ArrayList<Book>();}
		
		// - b. adding book to the arraylist
		thisGenreList.add(b);
		
		//- c. sorting the arraylist
		if(thisGenreList.size()>1) {
			this.bubbleSortHash(genre);
		}
				
		//- d. adding updated arrayList with this book to hashtable 
		hashbooks.put(genre, thisGenreList);
	}
	
	/*from in class array list exercise
	private void expandCapacity() {
		books = Arrays.copyOf(books, size * 2);
	}*/
	
	
	public void bubbleSort() {
		int swaps = -1; //counting the number of swaps each time through the list, initialize as not 0 so it starts loop
		while(swaps != 0) { 
			swaps = 0;
			for (int i=0; i<(this.size-1); i++) {
				if (books[i].compareTo(books[i+1]) < 0) {
					Book temp = books[i+1];
					books[i+1] = books[i];
					books[i]= temp;	
					swaps ++;
				}
			}
		}
	}
	
	public void bubbleSortHash(String genre) {
		ArrayList<Book> genreList = hashbooks.get(genre);
		int swaps = -1; //counting the number of swaps each time through the list, initialize as not 0 so it starts loop
		while(swaps != 0) { 
			swaps = 0;
			for (int i=0; i<(genreList.size()-1); i++) {
				if (genreList.get(i).compareTo(genreList.get(i+1)) < 0) {
					Book temp = genreList.get(i + 1);
					genreList.set(i + 1, genreList.get(i));
					genreList.set(i, temp);
					swaps ++;
				}
			}
		}
	}

	
	public int findBook(String t, String a) {
		//create fake book w/ title & author attributes
		String[] q = new String[]{t, a, "",""};
		Book remove = new Book(q);
		int indexOfBook = -1;
		for(int i=0; i<this.size; i++) {
			if (this.books[i].compareTo(remove) == 0) {
				indexOfBook = i;
			}
		}
		return(indexOfBook);
	}
	
	public void removeBook(String t, String a) {
		//find Book in the books array
		int index = this.findBook(t, a);
      Book b = books[index];
		if(index >= 0) {
			for(int i= index; i < (this.size-1); i++) { //shift down the books
				if (i == size-1) { 
					books[i]= null;
				}
				books[i] = books[i+1];
			}
			size --;
		}
      removeBookHash(b);
	}
   
   public void removeBookHash(Book b){
      ArrayList<Book> genreList = hashbooks.get(b.getGenre());
      for (int i= 0; i < genreList.size(); i++){
         if(genreList.get(i)== b) {
            genreList.remove(i);
         }
      }
      hashbooks.put(b.getGenre(), genreList);
   }
	
	

}
