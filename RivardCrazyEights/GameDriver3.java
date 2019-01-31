
public class GameDriver3 {
	
	public static void main(String[] args) {
		System.out.println("Charlotte Rivard\nAmber Stubbs\nData Structures\n10/26/18\n\nCrazy Eights Project\n ");
		System.out.println("NEW GAME");
		
		//Creating and Shuffling the Deck
		Deck deck = new Deck();
		deck.fillDeck();
		deck.shuffleDeck();
		System.out.println("\nDeck:\n" + deck); //check that cards are in the deck
		
		//Instantiating Players
		System.out.println("Players:");
		Player al = new Player("Alex");
		Player charl = new Player("Charlotte");
		
		//Dealing Cards to Players
		for (int i=0; i<10; i++) {
			if (i%2 == 0) {
				al.Deal(deck.pop());	
			}
			else {
				charl.Deal(deck.pop());
			}	
		}
		System.out.println(al + "\n" + charl); //check that cards were dealt
		
		//Setting Variables for the Loop
		Player player = al;//setting initial player to be alex
		int TurnNum = 0; //To keep track of turns between players
		Deck discard = new Deck();//to keep track of cards that have been played
		Card playCard; //The Card the player will play (put in discard)
		
		Card topCard = deck.pop(); //flip over first card, topCard is one being compared to
		discard.push(topCard);//add it to the discard stack
	
		
		//Playing the Game...
		while (player.gameOverStatus() == false) {
			
			System.out.println("Turn # " + (TurnNum+1));//print the turn # so we know its beginning of new turn
			System.out.println("TopCard: "+ topCard); //print what the top card is, that player will compare to
			
			//Seeing whose turn it is
			if(TurnNum%2 ==0) {
				player = al;
			}
			else {
				player = charl;
			}
			System.out.println("Player is...\n" + player); //printing who's turn it is
			
			//Player takes their turn & has cards that match
			try { 
				//make sure enough cards in deck
				if(deck.size() == 0) {
					deck= discard;//discard cards go into the deck
					deck.shuffleDeck();
					discard = new Deck();
					discard.push(topCard);
					System.out.println("--Discard pile becomes draw pile--");
				}
				else {
					playCard = player.takeTurn(topCard);//Selects & Removes card from hand & returns it
					discard.push(playCard); //moves card to discard pile
					System.out.println(playCard + " played\n ");//prints what was played
				}
			}
			
			//Player has no cards that matched 
			catch(NoMatchException n) {
				System.out.println("> No cards to play, draw a card");
				
				//Attempt to have this player draw a card, until they get one they can play
				playCard = new Card("Joker",2);//initializing it 
				
				while (playCard.getSuit()== "Joker") {
					
					//If deck is empty, add discards to deck and start again
					if (deck.size() == 0) {
						deck= discard;//discard cards go into the deck
						deck.shuffleDeck();
						discard = new Deck();
						discard.push(topCard);
						System.out.println("--Discard pile becomes draw pile--");
					}
					else {
						Card drawCard = deck.pop();//Card picked up from the pile 
						player.Deal(drawCard); // added to the player's hand regardless of if it matches topCard
						System.out.println("> " + drawCard + " was drawn");
						try { //check to see if card can be played/ matches topCard
							playCard = player.takeTurn(topCard);//Selects & Removes card from hand & returns it
							discard.push(playCard);//moves card to discard pile
						}
						catch(NoMatchException m) {
							System.out.println("> Can't play the " + drawCard + " draw again"); 
						}	
					} 
				}
				//Out of loop means we played a card, yay!
				System.out.println("> Nice you can play the " + playCard);
				System.out.println("> " + playCard + " played\n ");
			}	
			//After each turn, show the updates to player hand and discard pile, uncomment these to test code
			//System.out.println("\nAfter...\n" + player);
			//System.out.println("Discard:\n" + discard);
			
			//Adjust topCard as the first in discard pile
			topCard = discard.peek();
			
			//Increment turn 
			TurnNum ++;
		}
		
		
		//find winner
		Player winner;
		if (al.getHand().size() < charl.getHand().size()) { // this includes if they ran out of cards, or if deck ran out
			winner = al;
		}
		else {
			winner = charl;
		}
		
		System.out.println("This is the end of the game! " + winner.getName() + " ran out of cards first");
			
	}
}
