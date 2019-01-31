
public class Player {
	private ArrayStack<Card> hand;
	private String name;
	
	Player(String n){
		name = n;
		hand = new ArrayStack<Card>();
	}
	
	public void Deal(Card c) {
		hand.push(c);
	}
	
	public ArrayStack<Card> Compare(Card c) throws NoMatchException{
		//Function for finding & returning matches that the player has for the top card of the discard pile
		ArrayStack<Card> matches = new ArrayStack<Card>();
		
		ArrayStack<Card> temp = new ArrayStack<Card>();//creating a copy of hand so we don't change it
		int count = hand.size();
		
		for(int i=0; i<count; i++) {
			Card checkCard = hand.pop();
			temp.push(checkCard);//all cards added to temp, to maintain the hand
			
			//Compare here 
			if (checkCard.equals(c)) {
				matches.push(checkCard);//add card to matches stack
			}
		}
		hand = temp;
		
		//Checking to see if no matches
		if (matches.size() == 0) {
			throw new NoMatchException("no-match");
		}
		
		//Returning matches
		return(matches);
	}
	
	
	public void playCard(Card c) {
		//Function for getting a specific card out of the players hand to "play" it
		ArrayStack<Card> temp = new ArrayStack<Card>();
		int count = hand.size();
		for(int i = 0; i<count; i++) {
			//pop off card to see it
			Card checkCard = hand.pop();
			if(checkCard.getSuit()==c.getSuit()) {
				if(checkCard.getValue()==c.getValue()) {
					//do nothing if its the correct card because you already popped it
				}
				else {
					temp.push(checkCard);//add back to hand
				}
			}
			//otherwise, add it back to the hand
			else{
				temp.push(checkCard);
			}
		}
		this.hand = temp; //set the hand to all the cards besides the one that was played
	}
	
	
	public Card takeTurn(Card topCard) {
		//Based on the top card of the discard pile, this function...
		//1) finds cards the player can play & picks one
		//2) "plays" the card removing it from the players hand & returning it to driver
		//**Does not include adding the card to the discard pile-- that is done in driver after function is called
		ArrayStack<Card> matches = this.Compare(topCard);
		Card discardCard = matches.pop();
		this.playCard(discardCard);
		return(discardCard);
	}
	
	
	public boolean gameOverStatus() {
		//Checks if any of the players have run out of cards, and thus the game is over
		if (hand.size()==0) {
			return(true);
		}
		else {
			return(false);
		}
	}
	
	public String toString() {
		//prints out the name and hand of the player
		return("Name: " + name + "\nHand: \n" + hand);
	}
	
	public String getName() {
		return(this.name);
	}
	
	public ArrayStack<Card> getHand(){
		return(this.hand);
	}
	
}
