
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.*;


public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
    
	//1- Scan in books to the Library
	    Scanner sc = new Scanner(new File("books.csv"));
	    //we can discard the first line, since it just describes the contents of each column
	    sc.nextLine();
	      
	    Library lib = new Library(); //creating the library
	    while (sc.hasNextLine()){
	      //split each line on commas
	      String[] bookInfo = sc.nextLine().split(",");
	       
	      //create a Book from the string array--> hint: make a constructor that takes in an array of strings...
	      Book b = new Book(bookInfo);
	      
	      //add the Book to the Library
	      lib.AddBook(b);
          }
	    
	    sc.close();
	    
	 
	    System.out.println("\n...sorting your library by author & title...");
      	lib.bubbleSort();
      	System.out.println("Here is your library:");
      	System.out.println(lib);
      	
      	//removing the book (by finding it based on title, author)
      	System.out.println("\n...removing On Writing from your library...");
      	lib.removeBook("On Writing", "Stephen King");
      	System.out.println(lib + "\n^On writing is not there");
         System.out.println(lib.getHashBooks());
      	
      	//adding the book back in manually
      	System.out.println("\n...re-adding On Writing to your library...\n");
      	lib.AddBook(new Book(new String[]{"On Writing", "Stephen King", "Nonfiction", "4.32"}));
      	System.out.println("Your Library with On Writing added again:\n" + lib);
      	
      	//2- Testing the Hashtable 
      	System.out.println("\n...Printing a HashTable of your books, stored as lists by genre...\n" + lib.getHashBooks());
      	System.out.println("...individual genre lists...\n");
      	System.out.println("Fantasy Books:" + lib.getHashBooks().get("Fantasy"));
      	System.out.println("Science Fiction Books:" + lib.getHashBooks().get("Science Fiction"));
      	System.out.println("Manga Books:" + lib.getHashBooks().get("Manga"));
      	System.out.println("Science Books:" + lib.getHashBooks().get("Science"));
      	System.out.println("Nonfiction Books:" + lib.getHashBooks().get("Nonfiction"));
      	
      	//3- Testing the Heap
      	System.out.println("\n...Printing a heap of your books in the order you want to read them...");
      	System.out.println(lib.getHeapBooks());
      	
        
	}//main
}//driver




