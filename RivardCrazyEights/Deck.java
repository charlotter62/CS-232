import java.util.Random;

public class Deck extends ArrayStack <Card>{
	
	//The constructor
	Deck(){
		stack = new Card[52]; //An Array stack of 52 Card objects, our deck 
	}
	
	public void fillDeck() {
		for (int i = 0; i<52; i++) {
			String suit = "";
			int divided = i/13;
			switch(divided) {
				case(0):
					suit = "Hearts";
					break;
				case(1):
					suit = "Diamonds";
					break;
				case(2):
					suit = "Spades";
					break;
				case(3):
					suit = "Clubs";
					break;
			}
			int value = i%13 + 1; //remainder tells us the value
			Card c = new Card(suit, value);
			this.push(c);
		}
			
	}
	
	//Shuffle Function
	public void shuffleDeck() {
		Random rgen = new Random();  // Random number generator			
		 
		for (int i=0; i<size(); i++) {
		    int randomPosition = rgen.nextInt(size()); 
		    Card temp = stack[i]; //gets the card at position i 
		    stack[i] = stack[randomPosition]; //places another card, from a random position, at that location in the stack
		    stack[randomPosition] = temp;//places the card at that position (i), at the random position
		    //We make sure each card is swapped with a card at a random location in the stack
		}
	}
	
	/*public void reShuffleDeck(ArrayStack<Card> discard) {
		int count = discard.size();
		for (int i=0; i<count; i++) {
			stack[i]=(discard.pop()); //adds each discard element to end of the list called stack, within deck
		}
		this.shuffleDeck();
		//System.out.println(this.stack);
	}*/
	
	public String toString() {
		String print ="";
		int count = this.size()-1;
		for (int i = count; i>-1; i--) { //why do these end points work?
			print += stack[i].toString() + "\n";
		}
		return(print);
	}
	

}
