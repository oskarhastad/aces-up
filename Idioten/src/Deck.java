import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	
	List<Card> deck = new LinkedList<Card>();	
	
	Deck() {
		addSuit(Card.Suit.HEARTS);
		addSuit(Card.Suit.DIAMONDS);
		addSuit(Card.Suit.SPADES);
		addSuit(Card.Suit.CLUBS);	
		
	}
	
	void addSuit(Card.Suit suit){
		for(int i=2; i<=14; i++ ) {
			Card x = new Card(i,suit);
			deck.add(x);
		}
		
	}

	
	Card drawRandomCard() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, deck.size());
		Card tempCard = deck.get(randomNum);
		deck.remove(randomNum);
		return tempCard;
	}
	
	void printDeck() {
		for(Card i:deck) {
			System.out.println(i.value + " of " + i.suit);
		}
		
	}
	 
	
	
}

