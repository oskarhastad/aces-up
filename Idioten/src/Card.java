public class Card {

	public enum Suit {
		SPADES,
		HEARTS,
		CLUBS,
		DIAMONDS
	}

	int value;
	Suit suit;

	Card(int v, Suit s) {
		value = v;
		suit = s;
	}

	void printCard() {
		System.out.println(value + " of " + suit.toString());
	}
}
