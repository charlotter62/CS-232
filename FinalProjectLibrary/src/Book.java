
public class Book implements Comparable<Book> {
	private String title;
	private String author;
	private String genre;
	private String rating; 
	
	//Constructor
	Book(){}

	Book(String[] info){
		title = info[0];
		author = info[1];
		genre = info[2];
		rating = info[3];
	}
	
	//Setters
	public void setTitle(String t) {title = t;}
	
	public void setAuthor(String a) {author = a;}
	
	public void setGenre(String g) {genre = g;}
	
	public void setRating(String r) {rating = r;}
	
	//Getters
	public String getTitle() {return(title);}
	
	public String getAuthor() {return author;}
	
	public String getGenre() {return genre;}
	
	public String getRating() {return rating;}
	
	
	//toString
	public String toString() {
		return(this.getTitle() + " by " + this.getAuthor() + " is a " + this.getGenre() + " book, with a " + this.getRating() + " rating on goodreads.");
	}
	
	
	@Override
	public int compareTo(Book o) { //If returns 1, the first book comes 1st; returns -1, first book comes 2nd; 0, they are same book
		// TODO Auto-generated method stub
		if(o.getAuthor().compareTo(this.getAuthor()) > 0) {
			return(1);
		}
		else{
			if(o.getAuthor().compareTo(this.getAuthor()) < 0) {
				return(-1);
			}
			else {//meaning they have the same author
				if(o.getTitle().compareTo(this.getTitle()) > 0) {
					return(1);
				}
				else {
					if(o.getTitle().compareTo(this.getTitle()) < 0) {
						return(-1);
					}
					else{
						return(0);
					}
				}
			}
			
		}

	}

}
