
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

	List<Card> cards = new LinkedList<Card>();

	Deck() {
		addSuit(Card.Suit.HEARTS);
		addSuit(Card.Suit.DIAMONDS);
		addSuit(Card.Suit.SPADES);
		addSuit(Card.Suit.CLUBS);

	}

	void addSuit(Card.Suit suit) {
		for (int i = 2; i <= 14; i++) {
			Card x = new Card(i, suit);
			cards.add(x);
		}

	}

	Card drawRandomCard() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, cards.size());
		Card tempCard = cards.get(randomNum);
		cards.remove(randomNum);
		return tempCard;
	}

	void printDeck() {

		System.out.println("Deck starts:");
		for (Card i : cards) {
			System.out.println(i.value + " of " + i.suit);
		}
		System.out.println("Deck ends");

	}

	Deck cloneDeck() {

		Deck copy = new Deck();
		List<Card> temp = new LinkedList<Card>();
		for (Card c : cards) {
			temp.add(new Card(c.value, c.suit));
		}
		copy.cards = temp;
		return copy;

	}

}
